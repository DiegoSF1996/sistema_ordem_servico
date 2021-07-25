/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import helpers.ComboItem;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import Controller.ClienteController;
import Controller.ServicoController;
import Controller.TipoPagamentoController;
import Controller.TipoProdutoController;
import Controller.TipoServicoController;
import Model.ServicoModel;
import classes.ClienteClass;
import classes.ServicoClass;
import classes.TipoPagamentoClass;
import classes.TipoProdutoClass;
import classes.TipoServicoClass;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.util.converter.DoubleStringConverter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author CASA
 */
public final class cad_servico extends javax.swing.JFrame {

    private cons_servico consClien;
    private ServicoController controller;
    private ServicoClass SC;

    /**
     * Creates new form cad_servico
     */
    public cad_servico() throws SQLException {
        initComponents();
        int[] ids = new int[4];
        ids[0] = -1;
        ids[1] = -1;
        ids[2] = -1;
        ids[3] = -1;
        preenche_todos_combos(ids);

    }

    public cad_servico(ServicoClass SERVCLASS, cons_servico consServ) throws SQLException {
        initComponents();
        controller = new ServicoController();
        this.SC = SERVCLASS;

        ServicoClass oServico = controller.obterPorPk(SERVCLASS);

        ser_marca.setText(oServico.getSer_marca());
        ser_modelo.setText(oServico.getSer_modelo());
        ser_numeroserie.setText(oServico.getSer_numeroserie());
        ser_dataentrada.setText(oServico.getSer_dataentrada());
        ser_datasaida.setText(oServico.getSer_datasaida());
        ser_datapagamento.setText(oServico.getSer_datapagamento());
        ser_valorpagamento.setText(Float.toString(oServico.getSer_valorpagamento()));
        ser_observacao.setText(oServico.getSer_observacao());

        this.consClien = consServ;
        int[] ids = new int[4];
        ids[0] = oServico.getCli_codigo();
        ids[1] = oServico.getTps_codigo();
        ids[2] = oServico.getTpprod_codigo();
        ids[3] = oServico.getTpp_codigo();
        preenche_todos_combos(ids);

    }

    public void preenche_todos_combos(int ids[]) throws SQLException {
        preenche_tipo_cliente_combo();
        preenche_cli_descricao_combo(0, ids[0]);
        preenche_tps_descricao_combo(ids[1]);
        preenche_tpprod_descricao_combo(ids[2]);
        preenche_tpp_descricao_combo(ids[3]);
        //Carregar nomes de clientes ao alterar tipo
        tipo_cliente_combo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object item_tipo_cliente = tipo_cliente_combo.getSelectedItem();
                int tipo_cliente_codigo = BasisLibrary.stringToInt(((ComboItem) item_tipo_cliente).getValue());
                try {
                    preenche_cli_descricao_combo(tipo_cliente_codigo,ids[0]);
                } catch (SQLException ex) {
                    Logger.getLogger(cad_servico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void preenche_tipo_cliente_combo() throws SQLException {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement(new ComboItem("Todos", "0"));
        model.addElement(new ComboItem("Pessoa Física", "1"));
        model.addElement(new ComboItem("Pessoa Jurídica", "2"));
        model.setSelectedItem("Todos");
        tipo_cliente_combo.setModel(model);
        AutoCompleteDecorator.decorate(tipo_cliente_combo);

    }

    public void preenche_cli_descricao_combo(int cli_pj, int cli_codigo) throws SQLException {
        ClienteClass CLIENCLASS = new ClienteClass();
        ClienteController oCli = new ClienteController();
        if (cli_pj == 0) {
        }
        if (cli_pj == 1) {
            CLIENCLASS.setCli_pj(false);
        }
        if (cli_pj == 2) {
            CLIENCLASS.setCli_pj(true);
        }
        List<ClienteClass> lista_cliente = oCli.listarClien(CLIENCLASS);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        int i = 0;
        model.addElement("");
        Object obj = null;
        while (i <= lista_cliente.size() - 1) {
            Object combo = new ComboItem(lista_cliente.get(i).getCli_descricao(),
                    Integer.toString(lista_cliente.get(i).getCli_codigo()));
            model.addElement(combo);
           if (cli_codigo == lista_cliente.get(i).getCli_codigo()) {
                //model.setSelectedItem(combo);
                obj = combo;
           }
            i++;
        }
        cli_descricao_combo.setModel(model);
        cli_descricao_combo.setSelectedItem(obj);
        AutoCompleteDecorator.decorate(cli_descricao_combo);
    }

    public void preenche_tps_descricao_combo(int tps_codigo) throws SQLException {
        TipoServicoClass TPSCLASS = new TipoServicoClass();
        TipoServicoController oTPS = new TipoServicoController();

        List<TipoServicoClass> lista_tipo_servico = oTPS.listarTPP(TPSCLASS);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        int i = 0;
        model.addElement("");
        while (i <= lista_tipo_servico.size() - 1) {
            model.addElement(new ComboItem(lista_tipo_servico.get(i).getTps_descricao(),
                    Integer.toString(lista_tipo_servico.get(i).getTps_codigo())));
            if (tps_codigo == lista_tipo_servico.get(i).getTps_codigo()) {
                model.setSelectedItem(lista_tipo_servico.get(i).getTps_descricao());
            }
            i++;
        }
        tps_descricao_combo.setModel(model);
        AutoCompleteDecorator.decorate(tps_descricao_combo);

    }

    public void preenche_tpprod_descricao_combo(int tpprod_codigo) throws SQLException {
        TipoProdutoClass TPCLASS = new TipoProdutoClass();
        TipoProdutoController oTPC = new TipoProdutoController();

        List<TipoProdutoClass> lista_tipo_produto = oTPC.listarTPP(TPCLASS);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        int i = 0;
        model.addElement("");
        while (i <= lista_tipo_produto.size() - 1) {
            model.addElement(new ComboItem(lista_tipo_produto.get(i).getTpprod_descricao(),
                    Integer.toString(lista_tipo_produto.get(i).getTpprod_codigo())));
            if (tpprod_codigo == lista_tipo_produto.get(i).getTpprod_codigo()) {
                model.setSelectedItem(lista_tipo_produto.get(i).getTpprod_descricao());
            }
            i++;
        }
        tpprod_descricao_combo.setModel(model);
        AutoCompleteDecorator.decorate(tpprod_descricao_combo);
    }

    public void preenche_tpp_descricao_combo(int tpp_codigo) throws SQLException {
        TipoPagamentoClass TIPOPAGAMENTOCLASS = new TipoPagamentoClass();
        TipoPagamentoController oTP = new TipoPagamentoController();

        List<TipoPagamentoClass> lista_tipo_pagamento = oTP.listarTPP(TIPOPAGAMENTOCLASS);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        int i = 0;
        model.addElement("");
        while (i <= lista_tipo_pagamento.size() - 1) {
            model.addElement(new ComboItem(lista_tipo_pagamento.get(i).getTpp_descricao(),
                    Integer.toString(lista_tipo_pagamento.get(i).getTpp_codigo())));
            if (tpp_codigo == lista_tipo_pagamento.get(i).getTpp_codigo()) {
                model.setSelectedItem(lista_tipo_pagamento.get(i).getTpp_descricao());
            }
            i++;
        }
        tpp_descricao_combo.setModel(model);
        AutoCompleteDecorator.decorate(tpp_descricao_combo);
    }

    /**
     * This method is called from willk5thin the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ser_marca = new javax.swing.JTextField();
        ser_numeroserie = new javax.swing.JTextField();
        ser_modelo = new javax.swing.JTextField();
        ser_datapagamento = new javax.swing.JTextField();
        ser_valorpagamento = new javax.swing.JTextField();
        ser_datasaida = new javax.swing.JTextField();
        ser_dataentrada = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ser_observacao = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        ser_externo = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        cli_descricao_combo = new javax.swing.JComboBox<>();
        tps_descricao_combo = new javax.swing.JComboBox<>();
        tpprod_descricao_combo = new javax.swing.JComboBox<>();
        tpp_descricao_combo = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        tipo_cliente_combo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CADASTRO SERVIÇO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("CLIENTE");

        jLabel2.setText("TIPO DE SERVIÇO");

        jLabel3.setText("MARCA");

        jLabel4.setText("NÚMERO DE SÉRIE");

        jLabel5.setText("MODELO");

        jLabel6.setText("DATA DE ENTRADA");

        jLabel7.setText("DATA DE SAÍDA");

        jLabel8.setText("DATA PAGAMENTO");

        jLabel9.setText("FORMA DE PAGAMENTO");

        jLabel10.setText("VALOR (R$)");

        ser_marca.setName("ser_marca"); // NOI18N

        ser_numeroserie.setName("ser_numeroserie"); // NOI18N

        ser_modelo.setName("ser_modelo"); // NOI18N

        ser_datapagamento.setName("ser_datapagamento"); // NOI18N
        ser_datapagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ser_datapagamentoActionPerformed(evt);
            }
        });

        ser_valorpagamento.setName("ser_valorpagamento"); // NOI18N

        ser_datasaida.setName("ser_dstasaida"); // NOI18N

        ser_dataentrada.setName("ser_dataentrada"); // NOI18N
        ser_dataentrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ser_dataentradaMouseClicked(evt);
            }
        });
        ser_dataentrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ser_dataentradaActionPerformed(evt);
            }
        });

        jButton1.setText("SALVAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ser_observacao.setColumns(20);
        ser_observacao.setRows(5);
        ser_observacao.setName("ser_observacao"); // NOI18N
        jScrollPane1.setViewportView(ser_observacao);

        jLabel11.setText("OBSERVAÇÃO");

        ser_externo.setText("Externo");
        ser_externo.setName("ser_externo"); // NOI18N

        jLabel12.setText("TIPO DE APARELHO");

        jLabel13.setText("TIPO CLIENTE");

        tipo_cliente_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tps_descricao_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cli_descricao_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ser_numeroserie, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9)
                                            .addComponent(tpprod_descricao_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(54, 54, 54)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(ser_dataentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(ser_datasaida)
                                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(ser_datapagamento, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                                                        .addGap(47, 47, 47)
                                                        .addComponent(ser_externo)))
                                                .addGap(38, 38, 38))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel3)
                                                        .addGap(211, 211, 211))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(ser_marca)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(ser_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel5)))))
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tpp_descricao_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(ser_valorpagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel13)
                            .addComponent(tipo_cliente_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipo_cliente_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cli_descricao_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(tps_descricao_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ser_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ser_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tpprod_descricao_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ser_numeroserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ser_datasaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ser_dataentrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ser_externo))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tpp_descricao_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ser_valorpagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ser_datapagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ser_datapagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ser_datapagamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ser_datapagamentoActionPerformed

    private void ser_dataentradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ser_dataentradaActionPerformed

    }//GEN-LAST:event_ser_dataentradaActionPerformed

    private void ser_dataentradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ser_dataentradaMouseClicked

    }//GEN-LAST:event_ser_dataentradaMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        ser_dataentrada.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ServicoController oServicoController = new ServicoController();
        //JOptionPane.showMessageDialog(this, ((ComboItem) item).getValue());
        //Carrega os codigos dos combos
        Object item_cli_descricao = cli_descricao_combo.getSelectedItem();
        Object item_tps_descricao = tps_descricao_combo.getSelectedItem();
        Object item_tpprod_descricao = tpprod_descricao_combo.getSelectedItem();
        Object item_tpp_descricao = tpp_descricao_combo.getSelectedItem();

        //Cria objeto para transferir pro controller
        ServicoClass SERVICOMODELO = new ServicoClass();

        if (!cli_descricao_combo.getSelectedItem().toString().isEmpty()) {
            SERVICOMODELO.setCli_codigo(parseInt(((ComboItem) item_cli_descricao).getValue()));
        }
        if (!tps_descricao_combo.getSelectedItem().toString().isEmpty()) {
            SERVICOMODELO.setTps_codigo(parseInt(((ComboItem) item_tps_descricao).getValue()));
        } else {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!tpprod_descricao_combo.getSelectedItem().toString().isEmpty()) {
            SERVICOMODELO.setTpprod_codigo(parseInt(((ComboItem) item_tpprod_descricao).getValue()));
        }
        if (!tpp_descricao_combo.getSelectedItem().toString().isEmpty()) {
            SERVICOMODELO.setTpp_codigo(parseInt(((ComboItem) item_tpp_descricao).getValue()));
        }

        SERVICOMODELO.setSer_dataentrada(ser_dataentrada.getText());
        SERVICOMODELO.setSer_datapagamento(ser_datapagamento.getText());
        SERVICOMODELO.setSer_datasaida(ser_datasaida.getText());
        SERVICOMODELO.setSer_marca(ser_marca.getText());
        SERVICOMODELO.setSer_modelo(ser_modelo.getText());
        SERVICOMODELO.setSer_numeroserie(ser_numeroserie.getText());
        SERVICOMODELO.setSer_observacao(ser_observacao.getText());
        SERVICOMODELO.setSer_externo(false);
        if (ser_externo.isSelected()) {
            SERVICOMODELO.setSer_externo(true);
        }

        if (!ser_valorpagamento.getText().isEmpty()) {
            SERVICOMODELO.setSer_valorpagamento(Float.parseFloat(ser_valorpagamento.getText()));
        }

        try {
            oServicoController.salvar(SERVICOMODELO);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(cad_servico.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(cad_servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cad_servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cad_servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cad_servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new cad_servico().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(cad_servico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cli_descricao_combo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField ser_dataentrada;
    private javax.swing.JTextField ser_datapagamento;
    private javax.swing.JTextField ser_datasaida;
    private javax.swing.JCheckBox ser_externo;
    private javax.swing.JTextField ser_marca;
    private javax.swing.JTextField ser_modelo;
    private javax.swing.JTextField ser_numeroserie;
    private javax.swing.JTextArea ser_observacao;
    private javax.swing.JTextField ser_valorpagamento;
    private javax.swing.JComboBox<String> tipo_cliente_combo;
    private javax.swing.JComboBox<String> tpp_descricao_combo;
    private javax.swing.JComboBox<String> tpprod_descricao_combo;
    private javax.swing.JComboBox<String> tps_descricao_combo;
    // End of variables declaration//GEN-END:variables
}
