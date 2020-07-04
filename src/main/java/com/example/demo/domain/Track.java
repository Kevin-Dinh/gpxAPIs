package com.example.demo.domain;

import com.example.demo.service.TrackSegmentServiceImp;

import java.util.ArrayList;
import java.util.Date;

public class Track {
    private ArrayList<TrackSegmentServiceImp> segments = new ArrayList<>();

    /**
     * Adds a new segment to the track.
     *
     * <p>This method is used for extending a track by another segment.
     * The segment is added at the tracks's end.</p>
     *
     * @param segment the segment to be added to the track
     */
    public void addSegment(TrackSegmentServiceImp segment) {
        segments.add(segment);
    }

    /**
     * Returns the segments of which the track consists.
     *
     * <p>This method returns a list of the segments that make up the
     * track. They are returned in the order that they were added,
     * i.e. the segment at which the track start is at position 0
     * while the segment where the track ends is at the last position
     * of the list.</p>
     *
     * @return a list of the track's segments
     */
    public ArrayList<TrackSegmentServiceImp> getSegments() {
        return segments;
    }

    /**
     * Calculates the length of the track
     *
     * @return the tracks's length in meters
     */
    public double length()
    {
        double length = 0.0;

        for (int i = 0; i < segments.size(); i++) {
            length += segments.get(i).length();
        }

        return length;
    }

    /**
     * Calculates the total ascent in the track.
     *
     * <p>The total ascent of the track is calculated by comparing each
     * of the track's segments  with their predecessors. If the
     * elevation of a segments is higher than the elevation of the
     * predecessor, the total ascent is increased accordingly.</p>
     *
     * @see Track#cumulativeDescent()
     * @return the tracks's total ascent in meters
     */
    public double cumulativeAscent() {
        double ascent = 0.0;

        for (int i = 0; i < segments.size(); i++) {
            ascent += segments.get(i).cumulativeAscent();
        }

        return ascent;
    }

    /**
     * Calculates the total descent in the track.
     *
     * <p>The total descent of the track is calculated by comparing each
     * of the track's segments with their predecessors. If the
     * elevation of a segment is lower than the elevation of the
     * predecessor, the total descent is increased accordingly.</p>
     *
     * @see Track#cumulativeAscent()
     * @return the tracks's total descent in meters
     */
    public double cumulativeDescent() {
        double descent = 0.0;

        for (int i = 0; i < segments.size(); i++) {
            descent += segments.get(i).cumulativeDescent();
        }

        return descent;
    }

    /**
     * Returns the point in time when the track was entered
     *
     * <p>Usually this is the time stamp of the segment that was added
     * first to the track.</p>
     *
     * @see Track#endTime()
     * @return the point in time when the track was entered
     */
    public Date startingTime() {
        Date result = null;

        for (int i = 0; i < segments.size(); i++) {
            TrackSegmentServiceImp segment = segments.get(i);
            Date startingTime = segment.startingTime();

            if (startingTime != null) {
                if (result == null || startingTime.before(result)) {
                    result = startingTime;
                }
            }
        }

        return result;
    }

    /**
     * Returns the point in time when the track was left
     *
     * <p>Usually this is the time stamp of the segment that was added
     * last to the track.</p>
     *
     * @see Track#startingTime
     * @return the point in time when the track was left
     */
    public Date endTime() {
        Date result = null;

        for (int i = 0; i < segments.size(); i++) {
            TrackSegmentServiceImp segment = segments.get(i);
            Date endTime = segment.endTime();

            if (endTime != null) {
                if (result == null || endTime.after(result)) {
                    result = endTime;
                }
            }
        }

        return result;
    }
}
