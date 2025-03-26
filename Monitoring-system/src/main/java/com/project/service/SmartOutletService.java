package com.project.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.project.model.SmartOutlet;
import com.project.repository.SmartOutletRepository;



@Service
public class SmartOutletService {

    private final SmartOutletRepository smartOutletRepository;

    public SmartOutletService(SmartOutletRepository smartOutletRepository) {
        this.smartOutletRepository = smartOutletRepository;
    }

    public void deleteSmartOutletById(int id) {
        smartOutletRepository.deleteById(id);
    }

    public SmartOutlet getSmartOutletById(int id) {
        return smartOutletRepository.findById(id);
    }

    public List<SmartOutlet> findAllSmartOutlet() {
        return smartOutletRepository.findAll();
    }

    public SmartOutlet handleSaveSmartOutlet(SmartOutlet smartOutlet) {
        SmartOutlet smartOutlet1 = this.smartOutletRepository.save(smartOutlet);
        System.out.println("SmartOutlet saved successfully");
        return smartOutlet1;

    }

}