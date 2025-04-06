package com.project.dto;

public class OutletAvgDto {
    private int outletId;
    private String outletName;
    private float todayAverageConsumption;
    private float monthAverageConsumption;

    public OutletAvgDto() {}

    public OutletAvgDto(int outletId, String outletName, float todayAverageConsumption, float monthAverageConsumption) {
        this.outletId = outletId;
        this.outletName = outletName;
        this.todayAverageConsumption = todayAverageConsumption;
        this.monthAverageConsumption = monthAverageConsumption;
    }

    public int getOutletId() {
        return outletId;
    }

    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public float getTodayAverageConsumption() {
        return todayAverageConsumption;
    }

    public void setTodayAverageConsumption(float todayAverageConsumption) {
        this.todayAverageConsumption = todayAverageConsumption;
    }

    public float getMonthAverageConsumption() {
        return monthAverageConsumption;
    }

    public void setMonthAverageConsumption(float monthAverageConsumption) {
        this.monthAverageConsumption = monthAverageConsumption;
    }
}
