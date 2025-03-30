package com.project.service;

import org.springframework.stereotype.Service;

import com.project.repository.RawLogRepository;

import jakarta.transaction.Transactional;

@Service
public class RawLogService {
     private final RawLogRepository rawLogRepository;

     public RawLogService(RawLogRepository rawLogRepository) {
         this.rawLogRepository = rawLogRepository;
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