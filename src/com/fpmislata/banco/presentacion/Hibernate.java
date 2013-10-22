/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentacion;

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
        SessionFactory sessionFactory;

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        
//        EntidadBancaria entidadBancaria = new EntidadBancaria(9, "S34", "Bankia", "D418748");
        Session session = sessionFactory.openSession();
        EntidadBancaria entidadBancaria =(EntidadBancaria)session.get(EntidadBancaria.class, 9);
        System.out.println(entidadBancaria.getNombre());
//        session.beginTransaction();
//
//        session.save(entidadBancaria); //<|--- Aqui guardamos el objeto en la base de datos.
//
//        session.getTransaction().commit();
        session.close();

        sessionFactory.close();
    }
}
