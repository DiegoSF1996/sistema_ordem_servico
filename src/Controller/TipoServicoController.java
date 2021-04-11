/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TipoServicoModel;
import classes.TipoServicoClass;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author CASA
 */
public class TipoServicoController {

    public void salvar(TipoServicoClass TPSC) throws SQLException {

        TipoServicoModel oTppM = new TipoServicoModel();
        if (TPSC.getTps_codigo() > 0) {

            oTppM.update(TPSC);
        } else {

            oTppM.insert(TPSC);
        }
    }

    public List listarTPP(TipoServicoClass TPSC) throws SQLException {

        TipoServicoModel oTppM = new TipoServicoModel();

        return oTppM.obter(TPSC);
    }

    public void excluir(TipoServicoClass TPSC) throws SQLException {
        TipoServicoModel oTppM = new TipoServicoModel();

        oTppM.excluir(TPSC);

    }

}
