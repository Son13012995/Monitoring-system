package com.project.dto;

public class OutletAvgDto {
    private int outletId;
    private String outletName;
    private float averageConsumption;

    public OutletAvgDto() {}

    public OutletAvgDto(int outletId, String outletName, float averageConsumption) {
        this.outletId = outletId;
        this.outletName = outletName;
        this.averageConsumption = averageConsumption;
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

    public float getAverageConsumption() {
        return averageConsumption;
    }
    public void setAverageConsumption(float averageConsumption) {
        this.averageConsumption = averageConsumption;
    }
}
