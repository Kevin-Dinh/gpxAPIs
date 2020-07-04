package com.example.demo.service;

import java.util.Date;

public interface TrackSegmentService {
    double cumulativeAscent();

    double cumulativeDescent();

    double length();

    Date startingTime();

    Date endTime();
}
