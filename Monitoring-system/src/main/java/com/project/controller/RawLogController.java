package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.RawLogDto;
import com.project.model.RawLog;
import com.project.service.RawLogService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/rawlog")
public class RawLogController {
       @Autowired
       private RawLogService rawLogService;
       @GetMapping()
       public ResponseEntity<?> getRawLog() {
             List<RawLog> rawLogs = rawLogService.getAllRawLogs();
             List<RawLogDto> rawLogDtos = new ArrayList<>();
             for (RawLog rawLog : rawLogs) {
                RawLogDto dto = new RawLogDto();
                dto.setPower(rawLog.getPower());
                dto.setTimestamp(rawLog.getTimestamp());
                rawLogDtos.add(dto);
            }

            return new ResponseEntity<>(rawLogDtos,HttpStatus.OK);
       }


      
       


     
}
