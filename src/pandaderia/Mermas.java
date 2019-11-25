/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandaderia;

import Models.CorteModel;
import Models.MermaModel;
import Models.Pan;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alexis
 */
public class Mermas extends javax.swing.JFrame {

    /**
     * Creates new form Mermas
     */
    
    private Corte ventanaCorte;
    private CorteModel currentCorte;
    private ArrayList <MermaModel> currentMermas;
    private Conexion conexion;
    public DefaultTableModel modelo;
    private ArrayList <Pan> panesExistencia;
    private MermaModel selectedMerma;
    
    public Mermas() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Mermas");
    }
    public Mermas(Corte ventanaCorte,CorteModel currentModel) {
        this.ventanaCorte = ventanaCorte;
        panesExistencia = new ArrayList(0);
        initComponents();
        setLocationRelativeTo(ventanaCorte);
        setTitle("Mermas");
        conexion = new Conexion();
        this.currentCorte = currentModel;
        modelo= new DefaultTableModel();
        mermasjTable.setModel(modelo);
        modelo.addColumn("Pan");
        modelo.addColumn("Rotos");
        modelo.addColumn("Frios");
        modelo.addColumn("Comidos");
        getMermasByCorte();
        cargaCB();
    }

    public void cargaCB(){
        
                //System.out.println("aaaaa");
        try {
                PreparedStatement pst= conexion.conectar.prepareStatement("SELECT * FROM Inventario order By nombre asc");
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Pan nuevoPan = new Pan();
                    nuevoPan.setidPan(rs.getInt("id_Pan"));
                    nuevoPan.setcosto(rs.getFloat("costo"));
                    nuevoPan.setexistencia(rs.getInt("existencia"));
                    nuevoPan.setnombre(rs.getString("nombre"));
                    
                    panesExistencia.add(nuevoPan);
                    
                    CbPan.addItem(nuevoPan.getnombre());
                }                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR!");
            System.out.println(e);
        }
    }
    
    public void getMermasByCorte (){
        try {
            modelo.setRowCount(0);
            currentMermas = MermaModel.getMermasPorCorte(currentCorte, conexion.conectar);
            currentMermas.forEach(((el) -> {
                Object row []= new Object[4];
                row[0] = el.getNombrePan();
                row[1] = el.getRotos();
                row[2] = el.getFrios();
                row[3] = el.getComidos();
                System.out.println("merma --->"+el.getIdMerma()+ "del pan: "+el.getNombrePan());
                modelo.addRow(row);
            }));
        } catch (SQLException ex) {
            Logger.getLogger(Mermas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        CbPan = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        panesFriosjSpinner = new javax.swing.JSpinner();
        panesRotosjSpinner = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        panesComidosjSpinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        registraMermaJButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mermasjTable = new javax.swing.JTable();
        Salir = new javax.swing.JButton();
        eliminaSelectedMermaJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nueva Merma"));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Registra Merma por pan");

        CbPan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un pan" }));
        CbPan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbPanActionPerformed(evt);
            }
        });

        jLabel1.setText("Frios");

        panesFriosjSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        panesFriosjSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                panesFriosjSpinnerStateChanged(evt);
            }
        });

        panesRotosjSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        panesRotosjSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                panesRotosjSpinnerStateChanged(evt);
            }
        });

        jLabel3.setText("Rotos");

        panesComidosjSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        panesComidosjSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                panesComidosjSpinnerStateChanged(evt);
            }
        });

        jLabel4.setText("Comidos");

        registraMermaJButton.setText("<html><center>Registrar <br>Merma</center>");
        registraMermaJButton.setEnabled(false);
        registraMermaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registraMermaJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panesFriosjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panesRotosjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panesComidosjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CbPan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(registraMermaJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CbPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panesFriosjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panesRotosjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panesComidosjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(registraMermaJButton))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Mermas registradas al corte"));

        mermasjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Pan", "Rotos", "Frios", "Comidos"
            }
        ));
        mermasjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mermasjTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(mermasjTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Salir.setText("Volver");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        eliminaSelectedMermaJButton.setText("Eleminar seleccionado");
        eliminaSelectedMermaJButton.setEnabled(false);
        eliminaSelectedMermaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminaSelectedMermaJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eliminaSelectedMermaJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Salir)
                    .addComponent(eliminaSelectedMermaJButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        ventanaCorte.thismissVentanaMermas();
    }//GEN-LAST:event_SalirActionPerformed

    private void registraMermaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registraMermaJButtonActionPerformed
        // TODO add your handling code here:
        MermaModel nueva = new MermaModel();
        nueva.setComidos((int) panesComidosjSpinner.getValue());
        nueva.setFrios((int) panesFriosjSpinner.getValue());
        nueva.setRotos((int) panesRotosjSpinner.getValue());
        nueva.setIdCorte(currentCorte.getIdCorte());
        int SelectedPanIndex = CbPan.getSelectedIndex();
        
        nueva.setIdPan(panesExistencia.get(SelectedPanIndex-1).getidPan());
        nueva.setNombrePan(panesExistencia.get(SelectedPanIndex-1).getnombre());
        
        try {
            PreparedStatement pst = conexion.conectar.prepareStatement("insert into Merma (id_Corte,id_Pan,frios,comidos,rotos,fecha) values (?,?,?,?,?,now())",Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, nueva.getIdCorte());
            pst.setInt(2, nueva.getIdPan());
            pst.setInt(3, nueva.getFrios());
            pst.setInt(4, nueva.getComidos());
            pst.setInt(5, nueva.getRotos());
            
            pst.execute();
            ResultSet idCreated = pst.getGeneratedKeys();
            if(idCreated.next()){
                int idMerma = idCreated.getInt(1);
                nueva.setIdMerma(idMerma);
                Object row []= new Object[4];
                row[0] = nueva.getNombrePan();
                row[1] = nueva.getRotos();
                row[2] = nueva.getFrios();
                row[3] = nueva.getComidos();
                modelo.addRow(row);
                currentMermas.add(nueva);
                limpiaView();
            }else{ JOptionPane.showMessageDialog(this, "Algo salio mal, no pudo registrar la merma", "Error al Registrar", JOptionPane.ERROR_MESSAGE);}
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Mermas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_registraMermaJButtonActionPerformed

    
    public void limpiaView(){
        CbPan.setSelectedIndex(0);
        panesComidosjSpinner.setValue(0);
        panesRotosjSpinner.setValue(0);
        panesFriosjSpinner.setValue(0);
    }
    private void CbPanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbPanActionPerformed
        // TODO add your handling code here:
        validaInputs();
    }//GEN-LAST:event_CbPanActionPerformed

    public void validaInputs(){
        registraMermaJButton.setEnabled(CbPan.getSelectedIndex()>0 &&
                                        ((int) panesComidosjSpinner.getValue() > 0 ||
                                        (int) panesFriosjSpinner.getValue() > 0 ||
                                        (int) panesRotosjSpinner.getValue() > 0));
    }
    
    private void mermasjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mermasjTableMouseClicked
        // TODO add your handling code here:
        int rowSelected = mermasjTable.getSelectedRow();
        if (rowSelected>=0){
            selectedMerma = currentMermas.get(rowSelected);
            eliminaSelectedMermaJButton.setEnabled(true);
        }else{
            selectedMerma = null;
            eliminaSelectedMermaJButton.setEnabled(false);
        }
    }//GEN-LAST:event_mermasjTableMouseClicked

    private void eliminaSelectedMermaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminaSelectedMermaJButtonActionPerformed
        try {
            // TODO add your handling code here:
            PreparedStatement pst = conexion.conectar.prepareStatement("DELETE from Merma where id_Merma = ? ");
            
            pst.setInt(1, selectedMerma.getIdMerma());
            pst.execute();
            getMermasByCorte();
            eliminaSelectedMermaJButton.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(Mermas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_eliminaSelectedMermaJButtonActionPerformed

    private void panesRotosjSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_panesRotosjSpinnerStateChanged
        // TODO add your handling code here:
        validaInputs();
    }//GEN-LAST:event_panesRotosjSpinnerStateChanged

    private void panesFriosjSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_panesFriosjSpinnerStateChanged
        // TODO add your handling code here:
        validaInputs();
    }//GEN-LAST:event_panesFriosjSpinnerStateChanged

    private void panesComidosjSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_panesComidosjSpinnerStateChanged
        // TODO add your handling code here:
        validaInputs();
    }//GEN-LAST:event_panesComidosjSpinnerStateChanged

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
            java.util.logging.Logger.getLogger(Mermas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mermas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mermas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mermas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mermas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CbPan;
    private javax.swing.JButton Salir;
    private javax.swing.JButton eliminaSelectedMermaJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable mermasjTable;
    private javax.swing.JSpinner panesComidosjSpinner;
    private javax.swing.JSpinner panesFriosjSpinner;
    private javax.swing.JSpinner panesRotosjSpinner;
    private javax.swing.JButton registraMermaJButton;
    // End of variables declaration//GEN-END:variables
}
