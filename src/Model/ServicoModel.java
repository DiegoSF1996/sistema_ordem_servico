/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import classes.ServicoClass;
import java.sql.Array;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author CASA
 */
public class ServicoModel {

    private String table = "servico";
    private final DB db = new DB();
    public ArrayList<ServicoClass> ASERVICLASS = new ArrayList<ServicoClass>();

    public boolean insert(ServicoClass SERVICLASS) throws SQLException, ParseException {

        PreparedStatement pstmt = db.getConexao().prepareStatement("INSERT INTO " + table + ""
                + " (cli_codigo, tps_codigo, tpp_codigo, tpprod_codigo, ser_marca, "
                + "ser_numeroserie, ser_modelo, ser_dataentrada, ser_datasaida, ser_datapagamento, "
                + "ser_valorpagamento, ser_observacao, ser_externo)" + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

        if (SERVICLASS.getCli_codigo() > 0) {
            pstmt.setInt(1, SERVICLASS.getCli_codigo());
        }
        if (SERVICLASS.getTps_codigo() != 0) {
            pstmt.setInt(2, SERVICLASS.getTps_codigo());
        } else {
        }
        if (SERVICLASS.getTpp_codigo() > 0) {
            pstmt.setInt(3, SERVICLASS.getTpp_codigo());
        }
        if (SERVICLASS.getTpprod_codigo() > 0) {
            pstmt.setInt(4, SERVICLASS.getTpprod_codigo());
        }
        if (SERVICLASS.getSer_marca() != null) {
            pstmt.setString(5, SERVICLASS.getSer_marca());
        }
        if (SERVICLASS.getSer_numeroserie() != null) {
            pstmt.setString(6, SERVICLASS.getSer_numeroserie());
        }
        if (SERVICLASS.getSer_modelo() != null) {
            pstmt.setString(7, SERVICLASS.getSer_modelo());
        }
        if (SERVICLASS.getSer_dataentrada() != null) {
            pstmt.setString(8, SERVICLASS.getSer_dataentrada());
        }
        if (SERVICLASS.getSer_datasaida() != null) {
            pstmt.setString(9, SERVICLASS.getSer_datasaida());
        }
        if (SERVICLASS.getSer_datapagamento() != null) {
            pstmt.setString(10, SERVICLASS.getSer_datapagamento());
        }
        if (SERVICLASS.getSer_valorpagamento() >= 0) {
            pstmt.setFloat(11, SERVICLASS.getSer_valorpagamento());
        }
        if (SERVICLASS.getSer_observacao() != null) {
            pstmt.setString(12, SERVICLASS.getSer_observacao());
        }
        if (SERVICLASS.getSer_externo() != null) {
            pstmt.setBoolean(13, SERVICLASS.getSer_externo());
        }
        System.out.println(SERVICLASS.getTps_codigo());
        int registros = pstmt.executeUpdate();
        pstmt.close();// fecha a db
        if (registros == 1) {
            return true;

        }
        return false;

    }

    public boolean update(ServicoClass SERVICLASS) throws SQLException {

        String query = "UPDATE " + table + " SET ";

       
        if (SERVICLASS.getCli_codigo() > 0) {

            query += "cli_codigo = '" + SERVICLASS.getCli_codigo() + "' ,";
        }
        if (SERVICLASS.getTps_codigo() > 0) {

            query += "tps_codigo = '" + SERVICLASS.getTps_codigo() + "' ,";
        }
        if (SERVICLASS.getTpp_codigo() > 0) {

            query += "tpp_codigo = '" + SERVICLASS.getTpp_codigo() + "' ,";
        }
        if (SERVICLASS.getTpprod_codigo() > 0) {

            query += "tpprod_codigo = '" + SERVICLASS.getTpprod_codigo() + "' ,";
        }
        if (SERVICLASS.getSer_marca() != null) {

            query += "ser_marca = '" + SERVICLASS.getSer_marca() + "' ,";
        }
        if (SERVICLASS.getSer_numeroserie() != null) {

            query += "ser_numeroserie = '" + SERVICLASS.getSer_numeroserie() + "' ,";
        }
        if (SERVICLASS.getSer_modelo() != null) {

            query += "ser_modelo = '" + SERVICLASS.getSer_modelo() + "' ,";
        }
        if (SERVICLASS.getSer_externo() != null) {
            int marcado = 0;
            if(SERVICLASS.getSer_externo() == true ){
                marcado = 1;
            }
            query += "ser_externo = " + marcado + " ,";
        }
        if (SERVICLASS.getSer_dataentrada() != null) {

            query += "ser_dataentrada = '" + SERVICLASS.getSer_dataentrada() + "' ,";
        }
        if (SERVICLASS.getSer_datasaida() != null) {

            query += "ser_datasaida = '" + SERVICLASS.getSer_datasaida() + "' ,";
        }
        if (SERVICLASS.getSer_datapagamento() != null) {

            query += "ser_datapagamento = '" + SERVICLASS.getSer_datapagamento() + "' ,";
        }

        if (SERVICLASS.getSer_dataentrada_formatada() != null) {

            query += "ser_dataentrada_formatada = '" + SERVICLASS.getSer_dataentrada_formatada() + "' ,";
        }
        if (SERVICLASS.getSer_datasaida_formatada() != null) {

            query += "ser_datasaida_formatada = '" + SERVICLASS.getSer_datasaida_formatada() + "' ,";
        }
        if (SERVICLASS.getSer_datapagamento_formatada() != null) {

            query += "ser_datapagamento_formatada = '" + SERVICLASS.getSer_datapagamento_formatada() + "' ,";
        }

        if (SERVICLASS.getSer_valorpagamento() > 0) {

            query += "ser_valorpagamento = '" + SERVICLASS.getSer_valorpagamento() + "' ,";
        }
        if (SERVICLASS.getSer_observacao() != null) {

            query += "ser_observacao = '" + SERVICLASS.getSer_observacao() + "'";
        }
         if (SERVICLASS.getSer_codigo() > 0) {

            query += " where ser_codigo = " + SERVICLASS.getSer_codigo();
        }
         System.out.println(query);
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);
        int registros = pstmt.executeUpdate();
        pstmt.close();// fecha a db
        if (registros == 1) {
            return true;

        }
        return false;

    }

    public List obter(ServicoClass SERVICLASS) throws SQLException {
        String query = "select * from " + table + " where 1=1 ";
        if (SERVICLASS.getCli_codigo() != 0) {
            query = query + " and cli_codigo = " + SERVICLASS.getCli_codigo();
        }

        query = query + " ORDER BY ser_codigo DESC ";
        System.out.println(query);
        PreparedStatement pstmt = db.getConexao().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();

        List<ServicoClass> ASERVICLASS = new ArrayList<ServicoClass>();
        while (rs.next()) {
            ServicoClass TPPSalvar = new ServicoClass();
            TPPSalvar.setSer_codigo(rs.getInt("ser_codigo"));
            TPPSalvar.setCli_codigo(rs.getInt("cli_codigo"));
            TPPSalvar.setTps_codigo(rs.getInt("tps_codigo"));
            TPPSalvar.setTpp_codigo(rs.getInt("tpp_codigo"));
            TPPSalvar.setSer_marca(rs.getString("ser_marca"));
            TPPSalvar.setSer_numeroserie(rs.getString("ser_numeroserie"));
            TPPSalvar.setSer_modelo(rs.getString("ser_modelo"));
            TPPSalvar.setSer_dataentrada(rs.getString("ser_dataentrada"));
            TPPSalvar.setSer_datasaida(rs.getString("ser_datasaida"));
            TPPSalvar.setSer_datapagamento(rs.getString("ser_datapagamento"));
            TPPSalvar.setSer_valorpagamento(rs.getFloat("ser_valorpagamento"));
            TPPSalvar.setSer_externo(rs.getBoolean("ser_externo"));
            TPPSalvar.setSer_observacao(rs.getString("ser_observacao"));
            TPPSalvar.setTpprod_codigo(rs.getInt("tpprod_codigo"));
            ASERVICLASS.add(TPPSalvar);

        }

        pstmt.close();

        return ASERVICLASS;
    }

    public void excluir(ServicoClass SERVICLASS) throws SQLException {
        String query = "delete from " + table + " where 1=1 ";

        if (SERVICLASS.getSer_codigo() != 0) {
            query = query + " and  ser_codigo = " + SERVICLASS.getSer_codigo();
        }

        PreparedStatement pstmt = db.getConexao().prepareStatement(query);
        pstmt.execute();
    }

    public ServicoClass obterPorPk(ServicoClass SERVICLASS) throws SQLException {

        String query = "select * from " + table + " where 1=1 ";
        if (SERVICLASS.getCli_codigo() != 0) {
            query = query + " and ser_codigo = " + SERVICLASS.getSer_codigo();
        }

        PreparedStatement pstmt = db.getConexao().prepareStatement(query);

        ResultSet rs = pstmt.executeQuery();
        ServicoClass TPPSalvar = new ServicoClass();
        while (rs.next()) {
            TPPSalvar.setSer_codigo(rs.getInt("ser_codigo"));
            TPPSalvar.setCli_codigo(rs.getInt("cli_codigo"));
            TPPSalvar.setTps_codigo(rs.getInt("tps_codigo"));
            TPPSalvar.setTpp_codigo(rs.getInt("tpp_codigo"));
            TPPSalvar.setSer_marca(rs.getString("ser_marca"));
            TPPSalvar.setSer_numeroserie(rs.getString("ser_numeroserie"));
            TPPSalvar.setSer_modelo(rs.getString("ser_modelo"));
            TPPSalvar.setSer_dataentrada(rs.getString("ser_dataentrada"));
            TPPSalvar.setSer_datasaida(rs.getString("ser_datasaida"));
            TPPSalvar.setSer_datapagamento(rs.getString("ser_datapagamento"));
            TPPSalvar.setSer_valorpagamento(rs.getFloat("ser_valorpagamento"));
            TPPSalvar.setSer_externo(rs.getBoolean("ser_externo"));
            
            System.out.println("adasdsadasdasdasdasdsad " +rs.getInt("ser_externo"));
            TPPSalvar.setSer_observacao(rs.getString("ser_observacao"));
            TPPSalvar.setTpprod_codigo(rs.getInt("tpprod_codigo"));
            ASERVICLASS.add(TPPSalvar);

        }
        return TPPSalvar;

    }

}
