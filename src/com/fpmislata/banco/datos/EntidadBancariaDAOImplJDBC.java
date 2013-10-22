/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.datos;

import com.fpmislata.banco.negocio.EntidadBancaria;
import com.fpmislata.banco.negocio.TipoEntidadBancaria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class EntidadBancariaDAOImplJDBC implements EntidadBancariaDAO {

    ConnectionFactory connectionFactory = new ConnectionFactoryImplDataSource();

    public EntidadBancariaDAOImplJDBC() {
    }

    @Override
    public EntidadBancaria read(Integer idEntidadBancaria) {
        try {
            Connection connection = connectionFactory.getConnection();
            EntidadBancaria entidadBancaria;

            String selectSQL = "SELECT idEntidad, codigoEntidad, nombre, cif, tipoEntidadBancaria FROM entidadbancaria WHERE idEntidad = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idEntidadBancaria);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == true) {
                entidadBancaria = new EntidadBancaria();
                entidadBancaria.setIdEntidad(resultSet.getInt("idEntidad"));
                entidadBancaria.setCodigoEntidad(resultSet.getString("codigoEntidad"));
                entidadBancaria.setNombre(resultSet.getString("nombre"));
                entidadBancaria.setCif(resultSet.getString("cif"));
                String tipoEntidadBancaria = resultSet.getString("tipoEntidadBancaria");
                entidadBancaria.setTipoEntidadBancaria(TipoEntidadBancaria.valueOf(tipoEntidadBancaria));

                if (resultSet.next() == true) {
                    throw new RuntimeException("Existe mas de una entidad bancaria " + idEntidadBancaria);
                }
            } else {
                //Devuelve null si no existe la entidad.
                entidadBancaria = null;
            }
            connection.close();
            return entidadBancaria;
        } catch (Exception ex) {
            throw new RuntimeException("Fallo", ex);
        }
    }

    @Override
    public void insert(EntidadBancaria entidadBancaria) {
        try {
            Connection connection = connectionFactory.getConnection();
            String insertTableSQL = "INSERT INTO entidadbancaria"
                    + "(idEntidad, codigoEntidad, nombre, cif, tipoEntidadBancaria) VALUES"
                    + "(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, entidadBancaria.getIdEntidad());
            preparedStatement.setString(2, entidadBancaria.getCodigoEntidad());
            preparedStatement.setString(3, entidadBancaria.getNombre());
            preparedStatement.setString(4, entidadBancaria.getCif());
            preparedStatement.setString(5, entidadBancaria.getTipoEntidadBancaria().name());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            throw new RuntimeException("Fallo", ex);
        }
    }

    @Override
    public void update(EntidadBancaria entidadBancaria) {
        try {
            Connection connection = connectionFactory.getConnection();
            String updateTableSQL = "UPDATE entidadBancaria SET codigoEntidad = ?, nombre = ? , cif= ?, tipoEntidadBancaria = ? WHERE idEntidad = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, entidadBancaria.getCodigoEntidad());
            preparedStatement.setString(2, entidadBancaria.getNombre());
            preparedStatement.setString(3, entidadBancaria.getCif());
            preparedStatement.setString(4, entidadBancaria.getTipoEntidadBancaria().name());
            preparedStatement.setInt(5, entidadBancaria.getIdEntidad());

            preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            throw new RuntimeException("Fallo", ex);
        }
    }

    @Override
    public void delete(Integer idEntidadBancaria) {
        try {
            Connection connection = connectionFactory.getConnection();
            String deleteSQL = "DELETE FROM entidadbancaria WHERE idEntidad = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, idEntidadBancaria);
            int filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas > 1) {
                throw new RuntimeException("Se han borrado mas de un registro de la entidad: " + idEntidadBancaria);
            }
            connection.close();
        } catch (Exception ex) {
            throw new RuntimeException("Fallo", ex);
        }
    }

    @Override
    public List<EntidadBancaria> findAll() {
        try {
            Connection connection = connectionFactory.getConnection();
            List<EntidadBancaria> entidadesBancarias = new ArrayList<>();

            String selectSQL = "SELECT idEntidad, codigoEntidad, nombre, cif, tipoEntidadBancaria FROM entidadbancaria";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next() == true) {
                EntidadBancaria entidadBancaria = new EntidadBancaria();
                entidadBancaria.setIdEntidad(resultSet.getInt("idEntidad"));
                entidadBancaria.setCodigoEntidad(resultSet.getString("codigoEntidad"));
                entidadBancaria.setNombre(resultSet.getString("nombre"));
                entidadBancaria.setCif(resultSet.getString("cif"));
                String tipoEntidadBancaria = resultSet.getString("tipoEntidadBancaria");
                entidadBancaria.setTipoEntidadBancaria(TipoEntidadBancaria.valueOf(tipoEntidadBancaria));
                entidadesBancarias.add(entidadBancaria);

            }
            connection.close();
            return entidadesBancarias;
        } catch (Exception ex) {
            throw new RuntimeException("Fallo", ex);
        }
    }

    @Override
    public List<EntidadBancaria> findByCodigo(String codigo) {
        try {
            Connection connection = connectionFactory.getConnection();
            List<EntidadBancaria> entidadesBancarias = new ArrayList<>();

            String selectSQL = "SELECT idEntidad, codigoEntidad, nombre, cif, tipoEntidadBancaria FROM entidadbancaria WHERE codigoEntidad = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next() == true) {
                EntidadBancaria entidadBancaria = new EntidadBancaria();
                entidadBancaria.setIdEntidad(resultSet.getInt("idEntidad"));
                entidadBancaria.setCodigoEntidad(resultSet.getString("codigoEntidad"));
                entidadBancaria.setNombre(resultSet.getString("nombre"));
                entidadBancaria.setCif(resultSet.getString("cif"));
                String tipoEntidadBancaria = resultSet.getString("tipoEntidadBancaria");
                entidadBancaria.setTipoEntidadBancaria(TipoEntidadBancaria.valueOf(tipoEntidadBancaria));
                entidadesBancarias.add(entidadBancaria);

            }
            connection.close();
            return entidadesBancarias;
        } catch (Exception ex) {
            throw new RuntimeException("Fallo", ex);
        }
    }

    @Override
    public List<EntidadBancaria> findByNombre(String nombre) {
        try {
            Connection connection = connectionFactory.getConnection();
            List<EntidadBancaria> entidadesBancarias = new ArrayList<>();
            

            if (nombre == null || nombre.trim().equals("")) {
                entidadesBancarias = findAll();
            } else {
                String selectSQL = "SELECT idEntidad, codigoEntidad, nombre, cif, tipoEntidadBancaria FROM entidadbancaria WHERE nombre LIKE ? ";
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, "%" + nombre + "%");
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next() == true) {
                    EntidadBancaria entidadBancaria = new EntidadBancaria();
                    entidadBancaria.setIdEntidad(resultSet.getInt("idEntidad"));
                    entidadBancaria.setCodigoEntidad(resultSet.getString("codigoEntidad"));
                    entidadBancaria.setNombre(resultSet.getString("nombre"));
                    entidadBancaria.setCif(resultSet.getString("cif"));
                    String tipoEntidadBancaria = resultSet.getString("tipoEntidadBancaria");
                    entidadBancaria.setTipoEntidadBancaria(TipoEntidadBancaria.valueOf(tipoEntidadBancaria));
                    entidadesBancarias.add(entidadBancaria);
                }
                connection.close();
            }

            return entidadesBancarias;
        } catch (Exception ex) {
            throw new RuntimeException("Fallo", ex);
        }
    }
}
