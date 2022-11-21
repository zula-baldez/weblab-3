package com.example.facelets;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.Map;

@ManagedBean
public class AttemptHitter {
    private double x;
    private double y;
    private double r;
    private int attempt = 0;
    @ManagedProperty(value = "#{dbManager}")
    private DbManager dbManager;

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

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public DbManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void checkHit() {
        attempt++;
        long start = System.currentTimeMillis();
        long start_time_nano = System.nanoTime();
        boolean hit;
        if (x >= 0 && y <= r / 2 - x && y >= 0) hit = true;
        else if (x >= 0 && y <= 0 && y >= -r && x <= r / 2) hit = true;
        else if (x <= 0 && y >= 0 && x * x + y * y <= r * r) hit = true;
        else hit = false;
        Long startTime = (start);
        Long workTime = ((System.nanoTime() - start_time_nano));

        Attempt attempt = new Attempt(this.attempt, x, y, r, hit, workTime, startTime);
        dbManager.addAttempt(attempt);
    }

    public void addAttemptFromJsParams() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            if(r == 0) return;
            double x = Double.parseDouble(params.get("x"));
            double y = Double.parseDouble(params.get("y"));
            this.x = x;
            this.y = y;
            checkHit();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


}
