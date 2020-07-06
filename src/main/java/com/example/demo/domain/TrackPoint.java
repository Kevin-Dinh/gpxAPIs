package com.example.demo.domain;

import com.example.demo.core.model.AbstractTimeGeneratedIdEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "track_point")
public class TrackPoint  extends AbstractTimeGeneratedIdEntity {

    @Column(name = "latitude")
    private BigDecimal lat;
    @Column(name = "longitude")
    private BigDecimal lon;
    @Column(name = "ele")
    private BigDecimal ele;
    @Column(name = "time")
    private Timestamp time;
    @Column(name = "track_segment_id")
    private int trackSegmentId;


    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getEle() {
        return ele;
    }

    public void setEle(BigDecimal ele) {
        this.ele = ele;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getTrackSegmentId() {
        return trackSegmentId;
    }

    public void setTrackSegmentId(int trackSegmentId) {
        this.trackSegmentId = trackSegmentId;
    }
}
