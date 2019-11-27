/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ProdutoDAO;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author 0489166
 */
public class Produto {
    
    private int idProduto;
    private long codBarras;
    private String descricao;
    private double valorUnitario;
    
    public Produto(){}

    public Produto(int idProduto, long codBarras, String descricao, double valorUnitario) {
        this.idProduto = idProduto;
        this.codBarras = codBarras;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }

        public Produto(long codBarras, String descricao, double valorUnitario) {
        this.codBarras = codBarras;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }

    
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public long getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(long codBarras) {
        this.codBarras = codBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idProduto;
        hash = 71 * hash + (int) (this.codBarras ^ (this.codBarras >>> 32));
        hash = 71 * hash + Objects.hashCode(this.descricao);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.valorUnitario) ^ (Double.doubleToLongBits(this.valorUnitario) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.idProduto != other.idProduto) {
            return false;
        }
        if (this.codBarras != other.codBarras) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorUnitario) != Double.doubleToLongBits(other.valorUnitario)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto{" + "idProduto=" + idProduto + ", codBarras=" + codBarras + ", descricao=" + descricao + ", valorUnitario=" + valorUnitario + '}';
    }
    
    public  List<Produto> listAll() { return new ProdutoDAO().listAll();}
    
    public int insert() {
        return new ProdutoDAO().insert(this);
    }
    public int delete(){
        return new ProdutoDAO().delete(this);
    }
    
    public int update() {
         return new ProdutoDAO().update(this);
    }
}
