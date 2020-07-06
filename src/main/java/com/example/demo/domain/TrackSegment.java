package com.example.demo.domain;

import com.example.demo.core.model.AbstractTimeGeneratedIdEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@Entity
@Table(name = "track_segment")
public class TrackSegment extends AbstractTimeGeneratedIdEntity {

    @Column(name = "track_id")
    private int trackId;

    @Transient
    private ArrayList<TrackPoint> trkpt;

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public ArrayList<TrackPoint> getTrkpt() {
        return trkpt;
    }

    public void setTrkpt(ArrayList<TrackPoint> trkpt) {
        this.trkpt = trkpt;
    }
}
