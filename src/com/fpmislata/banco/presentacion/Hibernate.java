/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentacion;

import com.fpmislata.banco.datos.EntidadBancariaDAOImplHibernate;
import com.fpmislata.banco.datos.HibernateUtil;
import com.fpmislata.banco.negocio.EntidadBancaria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author alumno
 */
public class Hibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        HibernateUtil.buildSessionFactory();
        HibernateUtil.openSessionAndBindToThread();
        
        EntidadBancariaDAOImplHibernate entidadBancariaDAOImplHibernate = new EntidadBancariaDAOImplHibernate();
        EntidadBancaria entidadBancaria = entidadBancariaDAOImplHibernate.read(5);
        System.out.println(entidadBancaria.getNombre());
       
        HibernateUtil.closeSessionAndUnbindFromThread();
        HibernateUtil.closeSessionFactory();
    }
}
