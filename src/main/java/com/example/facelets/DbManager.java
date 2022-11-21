package com.example.facelets;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@SessionScoped
@ManagedBean
public class DbManager implements Serializable {
    private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Attempt.class)
            .buildSessionFactory();
    private String getString = "From Attempt";

    public void addAttempt(Attempt attempt) {
        if(attempt.getR() == 0) return;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(attempt);
        session.getTransaction().commit();



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


    public String getGetString() {
        return getString;
    }

    public void setGetString(String getString) {
        this.getString = getString;
    }


}
