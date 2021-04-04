/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author CASA
 */
public class TipoPagamentoClass {

    public int tpp_codigo;
    public String tpp_descricao;

    public TipoPagamentoClass() {

    }

    public TipoPagamentoClass(int tpp_codigo, String tpp_descricao) {
        this.tpp_descricao = tpp_descricao;
        this.tpp_codigo = tpp_codigo;

    }

    public TipoPagamentoClass(String tpp_descricao) {
        this.tpp_descricao = tpp_descricao;
    }

    public int getTpp_codigo() {
        return tpp_codigo;
    }

    public void setTpp_codigo(int tpp_codigo) {
        this.tpp_codigo = tpp_codigo;
    }

    public String getTpp_descricao() {
        return tpp_descricao;
    }

    public void setTpp_descricao(String tpp_descricao) {
        this.tpp_descricao = tpp_descricao;
    }

}
