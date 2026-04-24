package com.factory.productivity_dashboard.dto;

import java.time.Instant;

public class EventRequestDto {

    private Instant timestamp;
    private String workerId;
    private String workstationId;
    private String eventType;
    private Double confidence;
    private Integer count;

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public String getWorkerId() { return workerId; }
    public void setWorkerId(String workerId) { this.workerId = workerId; }

    public String getWorkstationId() { return workstationId; }
    public void setWorkstationId(String workstationId) { this.workstationId = workstationId; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }

    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }
}