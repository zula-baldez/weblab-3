package com.example.facelets;

import javax.faces.annotation.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
public class AttemptBean {
    private double x;
    private double y;
    private double r;
    private boolean hit;
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        System.out.println(x);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        System.out.println(y);
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
        System.out.println(r);
    }
    public void addAttempt() {
        if (x >= 0 && y <= r/2 - x && y >= 0) hit = true;
        else if (x >= 0 && y<=0 && y >= -r && x <= r/2) hit = true;
        else if (x<=0 && y >= 0 && x*x+y*y <= r*r) hit = true;
        else hit = false;
        System.out.println(hit);
    }
}
