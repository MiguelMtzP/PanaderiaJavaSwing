package pandaderia;

import Models.Empleado;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MisEmpleados extends javax.swing.JFrame {

    private Empleado empleadoLoggeado;
    public DefaultTableModel modelo = new DefaultTableModel();
    private Menu ventanaMenu;
    private ButtonGroup rolButton;
    
    public MisEmpleados() {

        initComponents();
        setLocationRelativeTo(null);
        setTitle("Mis Empleados");
        Actualizar("");
        setResizable(false);
    }
 
    public MisEmpleados(Component father,Empleado logged) { //aqui  sobreescribo el constructor
        initComponents();
        setTitle("Mis Empleados");
        Actualizar("");
        setResizable(false);
        empleadoLoggeado = logged;
        //Empleados.setVisible(empleadoLoggeado.getRol() == 1);
        this.setLocationRelativeTo(father);
        //System.out.println(empleadoLoggeado.toString());
    }
    
    public MisEmpleados(Menu previewView, Empleado emp){
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Mis empleados");
        this.ventanaMenu = previewView;
        empleadoLoggeado = emp;
        setResizable(false);
        validateButtons();
        rolButton = new ButtonGroup();
        rolButton.add(gerenteOptionRadioButton);
        rolButton.add(empleadoOptionRadioButton);
        Actualizar("");
    }
    
        public void Actualizar(String valor){
        
        idTrabajadorField.setEnabled(false); 
        String sql = "";
        if(valor.equals("")){
            sql = "SELECT * FROM Usuarios";
        }else{
            sql = "SELECT * FROM Usuarios where id_Trabajador = '"+valor+"'";
        }
        
        Conexion cc = new Conexion();
        
        try {
                modelo = new DefaultTableModel();
                EmpleadosTabla.setModel(modelo);
                PreparedStatement pst= cc.conectar.prepareStatement(sql);
                //System.out.println(pst);
                ResultSet a = pst.executeQuery(sql);
                ResultSetMetaData aMd = a.getMetaData(); //Le pasamos el resultado de la consulta
                int numColumns = aMd.getColumnCount();//Datos que me regresa la consulta
                
                modelo.addColumn("id_Trabajador");
                modelo.addColumn("Nombre");
                modelo.addColumn("Paterno");
                modelo.addColumn("Materno");
                modelo.addColumn("Número");
                modelo.addColumn("Contraseña");
                modelo.addColumn("Rol");

                int[] anchos = {50,50,50,50,50,50,50};

                for(int j=0; j< numColumns; j++){
                    EmpleadosTabla.getColumnModel().getColumn(j).setPreferredWidth(anchos[j]);
                }
                
                while(a.next()){
                    
                        Object[] filas = new Object[numColumns];
                
                    for(int i=0; i< numColumns; i++){//Se agregan los datos a la tabla  
                        filas[i] = a.getObject(i+1);
                    }
                    modelo.addRow(filas);
                }
                    EmpleadosTabla.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.toString());
            //System.out.println(e);
        }
    }
        
    private void validateButtons(){
        if (idTrabajadorField.getText().isEmpty()){
            ModificarButton.setEnabled(false);
            BorrarButton.setEnabled(false);
            System.out.println("entro 1");
        }else{
            System.out.println("entro 2");
            BorrarButton.setEnabled(true);
            if((!gerenteOptionRadioButton.isSelected()&&!empleadoOptionRadioButton.isSelected())||
            PaternoField.getText().isEmpty()||
            MaternoField.getText().isEmpty()||
            NombreField.getText().isEmpty()||
            NumeroField.getText().isEmpty()||
            PasswField.getText().isEmpty()){
            System.out.println("entro 3");
            ModificarButton.setEnabled(false);
            }else{
                ModificarButton.setEnabled(true);
                System.out.println("entro 4");
            }
        }
        if (!idTrabajadorField.getText().isEmpty()||
            (!gerenteOptionRadioButton.isSelected()&&!empleadoOptionRadioButton.isSelected())||
            PaternoField.getText().isEmpty()||
            MaternoField.getText().isEmpty()||
            NombreField.getText().isEmpty()||
            NumeroField.getText().isEmpty()||
            PasswField.getText().isEmpty()){
            System.out.println("entro 5");
            GuardarButton.setEnabled(false);
        }else{
            GuardarButton.setEnabled(true);
            System.out.println("entro 6");
        }
    }
    
    public void limpiaFields(){
        idTrabajadorField.setText("");
        NombreField.setText("");
        PaternoField.setText("");
        MaternoField.setText("");
        NumeroField.setText("");
        PasswField.setText("");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        EmpleadosTabla = new javax.swing.JTable();
        Regresar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TextBuscar = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        idTrabajadorField = new javax.swing.JTextField();
        label1 = new javax.swing.JLabel();
        NombreField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        PaternoField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        PasswField = new javax.swing.JTextField();
        NumeroField = new javax.swing.JTextField();
        MaternoField = new javax.swing.JTextField();
        label6 = new javax.swing.JLabel();
        gerenteOptionRadioButton = new javax.swing.JRadioButton();
        empleadoOptionRadioButton = new javax.swing.JRadioButton();
        limpiaCampos = new javax.swing.JButton();
        GuardarButton = new javax.swing.JButton();
        ModificarButton = new javax.swing.JButton();
        BorrarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        EmpleadosTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id_Trabajador", "Nombre", "Paterno", "Materno", "Contraseña", "Número", "Rol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        EmpleadosTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EmpleadosTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(EmpleadosTabla);

        Regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/regresar.png"))); // NOI18N
        Regresar.setText("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Buscar por ID:");

        TextBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextBuscarActionPerformed(evt);
            }
        });

        Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/buscar.png"))); // NOI18N
        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
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
                        .addComponent(TextBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Buscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Buscar)
                .addGap(0, 37, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel5.setText("id Trabajador:");

        idTrabajadorField.setEditable(false);

        label1.setText("Nombre: ");

        NombreField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Paterno: ");

        jLabel3.setText("Materno: ");

        label5.setText("Número: ");

        label4.setText("Contraseña: ");

        NumeroField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroFieldActionPerformed(evt);
            }
        });

        MaternoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaternoFieldActionPerformed(evt);
            }
        });

        label6.setText("Rol:  ");

        gerenteOptionRadioButton.setText("Gerente");

        empleadoOptionRadioButton.setText("Empleado");

        limpiaCampos.setText("Limpiar campos de texto");
        limpiaCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiaCamposActionPerformed(evt);
            }
        });

        GuardarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/guardar.png"))); // NOI18N
        GuardarButton.setText("Guardar");
        GuardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarButtonActionPerformed(evt);
            }
        });

        ModificarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/editar.png"))); // NOI18N
        ModificarButton.setText("Modificar");
        ModificarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarButtonActionPerformed(evt);
            }
        });

        BorrarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/borrar.png"))); // NOI18N
        BorrarButton.setText("Borrar");
        BorrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PaternoField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(idTrabajadorField, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MaternoField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumeroField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(empleadoOptionRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(limpiaCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(GuardarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ModificarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BorrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gerenteOptionRadioButton))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MaternoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NumeroField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PasswField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(idTrabajadorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(PaternoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(gerenteOptionRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(empleadoOptionRadioButton))
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BorrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ModificarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GuardarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(limpiaCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1033, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NombreFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreFieldActionPerformed

    private void MaternoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaternoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaternoFieldActionPerformed

    private void NumeroFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumeroFieldActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
       Actualizar(TextBuscar.getText());
       TextBuscar.setText("");
    }//GEN-LAST:event_BuscarActionPerformed

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        Empleado logueado = new Empleado();
        System.out.println(logueado);
        ventanaMenu.setVisible(true);
        this.setVisible(false);
        ventanaMenu.thismissEmpleados();
    }//GEN-LAST:event_RegresarActionPerformed

    private void BorrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarButtonActionPerformed
           
            Conexion cc = new Conexion();
            
            try {   
                    int fila = EmpleadosTabla.getSelectedRow();
                    System.out.println(fila);
                    int idTrabaj = (int)EmpleadosTabla.getValueAt(fila, 0);
                    PreparedStatement pst= cc.conectar.prepareStatement("DELETE FROM Usuarios WHERE id_Trabajdor = ?");
                    pst.setInt(1, idTrabaj);
                    pst.execute();
                    modelo.removeRow(fila);
                    JOptionPane.showMessageDialog(null, "BORRADO");
            
        } catch (Exception e) {
                //JOptionPane.showMessageDialog(null, "ERROR!!");
                JOptionPane.showMessageDialog(null,"No hay datos que eliminar");
        }    
           
       
    }//GEN-LAST:event_BorrarButtonActionPerformed

    private void TextBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextBuscarActionPerformed

    private void GuardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarButtonActionPerformed
                    
                    Conexion cc = new Conexion();
            
            try {          
                    PreparedStatement pst= cc.conectar.prepareStatement("INSERT INTO Usuarios  (nombre, paterno, materno, numero, password, rol) VALUES(?,?,?,?,?,?)");
                    //kstor modifique cosas a partir de aqui
                    //System.out.println(pst);
                    pst.setString(1, NombreField.getText());
                    pst.setString(2, PaternoField.getText());
                    pst.setString(3, MaternoField.getText());
                    pst.setString(4, NumeroField.getText());
                    pst.setString(5, PasswField.getText());
                    int rol = empleadoOptionRadioButton.isSelected() ? 2 : 1;
                    pst.setInt(6, rol);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Guardado");
                        
                    Object[] fila = new Object[7];
                    fila[1] = NombreField.getText();
                    fila[2] = PaternoField.getText();
                    fila[3] = MaternoField.getText();
                    fila[4] = NumeroField.getText();
                    fila[5] = PasswField.getText();
                    fila[6] = gerenteOptionRadioButton.isSelected() ? "Gerente" : "Empleado";
                    modelo.addRow(fila);
                    
                }catch (SQLException e) {
                        //JOptionPane.showMessageDialog(null, "ERROR!!");
                        System.out.println(e);
        }
    }//GEN-LAST:event_GuardarButtonActionPerformed

    private void ModificarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarButtonActionPerformed
            
        Conexion cc = new Conexion();
            
            try {
                  PreparedStatement pst = cc.conectar.prepareStatement("UPDATE Usuarios SET nombre = '"+NombreField.getText()+"', paterno = '"+PaternoField.getText()+"', materno = '"+MaternoField.getText()+"', password = '"+PasswField.getText()+"', numero = '"+NumeroField.getText()+"', rol = '"+(gerenteOptionRadioButton.isSelected()?"1":"2")+"' WHERE id_Trabajador = '"+idTrabajadorField.getText()+"'");
                  pst.executeUpdate();
                  Actualizar("");
                  JOptionPane.showMessageDialog(null,"MODIFICADO!");  
        } catch (Exception e) {
                  //JOptionPane.showMessageDialog(null,"ERROR");
                  System.out.println(e);
        }
    }//GEN-LAST:event_ModificarButtonActionPerformed

    private void EmpleadosTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmpleadosTablaMouseClicked
        //kstor
               Conexion cc = new Conexion();
                
            try {

                    int Fila = EmpleadosTabla.getSelectedRow();
                    String codigo = EmpleadosTabla.getValueAt(Fila, 0).toString();

                    PreparedStatement pst= cc.conectar.prepareStatement("select * from Usuarios where id_Trabajador = ?");
                    //System.out.println(pst);
                    pst.setString(1, codigo);
                    ResultSet a = pst.executeQuery();
                    //ResultSetMetaData aMd = a.getMetaData(); //Le pasamos el resultado de la consulta

                    while(a.next()){  //Recorremos los datos de la consulta //En cada ciclo me va a dar los datos de una sola fila

                    idTrabajadorField.setText(a.getString("id_Trabajador"));
                    NombreField.setText(a.getString("Nombre"));
                    PaternoField.setText(a.getString("Paterno"));
                    MaternoField.setText(a.getString("Materno"));
                    NumeroField.setText(a.getString("Numero"));
                    PasswField.setText(a.getString("password"));
                    int rol = a.getInt("Rol");
                    }

            } catch (SQLException e) {
                System.out.println(e);
            }
    }//GEN-LAST:event_EmpleadosTablaMouseClicked

    private void limpiaCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiaCamposActionPerformed
        limpiaFields();
    }//GEN-LAST:event_limpiaCamposActionPerformed

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
            java.util.logging.Logger.getLogger(MisEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MisEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MisEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MisEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MisEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BorrarButton;
    private javax.swing.JButton Buscar;
    private javax.swing.JTable EmpleadosTabla;
    private javax.swing.JButton GuardarButton;
    private javax.swing.JTextField MaternoField;
    private javax.swing.JButton ModificarButton;
    private javax.swing.JTextField NombreField;
    private javax.swing.JTextField NumeroField;
    private javax.swing.JTextField PasswField;
    private javax.swing.JTextField PaternoField;
    private javax.swing.JButton Regresar;
    private javax.swing.JTextField TextBuscar;
    private javax.swing.JRadioButton empleadoOptionRadioButton;
    private javax.swing.JRadioButton gerenteOptionRadioButton;
    private javax.swing.JTextField idTrabajadorField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JButton limpiaCampos;
    // End of variables declaration//GEN-END:variables
}
