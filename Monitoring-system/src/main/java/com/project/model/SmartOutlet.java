package com.project.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SmartOutlet")
public class SmartOutlet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    // Quan hệ 1-N với AggregatedLog
    @OneToMany(mappedBy = "outlet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AggregatedLog> aggregatedLogs;

    // Quan hệ 1-N với RawLog
    @OneToMany(mappedBy = "outlet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RawLog> rawLogs;

    public SmartOutlet() {
        super();
    }

    public SmartOutlet(String name) {
        this.name = name;
    }

    // Getter / Setter cho các trường

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AggregatedLog> getAggregatedLogs() {
        return aggregatedLogs;
    }

    public void setAggregatedLogs(List<AggregatedLog> aggregatedLogs) {
        this.aggregatedLogs = aggregatedLogs;
    }

    public List<RawLog> getRawLogs() {
        return rawLogs;
    }

    public void setRawLogs(List<RawLog> rawLogs) {
        this.rawLogs = rawLogs;
    }

    @Override
    public String toString() {
        return "SmartOutlet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
