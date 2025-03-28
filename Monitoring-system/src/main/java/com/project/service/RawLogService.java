package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.model.RawLog;
import com.project.repository.RawLogRepository;

@Service
public class RawLogService {
     private final RawLogRepository rawLogRepository;

     public RawLogService(RawLogRepository rawLogRepository) {
         this.rawLogRepository = rawLogRepository;
     }

     public void deleteRawLogById(int id) {
         rawLogRepository.deleteById(id);
     }

     public RawLog getRawLogById(int id) {
         return rawLogRepository.findById(id);
     }

     public List<RawLog> findAllRawLog() {
            return rawLogRepository.findAll();
     }
}
