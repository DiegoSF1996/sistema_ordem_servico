/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author CASA
 */
public final class ServicoClass {

    private int ser_codigo;
    private int cli_codigo;
    private int tps_codigo;
    private int tpp_codigo;
    private int tpprod_codigo;

    private String ser_marca;
    private String ser_numeroserie;
    private String ser_modelo;
    private Boolean ser_externo;
    private String ser_dataentrada;
    private String ser_datasaida;
    private String ser_datapagamento;

    private Date ser_dataentrada_formatada;
    private Date ser_datasaida_formatada;
    private Date ser_datapagamento_formatada;

    private float ser_valorpagamento;
    private String ser_observacao;

    public ServicoClass() {

    }

    public Date formataDataBanco(String data) throws ParseException {

        if (data.isEmpty()) {
            data = "00/00/0000";
        }
        Date dataFormatada = new SimpleDateFormat("yyyy/MM/dd").parse(data);
        return dataFormatada;
    }

    public Date formataData(String data) throws ParseException {

        if (data.isEmpty()) {
            data = "00/00/0000";
        }
        Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        return dataFormatada;
    }

    public void formataTodasDatas() throws ParseException {
        setSer_dataentrada_formatada(formataData(getSer_dataentrada()));
        setSer_datasaida_formatada(formataData(getSer_datasaida()));
        setSer_datapagamento_formatada(formataData(getSer_datapagamento()));
    }

    public void formataTodasDatasBanco() throws ParseException {
        setSer_dataentrada_formatada(formataDataBanco(getSer_dataentrada()));
        setSer_datasaida_formatada(formataDataBanco(getSer_datasaida()));
        setSer_datapagamento_formatada(formataDataBanco(getSer_datapagamento()));
    }

    public Date getSer_dataentrada_formatada() {
        return ser_dataentrada_formatada;
    }

    public void setSer_dataentrada_formatada(Date ser_dataentrada_formatada) {
        this.ser_dataentrada_formatada = ser_dataentrada_formatada;
    }

    public Date getSer_datasaida_formatada() {
        return ser_datasaida_formatada;
    }

    public void setSer_datasaida_formatada(Date ser_datasaida_formatada) {
        this.ser_datasaida_formatada = ser_datasaida_formatada;
    }

    public Date getSer_datapagamento_formatada() {
        return ser_datapagamento_formatada;
    }

    public void setSer_datapagamento_formatada(Date ser_datapagamento_formatada) {
        this.ser_datapagamento_formatada = ser_datapagamento_formatada;
    }

    public Boolean getSer_externo() {
        return ser_externo;
    }

    public void setSer_externo(Boolean ser_externo) {
        this.ser_externo = ser_externo;
    }

    public int getSer_codigo() {
        return ser_codigo;
    }

    public void setSer_codigo(int ser_codigo) {
        this.ser_codigo = ser_codigo;
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

    public int getTpprod_codigo() {
        return tpprod_codigo;
    }

    public void setTpprod_codigo(int tpprod_codigo) {
        this.tpprod_codigo = tpprod_codigo;
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

    public String getSer_dataentrada() {
        return ser_dataentrada;
    }

    public void setSer_dataentrada(String ser_dataentrada) {
        this.ser_dataentrada = ser_dataentrada;
    }

    public String getSer_datasaida() {
        return ser_datasaida;
    }

    public void setSer_datasaida(String ser_datasaida) {
        this.ser_datasaida = ser_datasaida;
    }

    public String getSer_datapagamento() {
        return ser_datapagamento;
    }

    public void setSer_datapagamento(String ser_datapagamento) {
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
