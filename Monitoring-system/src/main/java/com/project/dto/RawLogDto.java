package com.project.dto;

import java.util.Date;

public class RawLogDto {

    private float power;
    private Date timestamp;

    public RawLogDto() {
        super();
    }

    public RawLogDto(float power, Date timestamp) {
        this.power = power;
        this.timestamp = timestamp;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    

}