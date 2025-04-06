package com.project.dto;

public class OutletAvgDto {
    private int outletId;
    private String outletName;
    private float todayAverageConsumption;

    public OutletAvgDto() {}

    public OutletAvgDto(int outletId, String outletName, float todayAverageConsumption) {
        this.outletId = outletId;
        this.outletName = outletName;
        this.todayAverageConsumption = todayAverageConsumption;
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
}
