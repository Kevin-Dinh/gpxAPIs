package com.example.demo.dao;

import com.example.demo.domain.Waypoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional(rollbackFor = Throwable.class)
public interface WayPointRepository extends JpaRepository<Waypoint, Integer> {
    ArrayList<Waypoint> findByGpxId(int id);
}
