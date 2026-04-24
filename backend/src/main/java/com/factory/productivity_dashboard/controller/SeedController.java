package com.factory.productivity_dashboard.controller;

import com.factory.productivity_dashboard.service.SeedService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seed")
public class SeedController {

    private final SeedService seedService;

    public SeedController(SeedService seedService) {
        this.seedService = seedService;
    }

    @PostMapping
    public String seed() {
        seedService.seedData();
        return "Data seeded successfully";
    }
}