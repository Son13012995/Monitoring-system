package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import com.project.service.SmartOutletService;


@Controller
public class SmartOutletController{
    @SuppressWarnings("unused")
    private final SmartOutletService smartOutletService;

    public SmartOutletController(SmartOutletService smartOutletService){
        this.smartOutletService = smartOutletService;
    }

    @GetMapping("/admin/smart-outlet")   
    public String getDetailSmartOutlet(){
         return "admin/smart-outlet/detail";
     }


}