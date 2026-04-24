package com.factory.productivity_dashboard.service;

import com.factory.productivity_dashboard.entity.Worker;
import com.factory.productivity_dashboard.entity.Workstation;
import com.factory.productivity_dashboard.entity.Event;
import com.factory.productivity_dashboard.repository.WorkerRepository;
import com.factory.productivity_dashboard.repository.WorkstationRepository;
import com.factory.productivity_dashboard.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
public class SeedService {

    private final WorkerRepository workerRepo;
    private final WorkstationRepository stationRepo;
    private final EventRepository eventRepo;

    public SeedService(WorkerRepository workerRepo,
                       WorkstationRepository stationRepo,
                       EventRepository eventRepo) {
        this.workerRepo = workerRepo;
        this.stationRepo = stationRepo;
        this.eventRepo = eventRepo;
    }

    public void seedData() {

        eventRepo.deleteAll();
        workerRepo.deleteAll();
        stationRepo.deleteAll();


        for (int i = 1; i <= 6; i++) {
            Worker w = new Worker();
            w.setWorkerId("W" + i);
            w.setName("Worker " + i);
            workerRepo.save(w);
        }


        for (int i = 1; i <= 6; i++) {
            Workstation s = new Workstation();
            s.setStationId("S" + i);
            s.setName("Station " + i);
            stationRepo.save(s);
        }

        Random random = new Random();
        Instant now = Instant.now();

        for (int i = 1; i <= 6; i++) {

            for (int j = 0; j < 10; j++) {

                Event e = new Event();
                e.setWorkerId("W" + i);
                e.setWorkstationId("S" + i);

                e.setTimestamp(now.minusSeconds(3600 - j * 300)); // last 1 hour

                String[] types = {"working", "idle", "product_count"};
                String type = types[random.nextInt(types.length)];

                e.setEventType(type);
                e.setConfidence(0.9);

                if (type.equals("product_count")) {
                    e.setCount(random.nextInt(5) + 1);
                }

                eventRepo.save(e);
            }
        }
    }
}