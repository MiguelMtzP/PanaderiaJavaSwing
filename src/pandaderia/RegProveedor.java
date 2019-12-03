package pandaderia;

import Models.CorteModel;
import Models.Empleado;
import Models.GastoMateriaPrima;
import Models.MateriaPrima;
import Models.Proveedores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alexis
 */
public class RegProveedor extends javax.swing.JFrame {

    private Empleado empleadoLoggeado;
    private Menu ventanaMenu;
    private NuevoProveedor ventanaNuevoProveedor;
    private Conexion conexion;
    private CorteModel currentCorte;
    private Proveedores currentProveedor;
    private ArrayList<MateriaPrima> materiaPrimaExistente;
    private ArrayList<GastoMateriaPrima> gastosMTRegistrados;
    private ArrayList<Proveedores> proveedoresExistentes;
    
    public DefaultTableModel modelo ;
    public DefaultTableModel modeloProveedores ;
    
    public RegProveedor() {
        initComponents();
        setResizable(false);
    }
    
    public RegProveedor(Menu previewView, Empleado emp, Conexion c, CorteModel current){
        //this.setLocationRelativeTo(previewView);
        this.setLocation(previewView.getLocation());                
        initComponents();
        
        empleadoLoggeado = emp;
        currentCorte = current;
        
        materiaPrimaExistente = new ArrayList<MateriaPrima>(0);
        gastosMTRegistrados = new ArrayList<GastoMateriaPrima>(0);
        proveedoresExistentes = new ArrayList<Proveedores>(0);
        //clientesExistentes = new ArrayList<Clientes>(0);
        //currentPedido = new Pedido();
        conexion = c;
        
        cargaCB();
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
        cargaProveedores();
        
        setTitle("Registro proveedor");
        //cargaCB();
        this.ventanaMenu = previewView;
        setResizable(false);
        validaRegistroCompleto();
        validaNuevaMateriaPrimaFormulario();
    }
    
    public void validaNuevaMateriaPrimaFormulario(){
        try{
            float cUnitario = Float.parseFloat(precioUnitarioJTF.getText());
            agregarProductoJBtn.setEnabled(cUnitario > 0 && !unidadMedidaJTF.getText().isEmpty()&&!nombreProductoJTF.getText().isEmpty());
        }catch(Exception e){
            agregarProductoJBtn.setEnabled(false);
        }
    }
    
    public void validaCantProducto(){
        try {
            System.out.println("El valor es --->"+CantProductoJtf.getText());
            int cantidad = Integer.parseInt(CantProductoJtf.getText());
            //CantPan.setText(String.valueOf(cantidad));
            agregarJBtn.setEnabled(CbProducto.getSelectedIndex() > 0);
         } catch (Exception e) {
             agregarJBtn.setEnabled(false);
         }
    }
    
    public void thismissNewProveedor(){
        
        ventanaNuevoProveedor.setVisible(false);
        this.setVisible(true);
        ventanaNuevoProveedor = null;
    }
    
    public void cargaCB(){
        try {
            CbProducto.removeAllItems();
            CbProducto.addItem("Seleccione un producto");
            PreparedStatement pst = conexion.conectar.prepareStatement("select * from MateriaPrima order by nombre asc");
            ResultSet rs = pst.executeQuery();
            materiaPrimaExistente = new ArrayList<MateriaPrima>(0);
            while(rs.next()){
                MateriaPrima materiaPrima = new MateriaPrima();
                materiaPrima.setNombre(rs.getString("nombre"));
                materiaPrima.setCostoUnitario(rs.getFloat("cosUnitario"));
                materiaPrima.setUnidadMedida(rs.getString("uMedida"));
                materiaPrima.setIdMateriPrima(rs.getInt("id_MateriaPrima"));
                
                
                materiaPrimaExistente.add(materiaPrima);
                
                CbProducto.addItem(materiaPrima.getNombre());
                        
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR  "+e.getMessage());
        }
    }
    
    public void cargaProveedores(){
        try {
            PreparedStatement pst = conexion.conectar.prepareStatement("select * from Proveedor order by paterno asc");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Proveedores nuevoProveedor = new Proveedores();
                nuevoProveedor.setMaterno(rs.getString("materno"));
                nuevoProveedor.setPaterno(rs.getString("paterno"));
                nuevoProveedor.setNombre(rs.getString("nombre"));
                nuevoProveedor.setTelefono(rs.getString("telefono"));
                nuevoProveedor.setCorreo(rs.getString("correo"));
                nuevoProveedor.setIdProveedor(rs.getInt("id_Proveedor"));
                proveedoresExistentes.add(nuevoProveedor);
                
                Object row[] = new Object[4];
                row[0] = nuevoProveedor.getPaterno();
                row[1] = nuevoProveedor.getMaterno();
                row[2] = nuevoProveedor.getNombre();
                row[3] = nuevoProveedor.getTelefono();
                
                modeloProveedores.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR!");
            System.out.println(ex);        
        }
    }
       
    
    public void seleccionaNuevoProveedorRegistrado(Proveedores nuevoProveedor){
        proveedoresExistentes.add(nuevoProveedor);
        String nombreCompleto = nuevoProveedor.getPaterno()+ " "+nuevoProveedor.getMaterno()+" "+nuevoProveedor.getNombre();
        nombreBuscaProveedorJTF.setText(nombreCompleto);
        idProveedorSelectedJTF.setText(String.valueOf(nuevoProveedor.getIdProveedor()));
        
        Object row[] = new Object[4];
        row[0] = nuevoProveedor.getPaterno();
        row[1] = nuevoProveedor.getMaterno();
        row[2] = nuevoProveedor.getNombre();

        modeloProveedores.addRow(row);
        
        currentProveedor  = nuevoProveedor;
        
        selectedProveedorJTF.setText(nombreCompleto);

        ventanaNuevoProveedor.setVisible(false);
        this.setVisible(true);
        ventanaNuevoProveedor = null;
        validaRegistroCompleto();
    }
    
    public void calculaTotal(){
        float total = 0;
        for (GastoMateriaPrima gasto : gastosMTRegistrados) {
            total += gasto.getTotalCosto();
        }
        totalGastosJL.setText(String.valueOf(Math.round(total*100)/100.0));
    }
    
    
    public void validaRegistroCompleto(){
        if(currentProveedor != null
            && gastosMTRegistrados.size()>0
        ){
            addProveedorJButton.setEnabled(true);
        }else{
            addProveedorJButton.setEnabled(false);
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
        CbProducto = new javax.swing.JComboBox<>();
        agregarJBtn = new javax.swing.JButton();
        addProveedorJButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Regresar = new javax.swing.JButton();
        CantProductoJtf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        totalGastosJL = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemsPedidoJTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        clientesJTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        nombreBuscaProveedorJTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        idProveedorSelectedJTF = new javax.swing.JTextField();
        nuevoProveedorJBtn = new javax.swing.JButton();
        invalidIdJLabel = new javax.swing.JLabel();
        selectedProveedorJTF = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        nombreProductoJTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        precioUnitarioJTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        unidadMedidaJTF = new javax.swing.JTextField();
        agregarProductoJBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NUEVO PROVEEDOR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans Mono", 1, 14), new java.awt.Color(56, 27, 27))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel1.setText("Producto");

        CbProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbProductoActionPerformed(evt);
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
        addProveedorJButton.setText("Agregar provisión");
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

        CantProductoJtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CantProductoJtfKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cantidad");

        totalGastosJL.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        totalGastosJL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalGastosJL.setText("0");

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

        nombreBuscaProveedorJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreBuscaProveedorJTFKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("ID proveedor");

        idProveedorSelectedJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idProveedorSelectedJTFKeyReleased(evt);
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
                        .addComponent(nombreBuscaProveedorJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invalidIdJLabel)
                            .addComponent(idProveedorSelectedJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(nombreBuscaProveedorJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(idProveedorSelectedJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        nombreProductoJTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreProductoJTFActionPerformed(evt);
            }
        });
        nombreProductoJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreProductoJTFKeyReleased(evt);
            }
        });

        jLabel5.setText("Precio unitario:");

        precioUnitarioJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                precioUnitarioJTFKeyReleased(evt);
            }
        });

        jLabel6.setText("Unidad de medida:");

        unidadMedidaJTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unidadMedidaJTFActionPerformed(evt);
            }
        });
        unidadMedidaJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                unidadMedidaJTFKeyReleased(evt);
            }
        });

        agregarProductoJBtn.setText("Agregar producto");
        agregarProductoJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoJBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(unidadMedidaJTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(precioUnitarioJTF, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreProductoJTF)))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(agregarProductoJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nombreProductoJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(precioUnitarioJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(unidadMedidaJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(agregarProductoJBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalGastosJL, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addProveedorJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CantProductoJtf, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(agregarJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(selectedProveedorJTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())))
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
                                    .addComponent(CbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CantProductoJtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addProveedorJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(totalGastosJL)
                                .addComponent(jLabel2))
                            .addComponent(Regresar, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
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

    private void CantProductoJtfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantProductoJtfKeyReleased
        validaCantProducto();
    }//GEN-LAST:event_CantProductoJtfKeyReleased

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        ventanaMenu.thismissRegProveedor();
    }//GEN-LAST:event_RegresarActionPerformed

    private void addProveedorJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProveedorJButtonActionPerformed
        try {
            
            for (GastoMateriaPrima gasto : gastosMTRegistrados) {                
                PreparedStatement pst = conexion.conectar.prepareStatement("insert into GastoMateriaPrima "
                                                         +"(totalCosto,cantidadProducto,id_Corte,id_Proveedor,id_MateriaPrima,fecha) "
                                                         +"values(?,?,?,?,?, now())");
                pst.setFloat(1, gasto.getTotalCosto());
                pst.setInt(2, gasto.getCantProducto());
                pst.setInt(3, gasto.getIdCorte());
                pst.setInt(4, currentProveedor.getIdProveedor());
                pst.setInt(5, gasto.getMateriaPrima().getIdMateriPrima());
                pst.execute();
            }
            JOptionPane.showMessageDialog(null, "Registro Guardado exitosamente!");
            ventanaMenu.thismissRegProveedor();
        } catch (SQLException ex) {
            System.out.println("ERROR:  "+ex);
        }
    }//GEN-LAST:event_addProveedorJButtonActionPerformed

    private void agregarJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarJBtnActionPerformed
        int idProdSelected = CbProducto.getSelectedIndex();
        MateriaPrima MTEncontrado = materiaPrimaExistente.get(idProdSelected-1);
      
        
        if (null != MTEncontrado){
            
            int cantidad = Integer.parseInt(CantProductoJtf.getText());
            GastoMateriaPrima nuevoItem = new GastoMateriaPrima();
            nuevoItem.setCantProducto(cantidad);
            nuevoItem.setIdCorte(currentCorte.getIdCorte());
            nuevoItem.setMateriaPrima(MTEncontrado);
            nuevoItem.setTotalCosto(MTEncontrado.getCostoUnitario()*cantidad);
           
            //agregar a oa tabla
            Object row[] = new Object[5];
            row [0]= nuevoItem.getMateriaPrima().getNombre();
            row [1]= nuevoItem.getCantProducto();
            row [2]= nuevoItem.getMateriaPrima().getCostoUnitario();
            row [3]= nuevoItem.getMateriaPrima().getUnidadMedida();
            row [4]= nuevoItem.getTotalCosto();
            
            modelo.addRow(row);
            
            gastosMTRegistrados.add(nuevoItem);
            CantProductoJtf.setText("");
            CbProducto.setSelectedIndex(0);
            calculaTotal();
            validaRegistroCompleto();
        }
    }//GEN-LAST:event_agregarJBtnActionPerformed

    private void CbProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbProductoActionPerformed
        validaCantProducto();
    }//GEN-LAST:event_CbProductoActionPerformed

    private void nuevoProveedorJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoProveedorJBtnActionPerformed
        // TODO add your handling code here:
        this.ventanaNuevoProveedor = new NuevoProveedor(this,empleadoLoggeado);
        ventanaNuevoProveedor.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_nuevoProveedorJBtnActionPerformed

    private void idProveedorSelectedJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idProveedorSelectedJTFKeyReleased
        // TODO add your handling code here:
        try{
            ArrayList<Proveedores> filtrados = new ArrayList<Proveedores>(0);
            int input = Integer.parseInt(idProveedorSelectedJTF.getText());
            invalidIdJLabel.setVisible(false);
            for (Proveedores proveedor : proveedoresExistentes) {
                if (proveedor.getIdProveedor()== input){
                    filtrados.add(proveedor);
                }
            }
            if(filtrados.size()>0){
                modeloProveedores.setRowCount(0);
                for (Proveedores filtrado : filtrados) {
                    Object row[] = new Object[4];
                    row[0] = filtrado.getPaterno();
                    row[1] = filtrado.getMaterno();
                    row[2] = filtrado.getNombre();
                    row[3] = filtrado.getTelefono();
                    modeloProveedores.addRow(row);
                }
            }else if(!idProveedorSelectedJTF.getText().isEmpty())
            modeloProveedores.setRowCount(0);

        }catch(NumberFormatException e){
            modeloProveedores.setRowCount(0);
            if(idProveedorSelectedJTF.getText().isEmpty()){
                for (Proveedores filtrado : proveedoresExistentes) {
                    Object row[] = new Object[4];
                    row[0] = filtrado.getPaterno();
                    row[1] = filtrado.getMaterno();
                    row[2] = filtrado.getNombre();
                    row[3] = filtrado.getTelefono();
                    modeloProveedores.addRow(row);
                }
            }else{
                invalidIdJLabel.setVisible(true);
            }
        }
    }//GEN-LAST:event_idProveedorSelectedJTFKeyReleased

    private void nombreBuscaProveedorJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreBuscaProveedorJTFKeyReleased
        
        ArrayList<Proveedores> filtrados = new ArrayList<Proveedores>(0);
        String input = nombreBuscaProveedorJTF.getText();
        for (Proveedores proveedor : proveedoresExistentes) {
            if (input.isEmpty()
                ||proveedor.getMaterno().toUpperCase().contains(input.toUpperCase())
                ||proveedor.getPaterno().toUpperCase().contains(input.toUpperCase())
                ||proveedor.getNombre().toUpperCase().contains(input.toUpperCase())){
                filtrados.add(proveedor);
            }
        }
        if(filtrados.size()>0){
            modeloProveedores.setRowCount(0);
            for (Proveedores filtrado : filtrados) {
                Object row[] = new Object[4];
                row[0] = filtrado.getPaterno();
                row[1] = filtrado.getMaterno();
                row[2] = filtrado.getNombre();
                row[3] = filtrado.getTelefono();

                modeloProveedores.addRow(row);
            }
        }else if(!nombreBuscaProveedorJTF.getText().isEmpty())
        modeloProveedores.setRowCount(0);
    }//GEN-LAST:event_nombreBuscaProveedorJTFKeyReleased

    private void clientesJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesJTableMouseClicked
        
        int id = clientesJTable.getSelectedRow();
        if (id >= 0){
            currentProveedor  = proveedoresExistentes.get(id);
            selectedProveedorJTF.setText(proveedoresExistentes.get(id).fullName());
            nombreBuscaProveedorJTF.setText(proveedoresExistentes.get(id).fullName());
            idProveedorSelectedJTF.setText(String.valueOf(proveedoresExistentes.get(id).getIdProveedor()));
        }
        validaRegistroCompleto();
    }//GEN-LAST:event_clientesJTableMouseClicked

    private void nombreProductoJTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreProductoJTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreProductoJTFActionPerformed

    private void unidadMedidaJTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unidadMedidaJTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unidadMedidaJTFActionPerformed

    private void agregarProductoJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarProductoJBtnActionPerformed
        try {
            PreparedStatement pst = conexion.conectar.prepareStatement("INSERT INTO MateriaPrima ( nombre, cosUnitario, uMedida) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);
            
            MateriaPrima nuevaMateriaPrima = new MateriaPrima();
            nuevaMateriaPrima.setNombre(nombreProductoJTF.getText());
            nuevaMateriaPrima.setCostoUnitario(Float.parseFloat(precioUnitarioJTF.getText()));
            nuevaMateriaPrima.setUnidadMedida(unidadMedidaJTF.getText());
            pst.setString(1, nuevaMateriaPrima.getNombre());
            pst.setFloat(2, nuevaMateriaPrima.getCostoUnitario());
            pst.setString(3, nuevaMateriaPrima.getUnidadMedida());
            pst.execute();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                nuevaMateriaPrima.setIdMateriPrima(idGenerado);
                cargaCB();
                seleccionaCB(idGenerado);
            }
            
            JOptionPane.showMessageDialog(null, "GUARDADO!");
           
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR!");

        }
    }//GEN-LAST:event_agregarProductoJBtnActionPerformed

    private void unidadMedidaJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unidadMedidaJTFKeyReleased
        validaNuevaMateriaPrimaFormulario();
    }//GEN-LAST:event_unidadMedidaJTFKeyReleased

    private void precioUnitarioJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioUnitarioJTFKeyReleased
        validaNuevaMateriaPrimaFormulario();
    }//GEN-LAST:event_precioUnitarioJTFKeyReleased

    private void nombreProductoJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreProductoJTFKeyReleased
        validaNuevaMateriaPrimaFormulario();
    }//GEN-LAST:event_nombreProductoJTFKeyReleased
    
    public void seleccionaCB(int id){
        int indexArreglo = 0;
        
        for (int i = 0; i < materiaPrimaExistente.size(); i++) {
            if(materiaPrimaExistente.get(i).getIdMateriPrima() == id){
                indexArreglo = i+1;
                break;
            }
        }
        
        CbProducto.setSelectedIndex(indexArreglo);
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
    private javax.swing.JTextField CantProductoJtf;
    private javax.swing.JComboBox<String> CbProducto;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton addProveedorJButton;
    private javax.swing.JButton agregarJBtn;
    private javax.swing.JButton agregarProductoJBtn;
    private javax.swing.JTable clientesJTable;
    private javax.swing.JTextField idProveedorSelectedJTF;
    private javax.swing.JLabel invalidIdJLabel;
    private javax.swing.JTable itemsPedidoJTable;
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
    private javax.swing.JTextField nombreBuscaProveedorJTF;
    private javax.swing.JTextField nombreProductoJTF;
    private javax.swing.JButton nuevoProveedorJBtn;
    private javax.swing.JTextField precioUnitarioJTF;
    private javax.swing.JLabel selectedProveedorJTF;
    private javax.swing.JLabel totalGastosJL;
    private javax.swing.JTextField unidadMedidaJTF;
    // End of variables declaration//GEN-END:variables
}
