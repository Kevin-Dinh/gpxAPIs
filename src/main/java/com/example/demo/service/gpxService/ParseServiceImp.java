package com.example.demo.service.gpxService;

import com.example.demo.dao.*;
import com.example.demo.domain.*;
import com.example.demo.dto.*;
import com.example.demo.dto.response.GPXResponseDto;
import com.example.demo.utils.DateHelper;
import com.example.demo.utils.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ParseServiceImp implements ParseService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private JAXBContext jaxbContext;

    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private MetadataRepository metadataRepository;
    @Autowired
    private WayPointRepository wayPointRepository;
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private TrackSegmentRepository trackSegmentRepository;
    @Autowired
    private GPXRepository gpxRepository;
    @Autowired
    private TrackPointRepository trackPointRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public GPXDto parseFile(File file) throws JAXBException, XMLStreamException, IOException {
        return null;
    }

    @Override
    public GPXDto parseFile(MultipartFile file) throws JAXBException, XMLStreamException, IOException {

        // Convert to file
        File f = FileHelper.moveAndStoreFile(file);
        // Ignore namespace
        XMLStreamReader newFile = getXmlStreamReader(f);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        GPXDto gpxDto = (GPXDto) jaxbUnmarshaller.unmarshal(newFile);


        User user = storeUser(gpxDto);
        Link link = storeLink(gpxDto);
        Metadata metadata = storeMetadata(gpxDto, link, user);

        GPX gpx = storeGpx(gpxDto, metadata);
        if (gpx.getId() != null) {
            gpxDto.setId(gpx.getId());
        }
        storeTrack(gpxDto, gpx);

        storeWayPoint(gpxDto, gpx);

        return gpxDto;
    }

    @Override
    public GPXResponseDto getDetails(int userId) {
        GPXResponseDto gpxResponseDto = new GPXResponseDto();
        try {
            // Find this user with id
            User user = userRepository.findById(userId).orElse(null);;

            if (user == null){
                gpxResponseDto.setStatusCode(HttpStatus.NOT_FOUND);
                gpxResponseDto.setMessage("Cannot find this user");
                return gpxResponseDto;
            }
            UserDto userDto = new UserDto();
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());

            // Get metadata
            Metadata metadata = metadataRepository.findByUserId(userId);

            if (metadata == null) {
                gpxResponseDto.setStatusCode(HttpStatus.NOT_FOUND);
                gpxResponseDto.setMessage("Cannot find metadata");
                return gpxResponseDto;
            }


            // Get link
            Link link = linkRepository.findById(metadata.getLinkId()).orElse(null);
            LinkDto linkDto = new LinkDto();
            if (link != null) {
                linkDto.setHref(link.getHref());
                linkDto.setText(link.getText());
            }

            // Put link and user into metadata
            MetadataDto metadataDto = new MetadataDto();
            metadataDto.setAuthor(userDto);
            metadataDto.setDesc(metadata.getDesc());
            metadataDto.setKeywords(metadata.getKeywords());
            metadataDto.setName(metadata.getName());
            metadataDto.setTime(DateHelper.convertTimestampToString(metadata.getTime()));
            metadataDto.setLink(linkDto);


            // Get Gpx
            GPX gpx = gpxRepository.findByMetadataId(metadata.getId());
            if (gpx == null) {
                gpxResponseDto.setStatusCode(HttpStatus.NOT_FOUND);
                gpxResponseDto.setMessage("Cannot find GPX file");
                return gpxResponseDto;
            }

            // Get way point
            ArrayList<Waypoint> waypoints = wayPointRepository.findByGpxId(gpx.getId());
            List<WayPointDto> wayPointDtoList = new ArrayList<>();
            if (waypoints.size() > 0) {
                wayPointDtoList = waypoints.stream().map(wpt -> {
                    WayPointDto wayPointDto = new WayPointDto();
                    wayPointDto.setLat(wpt.getLat());
                    wayPointDto.setLon(wpt.getLon());
                    wayPointDto.setName(wpt.getName());
                    wayPointDto.setSym(wpt.getSym());
                    return wayPointDto;
                }).collect(Collectors.toList());
            }

            // Put way point into Gpx
            GPXDto gpxDto = new GPXDto();
            gpxDto.setCreator(gpx.getCreator());
            gpxDto.setMetadata(metadataDto);
            gpxDto.setVersion(gpx.getVersion());
            gpxDto.setUri(gpx.getUri());
            gpxDto.setWpt((ArrayList<WayPointDto>) wayPointDtoList);
            // Get Track based on gpx_id
            List<Track> tracks = trackRepository.findByGpxId(gpx.getId());

            List<TrackSegment> trackSegments = new ArrayList<>();
            List<TrackDto> trackDtoList = tracks.stream().map(trk -> {
                TrackDto trackDto =  new TrackDto();
                trackDto.setId(trk.getId());
                trackDto.setGpxId(trk.getId());

                return trackDto;
            }).collect(Collectors.toList());

            // get Track Segment based on track_id
            List<TrackSegmentDto> trackSegmentDtos = new ArrayList<>();
            for (TrackDto trackDto : trackDtoList) {
                trackSegments = trackSegmentRepository.findByTrackId(trackDto.getId());

                trackSegmentDtos = trackSegments.stream().map(trk -> {
                    TrackSegmentDto trackSegmentDto = new TrackSegmentDto();
                    trackSegmentDto.setId(trk.getId());
                    return trackSegmentDto;
                }).collect(Collectors.toList());

                trackDto.setTrkseg(trackSegmentDtos);
            }

            // get Track point based on track_segment_id
            List<TrackPoint> trackPoints = new ArrayList<>();
            List<TrackPointDto> trackPointDtoList = new ArrayList<>();
            for (TrackSegmentDto trackSegmentDto : trackSegmentDtos) {
                trackPoints = trackPointRepository.findByTrackSegmentId(trackSegmentDto.getId());

                trackPointDtoList = trackPoints.stream().map(trkPt -> {
                    TrackPointDto trackPointDto = new TrackPointDto();
                    trackPointDto.setEle(trkPt.getEle());
                    trackPointDto.setLat(trkPt.getLat());
                    trackPointDto.setLon(trkPt.getLon());

                    trackPointDto.setTime(DateHelper.convertTimestampToString(trkPt.getTime()));
                    return trackPointDto;
                }).collect(Collectors.toList());

                trackSegmentDto.setTrkpt((ArrayList<TrackPointDto>) trackPointDtoList);
            }


            // put track into gpx
            gpxDto.setTrk((ArrayList<TrackDto>) trackDtoList);

            gpxResponseDto.setGpxDto(gpxDto);
            gpxResponseDto.setStatusCode(HttpStatus.OK);
        } catch (Exception e) {
            gpxResponseDto.setMessage("Error");
            gpxResponseDto.setStatusCode(HttpStatus.NOT_FOUND);
            logger.error(e.getMessage());
        }
        return gpxResponseDto;
    }

    private XMLStreamReader getXmlStreamReader(File file) throws XMLStreamException, JAXBException {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
        StreamSource source = new StreamSource(file);
        // Store this into new file
        XMLStreamReader newFile = xif.createXMLStreamReader(source);
        jaxbContext = JAXBContext.newInstance(GPXDto.class);
        return newFile;
    }

    private void storeWayPoint(GPXDto gpxDto, GPX gpx) {
        List<WayPointDto> waypointList = gpxDto.getWpt();
        List<Waypoint> waypoints = waypointList.stream().map(wpt -> {
            Waypoint wp = new Waypoint();
            wp.setLat(wpt.getLat());
            wp.setLon(wpt.getLon());
            wp.setName(wpt.getName());
            wp.setSym(wpt.getSym());
            return wp;
        }).collect(Collectors.toList());

        for (Waypoint wpt : waypoints) {
            wpt.setGpxId(gpxDto.getId());
            logger.info("Waypoint = {}", gpx);
            wayPointRepository.saveAndFlush(wpt);
        }
    }

    private Link storeLink(GPXDto gpxDto) {
        LinkDto linkDto = gpxDto.getMetadata().getLink();

        Link link = new Link();
        link.setHref(linkDto.getHref());
        link.setText(linkDto.getText());

        logger.info("Link = {}", link);
        linkRepository.saveAndFlush(link);
        return link;
    }

    private User storeUser(GPXDto gpxDto) {
        UserDto userDto = gpxDto.getMetadata().getAuthor();
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        logger.info("User = {}", user);
        userRepository.saveAndFlush(user);
        return user;
    }

    private Metadata storeMetadata(GPXDto gpxDto, Link link, User user) {
        MetadataDto metadataDto = gpxDto.getMetadata();
        Metadata metadata = new Metadata();

        if (link.getId() != null) {
            metadata.setLinkId(link.getId());
        }
        if(user.getId() != null) {
            metadata.setUserId(user.getId());
        }
        metadata.setDesc(metadataDto.getDesc());
        metadata.setName(metadataDto.getName());

        Timestamp timestamp = DateHelper.convertStringToTimeStamp(gpxDto.getMetadata().getTime());
        metadata.setTime(timestamp);

        logger.info("Metadata = {}", metadata);
        metadataRepository.saveAndFlush(metadata);
        return metadata;
    }

    private GPX storeGpx(GPXDto gpxDto, Metadata metadata) {
        GPX gpx = new GPX();
        gpx.setCreator(gpxDto.getCreator());
        if (metadata.getId() != null) {
            gpx.setMetadataId(metadata.getId());
        }

        gpx.setVersion(gpxDto.getVersion());

        logger.info("GPX = {}", gpx);
        gpxRepository.saveAndFlush(gpx);
        return gpx;
    }

    private void storeTrack(GPXDto gpxDto, GPX gpx) {
        // Store track
        List<TrackDto> trackDtoList = gpxDto.getTrk();
        List<Track> tracks = trackDtoList.stream().map(trk -> {
            Track track = new Track();
            track.setGpxId(gpx.getId());
            logger.info("Track = {}", gpx);
            trackRepository.saveAndFlush(track);

            track.setId(track.getId());

            List<TrackSegmentDto> trackSegmentDtoList = trk.getTrkseg();
            List<TrackSegment> trackSegment = trackSegmentDtoList.stream().map(trkSeg -> {
                TrackSegment trkS = new TrackSegment();
                if (track.getId() != null) {
                    trkS.setTrackId(track.getId());
                }

                logger.info("Track segment = {}", gpx);
                trackSegmentRepository.saveAndFlush(trkS);

                List<TrackPointDto> trackPointDtoList = trkSeg.getTrkpt();
                List<TrackPoint> trackPoints = trackPointDtoList.stream().map(trkPt -> {
                    TrackPoint trackPoint = new TrackPoint();
                    trackPoint.setLat(trkPt.getLat());
                    trackPoint.setLon(trkPt.getLon());
                    trackPoint.setEle(trkPt.getEle());

                    Timestamp timestamp = DateHelper.convertStringToTimeStamp(trkPt.getTime());
                    trackPoint.setTime(timestamp);
                    if (trkS.getId() != null) {
                        trackPoint.setTrackSegmentId(trkS.getId());
                    }

                    logger.info("Track point = {}", gpx);
                    trackPointRepository.saveAndFlush(trackPoint);
                    return trackPoint;
                }).collect(Collectors.toList());
                return trkS;
            }).collect(Collectors.toList());
            return track;
        }).collect(Collectors.toList());
    }
}
