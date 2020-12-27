package com.machine.coffee.dao;

import com.machine.coffee.model.CupOfCoffee;
import factory.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CupOfCoffeeDAO extends BaseDAO<CupOfCoffee> {

    @Autowired
    private HibernateFactory hibernateFactory;

    private SessionFactory sessionFactory = hibernateFactory.getSessionFactory();

    public CupOfCoffee findByName(String name) {
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            String sql = "SELECT * FROM coffee WHERE name=:name";
            Query query = session.createNativeQuery(sql, CupOfCoffee.class);
            query.setParameter("name", name);
            CupOfCoffee cupOfCoffee = (CupOfCoffee) query.getSingleResult();
            session.close();
            return cupOfCoffee;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<CupOfCoffee> findAllExistedCoffee() {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM coffee_type";
        Query query = session.createNativeQuery(sql, CupOfCoffee.class);
        List<CupOfCoffee> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list;


    }

}
