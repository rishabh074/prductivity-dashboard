package com.factory.productivity_dashboard.repository;

import com.factory.productivity_dashboard.entity.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkstationRepository extends JpaRepository<Workstation, String> {
}