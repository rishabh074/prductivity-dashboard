package com.factory.productivity_dashboard.service;

import com.factory.productivity_dashboard.dto.WorkerMetricsDto;
import com.factory.productivity_dashboard.dto.WorkstationMetricsDto;
import com.factory.productivity_dashboard.dto.FactoryMetricsDto;
import com.factory.productivity_dashboard.entity.Event;
import com.factory.productivity_dashboard.repository.EventRepository;
import com.factory.productivity_dashboard.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MetricsService {

    private final EventRepository eventRepo;
    private final WorkerRepository workerRepo;

    public MetricsService(EventRepository eventRepo, WorkerRepository workerRepo) {
        this.eventRepo = eventRepo;
        this.workerRepo = workerRepo;
    }

    public List<WorkerMetricsDto> getWorkerMetrics() {

        List<WorkerMetricsDto> result = new ArrayList<>();

        workerRepo.findAll().forEach(worker -> {

            List<Event> events = eventRepo.findByWorkerId(worker.getWorkerId());

            events.sort(Comparator.comparing(Event::getTimestamp));

            long activeTime = 0;
            long idleTime = 0;
            int units = 0;

            for (int i = 0; i < events.size() - 1; i++) {

                Event current = events.get(i);
                Event next = events.get(i + 1);

                long duration = Duration.between(
                        current.getTimestamp(),
                        next.getTimestamp()
                ).getSeconds();

                if ("working".equals(current.getEventType())) {
                    activeTime += duration;
                } else if ("idle".equals(current.getEventType())) {
                    idleTime += duration;
                }

                if ("product_count".equals(current.getEventType()) && current.getCount() != null) {
                    units += current.getCount();
                }
            }

            WorkerMetricsDto dto = new WorkerMetricsDto();
            dto.setWorkerId(worker.getWorkerId());
            dto.setActiveTime(activeTime);
            dto.setIdleTime(idleTime);
            dto.setUnitsProduced(units);

            long totalTime = activeTime + idleTime;
            dto.setUtilization(totalTime > 0 ? (double) activeTime / totalTime : 0);

            result.add(dto);
        });

        return result;
    }

    public List<WorkstationMetricsDto> getWorkstationMetrics() {

        List<WorkstationMetricsDto> result = new ArrayList<>();

        Map<String, List<Event>> grouped =
                eventRepo.findAll().stream()
                        .collect(Collectors.groupingBy(Event::getWorkstationId));

        for (String stationId : grouped.keySet()) {

            List<Event> events = grouped.get(stationId);
            events.sort(Comparator.comparing(Event::getTimestamp));

            long activeTime = 0;
            long idleTime = 0;
            int units = 0;

            for (int i = 0; i < events.size() - 1; i++) {

                Event current = events.get(i);
                Event next = events.get(i + 1);

                long duration = Duration.between(
                        current.getTimestamp(),
                        next.getTimestamp()
                ).getSeconds();

                if ("working".equals(current.getEventType())) {
                    activeTime += duration;
                } else if ("idle".equals(current.getEventType())) {
                    idleTime += duration;
                }

                if ("product_count".equals(current.getEventType()) && current.getCount() != null) {
                    units += current.getCount();
                }
            }

            WorkstationMetricsDto dto = new WorkstationMetricsDto();
            dto.setWorkstationId(stationId);
            dto.setActiveTime(activeTime);
            dto.setTotalUnits(units);

            long totalTime = activeTime + idleTime;
            dto.setUtilization(totalTime > 0 ? (double) activeTime / totalTime : 0);

            result.add(dto);
        }

        return result;
    }

    public FactoryMetricsDto getFactoryMetrics() {

        List<WorkerMetricsDto> workers = getWorkerMetrics();

        long totalActive = 0;
        int totalUnits = 0;
        double totalUtil = 0;

        for (WorkerMetricsDto w : workers) {
            totalActive += w.getActiveTime();
            totalUnits += w.getUnitsProduced();
            totalUtil += w.getUtilization();
        }

        FactoryMetricsDto dto = new FactoryMetricsDto();
        dto.setTotalActiveTime(totalActive);
        dto.setTotalProduction(totalUnits);
        dto.setAvgUtilization(workers.size() > 0 ? totalUtil / workers.size() : 0);

        return dto;
    }
}