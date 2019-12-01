package pandaderia;

import Models.Clientes;
import Models.CorteModel;
import Models.RegistroPedido;
import Models.Empleado;
import Models.ItemPedido;
import Models.Pan;
import Models.Pedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alexis
 */
public class RegProveedor extends javax.swing.JFrame {

    private Empleado empleadoLoggeado;
    private Menu ventanaMenu;
    private AddPedido ventanaAddPedido;
    private NuevoProveedor ventanaNuevoProveedor;
    private Conexion conexion;
    private CorteModel currentCorte;
    private Clientes currentSelectedCliente;
    
    public DefaultTableModel modelo ;
    public DefaultTableModel modeloProveedores ;
    
    public RegProveedor() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public RegProveedor(Menu previewView, Empleado emp, Conexion c, CorteModel current){
        empleadoLoggeado = emp;
        currentCorte = current;
        //clientesExistentes = new ArrayList<Clientes>(0);
        //currentPedido = new Pedido();
        conexion = c;
        
        initComponents();
        setLocationRelativeTo(previewView);
        modelo = new DefaultTableModel();
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Costo unitario");
        modelo.addColumn("Unidad de medida");
        modelo.addColumn("Total");
        itemsPedidoJTable.setModel(modelo);
        invalidIdJLabel.setVisible(false);

        modeloProveedores =  new DefaultTableModel();
        modeloProveedores.addColumn("Apellido Paterno");
        modeloProveedores.addColumn("Apellido Materno");
        modeloProveedores.addColumn("Nombre");
        modeloProveedores.addColumn("Teléfono");
        clientesJTable.setModel(modeloProveedores);
        //cargaClientes();
        
        setTitle("Registro proveedor");
        //cargaCB();
        this.ventanaMenu = previewView;
        this.setVisible(rootPaneCheckingEnabled);
        setResizable(false);
    }
    
    public void validaCantPan(){
        try {
            System.out.println("El valor es --->"+CantPanJtf.getText());
            int cantidad = Integer.parseInt(CantPanJtf.getText());
            //CantPan.setText(String.valueOf(cantidad));
            agregarJBtn.setEnabled(CbPan.getSelectedIndex() > 0);
         } catch (Exception e) {
             agregarJBtn.setEnabled(false);
         }
    }
    
    public void thismissNewProveedor(){
        
        ventanaNuevoProveedor.setVisible(false);
        this.setVisible(true);
        ventanaNuevoProveedor = null;
    }
    
    /*public void cargaClientes(){
        try {
            PreparedStatement pst = conexion.conectar.prepareStatement("select * from Cliente order by paterno asc");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Clientes nuevoCliente = new Clientes();
                nuevoCliente.setAppMat(rs.getString("materno"));
                nuevoCliente.setAppPat(rs.getString("paterno"));
                nuevoCliente.setnombre(rs.getString("nombre"));
                nuevoCliente.setTelefono(rs.getString("telefono"));
                nuevoCliente.setCorreo(rs.getString("correo"));
                nuevoCliente.setEmpresa(rs.getString("empresa"));
                nuevoCliente.setIdCliente(rs.getInt("id_Cliente"));
                clientesExistentes.add(nuevoCliente);
                
                Object row[] = new Object[4];
                row[0] = nuevoCliente.getAppPat();
                row[1] = nuevoCliente.getAppMat();
                row[2] = nuevoCliente.getnombre();
                row[3] = nuevoCliente.getEmpresa();
                
                modeloClientes.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR!");
            System.out.println(ex);        
        }
    }*/
    
    /*public void cargaCB(){
        
                //System.out.println("aaaaa");
        try {
                PreparedStatement pst= conexion.conectar.prepareStatement("SELECT * FROM Inventario order by nombre asc ");
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
    }*/
    
    
    /*public void seleccionaNuevoClienteRegistrado(Clientes nuevoCliente){
        clientesExistentes.add(nuevoCliente);
        String nombreCompleto = nuevoCliente.getAppPat() + " "+nuevoCliente.getAppMat()+" "+nuevoCliente.getnombre();
        nombreBuscaClienteJTF.setText(nombreCompleto);
        idClienteSelectedJTF.setText(String.valueOf(nuevoCliente.getIdCliente()));
        
        Object row[] = new Object[4];
        row[0] = nuevoCliente.getAppPat();
        row[1] = nuevoCliente.getAppMat();
        row[2] = nuevoCliente.getnombre();
        row[3] = nuevoCliente.getEmpresa();

        modeloClientes.addRow(row);
        
        currentSelectedCliente  = nuevoCliente;
        
        //currentPedido.setCliente(nuevoCliente);
        selectedProveedorJTF.setText(nuevoCliente.FullName());

        //ventanaNuevoCliente.setVisible(false);
        this.setVisible(true);
        //ventanaNuevoCliente = null;
    }*/
    
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
        CbPan = new javax.swing.JComboBox<>();
        agregarJBtn = new javax.swing.JButton();
        addProveedorJButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Regresar = new javax.swing.JButton();
        CantPanJtf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        totalPedidoJL = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemsPedidoJTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        clientesJTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        nombreBuscaClienteJTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        idClienteSelectedJTF = new javax.swing.JTextField();
        nuevoProveedorJBtn = new javax.swing.JButton();
        invalidIdJLabel = new javax.swing.JLabel();
        selectedProveedorJTF = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NUEVO PROVEEDOR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans Mono", 1, 14), new java.awt.Color(56, 27, 27))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel1.setText("Producto");

        CbPan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbPanActionPerformed(evt);
            }
        });

        agregarJBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iconfinder_sign-check_299110.png"))); // NOI18N
        agregarJBtn.setEnabled(false);
        agregarJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarJBtnActionPerformed(evt);
            }
        });

        addProveedorJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add_Pedido.png"))); // NOI18N
        addProveedorJButton.setText("Agregar provicion");
        addProveedorJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProveedorJButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel2.setText("TOTAL:");

        Regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/regresar.png"))); // NOI18N
        Regresar.setText("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        CantPanJtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CantPanJtfKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cantidad");

        totalPedidoJL.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        totalPedidoJL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalPedidoJL.setText("0");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panes agregados al pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(113, 113, 113))); // NOI18N

        itemsPedidoJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Precio unitario", "Unidad de medida", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(itemsPedidoJTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca y  selecciona proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gargi", 1, 12), new java.awt.Color(155, 96, 96))); // NOI18N

        clientesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Apellido Paterno", "Apellido Materno", "Nombre", "Teléfono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        clientesJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientesJTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(clientesJTable);

        jLabel10.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Nombre");

        nombreBuscaClienteJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreBuscaClienteJTFKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("ID proveedor");

        idClienteSelectedJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idClienteSelectedJTFKeyReleased(evt);
            }
        });

        nuevoProveedorJBtn.setText("Nuevo proveedor");
        nuevoProveedorJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoProveedorJBtnActionPerformed(evt);
            }
        });

        invalidIdJLabel.setFont(new java.awt.Font("Ubuntu", 3, 12)); // NOI18N
        invalidIdJLabel.setForeground(new java.awt.Color(242, 46, 46));
        invalidIdJLabel.setText("invalid ID");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreBuscaClienteJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invalidIdJLabel)
                            .addComponent(idClienteSelectedJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(nuevoProveedorJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(nombreBuscaClienteJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(idClienteSelectedJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invalidIdJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nuevoProveedorJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        selectedProveedorJTF.setFont(new java.awt.Font("Chandas", 0, 14)); // NOI18N
        selectedProveedorJTF.setForeground(new java.awt.Color(29, 136, 144));
        selectedProveedorJTF.setText("Selecciona un proveedor...");

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 22)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Proveedor");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel4.setText("Nombre del producto:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Precio unitario:");

        jLabel6.setText("Unidad de medida:");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton2.setText("Agregar producto");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CbPan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CantPanJtf, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agregarJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectedProveedorJTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalPedidoJL, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addProveedorJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(CbPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CantPanJtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(agregarJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedProveedorJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(totalPedidoJL)
                    .addComponent(addProveedorJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Regresar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CantPanJtfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantPanJtfKeyReleased
        //validaCantPan();
    }//GEN-LAST:event_CantPanJtfKeyReleased

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        //ventanaVenta.thismissRegPedido();
    }//GEN-LAST:event_RegresarActionPerformed

    private void addProveedorJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProveedorJButtonActionPerformed
        /*try {
            PreparedStatement pst = conexion.conectar.prepareStatement("insert into Pedidos "+
                                                                        "(id_Trabajador,id_corte,id_Cliente,costo,abono,tipo,fecha_entrega,fecha_creacion)"+
                                                                        " values (?,?,?,?,?,?,?, now())",Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, currentCorte.getIdTrabajador().getIdEmpleado());
            pst.setInt(2, currentCorte.getIdCorte());
            pst.setInt(3, currentPedido.getCliente().getIdCliente());
            pst.setFloat(4, currentPedido.getCosto());
            pst.setFloat(5, currentPedido.getAbono());
            pst.setFloat(6, currentPedido.getTipo());
            pst.setDate(7, currentPedido.getFechaEntrega());
            pst.execute();
            ResultSet idNuevoPedido = pst.getGeneratedKeys();
            if (idNuevoPedido.next()){
                currentPedido.setIdPedido(idNuevoPedido.getInt(1));
                for (ItemPedido itemPedido : currentPedido.getItemsEnPedido()) {                
                    pst = conexion.conectar.prepareStatement("insert into Pedidos_Inventario "
                                                             +"(id_Pan,id_Pedidos,cantidad) "
                                                             +"values(?,?,?)");
                    pst.setInt(1, itemPedido.getPan().getidPan());
                    pst.setInt(2, currentPedido.getIdPedido());
                    pst.setInt(3, itemPedido.getCantidad());
                    pst.execute();
                }
                
            }else System.out.println("valio vergaa!!!!");
            JOptionPane.showMessageDialog(null, "Registro Guardado exitosamente!");
            ventanaVenta.thismissRegPedido();
        } catch (SQLException ex) {
            System.out.println("ERROR:  "+ex);
        }*/
    }//GEN-LAST:event_addProveedorJButtonActionPerformed

    private void agregarJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarJBtnActionPerformed
        /*int idPanSelected = CbPan.getSelectedIndex();
        Pan panEncontrado = panesExistencia.get(idPanSelected-1);
      
        
        if (null != panEncontrado){
            
            int cantidad = Integer.parseInt(CantPanJtf.getText());
            ItemPedido nuevoItem = new ItemPedido();
            nuevoItem.setCantidad(cantidad);
            nuevoItem.setPan(panEncontrado);
            float total = cantidad * panEncontrado.getcosto();
            //currentPedido.setCosto(total+currentPedido.getCosto());
            //totalPedidoJL.setText(String.valueOf(currentPedido.getCosto()));
            //currentPedido.getItemsEnPedido().add(nuevoItem);
            
            //agregar a oa tabla
            Object row[] = new Object[4];
            row [0]= panEncontrado.getnombre();
            row [1]= panEncontrado.getcosto();
            row [2]= nuevoItem.getCantidad();
            row [3]= total;
            
            modelo.addRow(row);
            CantPanJtf.setText("");
            CbPan.setSelectedIndex(0);
        }*/ 
    }//GEN-LAST:event_agregarJBtnActionPerformed

    private void CbPanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbPanActionPerformed
        validaCantPan();
    }//GEN-LAST:event_CbPanActionPerformed

    private void nuevoProveedorJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoProveedorJBtnActionPerformed
        // TODO add your handling code here:
        this.ventanaNuevoProveedor = new NuevoProveedor(this,empleadoLoggeado);
        ventanaNuevoProveedor.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_nuevoProveedorJBtnActionPerformed

    private void idClienteSelectedJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idClienteSelectedJTFKeyReleased
        // TODO add your handling code here:
        /*try{
            ArrayList<Clientes> filtrados = new ArrayList<Clientes>(0);
            int input = Integer.parseInt(idClienteSelectedJTF.getText());
            invalidIdJLabel.setVisible(false);
            for (Clientes cliente : clientesExistentes) {
                if (cliente.getIdCliente() == input){
                    filtrados.add(cliente);
                }
            }
            if(filtrados.size()>0){
                modeloClientes.setRowCount(0);
                for (Clientes filtrado : filtrados) {
                    Object row[] = new Object[4];
                    row[0] = filtrado.getAppPat();
                    row[1] = filtrado.getAppMat();
                    row[2] = filtrado.getnombre();
                    row[3] = filtrado.getEmpresa();
                    modeloClientes.addRow(row);
                }
            }else if(!idClienteSelectedJTF.getText().isEmpty())
            modeloClientes.setRowCount(0);

        }catch(NumberFormatException e){
            modeloClientes.setRowCount(0);
            if(idClienteSelectedJTF.getText().isEmpty()){
                for (Clientes cliente : clientesExistentes) {
                    Object row[] = new Object[4];
                    row[0] = cliente.getAppPat();
                    row[1] = cliente.getAppMat();
                    row[2] = cliente.getnombre();
                    row[3] = cliente.getEmpresa();
                    modeloClientes.addRow(row);
                }
            }else{
                invalidIdJLabel.setVisible(true);
            }
        }*/
    }//GEN-LAST:event_idClienteSelectedJTFKeyReleased

    private void nombreBuscaClienteJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreBuscaClienteJTFKeyReleased
        /*
        ArrayList<Clientes> filtrados = new ArrayList<Clientes>(0);
        String input = nombreBuscaClienteJTF.getText();
        for (Clientes cliente : clientesExistentes) {
            if (input.isEmpty()
                ||cliente.getAppMat().contains(input)
                ||cliente.getAppPat().contains(input)
                ||cliente.getnombre().contains(input)){
                filtrados.add(cliente);
            }
        }
        if(filtrados.size()>0){
            modeloClientes.setRowCount(0);
            for (Clientes filtrado : filtrados) {
                Object row[] = new Object[4];
                row[0] = filtrado.getAppPat();
                row[1] = filtrado.getAppMat();
                row[2] = filtrado.getnombre();
                row[3] = filtrado.getEmpresa();

                modeloClientes.addRow(row);
            }
        }else if(!nombreBuscaClienteJTF.getText().isEmpty())
        modeloClientes.setRowCount(0);*/
    }//GEN-LAST:event_nombreBuscaClienteJTFKeyReleased

    private void clientesJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesJTableMouseClicked
        /*
        int id = clientesJTable.getSelectedRow();
        if (id >= 0){
            currentSelectedCliente  = clientesExistentes.get(id);
            selectedProveedorJTF.setText(clientesExistentes.get(id).FullName());
            nombreBuscaClienteJTF.setText(clientesExistentes.get(id).FullName());
            idClienteSelectedJTF.setText(String.valueOf(clientesExistentes.get(id).getIdCliente()));
            //currentPedido.setCliente(currentSelectedCliente);
        }*/
    }//GEN-LAST:event_clientesJTableMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed
    
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
            java.util.logging.Logger.getLogger(RegProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegProveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CantPanJtf;
    private javax.swing.JComboBox<String> CbPan;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton addProveedorJButton;
    private javax.swing.JButton agregarJBtn;
    private javax.swing.JTable clientesJTable;
    private javax.swing.JTextField idClienteSelectedJTF;
    private javax.swing.JLabel invalidIdJLabel;
    private javax.swing.JTable itemsPedidoJTable;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField nombreBuscaClienteJTF;
    private javax.swing.JButton nuevoProveedorJBtn;
    private javax.swing.JLabel selectedProveedorJTF;
    private javax.swing.JLabel totalPedidoJL;
    // End of variables declaration//GEN-END:variables
}