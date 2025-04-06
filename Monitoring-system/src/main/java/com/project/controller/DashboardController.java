package com.project.controller;

import com.project.dto.OutletAvgDto;
import com.project.model.SmartOutlet;
import com.project.service.AggregatedLogService;
import com.project.service.SmartOutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller 
public class DashboardController {
    @Autowired
    private SmartOutletService smartOutletService;

    @Autowired
    private AggregatedLogService aggregatedLogService;

    @GetMapping("/")
    public String getHome() {
        return "admin/hello"; // Đảm bảo file /WEB-INF/view/admin/hello.jsp tồn tại
    }

    @GetMapping("/admin")
    public String getDashboard(Model model) {
        Date today = new Date();
        List<SmartOutlet> outlets = smartOutletService.findAllSmartOutlet();
        List<OutletAvgDto> data = new ArrayList<>();
        for (SmartOutlet outlet : outlets) {
            float todayAvg = aggregatedLogService.calculateDailyAverageForOutlet(outlet, today);
            float monthAvg = aggregatedLogService.calculateMonthlyAverageForOutlet(outlet, today); // ✅ đổi tên
            data.add(new OutletAvgDto(outlet.getId(), outlet.getName(), todayAvg, monthAvg));
        }
        model.addAttribute("data", data);
        return "admin/dashboard/show";
    }

    // Endpoint mới để hiển thị bảng Outlet với năng lượng trung bình tiêu thụ trong ngày
}