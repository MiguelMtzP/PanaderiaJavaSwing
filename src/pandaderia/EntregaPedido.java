/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandaderia;

import Models.Clientes;
import Models.CorteModel;
import Models.Empleado;
import Models.Pedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alexis
 */
public class EntregaPedido extends javax.swing.JFrame {
    

    /**
     * Creates new form EntregaPedido
     */
    
    private Venta ventanaVenta;
    private Empleado empleadoLoggeado;
    private Conexion conexion;
    private CorteModel currentCorte;
    private ArrayList <Pedido> idPedidosExistentes;
    private Pedido currentSelectedPedido;
    public DefaultTableModel modelo ;
    public DefaultTableModel modeloPedidos ;
    
    public EntregaPedido(){
        
    }

    public EntregaPedido(Venta previewView, CorteModel currentCorte,Conexion cc) {
        empleadoLoggeado = currentCorte.getIdTrabajador();
        this.currentCorte = currentCorte;
        conexion = cc;
        initComponents();
        modelo = (DefaultTableModel) entregaPedidosJTable.getModel();
        setTitle("Entregar pedido");
        setLocationRelativeTo(previewView);
        this.ventanaVenta = previewView;
        setResizable(false);
        cargaPedidos();
        
    }
    
    public void cargaPedidos(){
        try {
            idPedidosExistentes = new ArrayList<Pedido>(0);
            modelo.setRowCount(0);
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT concat(Cliente.paterno,\" \",Cliente.materno,\" \",Cliente.nombre)as nombre,  " +
                                                                        "	Pedidos.tipo as tipo, " +
                                                                        "	Pedidos.id_Pedidos as idPedido, " +
                                                                        "    Pedidos.fecha_entrega as entrega, " +
                                                                        "    Pedidos.abono as abono, " +
                                                                        "    Pedidos.costo as total, " +
                                                                        "    Pedidos.costo - Pedidos.abono as restan " +
                                                                        "	FROM panaderia.Pedidos " +
                                                                        "    join Cliente on Pedidos.id_Cliente = Cliente.id_Cliente " +
                                                                        "    where Pedidos.status = 1 " +
                                                                        "    order by nombre asc");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                
                Pedido nuevo = new Pedido();
                nuevo.setIdPedido(rs.getInt("idPedido"));
                Clientes nCliente = new Clientes();
                nCliente.setnombre(rs.getString("nombre"));
                nuevo.setCliente(nCliente);
                nuevo.setTipo(rs.getInt("tipo"));
                nuevo.setFechaEntrega(rs.getDate("entrega"));
                nuevo.setAbono(rs.getFloat("abono"));
                nuevo.setResta(rs.getFloat("restan"));
                nuevo.setCosto(rs.getFloat("total"));
                        
                idPedidosExistentes.add(nuevo);
                Object [] obj = new Object[6];
                obj[0]= rs.getString("nombre");
                obj[1] = rs.getInt("tipo") == 1 ? "normal":"externo";
                obj[2] = rs.getDate("entrega");
                obj[3] = rs.getFloat("abono");
                obj[4]= rs.getFloat("restan");
                obj[5]= rs.getFloat("total");
                modelo.addRow(obj);
            }
        } catch (Exception e) {
            System.out.println("eee"+e.getLocalizedMessage());
        }
    }
    
    public void getDetallePedido(int idPedido){
        try {
            DefaultTableModel modelo = (DefaultTableModel)detallePedidojTable.getModel();
            modelo.setRowCount(0);
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT Inventario.nombre as nombre, " +
                    "		Pedidos_Inventario.cantidad as cantidad, " +
                    "        Pedidos_Inventario.cantidad*Inventario.costo as costo " +
                    "	FROM panaderia.Pedidos_Inventario " +
                    "	join Inventario on Inventario.id_Pan = Pedidos_Inventario.id_Pan " +
                    "	where Pedidos_Inventario.id_Pedidos = ?");
            
            pst.setInt(1, idPedido);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Object [] obj = new Object[3];
                obj[0]= rs.getString("nombre");
                obj[1] = rs.getInt("cantidad");
                obj[2] = rs.getFloat("costo");
                modelo.addRow(obj);
            }
        } catch (SQLException ex) {
            System.out.println("eee"+ex.getLocalizedMessage());
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        entregaPedidosJTable = new javax.swing.JTable();
        BuscaClientejTextField = new javax.swing.JTextField();
        nombreClientePedidoJLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        detallePedidojTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        RestanjLabel = new javax.swing.JLabel();
        entregarPedidoJButton = new javax.swing.JButton();
        regresarJBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        entregaPedidosJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre cliente", "Tipo Pedido", "Entrega", "Anticipo", "Restan", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        entregaPedidosJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entregaPedidosJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(entregaPedidosJTable);

        BuscaClientejTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscaClientejTextFieldActionPerformed(evt);
            }
        });
        BuscaClientejTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BuscaClientejTextFieldKeyReleased(evt);
            }
        });

        nombreClientePedidoJLabel.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        nombreClientePedidoJLabel.setText("Nombre");

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel1.setText("ID Pedido");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(nombreClientePedidoJLabel)
                .addGap(18, 18, 18)
                .addComponent(BuscaClientejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BuscaClientejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreClientePedidoJLabel)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        detallePedidojTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Pan", "Cantidad", "Costo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(detallePedidojTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel2.setText("Restan:");

        RestanjLabel.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        RestanjLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RestanjLabel.setText("0");

        entregarPedidoJButton.setText("Entregar pedido");
        entregarPedidoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entregarPedidoJButtonActionPerformed(evt);
            }
        });

        regresarJBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/regresar.png"))); // NOI18N
        regresarJBtn.setText("Regresar");
        regresarJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarJBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(regresarJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 898, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(RestanjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entregarPedidoJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(RestanjLabel))
                            .addComponent(entregarPedidoJButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(regresarJBtn)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscaClientejTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscaClientejTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscaClientejTextFieldActionPerformed

    private void regresarJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarJBtnActionPerformed
        ventanaVenta.thismissEntregaPedido();
    }//GEN-LAST:event_regresarJBtnActionPerformed

    private void entregaPedidosJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entregaPedidosJTableMouseClicked
        int index = entregaPedidosJTable.getSelectedRow();
        if(index>=0){
            currentSelectedPedido =idPedidosExistentes.get(index);
            
            RestanjLabel.setText(String.valueOf(currentSelectedPedido.getResta()));
            getDetallePedido(currentSelectedPedido.getIdPedido());
        }
    }//GEN-LAST:event_entregaPedidosJTableMouseClicked

    private void BuscaClientejTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BuscaClientejTextFieldKeyReleased
        ArrayList<Pedido> filtrados = new ArrayList<Pedido>(0);
        String input = BuscaClientejTextField.getText();
        for (Pedido pedido :idPedidosExistentes) {
            if (input.isEmpty()
                ||pedido.getCliente().getnombre().toUpperCase().contains(input.toUpperCase())){
                filtrados.add(pedido);
            }
        }
        
        if(filtrados.size() > 0){
            modelo.setRowCount(0);
            for (Pedido filtrado : filtrados) {
                Object [] obj = new Object[6];
                obj[0]= filtrado.getCliente().getnombre();
                obj[1] = filtrado.getTipo() == 1 ? "normal":"externo" ;
                obj[2] =filtrado.getFechaEntrega();
                obj[3] =filtrado.getAbono();
                obj[4]= filtrado.getResta();
                obj[5]= filtrado.getCosto();

                modelo.addRow(obj);
            }
        }else if(!BuscaClientejTextField.getText().isEmpty())
        modelo.setRowCount(0);
    }//GEN-LAST:event_BuscaClientejTextFieldKeyReleased

    private void entregarPedidoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entregarPedidoJButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entregarPedidoJButtonActionPerformed

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
            java.util.logging.Logger.getLogger(EntregaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntregaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntregaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntregaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EntregaPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BuscaClientejTextField;
    private javax.swing.JLabel RestanjLabel;
    private javax.swing.JTable detallePedidojTable;
    private javax.swing.JTable entregaPedidosJTable;
    private javax.swing.JButton entregarPedidoJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel nombreClientePedidoJLabel;
    private javax.swing.JButton regresarJBtn;
    // End of variables declaration//GEN-END:variables
}
