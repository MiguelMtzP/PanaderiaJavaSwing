package pandaderia;
import Models.Empleado;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alexis
 */
public class Inventario extends javax.swing.JFrame {

    private  Empleado empleadoLoggeado;
    public DefaultTableModel modelo = new DefaultTableModel();
    private Menu ventanaMenu;
    
    public void Actualizar(String valor){
        
        IdPan.setEnabled(false);
        String sql = "";
        
        if(valor.equals("")){
            sql = "SELECT * FROM Inventario";
        }else{
            sql  = "SELECT * FROM Inventario WHERE id_Pan = '"+valor+"'";
        }
     
        Conexion cc = new Conexion();
        
        try {
            modelo = new DefaultTableModel();
            InventTabla.setModel(modelo);
            PreparedStatement pst= cc.conectar.prepareStatement("select * from Inventario ");
            //System.out.println(pst);
            ResultSet a = pst.executeQuery(sql);
            ResultSetMetaData aMd = a.getMetaData(); //Le pasamos el resultado de la consulta
            int numColumns = aMd.getColumnCount();//Datos que me regresa la consulta
            
            modelo.addColumn("id_Pan");
            modelo.addColumn("Nombre");
            modelo.addColumn("Precio");
            modelo.addColumn("Existencia");
            
            int[] anchos = {50,50,50,50};
            
            for(int j=0; j< numColumns; j++){
                InventTabla.getColumnModel().getColumn(j).setPreferredWidth(anchos[j]);
            }
            
            while(a.next()){  //Recorremos los datos de la consulta //En cada ciclo me va a dar los datos de una sola fila
                
                Object[] filas = new Object[numColumns];
                
                for(int i=0; i< numColumns; i++){//Se agregan los datos a la tabla  
                    filas[i] = a.getObject(i+1);
                }
                modelo.addRow(filas);
            }
            InventTabla.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
    
    public Inventario() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Inventario");
        Actualizar("");
        setResizable(false);
    }
    
    public Inventario(Component father,Empleado logged) { //aqui  sobreescribo el constructor
        
        empleadoLoggeado = logged;
        
        initComponents();
        Buscar.setVisible(empleadoLoggeado.getRol() != 1);
        this.setLocationRelativeTo(father);
        setTitle("Inventario");
        Actualizar("");
        setResizable(false);
        //System.out.println(empleadoLoggeado.toString());
    }
    
    public Inventario(Menu previewView, Empleado emp){
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Inventario");
        this.ventanaMenu = previewView;
        empleadoLoggeado = emp;
        this.setVisible(rootPaneCheckingEnabled);
        Actualizar("");
        setResizable(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        InventTabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        NomPan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        PrecioPan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        IngresPan = new javax.swing.JTextField();
        Regresar = new javax.swing.JButton();
        Buscar = new javax.swing.JButton();
        Borrar = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        TextBusca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        IdPan = new javax.swing.JTextField();
        Modificar = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        InventTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id_Pan", "Nombre", "Precio", "Existencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        InventTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(InventTabla);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Precio: ");

        jLabel3.setText("Existencia:");

        Regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/regresar.png"))); // NOI18N
        Regresar.setText("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buscar.png"))); // NOI18N
        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        Borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/borrar.png"))); // NOI18N
        Borrar.setText("Borrar");
        Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarActionPerformed(evt);
            }
        });

        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/guardar.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        jLabel4.setText("Buscar por ID:");

        jLabel5.setText("id_Pan:");

        IdPan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdPanActionPerformed(evt);
            }
        });

        Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/editar.png"))); // NOI18N
        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(33, 33, 33)
                                .addComponent(IngresPan, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel5))
                                    .addGap(52, 52, 52)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PrecioPan, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(NomPan, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                                            .addComponent(IdPan)))))
                            .addComponent(Regresar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(TextBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(28, 28, 28))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(IdPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(NomPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel2)
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PrecioPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(IngresPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 77, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
            ventanaMenu.thismissInventario();
    }//GEN-LAST:event_RegresarActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
            Actualizar(TextBusca.getText());
    }//GEN-LAST:event_BuscarActionPerformed

    private void InventTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventTablaMouseClicked
        
            Conexion cc = new Conexion();
                
        try {

            int Fila = InventTabla.getSelectedRow();
            String codigo = InventTabla.getValueAt(Fila, 0).toString();
            
            PreparedStatement pst= cc.conectar.prepareStatement("select * from Inventario where id_Pan = ?");
            //System.out.println(pst);
            pst.setString(1, codigo);
            ResultSet a = pst.executeQuery();
            //ResultSetMetaData aMd = a.getMetaData(); //Le pasamos el resultado de la consulta

            while(a.next()){  //Recorremos los datos de la consulta //En cada ciclo me va a dar los datos de una sola fila
                   
            IdPan.setText(a.getString("id_Pan"));
            NomPan.setText(a.getString("Nombre"));
            PrecioPan.setText(a.getString("Costo"));
            IngresPan.setText(a.getString("Existencia"));
            }
            
        } catch (SQLException e) {
            //System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "ERROR!");
        }
    }//GEN-LAST:event_InventTablaMouseClicked

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
            
            Conexion cc = new Conexion();
            
            try {          
                    PreparedStatement pst= cc.conectar.prepareStatement("INSERT INTO Inventario  (nombre, costo, existencia) VALUES(?,?,?)");
                    
                    //System.out.println(pst);
                    pst.setString(1, NomPan.getText());
                    pst.setString(2, PrecioPan.getText());
                    pst.setString(3, IngresPan.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Guardado");
                        
                    Object[] fila = new Object[4];
                    fila[1] = NomPan.getText();
                    fila[2] = PrecioPan.getText();
                    fila[3] = IngresPan.getText();
                    modelo.addRow(fila);
                    
                }catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "No hay datos que guardar");
                        System.out.println(e);
        }
    }//GEN-LAST:event_GuardarActionPerformed

    private void BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarActionPerformed
            
            Conexion cc = new Conexion();
            
            try {
                    int fila = InventTabla.getSelectedRow();
                    System.out.println(fila);
                    int idPan = (int)InventTabla.getValueAt(fila, 0);
                    PreparedStatement pst= cc.conectar.prepareStatement("DELETE FROM Inventario WHERE id_Pan = ?");
                    pst.setInt(1, idPan);
                    pst.execute();
                    modelo.removeRow(fila);
                    JOptionPane.showMessageDialog(null, "BORRADO");
            
        } catch (Exception e) {
                //JOptionPane.showMessageDialog(null, "ERROR!!");
                JOptionPane.showMessageDialog(null,"No hay datos que eliminar"); 
        }
    }//GEN-LAST:event_BorrarActionPerformed

    private void IdPanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdPanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdPanActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        
        Conexion cc = new Conexion();
        
        try {   
                
                PreparedStatement pst = cc.conectar.prepareStatement("UPDATE Inventario SET nombre = '"+NomPan.getText()+"', costo = '"+PrecioPan.getText()+"', existencia = '"+IngresPan.getText()+"' WHERE id_Pan = '"+IdPan.getText()+"'");
                pst.executeUpdate();
                Actualizar("");
                JOptionPane.showMessageDialog(null,"MODIFICADO!");
            }catch (Exception e) {
                //System.out.println(e);
               JOptionPane.showMessageDialog(null,"ERROR"); 
        }
    }//GEN-LAST:event_ModificarActionPerformed

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
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Borrar;
    private javax.swing.JButton Buscar;
    private javax.swing.JButton Guardar;
    private javax.swing.JTextField IdPan;
    private javax.swing.JTextField IngresPan;
    private javax.swing.JTable InventTabla;
    private javax.swing.JButton Modificar;
    private javax.swing.JTextField NomPan;
    private javax.swing.JTextField PrecioPan;
    private javax.swing.JButton Regresar;
    private javax.swing.JTextField TextBusca;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
