package com.example.facelets;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.List;
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

    public List<Attempt> getAttempts() {
        try (Session session = sessionFactory.openSession()) {

            return session.createQuery(getString).list();
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
