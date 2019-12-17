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
import javax.swing.table.TableRowSorter;

/**
 *
 * @author alexis
 */
public class RegPedido extends javax.swing.JFrame {

    private Empleado empleadoLoggeado; 
    private Venta ventanaVenta;
    //private AddPedido ventanaAddPedido;
    private NuevoClient ventanaNuevoCliente;
    private ArrayList <Pan> panesExistencia;
    private ArrayList <Clientes> clientesExistentes;
    private Conexion conexion;
    private CorteModel currentCorte;
    private Pedido currentPedido;
    private ButtonGroup tipoPedidoBtnGrp;
    private Clientes currentSelectedCliente;
    
    public DefaultTableModel modelo ;
    public DefaultTableModel modeloClientes ;    
    
    public RegPedido() {
        initComponents();
        //setTitle("Registro pedido");
        setLocationRelativeTo(null);
        setResizable(false);
        cargaCB();
    }
    
    public RegPedido(Venta previewView, CorteModel currentCorte,Conexion cc){
        empleadoLoggeado = currentCorte.getIdTrabajador();
        this.currentCorte = currentCorte;
        panesExistencia = new ArrayList<Pan>(0);
        clientesExistentes = new ArrayList<Clientes>(0);
        currentPedido = new Pedido();
        conexion = cc;
        currentSelectedCliente = null;
        initComponents();
        validaRegistroCompleto();
        tipoPedidoBtnGrp = new ButtonGroup();
        tipoPedidoBtnGrp.add(tipoExternoJRadioButton);
        tipoPedidoBtnGrp.add(tipoNormalJRadioButton);
        fechaEntregaJCalendar.setMinSelectableDate(new Date());
        setLocationRelativeTo(previewView);
        modelo = new DefaultTableModel();
        modelo.addColumn("Pan");
        modelo.addColumn("Precio");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        itemsPedidoJTable.setModel(modelo);
        

        modeloClientes =  new DefaultTableModel();
        modeloClientes.addColumn("Apellido Paterno");
        modeloClientes.addColumn("Apellido Materno");
        modeloClientes.addColumn("Nombre");
        modeloClientes.addColumn("Empresa");
        clientesJTable.setModel(modeloClientes);
        cargaClientes();
        
        setTitle("Registrar pedido");
        cargaCB();
        this.ventanaVenta = previewView;
        this.setVisible(rootPaneCheckingEnabled);
        setResizable(false);
        validaCantidadPan();
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
       
    public void cargaClientes(){
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
            System.out.println(ex);        }

    }
    
    public void cargaCB(){
        
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
    }
    
    public void seleccionaNuevoClienteRegistrado(Clientes nuevoCliente){
        clientesExistentes.add(nuevoCliente);
        String nombreCompleto = nuevoCliente.getAppPat() + " "+nuevoCliente.getAppMat()+" "+nuevoCliente.getnombre();
        nombreBuscaClienteJTF.setText(nombreCompleto);
        //idClienteSelectedJTF.setText(String.valueOf(nuevoCliente.getIdCliente()));
        
        Object row[] = new Object[4];
        row[0] = nuevoCliente.getAppPat();
        row[1] = nuevoCliente.getAppMat();
        row[2] = nuevoCliente.getnombre();
        row[3] = nuevoCliente.getEmpresa();

        modeloClientes.addRow(row);
        
        currentSelectedCliente  = nuevoCliente;
        
        currentPedido.setCliente(nuevoCliente);
        selectedClienteJTF.setText(nuevoCliente.FullName());
        
        
        ventanaNuevoCliente.setVisible(false);
        this.setVisible(true);
        ventanaNuevoCliente = null;
        validaRegistroCompleto();
    }
    
    public void thismissNewClient(){
        
        ventanaNuevoCliente.setVisible(false);
        this.setVisible(true);
        ventanaNuevoCliente = null;
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
        CbPan = new javax.swing.JComboBox<>();
        agregarJBtn = new javax.swing.JButton();
        addPedidoJButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Regresar = new javax.swing.JButton();
        CantPanJtf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        totalPedidoJL = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemsPedidoJTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        anticipoJTF = new javax.swing.JTextField();
        fechaEntregaJCalendar = new com.toedter.calendar.JDateChooser();
        tipoExternoJRadioButton = new javax.swing.JRadioButton();
        tipoNormalJRadioButton = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        clientesJTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        nombreBuscaClienteJTF = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        selectedClienteJTF = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panesIsuficientesLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NUEVO PEDIDO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans Mono", 1, 14), new java.awt.Color(56, 27, 27))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel1.setText("Pan");

        CbPan.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
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

        addPedidoJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add_Pedido.png"))); // NOI18N
        addPedidoJButton.setText("Agregar Pedido");
        addPedidoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPedidoJButtonActionPerformed(evt);
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

        CantPanJtf.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        CantPanJtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CantPanJtfKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cantidad de panes");

        totalPedidoJL.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        totalPedidoJL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalPedidoJL.setText("0");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panes agregados al pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(113, 113, 113))); // NOI18N

        itemsPedidoJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Pan", "Cantidad", "Precio", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configura el pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gargi", 1, 12), new java.awt.Color(155, 96, 96))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Fecha de entrega");

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tipo de pedido");

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Anticipo");

        anticipoJTF.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        anticipoJTF.setText("0");
        anticipoJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                anticipoJTFKeyReleased(evt);
            }
        });

        fechaEntregaJCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaEntregaJCalendarPropertyChange(evt);
            }
        });

        tipoExternoJRadioButton.setText("Externo");
        tipoExternoJRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipoExternoJRadioButtonMouseClicked(evt);
            }
        });
        tipoExternoJRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoExternoJRadioButtonActionPerformed(evt);
            }
        });

        tipoNormalJRadioButton.setText("Normal");
        tipoNormalJRadioButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipoNormalJRadioButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anticipoJTF)
                    .addComponent(fechaEntregaJCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipoNormalJRadioButton)
                            .addComponent(tipoExternoJRadioButton))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(fechaEntregaJCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(anticipoJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(tipoExternoJRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(tipoNormalJRadioButton)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(34, 34, 34))))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca y  Selecciona Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gargi", 1, 12), new java.awt.Color(155, 96, 96))); // NOI18N

        clientesJTable.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        clientesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Apellido Paterno", "Apellido Materno", "Nombre", "Empresa"
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

        nombreBuscaClienteJTF.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        nombreBuscaClienteJTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreBuscaClienteJTFActionPerformed(evt);
            }
        });
        nombreBuscaClienteJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreBuscaClienteJTFKeyReleased(evt);
            }
        });

        jButton1.setText("Nuevo Cliente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreBuscaClienteJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 284, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(nombreBuscaClienteJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

        selectedClienteJTF.setFont(new java.awt.Font("Chandas", 0, 14)); // NOI18N
        selectedClienteJTF.setForeground(new java.awt.Color(29, 136, 144));
        selectedClienteJTF.setText("Selecciona un cliente...");

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 22)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Cliente");

        panesIsuficientesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        panesIsuficientesLabel.setForeground(new java.awt.Color(255, 47, 0));
        panesIsuficientesLabel.setText("jLabel4");

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
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CbPan, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CantPanJtf, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(agregarJBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panesIsuficientesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectedClienteJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalPedidoJL, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addPedidoJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CbPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectedClienteJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(agregarJBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CantPanJtf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(panesIsuficientesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(totalPedidoJL))
                            .addComponent(addPedidoJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Regresar)))
                .addGap(0, 0, 0))
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

    private void CantPanJtfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantPanJtfKeyReleased
        validaCantidadPan();
    }//GEN-LAST:event_CantPanJtfKeyReleased

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        ventanaVenta.thismissRegPedido();
    }//GEN-LAST:event_RegresarActionPerformed

    private void addPedidoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPedidoJButtonActionPerformed
        try {
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
        }
    }//GEN-LAST:event_addPedidoJButtonActionPerformed

    private void agregarJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarJBtnActionPerformed
        int idPanSelected = CbPan.getSelectedIndex();
        Pan panEncontrado = panesExistencia.get(idPanSelected-1);
      
        
        if (null != panEncontrado){
            
            int cantidad = Integer.parseInt(CantPanJtf.getText());
            ItemPedido nuevoItem = new ItemPedido();
            nuevoItem.setCantidad(cantidad);
            nuevoItem.setPan(panEncontrado);
            float total = cantidad * panEncontrado.getcosto();
            currentPedido.setCosto(total+currentPedido.getCosto());
            totalPedidoJL.setText(String.valueOf(currentPedido.getCosto()));
            currentPedido.getItemsEnPedido().add(nuevoItem);
            
            //agregar a oa tabla
            Object row[] = new Object[4];
            row [0]= panEncontrado.getnombre();
            row [1]= panEncontrado.getcosto();
            row [2]= nuevoItem.getCantidad();
            row [3]= total;
            
            modelo.addRow(row);
            CantPanJtf.setText("");
            CbPan.setSelectedIndex(0);
            validaRegistroCompleto();
        } 
    }//GEN-LAST:event_agregarJBtnActionPerformed

    private void CbPanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbPanActionPerformed
        validaCantidadPan();
    }//GEN-LAST:event_CbPanActionPerformed

    private void fechaEntregaJCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaEntregaJCalendarPropertyChange
        // TODO add your handling code here:
        System.out.println("puta madreee");
        try{
            
        currentPedido.setFechaEntrega(new java.sql.Date(fechaEntregaJCalendar.getDate().getTime()));
        }catch(NullPointerException e){
            System.out.println("chale "+e.getMessage());
        }
        validaCantidadPan();
    }//GEN-LAST:event_fechaEntregaJCalendarPropertyChange

    private void tipoExternoJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoExternoJRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoExternoJRadioButtonActionPerformed

    private void tipoNormalJRadioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipoNormalJRadioButtonMouseClicked
        // TODO add your handling code here:
        if(tipoNormalJRadioButton.isSelected()){
            currentPedido.setTipo(1);
        }
        validaRegistroCompleto();
    }//GEN-LAST:event_tipoNormalJRadioButtonMouseClicked

    private void tipoExternoJRadioButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipoExternoJRadioButtonMouseClicked
        // TODO add your handling code here:
        if(tipoExternoJRadioButton.isSelected()){
            currentPedido.setTipo(2);
        }
        validaRegistroCompleto();
    }//GEN-LAST:event_tipoExternoJRadioButtonMouseClicked

    private void anticipoJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anticipoJTFKeyReleased
        // TODO add your handling code here:
        try{
            Float input = Float.parseFloat(anticipoJTF.getText());
            currentPedido.setAbono(input);
        }catch (NumberFormatException e){
            
        }
    }//GEN-LAST:event_anticipoJTFKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.ventanaNuevoCliente = new NuevoClient(this,empleadoLoggeado);
        ventanaNuevoCliente.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void nombreBuscaClienteJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreBuscaClienteJTFKeyReleased
        // TODO add your handling code here:
        try {
            modeloClientes.setRowCount(0);
            clientesExistentes.clear();
            PreparedStatement pst = conexion.conectar.prepareStatement("select * from Cliente where concat(nombre,paterno,materno) like ? order by paterno asc");
            String input = nombreBuscaClienteJTF.getText();
            pst.setString(1, "%"+input+"%");
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
            System.out.println(ex);        }

    }//GEN-LAST:event_nombreBuscaClienteJTFKeyReleased

    private void clientesJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesJTableMouseClicked
        // TODO add your handling code here:
        int id = clientesJTable.getSelectedRow();
        if (id >= 0){
            currentSelectedCliente  = clientesExistentes.get(id);
            selectedClienteJTF.setText(clientesExistentes.get(id).FullName());
            nombreBuscaClienteJTF.setText(clientesExistentes.get(id).FullName());
            //idClienteSelectedJTF.setText(String.valueOf(clientesExistentes.get(id).getIdCliente()));
            currentPedido.setCliente(currentSelectedCliente);
        }
        validaRegistroCompleto();
    }//GEN-LAST:event_clientesJTableMouseClicked

    private void nombreBuscaClienteJTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreBuscaClienteJTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreBuscaClienteJTFActionPerformed
    
    public void validaRegistroCompleto(){
        if(currentSelectedCliente != null
            && currentPedido.getItemsEnPedido().size()>0
            && fechaEntregaJCalendar.getDate() != null
            && (tipoExternoJRadioButton.isSelected() || tipoNormalJRadioButton.isSelected())
                ){
            addPedidoJButton.setEnabled(true);
        }else{
            addPedidoJButton.setEnabled(false);
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
            java.util.logging.Logger.getLogger(RegPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CantPanJtf;
    private javax.swing.JComboBox<String> CbPan;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton addPedidoJButton;
    private javax.swing.JButton agregarJBtn;
    private javax.swing.JTextField anticipoJTF;
    private javax.swing.JTable clientesJTable;
    private com.toedter.calendar.JDateChooser fechaEntregaJCalendar;
    private javax.swing.JTable itemsPedidoJTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nombreBuscaClienteJTF;
    private javax.swing.JLabel panesIsuficientesLabel;
    private javax.swing.JLabel selectedClienteJTF;
    private javax.swing.JRadioButton tipoExternoJRadioButton;
    private javax.swing.JRadioButton tipoNormalJRadioButton;
    private javax.swing.JLabel totalPedidoJL;
    // End of variables declaration//GEN-END:variables
}
