package com.factory.productivity_dashboard.dto;

public class WorkerMetricsDto {

    private String workerId;
    private long activeTime;   // seconds
    private long idleTime;
    private double utilization;
    private int unitsProduced;


    public String getWorkerId() { return workerId; }
    public void setWorkerId(String workerId) { this.workerId = workerId; }

    public long getActiveTime() { return activeTime; }
    public void setActiveTime(long activeTime) { this.activeTime = activeTime; }

    public long getIdleTime() { return idleTime; }
    public void setIdleTime(long idleTime) { this.idleTime = idleTime; }

    public double getUtilization() { return utilization; }
    public void setUtilization(double utilization) { this.utilization = utilization; }

    public int getUnitsProduced() { return unitsProduced; }
    public void setUnitsProduced(int unitsProduced) { this.unitsProduced = unitsProduced; }
}
