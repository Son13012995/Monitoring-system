package com.project.controller;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.dto.OutletAvgDto;
import com.project.model.SmartOutlet;
import com.project.service.AggregatedLogService;
import com.project.service.SmartOutletService;

@Controller
@RequestMapping("/admin/smart-outlet")
public class SmartOutletController {

    @Autowired
    private SmartOutletService smartOutletService;

    @Autowired
    private AggregatedLogService aggregatedLogService;

    @GetMapping("")
    public String getSmartOutletPage(Model model) {
        List<SmartOutlet> smartOutlets = smartOutletService.findAllSmartOutlet();
        model.addAttribute("smartOutlets", smartOutlets);
        return "admin/smart-outlet/show";
    }

    @GetMapping("/{id}")
    public String getSmartOutletDetailPage(Model model, @PathVariable Integer id) {
        SmartOutlet smartOutlet = smartOutletService.getSmartOutletById(id);
        model.addAttribute("smartOutlet", smartOutlet);
        model.addAttribute("id", id);
        return "admin/smart-outlet/detail";
    }

    @GetMapping("/create")
    public String getCreateSmartOutletPage(Model model) {
        model.addAttribute("newSmartOutlet", new SmartOutlet());
        return "admin/smart-outlet/create";
    }

    @PostMapping("/create")
    public String createSmartOutletPage(Model model,
                                        @ModelAttribute("newSmartOutlet") SmartOutlet smartOutlet) {
        // Save new SmartOutlet
        smartOutletService.handleSaveSmartOutlet(smartOutlet);
        return "redirect:/admin/smart-outlet";
    }

    @GetMapping("/update/{id}")
    public String getUpdateSmartOutletPage(Model model, @PathVariable Integer id) {
        SmartOutlet currentSmartOutlet = smartOutletService.getSmartOutletById(id);
        model.addAttribute("newSmartOutlet", currentSmartOutlet);
        return "admin/smart-outlet/update";
    }

    @PostMapping("/update")
    public String postUpdateSmartOutlet(Model model, @ModelAttribute("newSmartOutlet") SmartOutlet smartOutlet) {
        SmartOutlet currentSmartOutlet = smartOutletService.getSmartOutletById(smartOutlet.getId());
        if (currentSmartOutlet != null) {
            currentSmartOutlet.setName(smartOutlet.getName());
            smartOutletService.handleSaveSmartOutlet(currentSmartOutlet);
        }
        return "redirect:/admin/smart-outlet";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteSmartOutletPage(Model model, @PathVariable long id) {
        SmartOutlet outlet = new SmartOutlet();
        outlet.setId((int) id); // hoặc chuyển đổi nếu cần
        model.addAttribute("newSmartOutlet", outlet);
        return "admin/smart-outlet/delete";
    }

    @PostMapping("/delete")
    public String postDeleteSmartOutlet(Model model, @ModelAttribute("newSmartOutlet") SmartOutlet smartOutlet) {
        smartOutletService.deleteSmartOutletById(smartOutlet.getId());
        return "redirect:/admin/smart-outlet";
    }

}
