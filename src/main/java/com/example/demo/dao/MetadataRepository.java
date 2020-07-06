package com.example.demo.dao;

import com.example.demo.domain.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Throwable.class)
public interface MetadataRepository extends JpaRepository<Metadata, Integer> {
    Metadata findByUserId(int userId);
}
