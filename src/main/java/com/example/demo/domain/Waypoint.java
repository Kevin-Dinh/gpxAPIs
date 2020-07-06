package com.example.demo.domain;

import com.example.demo.core.model.AbstractTimeGeneratedIdEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "way_point")
public class Waypoint extends AbstractTimeGeneratedIdEntity {

    @Column(name = "latitude")
    private BigDecimal lat;
    @Column(name = "longitude")
    private BigDecimal lon;
    private String name;
    private String sym;

    @Column(name = "gpx_id")
    private int gpxId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public Waypoint() {
        super();
    }

    public int getGpxId() {
        return gpxId;
    }

    public void setGpxId(int gpxId) {
        this.gpxId = gpxId;
    }
}
