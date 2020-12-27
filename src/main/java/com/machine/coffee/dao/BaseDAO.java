package com.machine.coffee.dao;

import factory.HibernateFactory;
import lombok.Data;
import com.machine.coffee.model.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

@Data
public abstract class BaseDAO<T extends BaseEntity>  {

    @Autowired
    private HibernateFactory hibernateFactory;

    private SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
    private Class<T> persistentClass;

    public BaseDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T save(T entity) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Integer id = (Integer) session.save(entity);
        entity.setId(id);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    public T update(T entity) {
        if (entity.getId() != null) {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
            return entity;
        } else return null;
    }

    public T findById(Integer id){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        T entityFromDB = session.find(getPersistentClass(), id);
        session.getTransaction().commit();
        session.close();
        return entityFromDB;
    }

    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }
}
