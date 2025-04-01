package com.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.AggregatedLog;
import com.project.repository.AggregatedLogRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AggregatedLogService {

    @Autowired
    private AggregatedLogRepository aggregatedLogRepository;

    public AggregatedLogService(AggregatedLogRepository aggregatedLogRepository) {
        this.aggregatedLogRepository = aggregatedLogRepository;
    }

    

   
    public Map<String, AggregatedLog> computeAggregatedEnergyPerDay() {
        // 1. Lấy danh sách AggregatedLog từ DB
        List<AggregatedLog> logs = aggregatedLogRepository.findAll();
        
        // 2. Gọi hàm tính toán (giống logic bạn viết trong Main)
        Map<String, AggregatedLog> aggregatedData = new HashMap<>();
        
        if (!logs.isEmpty()) {
            aggregatedData = computeAggregatedEnergyPerDay(logs);
        }
        
        return aggregatedData;
    }

    // Ham tính avg 
    private float computeAverageEnergyConsumption(List<Float> readings) {
        float totalEnergy = 0;
        for (float reading : readings) {
            totalEnergy += reading;
        }
        float avgEnergy = totalEnergy / readings.size();
        System.out.println("Trung bình năng lượng: " + avgEnergy);
        return avgEnergy;
    }

    // Gom nhóm theo ngày và tính min, max, avg
    private Map<String, AggregatedLog> computeAggregatedEnergyPerDay(List<AggregatedLog> readings) {
        Map<String, List<Float>> dailyReadings = new HashMap<>();

     
        for (AggregatedLog entry : readings) {
            String dateKey = entry.getDate().toString();
            dailyReadings.putIfAbsent(dateKey, new ArrayList<>());
            dailyReadings.get(dateKey).add(entry.getAvgPower());
        }

        Map<String, AggregatedLog> aggregatedData = new HashMap<>();
        for (Map.Entry<String, List<Float>> entry : dailyReadings.entrySet()) {
            String date = entry.getKey();
            List<Float> dailyPowerReadings = entry.getValue();
            AggregatedLog aggregatedEnergy = computeAggregatedEnergyConsumption(dailyPowerReadings);
            aggregatedData.put(date, aggregatedEnergy);
        }

        return aggregatedData;
    }

    // Tính min, max, total, trung bình
    private AggregatedLog computeAggregatedEnergyConsumption(List<Float> readings) {
        float minPower = Float.MAX_VALUE;
        float maxPower = Float.MIN_VALUE;
        float totalPower = 0;

        for (float power : readings) {
            if (power < minPower) minPower = power;
            if (power > maxPower) maxPower = power;
            totalPower += power;  // Tính tổng năng lượng
        }

        // trung bình năng lượng
        float avgPower = computeAverageEnergyConsumption(readings);

        // In kết quả để debug (không bắt buộc)
        System.out.println("Năng lượng tối thiểu: " + minPower);
        System.out.println("Năng lượng tối đa: " + maxPower);
        System.out.println("Tổng năng lượng: " + totalPower);

        // Trả về 1 AggregatedLog (hoặc bạn có thể tạo DTO chuyên chở kết quả)
        AggregatedLog aggregatedLog = new AggregatedLog();
        aggregatedLog.setMinPower(minPower);
        aggregatedLog.setMaxPower(maxPower);
        aggregatedLog.setAvgPower(avgPower);
        return aggregatedLog;
    }
}
