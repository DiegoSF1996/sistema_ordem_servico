/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import classes.TipoProdutoClass;
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
public class TipoProdutoModel {

    private String table = "tipo_produto";
    private final DB db = new DB();
    public ArrayList<TipoProdutoClass> ATPPRODC = new ArrayList<TipoProdutoClass>();

    public boolean insert(TipoProdutoClass TPPRODC) throws SQLException {

        PreparedStatement pstmt = db.getConexao().prepareStatement("INSERT INTO " + table + " (tpprod_descricao)" + " VALUES (?)");
        if (TPPRODC.tpprod_descricao != null) {
            pstmt.setString(1, TPPRODC.tpprod_descricao);
        }
        int registros = pstmt.executeUpdate();
        pstmt.close();// fecha a db
        if (registros == 1) {
            return true;

        }
        return false;

    }

    public boolean update(TipoProdutoClass TPPRODC) throws SQLException {

        String query = "UPDATE " + table;

        if (TPPRODC.tpprod_descricao != null) {

            query += " set tpprod_descricao = '" + TPPRODC.tpprod_descricao + "'";
        }

        if (TPPRODC.tpprod_codigo > 0) {

            query += " where tpprod_codigo = " + TPPRODC.tpprod_codigo;
        }

        PreparedStatement pstmt = db.getConexao().prepareStatement(query);
        int registros = pstmt.executeUpdate();
        pstmt.close();// fecha a db
        if (registros == 1) {
            return true;

        }
        return false;

    }

    public List obter(TipoProdutoClass TPPRODC) throws SQLException {
        String query = "select * from " + table + " where 1=1 ";
        if (TPPRODC.tpprod_codigo != 0) {
            query = query + " and tpprod_codigo = " + TPPRODC.tpprod_codigo;
        }

        if (TPPRODC.tpprod_descricao != null && !TPPRODC.tpprod_descricao.isEmpty()) {
            query = query + " and tpprod_descricao LIKE '%" + TPPRODC.tpprod_descricao + "%'";
        }
        query = query + " ORDER BY tpprod_descricao DESC";
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        List<TipoProdutoClass> ATPPRODC = new ArrayList<TipoProdutoClass>();
        while (rs.next()) {
            TipoProdutoClass TPPSalvar = new TipoProdutoClass();
            TPPSalvar.setTpprod_codigo(rs.getInt("tpprod_codigo"));
            TPPSalvar.setTpprod_descricao(rs.getString("tpprod_descricao"));
            ATPPRODC.add(TPPSalvar);

        }

        pstmt.close();

        return ATPPRODC;
    }

    public void excluir(TipoProdutoClass TPPRODC) throws SQLException {
        String query = "delete from " + table + " where 1=1 ";

        if (TPPRODC.tpprod_codigo != 0) {
            query = query + " and  tpprod_codigo = " + TPPRODC.tpprod_codigo;
        }
        if (TPPRODC.tpprod_descricao != null) {
            query = query + " and tpprod_descricao = " + TPPRODC.tpprod_descricao;
        }
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);
        pstmt.execute();
    }

}
