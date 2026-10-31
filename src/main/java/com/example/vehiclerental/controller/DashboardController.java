package com.example.vehiclerental.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedHashMap;
import java.util.Map;
import com.example.vehiclerental.service.DashboardService;



@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired 
    private DashboardService dashboardService;
    @GetMapping("/summary")
    public ResponseEntity<Map<String,Long>> getDashboardSummary(){
        return  ResponseEntity.ok(dashboardService.getDashboardSummary());
    }
    
}
