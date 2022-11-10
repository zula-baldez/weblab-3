package com.example.facelets;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SessionScoped
@ManagedBean
public class DbManager implements Serializable {
    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Attempt.class)
            .buildSessionFactory();
    private int id = 0;
    private String getString = "From Attempt";
    @ManagedProperty(value = "#{attempt}")
    private Attempt attempt;
    public void addAttempt() {
        if(attempt.getR() == 0) return;
        id++;
        attempt.checkHit();
        attempt.setAttempt(id);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(attempt);
        session.getTransaction().commit();



    }
    public void addAttemptFromJsParams() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            if(attempt.getR() == 0) return;
            double x = Double.parseDouble(params.get("x"));
            double y = Double.parseDouble(params.get("y"));
            double r = attempt.getR();
            attempt.setX(x);
            attempt.setY(y);
            attempt.checkHit();
            addAttempt();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }



    public List<Attempt> getAttempts() {
        try (Session session = sessionFactory.openSession()) {
            List<Attempt> attempts = session.createQuery(getString).list();
            Collections.reverse(attempts);
            return attempts;
        }
    }
    public String getX() {
        return new Gson().toJson(getAttempts().stream().map(Attempt::getX).collect(Collectors.toList()));
    }

    public String getY() {
        return new Gson().toJson(getAttempts().stream().map(Attempt::getY).collect(Collectors.toList()));
    }

    public String getR() {
        return new Gson().toJson(getAttempts().stream().map(Attempt::getR).collect(Collectors.toList()));
    }
    public String getHit() {
        return new Gson().toJson(getAttempts().stream().map(Attempt::isHit).collect(Collectors.toList()));
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGetString() {
        return getString;
    }

    public void setGetString(String getString) {
        this.getString = getString;
    }

    public Attempt getAttempt() {
        return attempt;
    }

    public void setAttempt(Attempt attempt) {
        this.attempt = attempt;
    }
}
