package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.model.AggregatedLog;
import com.project.repository.AggregatedLogRepository;

@Service
public class AggregatedLogService {
    private final AggregatedLogRepository aggregatedLogRepository;

    public AggregatedLogService(AggregatedLogRepository aggregatedLogRepository) {
        this.aggregatedLogRepository = aggregatedLogRepository;
    }

    public void deleteAggregatedLogById(int id) {
        aggregatedLogRepository.deleteById(id);
    }

    public AggregatedLog getAggregatedLogById(int id) {
        return aggregatedLogRepository.findById(id);
    }

    public List<AggregatedLog> findAllAggregatedLog() {
        return aggregatedLogRepository.findAll();
    }
}
