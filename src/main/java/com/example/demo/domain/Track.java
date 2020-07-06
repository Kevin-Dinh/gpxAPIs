package com.example.demo.domain;


import com.example.demo.core.model.AbstractTimeGeneratedIdEntity;

import javax.persistence.*;

@Entity
@Table(name = "track")
public class Track  extends AbstractTimeGeneratedIdEntity {

    @Column(name = "gpx_id")
    private int gpxId;

    @Transient
    private TrackSegment trkseg;

    public int getGpxId() {
        return gpxId;
    }

    public void setGpxId(int gpxId) {
        this.gpxId = gpxId;
    }

    public TrackSegment getTrkseg() {
        return trkseg;
    }

    public void setTrkseg(TrackSegment trkseg) {
        this.trkseg = trkseg;
    }
}
