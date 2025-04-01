package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.model.SmartOutlet;
import com.project.service.SmartOutletService;

@Controller
public class SmartOutletController {

    @Autowired
    private final SmartOutletService smartOutletService; 

    public SmartOutletController(SmartOutletService smartOutletService) {
        this.smartOutletService = smartOutletService;
    }
    
    @RequestMapping("/admin/smart-outlet")
    public String getSmartOutletPage(Model model) {
        List<SmartOutlet> smartOutlets = this.smartOutletService.findAllSmartOutlet();
        model.addAttribute("smartOutlets", smartOutlets);
        return "admin/smart-outlet/show";
    }   

    @RequestMapping("/admin/smart-outlet/{id}")
     public String getSmartOutletDetailPage(Model model, @PathVariable Integer id) {
         SmartOutlet smartOutlet = this.smartOutletService.getSmartOutletById(id);
         model.addAttribute("smartOutlet", smartOutlet);
         model.addAttribute("id", id);
         return "admin/smart-outlet/detail";
     }
 
    @GetMapping("/admin/smart-outlet/create") // GET
     public String getCreateSmartOutletPage(Model model) {
         model.addAttribute("newSmartOutlet", new SmartOutlet());
         return "admin/smart-outlet/create";
    }

    @PostMapping(value = "/admin/smart-outlet/create")
    public String createSmartOutletPage(Model model,
                                        @ModelAttribute("newSmartOutlet") SmartOutlet smartOutlet) {

        // Save new SmartOutlet
        this.smartOutletService.handleSaveSmartOutlet(smartOutlet);
        return "redirect:/admin/smart-outlet";
    }


    @RequestMapping("/admin/smart-outlet/update/{id}") // GET
     public String getUpdateSmartOutletPage(Model model, @PathVariable Integer id) {
         SmartOutlet currentSmartOutlet = this.smartOutletService.getSmartOutletById(id);
         model.addAttribute("newSmartOutlet", currentSmartOutlet);
         return "admin/smart-outlet/update";
     }
 
     @PostMapping("/admin/smart-outlet/update")
     public String postUpdateSmartOutlet(Model model, @ModelAttribute("newSmartOutlet") SmartOutlet smartOutlet) {
         SmartOutlet currentSmartOutlet = this.smartOutletService.getSmartOutletById(smartOutlet.getId());
         if (currentSmartOutlet != null) {
             currentSmartOutlet.setName(smartOutlet.getName());
             this.smartOutletService.handleSaveSmartOutlet(currentSmartOutlet);
         }
         return "redirect:/admin/smart-outlet";
     }
 
     @GetMapping("/admin/smart-outlet/delete/{id}")
     public String getDeleteSmartOutletPage(Model model, @PathVariable long id) {
         model.addAttribute("id", id);
         model.addAttribute("newSmartOutlet", new SmartOutlet());
         return "admin/smart-outlet/delete";
     }
 
     @PostMapping("/admin/smart-outlet/delete")
     public String postDeleteSmartOutlet(Model model, @ModelAttribute("newSmartOutlet") SmartOutlet smartOutlet) {
         this.smartOutletService.deleteSmartOutletById(smartOutlet.getId());
         return "redirect:/admin/smart-outlet";
     }
 }
