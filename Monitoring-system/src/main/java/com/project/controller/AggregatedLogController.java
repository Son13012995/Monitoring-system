package com.project.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.service.AggregatedLogService;
import com.project.model.AggregatedLog;

@RestController
@RequestMapping("/aggregated")
public class AggregatedLogController {

    @Autowired
    private AggregatedLogService aggregatedLogService;

    @GetMapping("/compute-per-day")
    public ResponseEntity<?> computeAggregatedPerDay() {
        Map<String, AggregatedLog> result = aggregatedLogService.computeAggregatedEnergyPerDay();

       
        System.out.println("Kết quả computeAggregatedEnergyPerDay:");
        result.forEach((day, aggLog) -> {
            System.out.println("Ngày: " + day + 
                    ", minPower=" + aggLog.getMinPower() + 
                    ", maxPower=" + aggLog.getMaxPower() + 
                    ", avgPower=" + aggLog.getAvgPower());
        });

        return ResponseEntity.ok(result); 
       
    }
}