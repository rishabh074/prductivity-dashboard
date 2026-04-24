package com.factory.productivity_dashboard.dto;

public class WorkstationMetricsDto {

    private String workstationId;
    private long activeTime;
    private double utilization;
    private int totalUnits;


    public String getWorkstationId() { return workstationId; }
    public void setWorkstationId(String workstationId) { this.workstationId = workstationId; }

    public long getActiveTime() { return activeTime; }
    public void setActiveTime(long activeTime) { this.activeTime = activeTime; }

    public double getUtilization() { return utilization; }
    public void setUtilization(double utilization) { this.utilization = utilization; }

    public int getTotalUnits() { return totalUnits; }
    public void setTotalUnits(int totalUnits) { this.totalUnits = totalUnits; }
}