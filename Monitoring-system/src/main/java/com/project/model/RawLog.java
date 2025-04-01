package com.project.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "RawLog")
public class RawLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "outlet_id", referencedColumnName = "id",nullable = false)
    private SmartOutlet outlet;


    @Column(name = "power", nullable = false)
    private float power;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp",
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date timestamp;

    public RawLog() {
        super();
    }

    public RawLog(float power, SmartOutlet outlet) {
        this.power = power;
        this.outlet = outlet;
        // timestamp sẽ được DB tự set theo default CURRENT_TIMESTAMP
    }

    public int getId() {
        return id;
    }

    public SmartOutlet getOutlet() {
        return outlet;
    }

    public void setOutlet(SmartOutlet outlet) {
        this.outlet = outlet;
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

    @Override
    public String toString() {
        return "RawLog{" +
                "id=" + id +
                ", outlet=" + (outlet != null ? outlet.getId() : "N/A") +
                ", power=" + power +
                ", timestamp=" + timestamp +
                '}';
    }
}
