/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.datos;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author alumno
 */
public class GenericDAOImplHibernate<T, ID extends Serializable> implements GenericDAO<T, ID> {

    protected SessionFactory sessionFactory;

    public GenericDAOImplHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T read(ID id) {
        Session session = sessionFactory.openSession();
        T tipo = (T) session.get(getEntityClass(), id);
        session.close();
        return tipo;
    }

    @Override
    public void insert(T tipo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(tipo);
        session.getTransaction().commit();
        session.close();
    }
    
    @Override
    public void update(T tipo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(tipo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(ID id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T tipo = (T) session.get(getEntityClass(), id);
        session.delete(tipo);
        session.getTransaction().commit();
        session.close();
    }
    
    @Override
    public List<T> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT t FROM " + getEntityClass().getName() + " t");
        List<T> lista = query.list();
        session.close();
        return lista;
    }

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
