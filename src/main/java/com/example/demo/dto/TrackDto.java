package com.example.demo.dto;

import com.example.demo.domain.TrackSegment;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "trk")
public class TrackDto {

    private Integer id;

    private Integer gpxId;

    private List<TrackSegmentDto> trkseg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGpxId() {
        return gpxId;
    }

    public void setGpxId(Integer gpxId) {
        this.gpxId = gpxId;
    }

    public List<TrackSegmentDto> getTrkseg() {
        return trkseg;
    }

    public void setTrkseg(List<TrackSegmentDto> trkseg) {
        this.trkseg = trkseg;
    }
}
