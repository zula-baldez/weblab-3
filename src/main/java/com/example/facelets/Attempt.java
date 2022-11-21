package com.example.facelets;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Objects;


@Entity
@SessionScoped
public class Attempt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private int attempt = 0;
    @Column
    private double x;
    @Column
    private double y;
    @Column
    private double r;
    @Column
    private boolean hit;
    @Column
    private Long workTime;
    @Column
    private Long startTime;
    @Transient
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public Attempt(int attempt, double x, double y, double r, boolean hit, Long workTime, Long startTime) {
        this.attempt = attempt;
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.workTime = workTime;
        this.startTime = startTime;
    }

    public Attempt() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public Long getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Long workTime) {
        this.workTime = workTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attempt that = (Attempt) o;
        return attempt == that.attempt && Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.r, r) == 0 && hit == that.hit && Objects.equals(workTime, that.workTime) && Objects.equals(startTime, that.startTime) && Objects.equals(simpleDateFormat, that.simpleDateFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt, x, y, r, hit, workTime, startTime, simpleDateFormat);
    }
}
