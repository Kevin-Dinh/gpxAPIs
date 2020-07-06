package com.example.demo.dto;

import com.example.demo.utils.DateHelper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.sql.Timestamp;

@XmlRootElement(name = "trkpt")
@XmlAccessorType(XmlAccessType.FIELD)
public class TrackPointDto {
    @XmlAttribute
    private BigDecimal lat;
    @XmlAttribute
    private BigDecimal lon;
    private BigDecimal ele;
    private String time;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
