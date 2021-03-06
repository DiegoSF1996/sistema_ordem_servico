/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import classes.TipoPagamentoClass;
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
public class TipoPagamentoModel {

    private String table = "tipo_pagamento";
    private final DB db = new DB();
    public ArrayList<TipoPagamentoClass> ATPPC = new ArrayList<TipoPagamentoClass>();

    public boolean insert(TipoPagamentoClass TPPC) throws SQLException {

        PreparedStatement pstmt = db.getConexao().prepareStatement("INSERT INTO " + table + " (tpp_descricao)" + " VALUES (?)");
        if (TPPC.getTpp_descricao() != null) {
            pstmt.setString(1, TPPC.getTpp_descricao());
        }
        int registros = pstmt.executeUpdate();
        pstmt.close();// fecha a db
        if (registros == 1) {
            return true;

        }
        return false;

    }

    public boolean update(TipoPagamentoClass TPPC) throws SQLException {

        String query = "UPDATE " + table;

        if (TPPC.getTpp_descricao() != null) {

            query += " set tpp_descricao = '" + TPPC.getTpp_descricao() + "'";
        }

        if (TPPC.getTpp_codigo() > 0) {

            query += " where tpp_codigo = " + TPPC.getTpp_codigo();
        }

        PreparedStatement pstmt = db.getConexao().prepareStatement(query);
        int registros = pstmt.executeUpdate();
        pstmt.close();// fecha a db
        if (registros == 1) {
            return true;

        }
        return false;

    }

    public List obter(TipoPagamentoClass TPPC) throws SQLException {
        String query = "select * from " + table + " where 1=1 ";
        if (TPPC.getTpp_codigo() != 0) {
            query = query + " and tpp_codigo = " + TPPC.getTpp_codigo();
        }

        if (TPPC.getTpp_descricao() != null && !TPPC.getTpp_descricao().isEmpty()) {
            query = query + " and tpp_descricao LIKE '%" + TPPC.getTpp_descricao() + "%'";
        }
        query = query + " ORDER BY tpp_descricao DESC";
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        List<TipoPagamentoClass> ATPPC = new ArrayList<TipoPagamentoClass>();
        while (rs.next()) {
            TipoPagamentoClass TPPSalvar = new TipoPagamentoClass();
            TPPSalvar.setTpp_codigo(rs.getInt("tpp_codigo"));
            TPPSalvar.setTpp_descricao(rs.getString("tpp_descricao"));
            ATPPC.add(TPPSalvar);

        }

        pstmt.close();

        return ATPPC;
    }

    public void excluir(TipoPagamentoClass TPPC) throws SQLException {
        String query = "delete from " + table + " where 1=1 ";

        if (TPPC.getTpp_codigo() != 0) {
            query = query + " and  tpp_codigo = " + TPPC.getTpp_codigo();
        }
        if (TPPC.getTpp_descricao() != null) {
            query = query + " and tpp_descricao = " + TPPC.getTpp_descricao();
        }
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);
        pstmt.execute();
    }

}
