package com.example.demo.domain;

import com.example.demo.core.model.AbstractTimeGeneratedIdEntity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "gpx")
public class GPX extends AbstractTimeGeneratedIdEntity {

    private String uri;
    private String version;
    private String creator;
    @Column(name = "metadata_id")
    private int metadataId;

    @Transient
    private Metadata metadata;
    @Transient
    private ArrayList<Waypoint> wpt;
    @Transient
    private ArrayList<Track> trk;

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

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public ArrayList<Waypoint> getWpt() {
        return wpt;
    }

    public void setWpt(ArrayList<Waypoint> wpt) {
        this.wpt = wpt;
    }

    public ArrayList<Track> getTrk() {
        return trk;
    }

    public void setTrk(ArrayList<Track> trk) {
        this.trk = trk;
    }

    public int getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(int metadataId) {
        this.metadataId = metadataId;
    }

    public GPX() {
        super();
    }
}
