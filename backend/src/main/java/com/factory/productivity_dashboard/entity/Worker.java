package com.factory.productivity_dashboard.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Worker {

    @Id
    private String workerId;

    private String name;


    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
