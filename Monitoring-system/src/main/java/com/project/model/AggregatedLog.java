package com.project.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "AggregatedLog")
public class AggregatedLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int id;

    @Column(name = "min_power")
    private float minPower;

    @Column(name = "max_power")
    private float maxPower;

    @Column(name = "avg_power")
    private float avgPower;

    @Column(name = "timestamp")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "outlet_id", referencedColumnName = "id")
    private SmartOutlet outlet; // Quan hệ với SmartOutlet (nhiều AggregatedLog thuộc về một SmartOutlet)

    public AggregatedLog() {
        super();
    }

    public AggregatedLog(float minPower, float maxPower, float avgPower) {
        this.minPower = minPower;
        this.maxPower = maxPower;
        this.avgPower = avgPower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMinPower() {
        return minPower;
    }

    public void setMinPower(float minPower) {
        this.minPower = minPower;
    }

    public float getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(float maxPower) {
        this.maxPower = maxPower;
    }

    public float getAvgPower() {
        return avgPower;
    }

    public void setAvgPower(float avgPower) {
        this.avgPower = avgPower;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SmartOutlet getOutlet() {
        return outlet;
    }

    public void setOutlet(SmartOutlet outlet) {
        this.outlet = outlet;
    }

    @Override
    public String toString() {
        return "AggregatedLog{" +
                "id=" + id +
                ", minPower=" + minPower +
                ", maxPower=" + maxPower +
                ", avgPower=" + avgPower +
                ", date=" + date +
                ", outlet=" + (outlet != null ? outlet.getId() : "N/A") +
                '}';
    }
}
