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
import model.Produto;

/**
 *
 * @author 0489166
 */
public class ProdutoDAO implements GenericDAO<Produto>{

    @Override
    public int insert(Produto obj) {
      int chavePrimaria = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.INSERT_PRODUTO.getSql(),
                        Statement.RETURN_GENERATED_KEYS)) {
            System.out.println("Conexão aberta!");
            stmt.setLong(1, obj.getCodBarras());
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(1, obj.getValorUnitario());
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
    public List<Produto> listAll() {
          List<Produto> lista = new LinkedList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.LISTALL_PRODUTO.getSql())) {

            System.out.println("Conexão aberta!");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idproduto = rs.getInt("idproduto");
                long codBarras = rs.getLong("codBarras");
                String descricao = rs.getString("descricao");
                double valorUnitario = rs.getDouble("valorUnitario");
                lista.add(new Produto(idproduto, codBarras, descricao, valorUnitario));
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
    public int update(Produto obj) {
        int retorno = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.UPDATE_PRODUTO.getSql())) {
            stmt.setLong(1, obj.getCodBarras());
            stmt.setString(2, obj.getDescricao());
            stmt.setDouble(3, obj.getValorUnitario());
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
    public int delete(Produto obj) {
        int retorno = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.DELETE_PRODUTO.getSql())) {
            stmt.setInt(1, obj.getIdProduto());
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
    public Produto findByID(int id) {
         Produto obj = null;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.FINDID_PRODUTO.getSql())) {
            stmt.setInt(1, id);

            System.out.println("Conexão aberta!");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                long codBarras = rs.getLong("codBarras");
                String descricao = rs.getString("descricao");
                double valorUnitario = rs.getDouble("valorUnitario");
                obj = new Produto(codBarras, descricao, valorUnitario);
            }

        } catch (SQLException e) {
            System.out.println("Exceção SQL - findById");
        } catch (Exception e) {
            System.out.println("Exceção no código!- findById");
        }
        return obj;
    }
}
