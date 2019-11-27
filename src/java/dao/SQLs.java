package dao;

public enum SQLs {
    INSERT_PRODUTO("insert into produto(codBarras, descricao, valorUnitario) values (?, ?, ?)"), 
    LISTALL_PRODUTO("select * from produto"),
    DELETE_PRODUTO("delete from produto where idproduto=? "),
    UPDATE_PRODUTO("update produto set codBarras=?, descricao=?, valorUnitario=?  where idproduto=?"),
    FINDID_PRODUTO("select * from produto where idproduto=?"),
    INSERT_USUARIO("insert into usuario(identificador, senha) values (?, ?)"), 
    LISTALL_USUARIO("select * from usuario"),
    DELETE_USUARIO("delete from usuario where idusuario=? "),
    UPDATE_USUARIO("update usuario set identificador=? where idusuario=?"),
    FINDID_USUARIO("select * from usuario where idusuario=?");
  
    private final String sql;
    SQLs(String sql){
        this.sql = sql; 
    
    }

    public String getSql() {
        return sql;
    }    
}

