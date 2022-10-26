package com.example.facelets;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
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
/*
    @Inject
*/
    private Attempt attempt;
    public void addAttempt() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        attempt = (Attempt) facesContext.getApplication().createValueBinding("#{attempt}").getValue(facesContext);

        System.out.println(attempt.getR());
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



}
