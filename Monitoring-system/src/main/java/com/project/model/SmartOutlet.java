package com.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "SmartOutlet")
public class SmartOutlet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    // Quan hệ Many-to-Many với AggregatedLog
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "smart_outlet_aggregated_log",
            joinColumns = @JoinColumn(name = "outlet_id"),
            inverseJoinColumns = @JoinColumn(name = "log_id")
    )
    private List<AggregatedLog> aggregatedLogs = new ArrayList<>();

    // Quan hệ 1-N với RawLog
    @OneToMany(mappedBy = "outlet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RawLog> rawLogs;

    public SmartOutlet() {}

    public SmartOutlet(String name) {
        this.name = name;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
