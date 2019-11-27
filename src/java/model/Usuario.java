/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.UsuarioDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author 0489166
 */
public class Usuario implements Serializable {
    
    private int idUsuario;
    private String identificador;
    private String senha;
    
    public Usuario(){}
    
    public Usuario(int idUsuario, String identificador, String senha){
        this.idUsuario = idUsuario;
        this.identificador = identificador;
        this.senha = senha; 
    }

     public Usuario(String identificador, String senha){
        this.identificador = identificador;
        this.senha = senha; 
    }
    
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getSenha() {
        return senha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idUsuario;
        hash = 53 * hash + Objects.hashCode(this.identificador);
        hash = 53 * hash + Objects.hashCode(this.senha);
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
        final Usuario other = (Usuario) obj;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (!Objects.equals(this.identificador, other.identificador)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", identificador=" + identificador + ", senha=" + senha + '}';
    }
   
    
     public  List<Usuario> listAll() { return new UsuarioDAO().listAll();}
    public int insert() {
        return new UsuarioDAO().insert(this);
    }
    public int delete(){
        return new UsuarioDAO().delete(this);
    }
    
    public int update() {
         return new UsuarioDAO().update(this);
    }
    
}
