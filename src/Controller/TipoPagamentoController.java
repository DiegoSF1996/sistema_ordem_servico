/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TipoPagamentoModel;
import View.cad_tipo_pagamento;
import View.cons_tipo_pagamento;
import classes.TipoPagamentoClass;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CASA
 */
public class TipoPagamentoController {

    public void salvar(TipoPagamentoClass TPPC) throws SQLException {

        TipoPagamentoModel oTppM = new TipoPagamentoModel();
        oTppM.insert(TPPC);
    }

    public List listarTPP(TipoPagamentoClass TPPC) throws SQLException {

        TipoPagamentoModel oTppM = new TipoPagamentoModel();

        return oTppM.obter(TPPC);
    }

    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void excluir(TipoPagamentoClass TPPC) throws SQLException {
        TipoPagamentoModel oTppM = new TipoPagamentoModel();

        oTppM.excluir(TPPC);

    }

}
