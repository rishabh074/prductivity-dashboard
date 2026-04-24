package com.factory.productivity_dashboard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Workstation {

    @Id
    private String stationId;

    private String name;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
