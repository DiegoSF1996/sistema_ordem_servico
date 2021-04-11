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
public class TipoServicoClass {

    public int tps_codigo;
    public String tps_descricao;

    public TipoServicoClass(String tps_descricao) {
        this.setTps_descricao(tps_descricao);
    }

    public TipoServicoClass() {

    }

    public int getTps_codigo() {
        return tps_codigo;
    }

    public void setTps_codigo(int tps_codigo) {
        this.tps_codigo = tps_codigo;
    }

    public String getTps_descricao() {
        return tps_descricao;
    }

    public void setTps_descricao(String tps_descricao) {
        this.tps_descricao = tps_descricao;
    }

}
