package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.model.RawLog;
import com.project.repository.RawLogRepository;

import jakarta.transaction.Transactional;

@Service
public class RawLogService {
     private final RawLogRepository rawLogRepository;

     public RawLogService(RawLogRepository rawLogRepository) {
         this.rawLogRepository = rawLogRepository;
     }

     public List<RawLog> getAllRawLogs() {
         return rawLogRepository.findAll();
     }

    // Để @Modifying được chạy, ta cần @Transactional
    @Transactional
    public void deleteAllAndReset() {
        rawLogRepository.deleteAllRows();      
        rawLogRepository.resetAutoIncrement();  
        System.out.println("Đã xóa tất cả và reset AUTO_INCREMENT thành công!");
    }
    
    public void printRowCount() {
        long count = rawLogRepository.countAllRows();
        System.out.println("Số hàng trong bảng RawLog: " + count);
    }
}