/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.datos;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author alumno
 */
public class ConnectionFactoryImplDataSource implements ConnectionFactory {

    @Override
    public Connection getConnection() throws NamingException, SQLException {

        DataSource datasource = null;
        InitialContext initialContext = new InitialContext();
        datasource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/Banco");

        return datasource.getConnection();
    }
}
