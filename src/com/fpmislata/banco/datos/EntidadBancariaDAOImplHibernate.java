/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.datos;

import com.fpmislata.banco.negocio.EntidadBancaria;
import java.io.Serializable;
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
public class EntidadBancariaDAOImplHibernate extends GenericDAOImplHibernate<EntidadBancaria, Integer> implements EntidadBancariaDAO {

    @Override
    public List<EntidadBancaria> findByCodigo(String codigo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT eb FROM EntidadBancaria eb WHERE codigoEntidad= ?");
        query.setString(1, codigo);
        List<EntidadBancaria> entidadesBancarias = query.list();
        return entidadesBancarias;
    }

    @Override
    public List<EntidadBancaria> findByNombre(String nombre) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT eb FROM EntidadBancaria eb WHERE nombre= ?");
        query.setString(1, nombre);
        List<EntidadBancaria> entidadesBancarias = query.list();
        return entidadesBancarias;
    }
}
