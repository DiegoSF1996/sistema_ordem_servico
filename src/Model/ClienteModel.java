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
        if (CLIENCLASS.cli_descricao != null) {
            pstmt.setString(1, CLIENCLASS.cli_descricao);
        }
        if (CLIENCLASS.cli_endereco != null) {
            pstmt.setString(2, CLIENCLASS.cli_endereco);
        }
        if (CLIENCLASS.cli_cep != null) {
            pstmt.setString(3, CLIENCLASS.cli_cep);
        }
        if (CLIENCLASS.cli_bairro != null) {
            pstmt.setString(4, CLIENCLASS.cli_bairro);
        }
        if (CLIENCLASS.cli_email != null) {
            pstmt.setString(5, CLIENCLASS.cli_email);
        }
        if (CLIENCLASS.cli_telefone != null) {
            pstmt.setString(6, CLIENCLASS.cli_telefone);
        }
        if (CLIENCLASS.cli_pj != null) {
            pstmt.setBoolean(7, CLIENCLASS.cli_pj);
        }
        int registros = pstmt.executeUpdate();
        pstmt.close();// fecha a db
        if (registros == 1) {
            return true;

        }
        return false;

    }

    public boolean update(ClienteClass CLIENCLASS) throws SQLException {

        String query = "UPDATE " + table + "SET ";

        if (CLIENCLASS.cli_descricao != null) {

            query += " cli_descricao = '" + CLIENCLASS.cli_descricao + "' ,";
        }
        if (CLIENCLASS.cli_endereco != null) {

            query += " cli_endereco = '" + CLIENCLASS.cli_endereco + "',";
        }
        if (CLIENCLASS.cli_bairro != null) {

            query += " cli_bairro = '" + CLIENCLASS.cli_bairro + "',";
        }
        if (CLIENCLASS.cli_cep != null) {

            query += "  cli_cep = '" + CLIENCLASS.cli_cep + "',";
        }
        if (CLIENCLASS.cli_telefone != null) {

            query += " cli_telefone = '" + CLIENCLASS.cli_telefone + "',";
        }
        if (CLIENCLASS.cli_pj != null) {

            query += " cli_pj = '" + CLIENCLASS.cli_pj + "',";
        }
        if (CLIENCLASS.cli_email != null) {

            query += " cli_email = '" + CLIENCLASS.cli_email + "'";
        }

        if (CLIENCLASS.cli_codigo > 0) {

            query += " where cli_codigo = " + CLIENCLASS.cli_codigo;
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
        if (CLIENCLASS.cli_codigo != 0) {
            query = query + " and cli_codigo = " + CLIENCLASS.cli_codigo;
        }

        if (CLIENCLASS.cli_descricao != null && !CLIENCLASS.cli_descricao.isEmpty()) {
            query = query + " and cli_descricao LIKE '%" + CLIENCLASS.cli_descricao + "%'";
        }
        query = query + " ORDER BY cli_descricao ";
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        List<ClienteClass> ACLIENCLASS = new ArrayList<ClienteClass>();
        while (rs.next()) {
            ClienteClass TPPSalvar = new ClienteClass();
            TPPSalvar.setCli_codigo(rs.getInt("cli_codigo"));
            TPPSalvar.setCli_descricao(rs.getString("cli_descricao"));
            ACLIENCLASS.add(TPPSalvar);

        }

        pstmt.close();

        return ACLIENCLASS;
    }

    public void excluir(ClienteClass CLIENCLASS) throws SQLException {
        String query = "delete from " + table + " where 1=1 ";

        if (CLIENCLASS.cli_codigo != 0) {
            query = query + " and  cli_codigo = " + CLIENCLASS.cli_codigo;
        }
        if (CLIENCLASS.cli_descricao != null) {
            query = query + " and cli_descricao = " + CLIENCLASS.cli_descricao;
        }
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);
        pstmt.execute();
    }

}
