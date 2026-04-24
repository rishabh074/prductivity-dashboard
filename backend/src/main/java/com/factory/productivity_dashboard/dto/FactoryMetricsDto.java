package com.factory.productivity_dashboard.dto;

public class FactoryMetricsDto {

    private long totalActiveTime;
    private int totalProduction;
    private double avgUtilization;

    public long getTotalActiveTime() {
        return totalActiveTime;
    }

    public void setTotalActiveTime(long totalActiveTime) {
        this.totalActiveTime = totalActiveTime;
    }

    public int getTotalProduction() {
        return totalProduction;
    }

    public void setTotalProduction(int totalProduction) {
        this.totalProduction = totalProduction;
    }

    public double getAvgUtilization() {
        return avgUtilization;
    }

    public void setAvgUtilization(double avgUtilization) {
        this.avgUtilization = avgUtilization;
    }
}
