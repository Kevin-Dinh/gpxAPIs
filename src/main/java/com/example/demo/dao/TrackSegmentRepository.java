package com.example.demo.dao;

import com.example.demo.domain.TrackSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Throwable.class)
public interface TrackSegmentRepository extends JpaRepository<TrackSegment, Integer> {
    List<TrackSegment> findByTrackId(int id);
}
