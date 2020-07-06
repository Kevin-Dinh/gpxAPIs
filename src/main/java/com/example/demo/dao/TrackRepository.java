package com.example.demo.dao;

import com.example.demo.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Throwable.class)
public interface TrackRepository extends JpaRepository<Track, Integer> {
    List<Track> findByGpxId(int id);
}
