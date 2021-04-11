/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClienteModel;
import classes.ClienteClass;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author CASA
 */
public class ClienteController {

    public void salvar(ClienteClass CLIENCLASS) throws SQLException {

        ClienteModel oClienM = new ClienteModel();
        if (CLIENCLASS.getCli_codigo() > 0) {

            oClienM.update(CLIENCLASS);
        } else {

            oClienM.insert(CLIENCLASS);
        }
    }

    public List listarTPP(ClienteClass CLIENCLASS) throws SQLException {

        ClienteModel oClienM = new ClienteModel();

        return oClienM.obter(CLIENCLASS);
    }

    public void excluir(ClienteClass CLIENCLASS) throws SQLException {
        ClienteModel oClienM = new ClienteModel();

        oClienM.excluir(CLIENCLASS);

    }

}
