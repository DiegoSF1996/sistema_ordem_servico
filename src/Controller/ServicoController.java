/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ServicoModel;
import classes.ServicoClass;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author CASA
 */
public class ServicoController {

    public void salvar(ServicoClass SERVICLASS) throws SQLException, ParseException {

        ServicoModel oServM = new ServicoModel();
        if (SERVICLASS.getSer_codigo() > 0) {

            oServM.update(SERVICLASS);
        } else {

            oServM.insert(SERVICLASS);
        }
    }

    public List listarServico(ServicoClass SERVICLASS) throws SQLException {

        ServicoModel oServM = new ServicoModel();

        return oServM.obter(SERVICLASS);
    }

    public ServicoClass obterPorPk(ServicoClass SERVICLASS) throws SQLException {

        ServicoModel oServM = new ServicoModel();

        return oServM.obterPorPk(SERVICLASS);
    }

    public void excluir(ServicoClass SERVICLASS) throws SQLException {
        ServicoModel oServM = new ServicoModel();

        oServM.excluir(SERVICLASS);

    }

}
