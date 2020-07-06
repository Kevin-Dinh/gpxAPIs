package com.example.demo.dao;

import com.example.demo.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Throwable.class)
public interface LinkRepository extends JpaRepository<Link, Integer> {
}
