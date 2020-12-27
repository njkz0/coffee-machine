package com.machine.coffee.dao;

import com.machine.coffee.model.Cleaner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CleanerDAO extends BaseDAO<Cleaner> {

    @Autowired
    private factory.HibernateFactory hibernateFactory;

    private SessionFactory sessionFactory = hibernateFactory.getSessionFactory();

    public Cleaner getLastCountOfPreparations() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM cleaner WHERE id = (SELECT max(id) FROM cleaner)";
        Query query = session.createNativeQuery(sql, Cleaner.class);
        List<Cleaner> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
