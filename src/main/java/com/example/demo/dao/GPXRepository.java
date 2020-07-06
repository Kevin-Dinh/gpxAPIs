package com.example.demo.dao;

import com.example.demo.domain.GPX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Throwable.class)
public interface GPXRepository extends JpaRepository<GPX, Integer> {
    GPX findByMetadataId(int id);
}
