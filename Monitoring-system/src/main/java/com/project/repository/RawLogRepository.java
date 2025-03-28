package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.RawLog;

@Repository
public interface RawLogRepository extends JpaRepository<RawLog, Integer> {
    RawLog findById(int id);
    @SuppressWarnings("null")
    List<RawLog> findAll();
    void deleteById(int id);
    
}
