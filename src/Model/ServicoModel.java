/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author CASA
 */
public class ServicoModel {

    private int ser_codigo;
    private int cli_codigo;
    private int tps_codigo;
    private int tpp_codigo;
    private String ser_marca;

    private String ser_numeroserie;
    private String ser_modelo;
    private Date ser_entrada;
    private Date ser_datasaida;
    private Date ser_datapagamento;
    private float ser_valorpagamento;
    private String ser_observacao;
    
  public ServicoModel( int cli_codigo, int tps_codigo, int tpp_codigo, Date ser_entrada) {
       
        this.cli_codigo = cli_codigo;
        this.tps_codigo = tps_codigo;
        this.tpp_codigo = tpp_codigo;
        this.ser_entrada = ser_entrada;
    }
  
    public int getCli_codigo() {
        return cli_codigo;
    }

    public void setCli_codigo(int cli_codigo) {
        this.cli_codigo = cli_codigo;
    }

    public int getTps_codigo() {
        return tps_codigo;
    }

    public void setTps_codigo(int tps_codigo) {
        this.tps_codigo = tps_codigo;
    }

    public int getTpp_codigo() {
        return tpp_codigo;
    }

    public void setTpp_codigo(int tpp_codigo) {
        this.tpp_codigo = tpp_codigo;
    }

    public String getSer_marca() {
        return ser_marca;
    }

    public void setSer_marca(String ser_marca) {
        this.ser_marca = ser_marca;
    }

    public String getSer_numeroserie() {
        return ser_numeroserie;
    }

    public void setSer_numeroserie(String ser_numeroserie) {
        this.ser_numeroserie = ser_numeroserie;
    }

    public String getSer_modelo() {
        return ser_modelo;
    }

    public void setSer_modelo(String ser_modelo) {
        this.ser_modelo = ser_modelo;
    }

    public Date getSer_entrada() {
        return ser_entrada;
    }

    public void setSer_entrada(Date ser_entrada) {
        this.ser_entrada = ser_entrada;
    }

    public Date getSer_datasaida() {
        return ser_datasaida;
    }

    public void setSer_datasaida(Date ser_datasaida) {
        this.ser_datasaida = ser_datasaida;
    }

    public Date getSer_datapagamento() {
        return ser_datapagamento;
    }

    public void setSer_datapagamento(Date ser_datapagamento) {
        this.ser_datapagamento = ser_datapagamento;
    }

    public float getSer_valorpagamento() {
        return ser_valorpagamento;
    }

    public void setSer_valorpagamento(float ser_valorpagamento) {
        this.ser_valorpagamento = ser_valorpagamento;
    }

    public String getSer_observacao() {
        return ser_observacao;
    }

    public void setSer_observacao(String ser_observacao) {
        this.ser_observacao = ser_observacao;
    }

  
}
