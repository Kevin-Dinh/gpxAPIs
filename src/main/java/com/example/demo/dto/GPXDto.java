package com.example.demo.dto;

import com.example.demo.domain.Metadata;
import com.example.demo.domain.Track;
import com.example.demo.domain.Waypoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "gpx")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPXDto {
    private int id;
    @XmlAttribute
    private String uri;
    @XmlAttribute
    private String version;
    @XmlAttribute
    private String creator;

    private MetadataDto metadata;

    private ArrayList<WayPointDto> wpt;

    private ArrayList<TrackDto> trk;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public MetadataDto getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataDto metadata) {
        this.metadata = metadata;
    }

    public ArrayList<WayPointDto> getWpt() {
        return wpt;
    }

    public void setWpt(ArrayList<WayPointDto> wpt) {
        this.wpt = wpt;
    }

    public ArrayList<TrackDto> getTrk() {
        return trk;
    }

    public void setTrk(ArrayList<TrackDto> trk) {
        this.trk = trk;
    }
}
