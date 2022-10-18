package com.example.facelets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;


@ManagedBean
@SessionScoped
public class DbManager {
    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Attempt.class)
            .buildSessionFactory();
    private int id = 0;
    private String getString = "From Attempt ";

    public void addAttempt(Attempt attemptBean) {
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


}
