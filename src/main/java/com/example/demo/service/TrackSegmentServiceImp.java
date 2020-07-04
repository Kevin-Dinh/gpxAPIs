package com.example.demo.service;

import com.example.demo.domain.Waypoint;

import java.util.ArrayList;
import java.util.Date;

public class TrackSegmentServiceImp implements TrackSegmentService{

    private ArrayList<Waypoint> waypoints = new ArrayList<>();


    public void addWaypoint(Waypoint waypoint) {
        waypoints.add(waypoint);
    }
    public ArrayList<Waypoint> getWaypoints()
    {
        return waypoints;
    }
    @Override
    public double cumulativeAscent() {
        double ascent = 0.0;

        if (waypoints.size() <= 1) {
            return 0.0;
        }

        for (int i = 0; i < waypoints.size(); i++) {
            if (i > 0 && waypoints.get(i - 1).getElevation() < waypoints.get(i).getElevation()) {
                ascent += waypoints.get(i).getElevation() - waypoints.get(i - 1).getElevation();
            }
        }

        return ascent;
    }

    @Override
    public double length() {
        double length = 0.0;

        Waypoint currentWaypoint;
        Waypoint previousWaypoint;

        for (int z = 0; z < waypoints.size(); z++) {
            /* Only attempt to calculate the distance if we are not
             * on the first way point of the segment.
             */
            if (z > 0) {
                currentWaypoint = waypoints.get(z);
                previousWaypoint = waypoints.get(z - 1);

                length += currentWaypoint.calculateDistanceTo(previousWaypoint);
            }
        }

        return length;
    }

    @Override
    public double cumulativeDescent() {
        double descent = 0.0;

        if (waypoints.size() <= 1) {
            return 0.0;
        }

        for (int i = 0; i < waypoints.size(); i++) {
            if (i > 1 && waypoints.get(i).getElevation() < waypoints.get(i - 1).getElevation()) {
                descent += waypoints.get(i - 1).getElevation() - waypoints.get(i).getElevation();
            }
        }

        return descent;
    }


    @Override
    public Date startingTime() {
        Date result = null;

        for (int i = 0; i < waypoints.size(); i++) {
            Date time = waypoints.get(i).getTime();

            if (time != null) {
                if (result == null || time.before(result)) {
                    result = time;
                }
            }
        }

        return result;
    }

    @Override
    public Date endTime() {
        Date result = null;

        for (int i = 0; i < waypoints.size(); i++) {
            Date time = waypoints.get(i).getTime();

            if (time != null) {
                if (result == null || time.after(result)) {
                    result = time;
                }
            }
        }

        return result;
    }
}
