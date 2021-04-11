/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TipoProdutoModel;
import View.cad_tipo_pagamento;
import View.cons_tipo_pagamento;
import classes.TipoProdutoClass;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CASA
 */
public class TipoProdutoController {

    public void salvar(TipoProdutoClass TPPRODC) throws SQLException {

        TipoProdutoModel oTppM = new TipoProdutoModel();
        if (TPPRODC.getTpprod_codigo() > 0) {

            oTppM.update(TPPRODC);
        } else {

            oTppM.insert(TPPRODC);
        }
    }

    public List listarTPP(TipoProdutoClass TPPRODC) throws SQLException {

        TipoProdutoModel oTppM = new TipoProdutoModel();

        return oTppM.obter(TPPRODC);
    }

    public void excluir(TipoProdutoClass TPPRODC) throws SQLException {
        TipoProdutoModel oTppM = new TipoProdutoModel();

        oTppM.excluir(TPPRODC);

    }

}
