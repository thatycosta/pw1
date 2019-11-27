/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.LinkedList;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import model.Usuario;

/**
 *
 * @author 0489166
 */
@ManagedBean
@SessionScoped
 public class UsuarioBean implements Serializable{
    private Usuario usuarioLogado = new Usuario();
    private Usuario usuario = new Usuario();
    private Usuario usuarioAntigo = new Usuario();
    private LinkedList<Usuario> usuarios = new LinkedList();
    
    public UsuarioBean() {
		this.usuarios.add(new Usuario("04577", "123"));
		this.usuarios.add(new Usuario("05779", "44"));
	}
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    
    public LinkedList<Usuario> getUsuarios() {
        return usuarios;
    }

   
    public String adicionar() {
            usuarios.add(usuario);
            this.usuario = new Usuario();
            for (Usuario usuario1 : usuarios) {
                System.out.println("=>"+usuario1.toString());
            }
            return "todosusuarios";
    }

    public String excluir(Usuario user) {
        usuarios.remove(user);
        usuario = new Usuario();
        return "todosusuarios";
    }

    public String editar(Usuario user) {
        this.usuario = user;
        this.usuarioAntigo = user;
        return "editarusuario";
    }

    public String editarUsuario(){
            int indice = usuarios.indexOf(usuarioAntigo);
            usuarios.set(indice, usuario);
             usuario = new Usuario();
            return "todosusuarios";
        }
    public String validar() {
       //finalizar validacao - nao esquecer **
            return "todosusuarios";
      
    }
    
    
}
