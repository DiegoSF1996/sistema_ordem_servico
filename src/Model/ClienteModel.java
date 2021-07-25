/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import classes.ClienteClass;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author CASA
 */
public class ClienteModel {

    private String table = "cliente";
    private final DB db = new DB();
    public ArrayList<ClienteClass> ACLIENCLASS = new ArrayList<ClienteClass>();

    public boolean insert(ClienteClass CLIENCLASS) throws SQLException {

        PreparedStatement pstmt = db.getConexao().prepareStatement("INSERT INTO " + table + " (cli_descricao, cli_endereco, cli_cep, cli_bairro, cli_email, cli_telefone, cli_pj)" + " VALUES (?,?,?,?,?,?,?)");
        if (CLIENCLASS.getCli_descricao() != null) {
            pstmt.setString(1, CLIENCLASS.getCli_descricao());
        }
        if (CLIENCLASS.getCli_endereco() != null) {
            pstmt.setString(2, CLIENCLASS.getCli_endereco());
        }
        if (CLIENCLASS.getCli_cep() != null) {
            pstmt.setString(3, CLIENCLASS.getCli_cep());
        }
        if (CLIENCLASS.getCli_bairro() != null) {
            pstmt.setString(4, CLIENCLASS.getCli_bairro());
        }
        if (CLIENCLASS.getCli_email() != null) {
            pstmt.setString(5, CLIENCLASS.getCli_email());
        }
        if (CLIENCLASS.getCli_telefone() != null) {
            pstmt.setString(6, CLIENCLASS.getCli_telefone());
        }
        if (CLIENCLASS.getCli_pj() != null) {
            pstmt.setBoolean(7, CLIENCLASS.getCli_pj());
        }
        int registros = pstmt.executeUpdate();
        pstmt.close();// fecha a db
        if (registros == 1) {
            return true;

        }
        return false;

    }

    public boolean update(ClienteClass CLIENCLASS) throws SQLException {

        String query = "UPDATE " + table + " SET ";

        if (CLIENCLASS.getCli_descricao() != null) {

            query += " cli_descricao = '" + CLIENCLASS.getCli_descricao() + "' ,";
        }
        if (CLIENCLASS.getCli_endereco() != null) {

            query += " cli_endereco = '" + CLIENCLASS.getCli_endereco() + "',";
        }
        if (CLIENCLASS.getCli_bairro() != null) {

            query += " cli_bairro = '" + CLIENCLASS.getCli_bairro() + "' ,";
        }
        if (CLIENCLASS.getCli_cep() != null) {

            query += "  cli_cep = '" + CLIENCLASS.getCli_cep() + "' ,";
        }
        if (CLIENCLASS.getCli_telefone() != null) {

            query += " cli_telefone = '" + CLIENCLASS.getCli_telefone() + "' ,";
        }
        if (CLIENCLASS.getCli_pj() != null) {

            query += " cli_pj = '" + CLIENCLASS.getCli_pj() + "' ,";
        }
        if (CLIENCLASS.getCli_email() != null) {

            query += " cli_email = '" + CLIENCLASS.getCli_email() + "'";
        }

        if (CLIENCLASS.getCli_codigo() > 0) {

            query += " where cli_codigo = " + CLIENCLASS.getCli_codigo();
        }
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);
        int registros = pstmt.executeUpdate();
        pstmt.close();// fecha a db
        if (registros == 1) {
            return true;

        }
        return false;

    }

    public List obter(ClienteClass CLIENCLASS) throws SQLException {
        String query = "select * from " + table + " where 1=1 ";
        if (CLIENCLASS.getCli_codigo() != 0) {
            query = query + " and cli_codigo = " + CLIENCLASS.getCli_codigo();
        }

        if (CLIENCLASS.getCli_descricao() != null && !CLIENCLASS.getCli_descricao().isEmpty()) {
            query = query + " and cli_descricao LIKE '%" + CLIENCLASS.getCli_descricao() + "%'";
        }
        if (CLIENCLASS.getCli_pj() != null ) {
            query = query + " and cli_pj = '" + CLIENCLASS.getCli_pj()+"'" ;
        }
        query = query + " ORDER BY cli_descricao DESC";
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);
        
        ResultSet rs = pstmt.executeQuery();

        List<ClienteClass> ACLIENCLASS = new ArrayList<ClienteClass>();
        while (rs.next()) {
            ClienteClass TPPSalvar = new ClienteClass();
            TPPSalvar.setCli_codigo(rs.getInt("cli_codigo"));
            TPPSalvar.setCli_descricao(rs.getString("cli_descricao"));
            TPPSalvar.setCli_endereco(rs.getString("cli_endereco"));
            TPPSalvar.setCli_bairro(rs.getString("cli_bairro"));
            TPPSalvar.setCli_cep(rs.getString("cli_cep"));
            TPPSalvar.setCli_telefone(rs.getString("cli_telefone"));
            TPPSalvar.setCli_email(rs.getString("cli_email"));
            TPPSalvar.setCli_pj(rs.getBoolean("cli_pj"));

            ACLIENCLASS.add(TPPSalvar);

        }

        pstmt.close();

        return ACLIENCLASS;
    }

    public void excluir(ClienteClass CLIENCLASS) throws SQLException {
        String query = "delete from " + table + " where 1=1 ";

        if (CLIENCLASS.getCli_codigo() != 0) {
            query = query + " and  cli_codigo = " + CLIENCLASS.getCli_codigo();
        }
        if (CLIENCLASS.getCli_descricao() != null) {
            query = query + " and cli_descricao = " + CLIENCLASS.getCli_descricao();
        }
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);
        pstmt.execute();
    }

    public ClienteClass obterPorPk(ClienteClass CLIENCLASS) throws SQLException {

        String query = "select * from " + table + " where 1=1 ";
        if (CLIENCLASS.getCli_codigo() != 0) {
            query = query + " and cli_codigo = " + CLIENCLASS.getCli_codigo();
        }

        PreparedStatement pstmt = db.getConexao().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();
        ClienteClass TPPSalvar = new ClienteClass();
        while (rs.next()) {
            TPPSalvar.setCli_codigo(rs.getInt("cli_codigo"));
            TPPSalvar.setCli_descricao(rs.getString("cli_descricao"));
            TPPSalvar.setCli_endereco(rs.getString("cli_endereco"));
            TPPSalvar.setCli_bairro(rs.getString("cli_bairro"));
            TPPSalvar.setCli_cep(rs.getString("cli_cep"));
            TPPSalvar.setCli_telefone(rs.getString("cli_telefone"));
            TPPSalvar.setCli_email(rs.getString("cli_email"));
            TPPSalvar.setCli_pj(false);
            if ("true".equals(rs.getString("cli_pj"))) {
                TPPSalvar.setCli_pj(true);
            }
        }
        return TPPSalvar;

    }

}
