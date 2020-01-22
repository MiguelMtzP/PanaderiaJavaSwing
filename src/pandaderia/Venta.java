package pandaderia;

import Models.CorteModel;
import Models.RegVenta;
import Models.Empleado;
import Models.Pan;
import java.awt.Component;
import java.awt.Window;
import static java.lang.Float.parseFloat;
//import java.sql.Date;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alexis
 */
public class Venta extends javax.swing.JFrame {

    private Empleado empleadoLoggeado;
    public DefaultTableModel modelo ;
    private Empleado logueado = new Empleado();
    private Menu ventanaMenu;
    private RegPedido ventanaRegPedido;
    //yprivate AddPedido ventanaAddPedido;
    private EntregaPedido ventanaEntregaPedidos;
    private ArrayList <Pan> panesExistencia;
    private ArrayList <RegVenta> panesVendidos;
    private Conexion conexion;
    private CorteModel currentCorte;    
    private DecimalFormat formato = new DecimalFormat("#.0"); 
    
    
    public Venta() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Ventas");
        cargaCB();
        setResizable(false);
    }
    
    public Venta(Component father,Empleado logged){
        empleadoLoggeado = logged;
        initComponents();
        this.setLocationRelativeTo(father);
        setTitle("Venta");
        setResizable(false);
        cargaCB();
    }
    
    public Venta(Menu previewView,Empleado emp, Conexion c, CorteModel current ) {
        conexion = c;
        currentCorte = current;
        panesVendidos = new ArrayList(0);
        panesExistencia = new ArrayList(0);
        empleadoLoggeado = emp;
        
        initComponents();
        //validaRegistraVenta();
        deleteSelectedJButton.setEnabled(false);
        panesIsuficientesLabel.setVisible(false);
        modelo = new DefaultTableModel();
        modelo.addColumn("Pan");
        modelo.addColumn("Precio");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        VentaPanJTable.setModel(modelo);
        //validaRegistraVenta();
        setLocationRelativeTo(previewView);
        setTitle("Ventas");
        this.ventanaMenu = previewView;
        cargaCB();
        this.setVisible(rootPaneCheckingEnabled);
        setResizable(false);
    }
    
    public void validaRegistrarVenta(){
        if(panesVendidos.isEmpty()){
            RegVenta.setEnabled(false);
        }else{
            RegVenta.setEnabled(true);
        }
    }
    
    public float calculaTotalTicket(){
        
        float suma = 0;
        for (RegVenta elem : panesVendidos) {
            suma += elem.getTotal();
        }
        return suma;
    }
    
    public float generarCambio(){
        
        float resta = 0;
        float total = 0;
        float recibi = 0;
        
        total = Float.parseFloat(totalJLabel.getText());
        recibi = Float.parseFloat(recibiJTF.getText());
   
        if(recibi>total){
           resta = recibi-total;
           CambioJlabel.setText(formato.format(resta));
           //System.out.println(formato.format(resta)); 
        }else{
            JOptionPane.showMessageDialog(null, "¡MONTO INGRESADO INSUFICIENTE!");
        }
        return resta;
    }
    
    public void borraSeleccionado(){
        int fila = VentaPanJTable.getSelectedRow();
        
        panesVendidos.remove(fila);
        totalJLabel.setText(formato.format(calculaTotalTicket()));
        modelo.removeRow(fila);
    }
    
    public void cargaCB(){
        
                CbPan.removeAllItems();
                panesExistencia.removeAll(panesExistencia);
        try {
                PreparedStatement pst= conexion.conectar.prepareStatement("SELECT * FROM Inventario order by nombre asc");
                ResultSet rs = pst.executeQuery();
                CbPan.addItem("Seleccione un pan");
                
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
    
    
    
    public void validaRegistraVenta(){
        if(panesVendidos.isEmpty()){
            //JOptionPane.showMessageDialog(null, "No hay panes seleccionados");
            RegVenta.setEnabled(false);
        }else{
            RegVenta.setEnabled(true);
        }  
    }
    
    public void registraVenta(){        
        try {
            if(panesVendidos.isEmpty()){
                   JOptionPane.showMessageDialog(null, "No hay panes seleccionados");
                   //RegVenta.setEnabled(false);
            }else{
                
                for (RegVenta elem : panesVendidos) {
                    PreparedStatement pst= conexion.conectar.prepareStatement("INSERT INTO Ventas  (id_Trabajador, id_Pan, id_Corte, fecha, cantidad, Total ) VALUES(?,?,?,now(),?,?)");

                    pst.setInt(1, elem.getIdTrabajador());
                    pst.setInt(2, elem.getIdPan());
                    pst.setInt(3,currentCorte.getIdCorte());
                    pst.setInt(4, elem.getCantidad());
                    pst.setFloat(5, elem.getTotal());
                    pst.execute();

                    pst = conexion.conectar.prepareStatement("update Inventario set existencia = existencia - ? where id_Pan = ?");
                    pst.setInt(1, elem.getCantidad());
                    pst.setInt(2, elem.getIdPan());
                    pst.execute();

                }
                    JOptionPane.showMessageDialog(null, "Venta registrada exitosamente");
                    limpiaVentana();
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR!!");
            System.out.println(e);
        }
    }
    
    public void limpiaVentana(){
        modelo.setRowCount(0);
        panesVendidos.removeAll(panesVendidos);
        System.out.println(panesVendidos);
        totalJLabel.setText("0");
        recibiJTF.setText("");
        CambioJlabel.setText("");
        cargaCB();
    }
    
    public void thismissRegPedido(){
        System.out.println(empleadoLoggeado);
        ventanaRegPedido.setVisible(false);
        this.setVisible(true);
        ventanaRegPedido = null;
    }
    
    public void thismissEntregaPedido(){
        System.out.println(empleadoLoggeado);
        ventanaEntregaPedidos.setVisible(false);
        this.setVisible(true);
        ventanaEntregaPedidos = null;
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
        VentaPanJTable = new javax.swing.JTable();
        RegVenta = new javax.swing.JButton();
        CbPan = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        agregarJBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        RegPedido = new javax.swing.JButton();
        Regresar = new javax.swing.JButton();
        CantPanJtf = new javax.swing.JTextField();
        totalJLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CambioJlabel = new javax.swing.JLabel();
        recibiJTF = new javax.swing.JTextField();
        genCambioJBtn = new javax.swing.JButton();
        panesIsuficientesLabel = new javax.swing.JLabel();
        entregaPedidoJBtn = new javax.swing.JButton();
        deleteSelectedJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        VentaPanJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Pan", "Precio", "Cantidad", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        VentaPanJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VentaPanJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(VentaPanJTable);

        RegVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/venta.png"))); // NOI18N
        RegVenta.setText("  Registrar Venta");
        RegVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegVentaActionPerformed(evt);
            }
        });

        CbPan.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        CbPan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CbPan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbPanActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel1.setText("Pan:");

        agregarJBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconfinder_sign-check_299110.png"))); // NOI18N
        agregarJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarJBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel3.setText("TOTAL:");

        RegPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/inventario.png"))); // NOI18N
        RegPedido.setText("Registrar Pedido");
        RegPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegPedidoActionPerformed(evt);
            }
        });

        Regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/regresar.png"))); // NOI18N
        Regresar.setText("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        CantPanJtf.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        CantPanJtf.setEnabled(false);
        CantPanJtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantPanJtfActionPerformed(evt);
            }
        });
        CantPanJtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CantPanJtfKeyReleased(evt);
            }
        });

        totalJLabel.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        totalJLabel.setForeground(new java.awt.Color(1, 1, 1));
        totalJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalJLabel.setText("0");

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel5.setText("Cantidad de panes");

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setForeground(new java.awt.Color(164, 247, 49));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel2.setText("Recibí $:");

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel4.setText("Cambio $:");

        CambioJlabel.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        CambioJlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        recibiJTF.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        recibiJTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        recibiJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recibiJTFKeyReleased(evt);
            }
        });

        genCambioJBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/compra.png"))); // NOI18N
        genCambioJBtn.setText(" Generar cambio");
        genCambioJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genCambioJBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CambioJlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(recibiJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(genCambioJBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CambioJlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(recibiJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addComponent(jLabel4))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(genCambioJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panesIsuficientesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        panesIsuficientesLabel.setForeground(new java.awt.Color(221, 1, 1));
        panesIsuficientesLabel.setText("jLabel6");

        entregaPedidoJBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconfinder_delivery-cart-1_3338971.png"))); // NOI18N
        entregaPedidoJBtn.setText("Entregar pedido");
        entregaPedidoJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entregaPedidoJBtnActionPerformed(evt);
            }
        });

        deleteSelectedJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconfinder_error_1646012.png"))); // NOI18N
        deleteSelectedJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSelectedJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(731, 731, 731)
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(totalJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(entregaPedidoJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(RegPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(RegVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(264, 264, 264)
                                .addComponent(panesIsuficientesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addComponent(CbPan, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CantPanJtf, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(agregarJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(50, 50, 50))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteSelectedJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jLabel1)
                                .addGap(5, 5, 5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(agregarJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(CbPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CantPanJtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(panesIsuficientesLabel)
                .addGap(2, 2, 2)
                .addComponent(deleteSelectedJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalJLabel)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RegVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RegPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Regresar)
                    .addComponent(entregaPedidoJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void agregarJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarJBtnActionPerformed
        int idPanSelected = CbPan.getSelectedIndex();
        Pan panEncontrado = null;
       
        
        panEncontrado = panesExistencia.get(idPanSelected-1);
        
        if (null != panEncontrado){
            RegVenta nuevoregistro = new RegVenta();
            nuevoregistro.setIdPan(panEncontrado.getidPan());
            int cantidad = Integer.parseInt(CantPanJtf.getText());
            nuevoregistro.setCantidad(cantidad);
            float total = cantidad * panEncontrado.getcosto();
            nuevoregistro.setTotal(total);
            nuevoregistro.setIdTrabajador(empleadoLoggeado.getIdEmpleado());
            
            panesVendidos.add(nuevoregistro);
            
            //agregar a oa tabla
            Object row[] = new Object[4];
            row [0]= panEncontrado.getnombre();
            row [1]= panEncontrado.getcosto();
            row [2]= nuevoregistro.getCantidad();
            row [3]= nuevoregistro.getTotal(); //Tiene el total del seleccionado
            
            modelo.addRow(row);
            totalJLabel.setText(String.valueOf(calculaTotalTicket())); 
            CantPanJtf.setText("");
            CbPan.setSelectedIndex(0);
        } else{System.out.println("no encontro una mierda");}
    }//GEN-LAST:event_agregarJBtnActionPerformed

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
            ventanaMenu.thismissVentas();
    }//GEN-LAST:event_RegresarActionPerformed

    private void CbPanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbPanActionPerformed
       validaCantidadPan();
       if(CbPan.getSelectedIndex()>0){
           CantPanJtf.setEnabled(true);
       }else{
           CantPanJtf.setEnabled(false);
           CantPanJtf.setText("");
       }
    }//GEN-LAST:event_CbPanActionPerformed

    private void RegPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegPedidoActionPerformed
            ventanaRegPedido = new RegPedido(this, currentCorte, conexion);
            ventanaRegPedido.setVisible(true);
            this.setVisible(false);
    }//GEN-LAST:event_RegPedidoActionPerformed

    private void RegVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegVentaActionPerformed
        registraVenta();
        deleteSelectedJButton.setEnabled(false);
    }//GEN-LAST:event_RegVentaActionPerformed

    private void genCambioJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genCambioJBtnActionPerformed
        try {
            generarCambio();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingresa un monto");
        }
    }//GEN-LAST:event_genCambioJBtnActionPerformed

    private void CantPanJtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantPanJtfActionPerformed
        
    }//GEN-LAST:event_CantPanJtfActionPerformed

    private void CantPanJtfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantPanJtfKeyReleased
        // TODO add your handling code here:
        validaCantidadPan();
    }//GEN-LAST:event_CantPanJtfKeyReleased

    private void recibiJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recibiJTFKeyReleased
        // TODO add your handling code here:
        validaEntradaDinero();
        
    }//GEN-LAST:event_recibiJTFKeyReleased

    private void entregaPedidoJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entregaPedidoJBtnActionPerformed
        ventanaEntregaPedidos = new EntregaPedido(this, currentCorte, conexion);
        ventanaEntregaPedidos.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_entregaPedidoJBtnActionPerformed

    private void VentaPanJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VentaPanJTableMouseClicked
        int fila = VentaPanJTable.getSelectedRow();
        if(fila >= 0){
            deleteSelectedJButton.setEnabled(true);
        }else{
            deleteSelectedJButton.setEnabled(false);
        }
    }//GEN-LAST:event_VentaPanJTableMouseClicked

    private void deleteSelectedJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSelectedJButtonActionPerformed
        borraSeleccionado();
        deleteSelectedJButton.setEnabled(false);
    }//GEN-LAST:event_deleteSelectedJButtonActionPerformed

    public void validaEntradaDinero(){
        try {
            Integer.parseInt(recibiJTF.getText());
            genCambioJBtn.setEnabled(true);
        } catch (Exception e) {
            genCambioJBtn.setEnabled(false);
        }
    }
    
    public void validaCantidadPan(){
        try {
            int index = CbPan.getSelectedIndex()-1;
            
            //System.out.println("el valor es "+CantPanJtf.getText());
            int cantidad = Integer.parseInt(CantPanJtf.getText());
            if(index >= 0){
                int enExistencia = panesExistencia.get(index).getexistencia();
                if(panesExistencia.get(index).getexistencia()>=cantidad){
                    agregarJBtn.setEnabled(true);
                    panesIsuficientesLabel.setVisible(false);
                }else{
                    panesIsuficientesLabel.setText((enExistencia>0 ? "¡Solo hay "+enExistencia:"¡No hay")+" panes disponibles!");
                    panesIsuficientesLabel.setVisible(true);
                    agregarJBtn.setEnabled(false);
                }
            }else{
                panesIsuficientesLabel.setVisible(false);
                agregarJBtn.setEnabled(false);
            }
        } catch (Exception e) {
            panesIsuficientesLabel.setVisible(false);
            agregarJBtn.setEnabled(false);
        }
    }
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
            java.util.logging.Logger.getLogger(Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Venta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CambioJlabel;
    private javax.swing.JTextField CantPanJtf;
    private javax.swing.JComboBox<String> CbPan;
    private javax.swing.JButton RegPedido;
    private javax.swing.JButton RegVenta;
    private javax.swing.JButton Regresar;
    private javax.swing.JTable VentaPanJTable;
    private javax.swing.JButton agregarJBtn;
    private javax.swing.JButton deleteSelectedJButton;
    private javax.swing.JButton entregaPedidoJBtn;
    private javax.swing.JButton genCambioJBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel panesIsuficientesLabel;
    private javax.swing.JTextField recibiJTF;
    private javax.swing.JLabel totalJLabel;
    // End of variables declaration//GEN-END:variables
}
