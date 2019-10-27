/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandaderia;

import Models.Empleado;
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
public class BuscaCliente extends javax.swing.JFrame {

    public DefaultTableModel modelo = new DefaultTableModel();
    private Empleado empleadoLoggeado;
    private AddPedido ventanaAddPedido;
    
    public BuscaCliente() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Buscar cliente");
        BuscaCliente("");
        BuscaID("");
    }
    
    public BuscaCliente(AddPedido previewView, Empleado emp){
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Buscar cliente");
        this.ventanaAddPedido = previewView;
        empleadoLoggeado = emp;
        this.setVisible(rootPaneCheckingEnabled);
        setResizable(false);
        BuscaCliente("");
        BuscaID("");
    }
    
    public void BuscaCliente(String valor){
        
        Conexion cc = new Conexion();
        
        String sql = "";
        
        if(valor.equals("")){
            sql = "SELECT * FROM Cliente"; //BUSCAMOS EN USUSARIOS PORQUE LA TABLA CLIENTE ESTA VACIA 
        }else{
            sql = "SELECT * FROM Cliente where paterno = '"+valor+"'";
        }
        
        try {
                modelo = new DefaultTableModel();
                ClientesTabla.setModel(modelo);
                PreparedStatement pst= cc.conectar.prepareStatement("select * from Cliente ");
                //System.out.println(pst);
                ResultSet a = pst.executeQuery(sql);
                ResultSetMetaData aMd = a.getMetaData(); //Le pasamos el resultado de la consulta
                int numColumns = aMd.getColumnCount();//Datos que me regresa la consulta
                
                modelo.addColumn("ID Cliente");
                modelo.addColumn("Nombre");
                modelo.addColumn("Paterno");
                modelo.addColumn("Materno");
                modelo.addColumn("Telefono");
                modelo.addColumn("Correo");

                int[] anchos = {50,50,50,50,50,50};

                for(int j=0; j< numColumns; j++){
                    ClientesTabla.getColumnModel().getColumn(j).setPreferredWidth(anchos[j]);
                }
                
                while(a.next()){
                    
                    Object[] filas = new Object[numColumns];
                
                for(int i=0; i< numColumns; i++){//Se agregan los datos a la tabla  
                        filas[i] = a.getObject(i+1);
                    }
                    modelo.addRow(filas);
                }
                    ClientesTabla.setModel(modelo);
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "ERROR EN BUSQUEDA");
            System.out.println(e);
        }
        
    }
    
    public void BuscaID(String valor){
        
        Conexion cc = new Conexion();
        
        String sql = "";
        
        if(valor.equals("")){
            sql = "SELECT * FROM Cliente"; //BUSCAMOS EN USUSARIOS PORQUE LA TABLA CLIENTE ESTA VACIA 
        }else{
            sql = "SELECT * FROM Cliente where id_Cliente = '"+valor+"'";
        }
        
        try {
                modelo = new DefaultTableModel();
                ClientesTabla.setModel(modelo);
                PreparedStatement pst= cc.conectar.prepareStatement("select * from Cliente ");
                //System.out.println(pst);
                ResultSet a = pst.executeQuery(sql);
                ResultSetMetaData aMd = a.getMetaData(); //Le pasamos el resultado de la consulta
                int numColumns = aMd.getColumnCount();//Datos que me regresa la consulta
                
                modelo.addColumn("ID Cliente");
                modelo.addColumn("Nombre");
                modelo.addColumn("Paterno");
                modelo.addColumn("Materno");
                modelo.addColumn("Telefono");
                modelo.addColumn("Correo");

                int[] anchos = {50,50,50,50,50,50};

                for(int j=0; j< numColumns; j++){
                    ClientesTabla.getColumnModel().getColumn(j).setPreferredWidth(anchos[j]);
                }
                
                while(a.next()){
                    
                    Object[] filas = new Object[numColumns];
                
                for(int i=0; i< numColumns; i++){//Se agregan los datos a la tabla  
                        filas[i] = a.getObject(i+1);
                    }
                    modelo.addRow(filas);
                }
                    ClientesTabla.setModel(modelo);
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "ERROR EN BUSQUEDA");
            System.out.println(e);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        idClientJtf = new javax.swing.JTextField();
        PaternClientJtf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ClientesTabla = new javax.swing.JTable();
        BuscaPaterno = new javax.swing.JButton();
        BuscaID = new javax.swing.JButton();
        Regresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel1.setText("Buscar por ID:");

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel3.setText("Buscar por apelleido paterno:");

        ClientesTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Cliente", "Nombre", "Paterno", "Materno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ClientesTabla);

        BuscaPaterno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pandaderia/buscar.png"))); // NOI18N
        BuscaPaterno.setText("Buscar ");
        BuscaPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscaPaternoActionPerformed(evt);
            }
        });

        BuscaID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pandaderia/buscar.png"))); // NOI18N
        BuscaID.setText("Buscar ");
        BuscaID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscaIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idClientJtf, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(PaternClientJtf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BuscaPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuscaID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(PaternClientJtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(idClientJtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BuscaID))
                        .addGap(14, 14, 14)
                        .addComponent(BuscaPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
        );

        Regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pandaderia/regresar.png"))); // NOI18N
        Regresar.setText("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addComponent(Regresar)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscaPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscaPaternoActionPerformed
        BuscaCliente(PaternClientJtf.getText());
        idClientJtf.setText("");
    }//GEN-LAST:event_BuscaPaternoActionPerformed

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        ventanaAddPedido.thismissBuscaCliente();
    }//GEN-LAST:event_RegresarActionPerformed

    private void BuscaIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscaIDActionPerformed
        BuscaID(idClientJtf.getText());
        idClientJtf.setText("");
    }//GEN-LAST:event_BuscaIDActionPerformed

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
            java.util.logging.Logger.getLogger(BuscaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuscaID;
    private javax.swing.JButton BuscaPaterno;
    private javax.swing.JTable ClientesTabla;
    private javax.swing.JTextField PaternClientJtf;
    private javax.swing.JButton Regresar;
    private javax.swing.JTextField idClientJtf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
