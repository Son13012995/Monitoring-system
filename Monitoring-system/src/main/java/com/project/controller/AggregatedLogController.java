package com.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.AggregatedLogDto;
import com.project.model.AggregatedLog;
import com.project.service.AggregatedLogService;

@RestController
@RequestMapping("/aggregated")
public class AggregatedLogController {

    @Autowired
    private AggregatedLogService aggregatedLogService;

    @GetMapping("/compute")
    public ResponseEntity<?> ListAggregatedLog() {
         List<AggregatedLog> entities = aggregatedLogService.getAllAggregatedLogs();
         List<AggregatedLogDto> dtos = new ArrayList<>();

         for(AggregatedLog agg : entities){
            AggregatedLogDto dto = new AggregatedLogDto();
            dto.setAvgPower(agg.getAvgPower());
            dto.setMaxPower(agg.getMaxPower());
            dto.setMinPower(agg.getMinPower());
            dtos.add(dto);
         }
         return new ResponseEntity<>(dtos,HttpStatus.OK);
    }  // chuyển về dto để chuẩn hóa dữ liệu chỉ bao gồm 3 giá trịtrị tính toán để tránh lộ quá nhiều thông tin 

    @GetMapping("/compute-per-day")
    public ResponseEntity<?> computeAggregatedPerDayGet() {   
        Map<String, AggregatedLog> result = aggregatedLogService.computeAggregatedEnergyPerDay();
        Map<String, AggregatedLogDto> resultLast = new HashMap<>();
        result.forEach((day, aggLog) -> {
            AggregatedLogDto dto = new AggregatedLogDto();
        
            dto.setMinPower(aggLog.getMinPower());
            dto.setMaxPower(aggLog.getMaxPower());
            dto.setAvgPower(aggLog.getAvgPower());
    
            resultLast.put(day, dto);
            System.out.println("Ngày: " + day + 
                    ", minPower=" + dto.getMinPower() + 
                    ", maxPower=" + dto.getMaxPower() + 
                    ", avgPower=" + dto.getAvgPower());
    });


    return new ResponseEntity<>(resultLast,HttpStatus.OK);
 } // convert to AggregatedLog sang dto để chuẩn hóa dữ liệu 
    
    


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