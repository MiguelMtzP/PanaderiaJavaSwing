package pandaderia;
import Models.Empleado;
import Models.Pan;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author alexis
 */
public class Inventario extends javax.swing.JFrame {

    private  Empleado empleadoLoggeado;
    public DefaultTableModel modelo = new DefaultTableModel();
    Conexion conexion ;
    private Menu ventanaMenu;
    private ArrayList <Pan> panesExistencia;
    private TableRowSorter trs;
    
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
        BuscarJBtn.setVisible(empleadoLoggeado.getRol() != 1);
        this.setLocationRelativeTo(father);
        setTitle("Inventario");
        Actualizar("");
        setResizable(false);
        //System.out.println(empleadoLoggeado.toString());
    }
    
    public Inventario(Menu previewView, Empleado emp){
        empleadoLoggeado = emp;
        conexion = new Conexion();
        
        initComponents();
        setLocationRelativeTo(previewView);
        setTitle("Inventario");
        this.ventanaMenu = previewView;
        

        this.setVisible(rootPaneCheckingEnabled);
        Actualizar("");
        setResizable(false);
    }
    
    public void Actualizar(String valor){
        
        IdPanJLabel.setEnabled(false);
        String sql = "";
        
        if(valor.isEmpty()){
            sql = "SELECT * FROM Inventario";
        }else{
            sql  = "SELECT * FROM Inventario WHERE id_Pan = '"+valor+"'";
        }
        
        try {
            modelo = new DefaultTableModel();
            InventTablaJTable.setModel(modelo);
            PreparedStatement pst= conexion.conectar.prepareStatement("select * from Inventario ");
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
                InventTablaJTable.getColumnModel().getColumn(j).setPreferredWidth(anchos[j]);
            }
            
            while(a.next()){  //Recorremos los datos de la consulta //En cada ciclo me va a dar los datos de una sola fila
                
                Object[] filas = new Object[numColumns];
                
                for(int i=0; i< numColumns; i++){//Se agregan los datos a la tabla  
                    filas[i] = a.getObject(i+1);
                }
                modelo.addRow(filas);
            }
            InventTablaJTable.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
            System.out.println("ERROR --->"+e.getMessage());
        }
        
    }
    
    public void buscaPorNombre(){
        
            String sql = buscaPorNombrejTField.getText();
            try {
            modelo = new DefaultTableModel();
            InventTablaJTable.setModel(modelo);
            PreparedStatement pst= conexion.conectar.prepareStatement("select nombre from Inventario ");
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
                InventTablaJTable.getColumnModel().getColumn(j).setPreferredWidth(anchos[j]);
            }
            
            while(a.next()){  //Recorremos los datos de la consulta //En cada ciclo me va a dar los datos de una sola fila
                
                Object[] filas = new Object[numColumns];
                
                for(int i=0; i< numColumns; i++){//Se agregan los datos a la tabla  
                    filas[i] = a.getObject(i+1);
                }
                modelo.addRow(filas);
            }
            InventTablaJTable.setModel(modelo);
        } catch (SQLException e) {
                System.out.println(e);
           //JOptionPane.showMessageDialog(null,e.toString());
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

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        InventTablaJTable = new javax.swing.JTable();
        Regresar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        IngresPanJLabel = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PrecioPanJLabel = new javax.swing.JTextField();
        NomPanJLabel = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        IdPanJLabel = new javax.swing.JTextField();
        Modificar = new javax.swing.JButton();
        Borrar = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();
        limpiaFieldsJBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TextBuscaJTField = new javax.swing.JTextField();
        BuscarJBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        buscaPorNombrejTField = new javax.swing.JTextField();
        ActualizarTablaJBtn = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        InventTablaJTable.setModel(new javax.swing.table.DefaultTableModel(
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
        InventTablaJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventTablaJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(InventTablaJTable);

        Regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/regresar.png"))); // NOI18N
        Regresar.setText("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo pan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel3.setText("Existencia:");

        jLabel2.setText("Precio: ");

        jLabel1.setText("Nombre:");

        jLabel5.setText("id_Pan:");

        IdPanJLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdPanJLabelActionPerformed(evt);
            }
        });

        Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/editar.png"))); // NOI18N
        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
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

        limpiaFieldsJBtn.setIcon(new javax.swing.ImageIcon("/home/miguel/Descargas/iconfinder_BRoom_Stick_2_896655.png")); // NOI18N
        limpiaFieldsJBtn.setText("Limpiar campos");
        limpiaFieldsJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiaFieldsJBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(33, 33, 33)
                                .addComponent(IngresPanJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel5))
                                    .addGap(52, 52, 52)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PrecioPanJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(NomPanJLabel)
                                            .addComponent(IdPanJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(limpiaFieldsJBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(IdPanJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NomPanJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PrecioPanJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(IngresPanJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiaFieldsJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busquedas y actualizar tabla", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel4.setText("Buscar por ID:");

        BuscarJBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buscar.png"))); // NOI18N
        BuscarJBtn.setText("Buscar");
        BuscarJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarJBtnActionPerformed(evt);
            }
        });

        jLabel6.setText("Buscar por nombre:");

        buscaPorNombrejTField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaPorNombrejTFieldActionPerformed(evt);
            }
        });
        buscaPorNombrejTField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscaPorNombrejTFieldKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscaPorNombrejTFieldKeyReleased(evt);
            }
        });

        ActualizarTablaJBtn.setText("Actualizar tabla");
        ActualizarTablaJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarTablaJBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TextBuscaJTField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BuscarJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(buscaPorNombrejTField)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(ActualizarTablaJBtn)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buscaPorNombrejTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextBuscaJTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuscarJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ActualizarTablaJBtn)
                .addContainerGap(20, Short.MAX_VALUE))
        );

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
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(Regresar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
            ventanaMenu.thismissInventario();
    }//GEN-LAST:event_RegresarActionPerformed

    private void BuscarJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarJBtnActionPerformed
            Actualizar(TextBuscaJTField.getText());
    }//GEN-LAST:event_BuscarJBtnActionPerformed

    private void InventTablaJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventTablaJTableMouseClicked
        
                
        try {

            int Fila = InventTablaJTable.getSelectedRow();
            String codigo = InventTablaJTable.getValueAt(Fila, 0).toString();
            
            PreparedStatement pst= conexion.conectar.prepareStatement("select * from Inventario where id_Pan = ?");
            //System.out.println(pst);
            pst.setString(1, codigo);
            ResultSet a = pst.executeQuery();
            //ResultSetMetaData aMd = a.getMetaData(); //Le pasamos el resultado de la consulta

            while(a.next()){  //Recorremos los datos de la consulta //En cada ciclo me va a dar los datos de una sola fila
                   
            IdPanJLabel.setText(a.getString("id_Pan"));
            NomPanJLabel.setText(a.getString("Nombre"));
            PrecioPanJLabel.setText(a.getString("Costo"));
            IngresPanJLabel.setText(a.getString("Existencia"));
            }
            
        } catch (SQLException e) {
            //System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "ERROR!");
        }
    }//GEN-LAST:event_InventTablaJTableMouseClicked

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
            
            
            try {          
                    PreparedStatement pst= conexion.conectar.prepareStatement("INSERT INTO Inventario  (nombre, costo, existencia) VALUES(?,?,?)");
                    
                    //System.out.println(pst);
                    pst.setString(1, NomPanJLabel.getText());
                    pst.setString(2, PrecioPanJLabel.getText());
                    pst.setString(3, IngresPanJLabel.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Guardado");
                        
                    Object[] fila = new Object[4];
                    fila[1] = NomPanJLabel.getText();
                    fila[2] = PrecioPanJLabel.getText();
                    fila[3] = IngresPanJLabel.getText();
                    modelo.addRow(fila);
                    
                }catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Ingrese todos los campos");
                        System.out.println(e);
        }
    }//GEN-LAST:event_GuardarActionPerformed

    private void BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarActionPerformed
            
            
            try {
                    int fila = InventTablaJTable.getSelectedRow();
                    System.out.println(fila);
                    int idPan = (int)InventTablaJTable.getValueAt(fila, 0);
                    PreparedStatement pst= conexion.conectar.prepareStatement("DELETE FROM Inventario WHERE id_Pan = ?");
                    pst.setInt(1, idPan);
                    pst.execute();
                    modelo.removeRow(fila);
                    JOptionPane.showMessageDialog(null, "BORRADO");
            
        } catch (Exception e) {
                //JOptionPane.showMessageDialog(null, "ERROR!!");
                JOptionPane.showMessageDialog(null,"No hay datos que eliminar"); 
        }
    }//GEN-LAST:event_BorrarActionPerformed

    private void IdPanJLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdPanJLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdPanJLabelActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        
        
        try {   
                PreparedStatement pst = conexion.conectar.prepareStatement("UPDATE Inventario SET nombre = '"+NomPanJLabel.getText()+"', costo = '"+PrecioPanJLabel.getText()+"', existencia = '"+IngresPanJLabel.getText()+"' WHERE id_Pan = '"+IdPanJLabel.getText()+"'");
                pst.executeUpdate();
                Actualizar("");
                JOptionPane.showMessageDialog(null,"MODIFICADO!");
            }catch (Exception e) {
                //System.out.println(e);
               JOptionPane.showMessageDialog(null,"ERROR"); 
        }
    }//GEN-LAST:event_ModificarActionPerformed

    private void buscaPorNombrejTFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaPorNombrejTFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscaPorNombrejTFieldActionPerformed

    private void limpiaFieldsJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiaFieldsJBtnActionPerformed
        IdPanJLabel.setText("");
        NomPanJLabel.setText("");
        PrecioPanJLabel.setText("");
        IngresPanJLabel.setText("");
    }//GEN-LAST:event_limpiaFieldsJBtnActionPerformed

    private void ActualizarTablaJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarTablaJBtnActionPerformed
        Actualizar("");
    }//GEN-LAST:event_ActualizarTablaJBtnActionPerformed

    private void buscaPorNombrejTFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscaPorNombrejTFieldKeyReleased
        /*try {
            ArrayList<Pan> filtrados = new ArrayList<Pan>(0);
            String input = buscaPorNombrejTField.getText();
            for(Pan panes : panesExistencia){
                if(input.isEmpty()
                    || panes.getnombre().contains(input)
                    || panes.getcosto().contains(input)
                    || panes.getexistencia().contains(input)){
                    filtrados.add(panes);
                }
            }
        } catch (Exception e) {
        }*/
    }//GEN-LAST:event_buscaPorNombrejTFieldKeyReleased

    private void buscaPorNombrejTFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscaPorNombrejTFieldKeyTyped
        
        /*buscaPorNombrejTField.addKeyListener(new KeyAdapter (){
        
        @Override
        public void keyReleased(KeyEvent ke){
            
            trs.setRowFilter(RowFilter.regexFilter("(?i)"+buscaPorNombrejTField.getText(), 1));
        }
        });
        
        trs = new TableRowSorter(modelo);
        InventTablaJTable.setRowSorter(trs);*/
        
    }//GEN-LAST:event_buscaPorNombrejTFieldKeyTyped

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
    private javax.swing.JButton ActualizarTablaJBtn;
    private javax.swing.JButton Borrar;
    private javax.swing.JButton BuscarJBtn;
    private javax.swing.JButton Guardar;
    private javax.swing.JTextField IdPanJLabel;
    private javax.swing.JTextField IngresPanJLabel;
    private javax.swing.JTable InventTablaJTable;
    private javax.swing.JButton Modificar;
    private javax.swing.JTextField NomPanJLabel;
    private javax.swing.JTextField PrecioPanJLabel;
    private javax.swing.JButton Regresar;
    private javax.swing.JTextField TextBuscaJTField;
    private javax.swing.JTextField buscaPorNombrejTField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiaFieldsJBtn;
    // End of variables declaration//GEN-END:variables
}
