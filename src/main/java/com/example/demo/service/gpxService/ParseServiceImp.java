package com.example.demo.service.gpxService;

import com.example.demo.core.ParsingException;
import com.example.demo.domain.*;
import com.example.demo.service.TrackSegmentServiceImp;
import org.jdom.*;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ParseServiceImp implements ParseService{

    private Namespace ns;
    private SAXBuilder parser = new SAXBuilder();

    @Override
    public GPX parse(File file) throws ParsingException {
        try {
            Document doc = parser.build(file);
            return parse(doc);
        } catch (IOException e) {
            throw new ParsingException("Unable to open input", e);
        } catch (JDOMException e) {
            throw new ParsingException("Unable to parse input", e);
        }
    }

    @Override
    public GPX parse(URL url) throws ParsingException {
        try {
            Document doc = parser.build(url);
            return parse(doc);
        } catch (IOException e) {
            throw new ParsingException("Unable to open input", e);
        } catch (JDOMException e) {
            throw new ParsingException("Unable to parse input", e);
        }
    }

    private GPX parse(Document doc) {
        GPX gpx = new GPX();
        Element rootNode = doc.getRootElement();
        ns = rootNode.getNamespace();
        List<Element> tracks = rootNode.getChildren("trk", ns);

        List<Element> metadata = rootNode.getChildren("metadata", ns);

        gpx.setName(metadata.get(0).getChildText("name", ns));
        gpx.setDescription(metadata.get(0).getChildText("desc", ns));
        gpx.setAuthor(metadata.get(0).getChildText("author", ns));
        gpx.setLink(metadata.get(0).getChild("link", ns).getAttribute("href").getValue());
        //gpx.setTime(metadata.get(0).getChildText("time", ns));

        for (int i = 0; i < tracks.size(); i++) {
            gpx.addTrack(parseTrack(tracks.get(i)));
        }

        return gpx;
    }

    private Waypoint parseWayPoint() {
        Waypoint waypoint = new Waypoint();

        return waypoint;
    }
    private Track parseTrack(Element trackXML) {
        Track track = new Track();

        List<Element> segments = trackXML.getChildren("trkseg", ns);
        for (int i = 0; i < segments.size(); i++) {
            track.addSegment(parseTrackSegment(segments.get(i)));
        }

        return track;
    }

    private TrackSegmentServiceImp parseTrackSegment(Element segmentXML) {
        TrackSegmentServiceImp segment = new TrackSegmentServiceImp();

        List<Element> waypoints = segmentXML.getChildren("trkpt", ns);
        for (int i = 0; i < waypoints.size(); i++) {
            Element pointXML = waypoints.get(i);
            double latitude = 0.0;
            double longitude = 0.0;
            double elevation = 0.0;

            try {
                latitude = pointXML.getAttribute("lat").getDoubleValue();
                longitude = pointXML.getAttribute("lon").getDoubleValue();
            } catch (DataConversionException e) {
                continue;
            }

            if (pointXML.getChild("ele", ns) != null) {
                elevation = new Double(pointXML.getChildText("ele", ns));
            }

            Waypoint waypoint = new Waypoint();

            Coordinate coordinate = new Coordinate();
            coordinate.setLatitude(latitude);
            coordinate.setLongitude(longitude);

            waypoint.setCoordinate(coordinate);
            waypoint.setElevation(elevation);

            if (pointXML.getChild("time", ns) != null) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    Date time = dateFormat.parse(pointXML.getChildText("time", ns));
                    waypoint.setTime(time);
                } catch (ParseException e) {
                }
            }

            segment.addWaypoint(waypoint);
        }

        return segment;
    }
}
