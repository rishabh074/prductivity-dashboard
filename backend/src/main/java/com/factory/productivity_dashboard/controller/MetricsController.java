package com.factory.productivity_dashboard.controller;

import com.factory.productivity_dashboard.dto.WorkerMetricsDto;
import com.factory.productivity_dashboard.dto.WorkstationMetricsDto;
import com.factory.productivity_dashboard.dto.FactoryMetricsDto;
import com.factory.productivity_dashboard.service.MetricsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
@CrossOrigin(origins = "*")
public class MetricsController {

    private final MetricsService metricsService;

    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/workers")
    public List<WorkerMetricsDto> getWorkerMetrics() {
        return metricsService.getWorkerMetrics();
    }

    @GetMapping("/workstations")
    public List<WorkstationMetricsDto> getWorkstationMetrics() {
        return metricsService.getWorkstationMetrics();
    }

    @GetMapping("/factory")
    public FactoryMetricsDto getFactoryMetrics() {
        return metricsService.getFactoryMetrics();
    }
}