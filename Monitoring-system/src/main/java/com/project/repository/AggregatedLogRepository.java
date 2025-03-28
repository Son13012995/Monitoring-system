package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.model.AggregatedLog;
import java.util.List;

@Repository
public interface AggregatedLogRepository extends JpaRepository<AggregatedLog, Integer> {
    AggregatedLog findById(int id);
    @SuppressWarnings("null")
    List<AggregatedLog> findAll();
    void deleteById(int id);

}
