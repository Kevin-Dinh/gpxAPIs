package com.example.demo.dto;

import com.example.demo.domain.TrackPoint;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "trkseg")
public class TrackSegmentDto {
    private int id;
    private ArrayList<TrackPointDto> trkpt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<TrackPointDto> getTrkpt() {
        return trkpt;
    }

    public void setTrkpt(ArrayList<TrackPointDto> trkpt) {
        this.trkpt = trkpt;
    }
}
