/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import model.Produto;

/**
 *
 * @author 0489166
 */

@ManagedBean
@SessionScoped
public class ProdutoBean implements Serializable {

    private static final long serialVersionUID = 5L;

    private Produto produto = new Produto();
    private Produto produtoAntigo = new Produto();
    private List<Produto> listaProdutos;

    public ProdutoBean() {
        if (listaProdutos == null) {
            listaProdutos = produto.listAll();
        }
    }

    public void adicionar() {
        int pk = produto.insert();
        if (pk > 0) {
            produto.setIdProduto(pk);
            this.listaProdutos.add(produto);
            this.produto = new Produto();
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public List<Produto> getTodosProdutos() {
        if (listaProdutos == null) {
            listaProdutos = produto.listAll();
        }
        return listaProdutos;
    }

    public String editar(Produto produto) {
        this.produto = produto;
        this.produtoAntigo = produto;
        return "editar";
    }

    public String editarCliente() {
        if (produto.update() > 0) {
            int indice = listaProdutos.indexOf(produtoAntigo);
            listaProdutos.set(indice, produto);
            produto = new Produto();
            return "listacadastro";
        }
        return "invalido";
    }

    public String excluir(Produto produto) {
        if (produto.delete() > 0) {
            listaProdutos = produto.listAll();
            produto = new Produto();
            return "listacadastro";
        }
        return "invalido";
    }

}

    

