/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author 0489166
 */
public class UsuarioDAO implements GenericDAO<Usuario>{
 @Override
    public int insert(Usuario obj) {
        int chavePrimaria = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.INSERT_USUARIO.getSql(),
                        Statement.RETURN_GENERATED_KEYS)) {
            System.out.println("Conexão aberta!");
            stmt.setString(1, obj.getIdentificador());
            stmt.setString(2, obj.getSenha());
            stmt.execute();
            System.out.println("Dados Gravados!");
            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                chavePrimaria = chaves.getInt(1);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("exceção com recursos");
        }
        return chavePrimaria;
    }

    @Override
    public List<Usuario> listAll() {
        List<Usuario> lista = new LinkedList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.LISTALL_USUARIO.getSql())) {

            System.out.println("Conexão aberta!");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idusuario = rs.getInt("idusuario");
                String identificador = rs.getString("identificador");
                String senha = rs.getString("senha");
                lista.add(new Usuario(idusuario, identificador, senha));
            }
            connection.close();
            return lista;
        } catch (SQLException e) {
            System.out.println("Exceção SQL - listAll");
        } catch (Exception e) {
            System.out.println("Exceção no código - listAll!");
        }
        return null;
    }

    @Override
    public int update(Usuario obj) {
        int retorno = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.UPDATE_USUARIO.getSql())) {
            stmt.setString(1, obj.getIdentificador());
            stmt.setString(2, obj.getSenha());
            stmt.setInt(3, obj.getIdUsuario());
            System.out.println("Conexão aberta!");
            if (stmt.executeUpdate()>0) {
                retorno = 1;
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Exceção SQL - update");
        } catch (Exception e) {
            System.out.println("Exceção no código! - update");
        }
        return retorno;
    }

    @Override
    public int delete(Usuario obj) {
        int retorno = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.DELETE_USUARIO.getSql())) {
            stmt.setInt(1, obj.getIdUsuario());
            if (stmt.executeUpdate()>0) {
                retorno = 1;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exceção SQL - delete");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exceção no código! - delete");
        }
        return retorno;
    }

    @Override
    public Usuario findByID(int id) {
        Usuario obj = null;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.FINDID_USUARIO.getSql())) {
            stmt.setInt(1, id);

            System.out.println("Conexão aberta!");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String identificador = rs.getString("identificador");
                String senha = rs.getString("senha");
                obj = new Usuario(identificador, senha);
            }

        } catch (SQLException e) {
            System.out.println("Exceção SQL - findById");
        } catch (Exception e) {
            System.out.println("Exceção no código!- findById");
        }
        return obj;
    }
}
