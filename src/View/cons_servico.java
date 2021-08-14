/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ClienteController;
import Controller.ServicoController;
import Controller.TipoServicoController;
import classes.ClienteClass;
import classes.ServicoClass;
import classes.TipoServicoClass;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import helpers.ComboItem;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableHeader;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import helpers.FileChooserTest;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 *
 * @author CASA
 */
public class cons_servico extends javax.swing.JFrame {

    /**
     * Creates new form cons_servico
     */
    private final ServicoController controller;
    private final ClienteController CliController = new ClienteController();

    public cons_servico() throws SQLException {
        initComponents();

        controller = new ServicoController();
        preenche_tipo_cliente_combo();
        preenche_tps_descricao_combo();

        TableColumnModel columnModel = jTable1.getColumnModel();
        /* columnModel.getColumn(0).setPreferredWidth(80);
        columnModel.getColumn(0).setMaxWidth(80); */

        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(1).setMaxWidth(Integer.MAX_VALUE);
    }

    public void preenche_tipo_cliente_combo() throws SQLException {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement(new ComboItem("Todos", "0"));
        model.addElement(new ComboItem("Pessoa Física", "1"));
        model.addElement(new ComboItem("Pessoa Jurídica", "2"));

        tipo_cliente_combo.setModel(model);
        AutoCompleteDecorator.decorate(tipo_cliente_combo);
    }

    public void preenche_tps_descricao_combo() throws SQLException {
        TipoServicoClass TPSCLASS = new TipoServicoClass();
        TipoServicoController oTPS = new TipoServicoController();

        List<TipoServicoClass> lista_tipo_servico = oTPS.listarTPP(TPSCLASS);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        int i = 0;
        model.addElement("");
        while (i <= lista_tipo_servico.size() - 1) {
            model.addElement(new ComboItem(lista_tipo_servico.get(i).getTps_descricao(),
                    Integer.toString(lista_tipo_servico.get(i).getTps_codigo())));

            i++;
        }
        tps_descricao_combo.setModel(model);
        AutoCompleteDecorator.decorate(tps_descricao_combo);

    }

    public void listarTabela() throws SQLException {
        ClienteClass CliClass = new ClienteClass();

        ServicoClass ServicoClass = new ServicoClass();
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setNumRows(0);
        List<ServicoClass> list = controller.listarServico(ServicoClass);
        for (ServicoClass serv : list) {
            CliClass.setCli_codigo(serv.getCli_codigo());
            ClienteClass CliClassController = CliController.obterPorPk(CliClass);
            tableModel.addRow(new Object[]{serv.getSer_codigo(),
                CliClassController.getCli_descricao(),
                serv.getSer_marca(),
                serv.getSer_modelo()});
        }

        jTable1.setModel(tableModel);
    }

    public void listarTabela(ServicoClass ServicoClass) throws SQLException {
        ClienteClass CliClass = new ClienteClass();
        ClienteClass CliClassController = new ClienteClass();
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setNumRows(0);
        List<ServicoClass> list = controller.listarServico(ServicoClass);
        for (ServicoClass serv : list) {
            System.out.println(serv.getSer_codigo());
            tableModel.addRow(new Object[]{serv.getSer_codigo(),
                serv.getCli_descricao(),
                serv.getSer_marca(),
                serv.getSer_modelo()});
        }

        jTable1.setModel(tableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cli_descricao_combo = new javax.swing.JTextField();
        tipo_cliente_combo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tps_descricao_combo = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTA SERVIÇO");

        tipo_cliente_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_cliente_comboActionPerformed(evt);
            }
        });

        jLabel1.setText("TIPO CLIENTE");

        jLabel2.setText("DESCRIÇÃO CLIENTE");

        jLabel3.setText("TIPO DE SERVIÇO");

        jButton2.setText("PESQUISAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "CLIENTE", "MARCA", "MODELO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTable1ComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("ALTERAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("APAGAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("IMPRIMIR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("NOVO");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(cli_descricao_combo)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(tipo_cliente_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(tps_descricao_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipo_cliente_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tps_descricao_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cli_descricao_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable1ComponentShown

        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1ComponentShown

    private void tipo_cliente_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_cliente_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_cliente_comboActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:

            ServicoClass ServicoClass = new ServicoClass();
            if (tipo_cliente_combo.getSelectedItem().toString().isEmpty()) {
                Object item_tps_descricao = tps_descricao_combo.getSelectedItem();
                ServicoClass.setTps_codigo(parseInt(((ComboItem) item_tps_descricao).getValue()));
            }
            if (!tps_descricao_combo.getSelectedItem().toString().isEmpty()) {

            }
            if (!cli_descricao_combo.getText().isEmpty()) {
                ServicoClass.setCli_descricao(cli_descricao_combo.getText());
            }
            this.listarTabela(ServicoClass);
        } catch (SQLException ex) {
            Logger.getLogger(cons_servico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        int i = jTable1.getSelectedRow();
        if (i >= 0) {
            ServicoClass SERVCLASS = new ServicoClass();
            SERVCLASS.setSer_codigo((int) tableModel.getValueAt(i, 0));

            cad_servico cadSer = null;
            try {
                cadSer = new cad_servico(SERVCLASS, this);
                cadSer.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(cons_cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // TODO add your handling code h
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        int i = jTable1.getSelectedRow();
        if (i >= 0) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Atenção", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {

                ServicoClass SERVICLASS = new ServicoClass();
                SERVICLASS.setSer_codigo((int) tableModel.getValueAt(i, 0));

                try {
                    controller.excluir(SERVICLASS);
                } catch (SQLException ex) {
                    Logger.getLogger(cons_servico.class.getName()).log(Level.SEVERE, null, ex);
                }

                tableModel.removeRow(i);
            }

        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //Pega os dados
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        int i = jTable1.getSelectedRow();
        if (i >= 0) {
            ServicoClass SERVCLASS = new ServicoClass();
            SERVCLASS.setSer_codigo((int) tableModel.getValueAt(i, 0));

            ServicoController oServCon = new ServicoController();
            try {
                SERVCLASS = oServCon.obterPorPk(SERVCLASS);
            } catch (SQLException ex) {
                Logger.getLogger(cons_servico.class.getName()).log(Level.SEVERE, null, ex);
            }

            String filename = null, dir = null;
            JFileChooser c = new JFileChooser();
            // Demonstrate "Open" dialog:
            int rVal = c.showSaveDialog(cons_servico.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename = c.getSelectedFile().getName();
                if (!filename.endsWith(".pdf")) {
                    filename += ".pdf";
                }
                dir = c.getCurrentDirectory().toString();
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename = null;
                dir = null;
            }
            Document doc = new Document();
            doc.setPageSize(PageSize.A4);
            doc.addSubject("");
            doc.addKeywords("Ordem de seriço");
            doc.addCreator("Ordem de seriço");
            doc.addAuthor("Admin");
            if (filename != null && dir != null) {

                try {
                    PdfWriter.getInstance(doc, new FileOutputStream(dir + "\\" + filename));
                    doc.open();
                    doc.add(new Paragraph( new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()))));

                    Paragraph p = new Paragraph("ORDEM DE SERVIÇO");
                    Chapter chapter1 = new Chapter(p, 1);
                    p.setAlignment(1);
                    p.setPaddingTop(60);
                    doc.add(p);

                    LineSeparator ls = new LineSeparator();
                    doc.add(new Chunk(ls));

                    PdfPTable tablee = new PdfPTable(1);
                    tablee.setWidthPercentage(100);
                    tablee.setSpacingAfter(6);
                    tablee.setSpacingBefore(6);
                    PdfPCell cell11 = new PdfPCell(new Paragraph("INFORMAÇÕES DO CLIENTE"));
                    tablee.addCell(cell11);
                    doc.add(tablee);

                    doc.add(new Paragraph("Nome: " + SERVCLASS.getCli_descricao()));
                    doc.add(new Paragraph("Tipo de Cliente: " + (SERVCLASS.getCli_pj() == true ? "PJ" : "PF")));
                    //----
                    tablee.deleteLastRow();
                    cell11 = new PdfPCell(new Paragraph("INFORMAÇÕES DO PRODUTO"));
                    tablee.addCell(cell11);
                    doc.add(tablee);
                    doc.add(new Paragraph("Tipo Aparelho: " + SERVCLASS.getTpprod_descricao()));
                    doc.add(new Paragraph("Modelo: " + SERVCLASS.getSer_modelo()));
                    doc.add(new Paragraph("Marca: " + SERVCLASS.getSer_marca()));
                    doc.add(new Paragraph("Número de Série: " + SERVCLASS.getSer_numeroserie()));
                    //----
                    tablee.deleteLastRow();
                    cell11 = new PdfPCell(new Paragraph("INFORMAÇÕES DO SERVIÇO"));
                    tablee.addCell(cell11);
                    doc.add(tablee);
                    doc.add(new Paragraph("Serviço Externo: " + (SERVCLASS.getSer_externo() == true ? "SIM" : "Não")));
                    doc.add(new Paragraph("Tipo Serviço: " + SERVCLASS.getTps_descricao()));
                    doc.add(new Paragraph("Data de Entrada: " + SERVCLASS.getSer_dataentrada()));
                    doc.add(new Paragraph("Data de Saída: " + SERVCLASS.getSer_datasaida()));
                    doc.add(new Paragraph("Observação: " + SERVCLASS.getSer_observacao()));
                    //----
                    tablee.deleteLastRow();
                    cell11 = new PdfPCell(new Paragraph("INFORMAÇÕES DE PAGAMENTO"));
                    tablee.addCell(cell11);
                    doc.add(tablee);

                    doc.add(new Paragraph("Tipo Pagamento: " + SERVCLASS.getTpp_descricao()));

                    doc.add(new Paragraph("Valor Pagamento: " + SERVCLASS.getSer_valorpagamento()));

                    doc.add(new Paragraph("Data Pagamento: " + SERVCLASS.getSer_datapagamento()));

                    /* PdfPTable table = new PdfPTable(3);
                    PdfPCell cell1 = new PdfPCell(new Paragraph("RG"));
                    PdfPCell cell2 = new PdfPCell(new Paragraph("Nome"));
                    PdfPCell cell3 = new PdfPCell(new Paragraph("Idade"));

                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);

                    cell1 = new PdfPCell(new Paragraph("1207591955"));
                    cell2 = new PdfPCell(new Paragraph("Diego"));
                    cell3 = new PdfPCell(new Paragraph("25"));
                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);

                    doc.add(table); */
                    
                    
                    doc.close();
                    Desktop.getDesktop().open(new File(dir + "\\" + filename));
                } catch (DocumentException de) {
                    System.err.println(de.getMessage());
                } catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        cad_servico cad_servico = null;
        try {
            cad_servico = new cad_servico();
            cad_servico.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(cons_servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cons_servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cons_servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cons_servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new cons_servico().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(cons_servico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cli_descricao_combo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> tipo_cliente_combo;
    private javax.swing.JComboBox<String> tps_descricao_combo;
    // End of variables declaration//GEN-END:variables
}
