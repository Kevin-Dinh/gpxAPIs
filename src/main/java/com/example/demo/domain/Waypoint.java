package com.example.demo.domain;

import java.util.Date;

public class Waypoint {
    private Date time;
    private Coordinate coordinate;
    private String name = "";
    private double elevation = .0;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public double calculateDistanceTo(Waypoint otherPoint) {
        // According to http://en.wikipedia.org/wiki/Earth_radius#Mean_radii
        // earth has an equatorial radius of 6,378.137 kilometers.
        final int R = 6378137;

        if (otherPoint.getCoordinate() == null || getCoordinate() == null) {
            return 0.0;
        }

        return Math.acos(
                Math.sin(Math.toRadians(getCoordinate().getLatitude())) * Math.sin(Math.toRadians(otherPoint.getCoordinate().getLatitude())) +
                        Math.cos(Math.toRadians(getCoordinate().getLatitude())) * Math.cos(Math.toRadians(otherPoint.getCoordinate().getLatitude())) *
                                Math.cos(Math.toRadians(otherPoint.getCoordinate().getLongitude() - getCoordinate().getLongitude()))) * R;
    }
}
