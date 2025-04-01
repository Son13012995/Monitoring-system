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

    public AggregatedLog save(AggregatedLog aggregatedLog) {
        return aggregatedLogRepository.save(aggregatedLog);
    }
   
    // Phương thức gốc lấy dữ liệu từ DB
    public Map<String, AggregatedLog> computeAggregatedEnergyPerDay() {
        List<AggregatedLog> logs = aggregatedLogRepository.findAll();
        Map<String, AggregatedLog> aggregatedData = new HashMap<>();
        if (!logs.isEmpty()) {
            aggregatedData = computeAggregatedEnergyPerDay(logs);
        }
        return aggregatedData;
    }

    // Phương thức test: nhận danh sách đầu vào từ client
    public Map<String, AggregatedLog> computeAggregatedEnergyPerDayFromInput(List<AggregatedLog> readings) {
        return computeAggregatedEnergyPerDay(readings);
    }

    private Map<String, AggregatedLog> computeAggregatedEnergyPerDay(List<AggregatedLog> readings) {
        Map<String, List<Float>> dailyReadings = new HashMap<>();

        for (AggregatedLog entry : readings) {
            // Ở đây bạn có thể định dạng lại date theo ý muốn (vd: chỉ lấy ngày)
            // Ví dụ: dùng entry.getDate().toString() hoặc format theo SimpleDateFormat
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

    private float computeAverageEnergyConsumption(List<Float> readings) {
        float totalEnergy = 0;
        for (float reading : readings) {
            totalEnergy += reading;
        }
        float avgEnergy = totalEnergy / readings.size();
        System.out.println("Trung bình năng lượng: " + avgEnergy);
        return avgEnergy;
    }

    // Tính min, max, tổng và trung bình
    private AggregatedLog computeAggregatedEnergyConsumption(List<Float> readings) {
        float minPower = Float.MAX_VALUE;
        float maxPower = Float.MIN_VALUE;
        float totalPower = 0;

        for (float power : readings) {
            if (power < minPower) minPower = power;
            if (power > maxPower) maxPower = power;
            totalPower += power;
        }

        float avgPower = computeAverageEnergyConsumption(readings);

        System.out.println("Năng lượng tối thiểu: " + minPower);
        System.out.println("Năng lượng tối đa: " + maxPower);
        System.out.println("Tổng năng lượng: " + totalPower);

        AggregatedLog aggregatedLog = new AggregatedLog();
        aggregatedLog.setMinPower(minPower);
        aggregatedLog.setMaxPower(maxPower);
        aggregatedLog.setAvgPower(avgPower);
        return aggregatedLog;
    }
}
