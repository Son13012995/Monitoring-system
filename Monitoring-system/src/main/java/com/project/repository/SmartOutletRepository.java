package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.SmartOutlet;

@Repository
public interface SmartOutletRepository extends JpaRepository<SmartOutlet, Integer> {

    @SuppressWarnings("null")
    List<SmartOutlet> findAll();

    void deleteById(int id);

    SmartOutlet findById(int id);

    @SuppressWarnings({ "null", "unchecked" })
    SmartOutlet save(SmartOutlet smartOutlet);





}
