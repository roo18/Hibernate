/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.datos;

import com.fpmislata.banco.negocio.EntidadBancaria;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author alumno
 */
public class EntidadBancariaDAOImplHibernate implements EntidadBancariaDAO {

    private SessionFactory sessionFactory;

    public EntidadBancariaDAOImplHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public EntidadBancaria read(Integer idEntidadBancaria) {
        Session session = sessionFactory.openSession();
        EntidadBancaria entidadBancaria = (EntidadBancaria) session.get(EntidadBancaria.class, idEntidadBancaria);
        session.close();
        return entidadBancaria;
    }

    @Override
    public void insert(EntidadBancaria entidadBancaria) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(entidadBancaria);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(EntidadBancaria entidadBancaria) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(entidadBancaria);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Integer idEntidadBancaria) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        EntidadBancaria entidadBancaria = (EntidadBancaria) session.get(EntidadBancaria.class, idEntidadBancaria);
        session.delete(entidadBancaria);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<EntidadBancaria> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT eb FROM EntidadBancaria eb");
        List<EntidadBancaria> entidadesBancarias = query.list();
        session.close();
        return entidadesBancarias;
    }

    @Override
    public List<EntidadBancaria> findByCodigo(String codigo) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT eb FROM EntidadBancaria eb WHERE codigoEntidad= ?");
        query.setString(1, codigo);
        List<EntidadBancaria> entidadesBancarias = query.list();
        session.close();
        return entidadesBancarias;
    }

    @Override
    public List<EntidadBancaria> findByNombre(String nombre) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT eb FROM EntidadBancaria eb WHERE nombre= ?");
        query.setString(1, nombre);
        List<EntidadBancaria> entidadesBancarias = query.list();
        session.close();
        return entidadesBancarias;
    }
}
