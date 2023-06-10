package com.fichosa.technicaltest.model;

import javax.persistence.*;

@Table(name = "RunawayResult")
@Entity
public class ScapeResult {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "scape")
    private boolean scape = false;

    public ScapeResult() {
    }

    public ScapeResult(boolean scape) {
        this.scape = scape;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isScape() {
        return scape;
    }

    public void setScape(boolean scape) {
        this.scape = scape;
    }
}
