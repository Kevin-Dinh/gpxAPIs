package com.example.demo.dao;

import com.example.demo.domain.TrackPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Throwable.class)
public interface TrackPointRepository extends JpaRepository<TrackPoint, Integer> {
    List<TrackPoint> findByTrackSegmentId(int id);
}
