/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import classes.TipoServicoClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CASA
 */
public class TipoServicoModel {

    private String table = "tipo_servico";
    private final DB db = new DB();
    public ArrayList<TipoServicoClass> ATPSC = new ArrayList<TipoServicoClass>();

    public boolean insert(TipoServicoClass TPSC) throws SQLException {

        PreparedStatement pstmt = db.getConexao().prepareStatement("INSERT INTO " + table + " (tps_descricao)" + " VALUES (?)");
        if (TPSC.getTps_descricao() != null) {
            pstmt.setString(1, TPSC.getTps_descricao());
        }
        int registros = pstmt.executeUpdate();
        pstmt.close();// fecha a db
        if (registros == 1) {
            return true;

        }
        return false;

    }

    public boolean update(TipoServicoClass TPSC) throws SQLException {

        String query = "UPDATE " + table;

        if (TPSC.getTps_descricao() != null) {

            query += " set getTps_descricao() = '" + TPSC.getTps_descricao() + "'";
        }

        if (TPSC.getTps_codigo() > 0) {

            query += " where tps_codigo = " + TPSC.getTps_codigo();
        }

        PreparedStatement pstmt = db.getConexao().prepareStatement(query);
        int registros = pstmt.executeUpdate();
        pstmt.close();// fecha a db
        if (registros == 1) {
            return true;

        }
        return false;

    }

    public List obter(TipoServicoClass TPSC) throws SQLException {
        String query = "select * from " + table + " where 1=1 ";
        if (TPSC.getTps_codigo() != 0) {
            query = query + " and tps_codigo = " + TPSC.getTps_codigo();
        }

        if (TPSC.getTps_descricao() != null && !TPSC.getTps_descricao().isEmpty()) {
            query = query + " and tps_descricao LIKE  '%" + TPSC.getTps_descricao() + "%'";
        }
        query = query + " ORDER BY tps_descricao DESC";
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        List<TipoServicoClass> ATPSC = new ArrayList<TipoServicoClass>();
        while (rs.next()) {
            TipoServicoClass TPPSalvar = new TipoServicoClass();
            TPPSalvar.setTps_codigo(rs.getInt("tps_codigo"));
            TPPSalvar.setTps_descricao(rs.getString("tps_descricao"));
            ATPSC.add(TPPSalvar);

        }

        pstmt.close();

        return ATPSC;
    }

    public void excluir(TipoServicoClass TPSC) throws SQLException {
        String query = "delete from " + table + " where 1=1 ";

        if (TPSC.getTps_codigo() != 0) {
            query = query + " and  tps_codigo = " + TPSC.getTps_codigo();
        }
        if (TPSC.getTps_descricao() != null) {
            query = query + " and tps_descricao = " + TPSC.getTps_descricao();
        }
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);
        pstmt.execute();
    }

}
