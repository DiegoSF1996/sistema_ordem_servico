/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author CASA
 */
public class DB {

    private static Connection conexao;

    public DB() {
        try {

            Class.forName("org.sqlite.JDBC");
            
            String url = "jdbc:sqlite:db/ordem_servico.db";

            conexao = DriverManager.getConnection(url);
            System.out.println("conexao ok");
            //JOptionPane.showMessageDialog(null,"conexao ok");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,
                    "Ocorreu um erro de conexão. Verifique a Base de Dados indicada !" + "\n" + erro.getMessage(), "Conexão", 3);
            erro.printStackTrace();
        }

    }

    // Mtodos pblicos:
    public Connection getConexao() {
        return conexao;

    }

}
