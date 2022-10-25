package com.example.facelets;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import java.util.stream.Collectors;


@ManagedBean
@SessionScoped
public class DbManager {
    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Attempt.class)
            .buildSessionFactory();
    private int id = 0;
    private String getString = "From Attempt";
    public void addAttempt(Attempt attemptBean) {
        System.out.println(attemptBean.getX());

        System.out.println(attemptBean.getY());

        System.out.println(attemptBean.getR());
        if(attemptBean.getR() == 0) return;
        id++;
        attemptBean.checkHit();
        attemptBean.setAttempt(id);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(attemptBean);
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



}
