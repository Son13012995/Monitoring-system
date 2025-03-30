package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class DashboardController {

    @GetMapping("/")
    public String getHome() {
        return "admin/hello"; // Đảm bảo file /WEB-INF/view/admin/hello.jsp tồn tại
    }
    
    @GetMapping("/admin")
    public String getDashboard() {
        return "admin/dashboard/show"; // Trả về /WEB-INF/view/admin/dashboard/show.jsp
    }
}