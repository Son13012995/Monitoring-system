package com.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;

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

    public List<AggregatedLog> getAllAggregatedLogs(){
        return aggregatedLogRepository.findAll();
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
            // Sử dụng toString() của Date để định danh ngày (có thể thay bằng định dạng khác nếu cần)
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

    // ---------------- PHẦN BỔ SUNG MỚI ----------------
    // Tính trung bình năng lượng tiêu thụ của một outlet trong một ngày cụ thể.
    // Lưu ý: Phương thức này sử dụng danh sách AggregatedLog từ SmartOutlet (quan hệ Many-to-Many)
    // và không thay đổi phần xử lý gốc của AggregatedLogService.
    public float calculateDailyAverageForOutlet(com.project.model.SmartOutlet outlet, Date date) {
        List<com.project.model.AggregatedLog> logs = outlet.getAggregatedLogs();
        List<com.project.model.AggregatedLog> logsOfDay = new ArrayList<>();
        for (com.project.model.AggregatedLog log : logs) {
            if (isSameDay(log.getDate(), date)) {
                logsOfDay.add(log);
            }
        }
        if (logsOfDay.isEmpty()) {
            return 0;
        }
        float sum = 0;
        for (com.project.model.AggregatedLog log : logsOfDay) {
            sum += log.getAvgPower();
        }
        return sum / logsOfDay.size();
    }

    // Hàm so sánh 2 ngày có cùng ngày không (so sánh theo năm và ngày trong năm)
    private boolean isSameDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    // ----------------------------------------------------
}
