package com.factory.productivity_dashboard.repository;

import com.factory.productivity_dashboard.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, String> {
}