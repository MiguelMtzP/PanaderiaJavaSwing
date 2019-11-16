package pandaderia;
import Models.CorteModel;
import Models.Empleado;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author alexis
 */
public class Login extends javax.swing.JFrame {

    private Menu ventanaMenu;
    private Conexion conection;
    
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Bienvenido");
        setResizable(false);
        conection = new Conexion();
    }
    
    
    public void ingresar(){
        
        try {
            if((usuario.getText().equals("")) || (contrasena.getPassword().length) == 0){
                //System.out.println("**********************************");
                JOptionPane.showMessageDialog(null, "Ingresa todos los campos");    
            } else{
                PreparedStatement pst=conection.conectar.prepareStatement("select * from Usuarios where id_Trabajador = ? and password = ?");
                pst.setInt(1,Integer.parseInt(usuario.getText().trim()));
                pst.setString(2,new String(contrasena.getPassword()));
                ResultSet a = pst.executeQuery();

                if(a.next()) {
                    Empleado logueado = new Empleado();
                    logueado.setIdEmpleado(a.getInt("id_Trabajador"));
                    logueado.setNombre(a.getString("nombre"));
                    logueado.setAppMat(a.getString("materno"));
                    logueado.setAppPat(a.getString("paterno"));
                    logueado.setRol(a.getInt("rol"));
                    logueado.setTelefono(a.getString("numero"));
                    logueado.setPassword(a.getString("password"));
                    CorteModel corte = null;
                    
                    if (logueado.getRol() != 1){
                        String currentCorteQuery = "select * from Corte where id_Trabajador = ? and estatus = ?";
                        pst = conection.conectar.prepareStatement(currentCorteQuery);
                        pst.setInt(1, logueado.getIdEmpleado());
                        pst.setInt(2, 0); // estatus 0 es igual a corte abierto 
                        ResultSet response = pst.executeQuery();
                        if (response.next()) {
                            corte = new CorteModel();
                            corte.setIdCorte(response.getInt("id_Corte"));
                            corte.setEstatus(response.getInt("estatus"));
                            corte.setTurno(response.getString("Turno"));
                            corte.setIdTrabajador(logueado);
                            corte.setFechaInicio(response.getDate("fecha_inicio"));
                            corte.setFechaFin(response.getDate("fecha_fin"));
                        }else{
                            int option = JOptionPane.showConfirmDialog(this, "No se encontro un corte abierto a tu nombre. \nRecuerda que debes tener asignado un corte activo para poder entrar al sistema. \n¿Deseas iniciar un nuevo corte?", "Iniciar corte", JOptionPane.OK_CANCEL_OPTION);
                            System.out.println(option);
                            if (option != 0){return;} //regresa y ya no deja loguear
                            else{
                                corte = initCorte(logueado);
                            }
                        }                           
                    }
                    ventanaMenu= new Menu(this,logueado,corte,conection);
                    System.out.println(logueado);
                    ventanaMenu.setVisible(true);
                    this.setVisible(false); 
                }else{
                    JOptionPane.showMessageDialog(null, "El usuario o contraseña que ingresaste no coincide con nuestros registros, inténtalo de nuevo.");
                }
            }          
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR");
        }
    }
    
    public CorteModel initCorte(Empleado emp) throws SQLException{
        PreparedStatement pst = conection.conectar.prepareStatement("INSERT INTO Corte (id_Trabajador, fecha_inicio, estatus, Turno) VALUES (?,NOW(),0,'mornig')",Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, emp.getIdEmpleado());
        pst.execute();
        ResultSet rset = pst.getGeneratedKeys();
        if(rset.next()){
            int idCorte = rset.getInt(1);
            rset = pst.executeQuery("select * from Corte where id_Corte = "+idCorte);
            if (rset.next()){
                CorteModel corte = new CorteModel();
                corte.setEstatus(rset.getInt("estatus"));
                corte.setIdTrabajador(emp);
                corte.setIdCorte(rset.getInt("id_Corte"));
                corte.setFechaInicio(rset.getDate("fecha_inicio"));
                return corte;
            }else{System.out.println("valio verga 1");}
        }else{System.out.println("valio verga 2");}

        return null;

    }
    
    public void dissmisMenu(){
        ventanaMenu.setVisible(false);
        this.setVisible(true);
        ventanaMenu = null;
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
        Ingresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        contrasena = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Ingresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconfinder_SignIn_1031506.png"))); // NOI18N
        Ingresar.setText("  Ingresar");
        Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel2.setText("Usuario:");

        usuario.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel3.setText("Contraseña:");

        contrasena.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        contrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contrasenaActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/emp.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(60, 60, 60)
                                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(Ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel4)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(Ingresar)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
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

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioActionPerformed

    private void IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresarActionPerformed
        ingresar();
    }//GEN-LAST:event_IngresarActionPerformed

    private void contrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contrasenaActionPerformed
        System.out.println("aaaaaaaaaaaaa");
        ingresar();
        /*Empleado logueado = new Empleado();
        Menu menu= new Menu(this,logueado);
        System.out.println(logueado);
        menu.setVisible(true);
        this.setVisible(false);*/
    }//GEN-LAST:event_contrasenaActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ingresar;
    private javax.swing.JPasswordField contrasena;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
