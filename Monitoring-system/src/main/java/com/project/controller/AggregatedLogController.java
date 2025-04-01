package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.AggregatedLog;
import com.project.service.AggregatedLogService;

@RestController
@RequestMapping("/aggregated")
public class AggregatedLogController {

    @Autowired
    private AggregatedLogService aggregatedLogService;

    @GetMapping("/compute-per-day")
    public ResponseEntity<?> computeAggregatedPerDayGet() {
        Map<String, AggregatedLog> result = aggregatedLogService.computeAggregatedEnergyPerDay();

        System.out.println("Kết quả computeAggregatedEnergyPerDay (GET):");
        result.forEach((day, aggLog) -> {
            System.out.println("Ngày: " + day + 
                    ", minPower=" + aggLog.getMinPower() + 
                    ", maxPower=" + aggLog.getMaxPower() + 
                    ", avgPower=" + aggLog.getAvgPower());
        });

        return ResponseEntity.ok(result);
    }

    @PostMapping("/compute-per-day")
    public ResponseEntity<AggregatedLog> createAggregatedLog(@RequestBody AggregatedLog aggregatedLog) {
    // In ra log để kiểm tra
        System.out.println("AggregatedLog nhận được từ client:");
        System.out.println(aggregatedLog);

        // Giả sử bạn có hàm save() trong AggregatedLogService để lưu xuống DB
        AggregatedLog savedLog = aggregatedLogService.save(aggregatedLog);

        // Trả về đối tượng vừa lưu
        return ResponseEntity.ok(savedLog);
    }

     // Endpoint new tét thuật toán 
     @PostMapping("/compute-aggregated-from-input")
     public ResponseEntity<?> computeAggregatedFromInput(@RequestBody List<AggregatedLog> logs) {
       
         System.out.println("Nhận được danh sách AggregatedLog:");
         logs.forEach(System.out::println);
         
         Map<String, AggregatedLog> aggregatedData = aggregatedLogService.computeAggregatedEnergyPerDayFromInput(logs);
         return ResponseEntity.ok(aggregatedData);
     }



}