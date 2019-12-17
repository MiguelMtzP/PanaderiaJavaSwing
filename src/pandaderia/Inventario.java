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
    public Conexion conexion ;
    public Pan currentPanSelected;
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
    
    
    public Inventario(Menu previewView, Empleado emp){
        empleadoLoggeado = emp;
        conexion = new Conexion();
        
        initComponents();
        setLocationRelativeTo(previewView);
        setTitle("Inventario");
        this.ventanaMenu = previewView;
        IdPanJTF.setEnabled(empleadoLoggeado.getRol() == 1);
        nombrePanJTF.setEnabled(empleadoLoggeado.getRol() == 1);
        PrecioPanJTF.setEnabled(empleadoLoggeado.getRol() == 1);
        modelo = (DefaultTableModel)InventTablaJTable.getModel();
        Actualizar("");
        setResizable(false);
        
        IdPanJTF.setEditable(false);
        validaDatos();
    }
    
    public void Actualizar(String valor){
        modelo.setRowCount(0);
        panesExistencia = new ArrayList<Pan>(0);
        currentPanSelected = null;
        String sql = "";
        
        if(valor.isEmpty()){
            sql = "SELECT * FROM Inventario";
        }else{
            sql  = "SELECT * FROM Inventario WHERE id_Pan = '"+valor+"'";
        }
        sql += " order by nombre asc"; 
        try {
            
            PreparedStatement pst= conexion.conectar.prepareStatement(sql);
            ResultSet a = pst.executeQuery();
            ResultSetMetaData aMd = a.getMetaData(); //Le pasamos el resultado de la consulta
            int numColumns = aMd.getColumnCount();//Datos que me regresa la consulta
            
            
            int[] anchos = {50,50,50,50};
            
            for(int j=0; j< numColumns; j++){
                InventTablaJTable.getColumnModel().getColumn(j).setPreferredWidth(anchos[j]);
            }
            
            while(a.next()){  //Recorremos los datos de la consulta //En cada ciclo me va a dar los datos de una sola fila
                Pan nuevo = new Pan();
                nuevo.setidPan(a.getInt("id_Pan"));
                nuevo.setnombre(a.getString("nombre"));
                nuevo.setcosto(a.getFloat("costo"));
                nuevo.setexistencia(a.getInt("existencia"));
                
                Object[] filas = new Object[numColumns];
                filas[0] = a.getInt("id_Pan");
                filas[1] = a.getString("nombre");
                filas[2] = a.getFloat("costo");
                filas[3] = a.getInt("existencia");
                modelo.addRow(filas);
                panesExistencia.add(nuevo);
            }
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
    
    public void limpiaCampos(){
        IdPanJTF.setText("");
        nombrePanJTF.setText("");
        nombrePanSelectedjLabel.setText("-----");
        PrecioPanJTF.setText("");
        nuevosPanesJtf.setText("0");
    }
    
    public void validaDatos(){
        BorrarjButton.setEnabled(currentPanSelected != null && empleadoLoggeado.getRol() == 1);
        ModificarjButton.setEnabled(currentPanSelected != null && empleadoLoggeado.getRol() == 1 && !nombrePanJTF.getText().isEmpty()&&!PrecioPanJTF.getText().isEmpty());
        GuardarjButton.setEnabled(currentPanSelected == null && empleadoLoggeado.getRol() == 1 && !nombrePanJTF.getText().isEmpty()&&!PrecioPanJTF.getText().isEmpty());
        try {
            agregaExistenciasjButton.setEnabled(currentPanSelected !=null && Integer.parseInt(nuevosPanesJtf.getText()) > 0);
        } catch (NumberFormatException e) {
            agregaExistenciasjButton.setEnabled(false);
            System.out.println("cayo en la exception ");
            nuevosPanesJtf.setText("0");
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
        Regresar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PrecioPanJTF = new javax.swing.JTextField();
        nombrePanJTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        IdPanJTF = new javax.swing.JTextField();
        ModificarjButton = new javax.swing.JButton();
        BorrarjButton = new javax.swing.JButton();
        GuardarjButton = new javax.swing.JButton();
        limpajButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        InventTablaJTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        buscaPorNombrejTField = new javax.swing.JTextField();
        buscaPorIdJTField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        nombrePanSelectedjLabel = new javax.swing.JLabel();
        agregaExistenciasjButton = new javax.swing.JButton();
        nuevosPanesJtf = new javax.swing.JTextField();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/regresar.png"))); // NOI18N
        Regresar.setText("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo pan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel2.setText("Precio: ");

        PrecioPanJTF.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        PrecioPanJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PrecioPanJTFKeyReleased(evt);
            }
        });

        nombrePanJTF.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        nombrePanJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombrePanJTFKeyReleased(evt);
            }
        });

        jLabel1.setText("Nombre:");

        jLabel5.setText("ID Pan:");

        IdPanJTF.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        IdPanJTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdPanJTFActionPerformed(evt);
            }
        });

        ModificarjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/editar.png"))); // NOI18N
        ModificarjButton.setText("Modificar");
        ModificarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarjButtonActionPerformed(evt);
            }
        });

        BorrarjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/borrar.png"))); // NOI18N
        BorrarjButton.setText("Borrar");
        BorrarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarjButtonActionPerformed(evt);
            }
        });

        GuardarjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/guardar.png"))); // NOI18N
        GuardarjButton.setText("Guardar");
        GuardarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarjButtonActionPerformed(evt);
            }
        });

        limpajButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coin.png"))); // NOI18N
        limpajButton.setText("Limpiar");
        limpajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpajButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombrePanJTF, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                            .addComponent(IdPanJTF)
                            .addComponent(PrecioPanJTF, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(limpajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GuardarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BorrarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModificarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(IdPanJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nombrePanJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BorrarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ModificarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(GuardarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PrecioPanJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(limpajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca y selecciona un pan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 15))); // NOI18N

        InventTablaJTable.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        InventTablaJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Pan", "Nombre", "Precio", "Existencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        InventTablaJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventTablaJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(InventTablaJTable);

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("Buscar por nombre:");

        buscaPorNombrejTField.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
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

        buscaPorIdJTField.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        buscaPorIdJTField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscaPorIdJTFieldKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Buscar por ID:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscaPorNombrejTField)
                        .addGap(143, 143, 143)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscaPorIdJTField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscaPorIdJTField, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addComponent(buscaPorNombrejTField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar existencia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Ingresa nuevos panes para ");

        nombrePanSelectedjLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        nombrePanSelectedjLabel.setForeground(new java.awt.Color(38, 161, 169));
        nombrePanSelectedjLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nombrePanSelectedjLabel.setText("-----");

        agregaExistenciasjButton.setText("Actualizar");
        agregaExistenciasjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregaExistenciasjButtonActionPerformed(evt);
            }
        });

        nuevosPanesJtf.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        nuevosPanesJtf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nuevosPanesJtf.setText("0");
        nuevosPanesJtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevosPanesJtfActionPerformed(evt);
            }
        });
        nuevosPanesJtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nuevosPanesJtfKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(nuevosPanesJtf, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(agregaExistenciasjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombrePanSelectedjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombrePanSelectedjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agregaExistenciasjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nuevosPanesJtf, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(424, 881, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
            ventanaMenu.thismissInventario();
    }//GEN-LAST:event_RegresarActionPerformed

    private void InventTablaJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventTablaJTableMouseClicked
        int Fila = InventTablaJTable.getSelectedRow();
        if(Fila >= 0){
            Pan encontrado = panesExistencia.get(Fila);
            currentPanSelected = encontrado;
            IdPanJTF.setText(String.valueOf(encontrado.getidPan()));
            nombrePanJTF.setText(encontrado.getnombre());
            nombrePanSelectedjLabel.setText(encontrado.getnombre());
            PrecioPanJTF.setText(String.valueOf(encontrado.getcosto()));
        }
        validaDatos();
    }//GEN-LAST:event_InventTablaJTableMouseClicked

    private void GuardarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarjButtonActionPerformed
            
            
        try {          
            PreparedStatement pst= conexion.conectar.prepareStatement("INSERT INTO Inventario  (nombre, costo, existencia) VALUES(?,?,0)");
            pst.setString(1, nombrePanJTF.getText());
            pst.setString(2, PrecioPanJTF.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Guardado");
            Actualizar("");
            limpiaCampos();
            validaDatos();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ingrese todos los campos");
            System.out.println(e);
        }
    }//GEN-LAST:event_GuardarjButtonActionPerformed

    private void BorrarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarjButtonActionPerformed
        try {
            String nombre = currentPanSelected.getnombre();
            PreparedStatement pst= conexion.conectar.prepareStatement("DELETE FROM Inventario WHERE id_Pan = ?");
            pst.setInt(1, currentPanSelected.getidPan());
            pst.execute();
            Actualizar("");
            limpiaCampos();
            
            JOptionPane.showMessageDialog(null, "¡El pan "+nombre+" fue borrado!");
            validaDatos();
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
        }
    }//GEN-LAST:event_BorrarjButtonActionPerformed

    private void IdPanJTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdPanJTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdPanJTFActionPerformed

    private void ModificarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarjButtonActionPerformed
     
        try {   
            String nombre = currentPanSelected.getnombre();
            PreparedStatement pst = conexion.conectar.prepareStatement("update Inventario set nombre = ?, costo = ? where id_Pan = ?");
            pst.setString(1, nombrePanJTF.getText());
            pst.setFloat(2, Float.parseFloat(PrecioPanJTF.getText()));
            pst.setInt(3, currentPanSelected.getidPan());
            pst.executeUpdate();
            Actualizar("");
            limpiaCampos();
            JOptionPane.showMessageDialog(null,"¡Se actualizo el pan "+nombre+"!");
            validaDatos();
        }catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"ERROR: Contacte al administrador del sistema \nCODERR:324"); 
        }
    }//GEN-LAST:event_ModificarjButtonActionPerformed

    private void buscaPorNombrejTFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaPorNombrejTFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscaPorNombrejTFieldActionPerformed

    private void buscaPorNombrejTFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscaPorNombrejTFieldKeyReleased
        try {
            panesExistencia.clear();
            modelo.setNumRows(0);
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT * from Inventario where nombre like ? " +
                                                                        "    order by nombre asc");
            String input = buscaPorNombrejTField.getText();
            pst.setString(1, "%"+input+"%");
            
            ResultSet a = pst.executeQuery();
            while(a.next()){
                
                Pan nuevo = new Pan();
                nuevo.setidPan(a.getInt("id_Pan"));
                nuevo.setnombre(a.getString("nombre"));
                nuevo.setcosto(a.getFloat("costo"));
                nuevo.setexistencia(a.getInt("existencia"));
                
                Object[] filas = new Object[4];
                filas[0] = a.getInt("id_Pan");
                filas[1] = a.getString("nombre");
                filas[2] = a.getFloat("costo");
                filas[3] = a.getInt("existencia");
                modelo.addRow(filas);
                panesExistencia.add(nuevo);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

    private void agregaExistenciasjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregaExistenciasjButtonActionPerformed
         try {   
            String nombre = currentPanSelected.getnombre();
            PreparedStatement pst = conexion.conectar.prepareStatement("update Inventario set existencia = existencia + ? where id_Pan = ?");
            
            pst.setInt(1,Integer.parseInt(nuevosPanesJtf.getText()));
            pst.setInt(2, currentPanSelected.getidPan());
            pst.execute();
            JOptionPane.showMessageDialog(null,"¡Piezas agregadas exitosamente al pan "+nombre+"!");
            Actualizar("");
            limpiaCampos();
        }catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"ERROR: Contacte al administrador del sistema \nCODERR:4432"); 
        }
    }//GEN-LAST:event_agregaExistenciasjButtonActionPerformed

    private void buscaPorIdJTFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscaPorIdJTFieldKeyReleased
        try{
            panesExistencia.clear();
            modelo.setNumRows(0);
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT * from Inventario where id_Pan =  ? " +
                                                                        "    order by nombre asc");
            int input = Integer.parseInt(buscaPorIdJTField.getText());
            pst.setInt(1, input);
            
            ResultSet a = pst.executeQuery();
            while(a.next()){
                
                Pan nuevo = new Pan();
                nuevo.setidPan(a.getInt("id_Pan"));
                nuevo.setnombre(a.getString("nombre"));
                nuevo.setcosto(a.getFloat("costo"));
                nuevo.setexistencia(a.getInt("existencia"));
                
                Object[] filas = new Object[4];
                filas[0] = a.getInt("id_Pan");
                filas[1] = a.getString("nombre");
                filas[2] = a.getFloat("costo");
                filas[3] = a.getInt("existencia");
                modelo.addRow(filas);
                panesExistencia.add(nuevo);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_buscaPorIdJTFieldKeyReleased

    private void nombrePanJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombrePanJTFKeyReleased
        // TODO add your handling code here:
        validaDatos();
    }//GEN-LAST:event_nombrePanJTFKeyReleased

    private void PrecioPanJTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PrecioPanJTFKeyReleased
        // TODO add your handling code here:
        validaDatos();
    }//GEN-LAST:event_PrecioPanJTFKeyReleased

    private void limpajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpajButtonActionPerformed
        // TODO add your handling code here:
        currentPanSelected = null;
        limpiaCampos();
        validaDatos();
    }//GEN-LAST:event_limpajButtonActionPerformed

    private void nuevosPanesJtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevosPanesJtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevosPanesJtfActionPerformed

    private void nuevosPanesJtfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevosPanesJtfKeyReleased
       validaDatos();
    }//GEN-LAST:event_nuevosPanesJtfKeyReleased

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
    private javax.swing.JButton BorrarjButton;
    private javax.swing.JButton GuardarjButton;
    private javax.swing.JTextField IdPanJTF;
    private javax.swing.JTable InventTablaJTable;
    private javax.swing.JButton ModificarjButton;
    private javax.swing.JTextField PrecioPanJTF;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton agregaExistenciasjButton;
    private javax.swing.JTextField buscaPorIdJTField;
    private javax.swing.JTextField buscaPorNombrejTField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpajButton;
    private javax.swing.JTextField nombrePanJTF;
    private javax.swing.JLabel nombrePanSelectedjLabel;
    private javax.swing.JTextField nuevosPanesJtf;
    // End of variables declaration//GEN-END:variables
}
