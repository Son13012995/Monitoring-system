package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class DashboardController {

    @GetMapping("/")
    public String getHome(){
        return "home";
    }
    
    @GetMapping("/admin")
    public String getDashboard(){
        return "dashboard/show";
    }
}    
