/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandaderia;

import Models.BalanceCorte;
import Models.Empleado;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguel
 */
public class CorteAdmin extends javax.swing.JFrame {

    private Menu ventanaMenu;
    private Conexion conexion;
    private Empleado logueado;
    private Date currentDaySelected;
    private int currentWeekSelected;
    private Date currentMonthSelected;
    private Corte currentcorteSelected;
    private BalanceCorte balancePorDia;
    private BalanceCorte balancePorSemana; 
    private BalanceCorte  balancePorMes;
    private BalanceCorte  balancePorCorte;
    /**
     * Creates new form CorteAdmin
     */
    public CorteAdmin() {
        initComponents();
        
    }
    public CorteAdmin(Menu ventanaMenu,Conexion conexion,Empleado logueado) {
        
        this.ventanaMenu = ventanaMenu;
        this.conexion = conexion;
        this.logueado = logueado;
        initComponents();
        
        initPorDiaComponents();
        initPorSemanaComponents();
        initPorMesComponents();
        initPorCorteComponents();
        
    }
    
    
    public void initPorDiaComponents(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Pan");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        ventasPorDiajTable.setModel(modelo);
        diaVentasGananciasTotalesLabel.setText("$" + "0.0");
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Pan");
        modelo.addColumn("Rotos");
        modelo.addColumn("Comidos");
        modelo.addColumn("Frios");
        modelo.addColumn("Total Merma");
        modelo.addColumn("Total Perdida");
        mermasPorDiajTable.setModel(modelo);
        diaMermasPerdidasTotalesLabel.setText("$" + "0.0");
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Cliente");
        modelo.addColumn("Creado");
        modelo.addColumn("Entrega");
        //modelo.addColumn("Estado");
        modelo.addColumn("Tipo");
        modelo.addColumn("Total");
        pedidosPorDiajTable.setModel(modelo);
        diaPedidosGananciasTotalesLabel.setText("$" + "0.0");
        
        diaBalanceGananciasPorVentasLabel.setText("$"+"0.0" );
        diaBalancePasivosPorMermasLabel.setText("$"+"0.0" );
        diaBalanceGananciasPorPedidosLabael.setText("$"+"0.0" );
        diaBalanceFugasPedidosExternosLabel.setText("$"+"0.0" );
        diaBalanceTotalLabel.setText("$"+"0.0" );
    }
    
    public void initPorSemanaComponents(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Pan");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        ventasPorSemanajTable.setModel(modelo);
        semanaVentasGananciasTotalesLabel.setText("$" + "0.0");
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Pan");
        modelo.addColumn("Rotos");
        modelo.addColumn("Comidos");
        modelo.addColumn("Frios");
        modelo.addColumn("Total Merma");
        modelo.addColumn("Total Perdida");
        mermasPorSemanajTable.setModel(modelo);
        semanaMermasPerdidasTotalesLabel.setText("$" + "0.0");
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Cliente");
        modelo.addColumn("Creado");
        modelo.addColumn("Entrega");
        //modelo.addColumn("Estado");
        modelo.addColumn("Tipo");
        modelo.addColumn("Total");
        pedidosPorSemanajTable.setModel(modelo);
        semanaPedidosGananciasTotalesLabel.setText("$" + "0.0");
        
        semanaBalanceGananciasPorVentasLabel.setText("$"+"0.0" );
        semanaBalancePasivosPorMermasLabel.setText("$"+"0.0" );
        semanaBalanceGananciasPorPedidosLabael.setText("$"+"0.0" );
        semanaBalanceFugasPedidosExternosLabel.setText("$"+"0.0" );
        semanaBalanceTotalLabel.setText("$"+"0.0" );
    }
    public void initPorMesComponents(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Pan");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        ventasPorMesjTable.setModel(modelo);
        mesVentasGananciasTotalesLabel.setText("$" + "0.0");
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Pan");
        modelo.addColumn("Rotos");
        modelo.addColumn("Comidos");
        modelo.addColumn("Frios");
        modelo.addColumn("Total Merma");
        modelo.addColumn("Total Perdida");
        MermasPorMesjTable.setModel(modelo);
        mesMermasPerdidasTotalesLabel.setText("$" + "0.0");
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Cliente");
        modelo.addColumn("Creado");
        modelo.addColumn("Entrega");
        //modelo.addColumn("Estado");
        modelo.addColumn("Tipo");
        modelo.addColumn("Total");
        PedidosPorMesjTable.setModel(modelo);
        mesPedidosGananciasTotalesLabel.setText("$" + "0.0");
        
        mesBalanceGananciasPorVentasLabel.setText("$"+"0.0" );
        mesBalancePasivosPorMermasLabel.setText("$"+"0.0" );
        mesBalanceGananciasPorPedidosLabael.setText("$"+"0.0" );
        mesBalanceFugasPedidosExternosLabel.setText("$"+"0.0" );
        mesBalanceTotalLabel.setText("$"+"0.0" );
    }
    public void initPorCorteComponents(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Pan");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        ventasPorCortejTable.setModel(modelo);
        corteVentasGananciasTotalesLabel.setText("$" + "0.0");
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Pan");
        modelo.addColumn("Rotos");
        modelo.addColumn("Comidos");
        modelo.addColumn("Frios");
        modelo.addColumn("Total Merma");
        modelo.addColumn("Total Perdida");
        mermasPorCortejTable.setModel(modelo);
        corteMermasPerdidasTotalesLabel.setText("$" + "0.0");
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Cliente");
        modelo.addColumn("Creado");
        modelo.addColumn("Entrega");
        //modelo.addColumn("Estado"); Estado de pedido
        modelo.addColumn("Tipo");
        modelo.addColumn("Total");
        pedidosPorCortejTable.setModel(modelo);
        cortePedidosGananciasTotalesLabel.setText("$" + "0.0");
        
        corteGananciasPorVentasLabel.setText("$"+"0.0" );
        cortePasivosPorMermasLabel.setText("$"+"0.0" );
        corteGananciasPorPedidoLabel.setText("$"+"0.0" );
        corteFugasPorPedidosExternosLabel.setText("$"+"0.0" );
        corteBalanceTotaljLabel.setText("$"+"0.0" );
        
        seleccionaCortePanel.setVisible(false);
    }

    private void getDataPorDia(){
        
         balancePorDia = new BalanceCorte();
        /*balancePorSemana = new BalanceCorte(); estos van en cada uno de sus respectivos metodos
        balancePorMes = new BalanceCorte();
        balancePorCorte = new BalanceCorte();*/
        try {
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT Ventas.id_Pan, Inventario.nombre as nombre_pan ,SUM(Ventas.cantidad) as T_cantidad , sum(Ventas.Total) as Total" +
                                                                        "	FROM panaderia.Ventas" +
                                                                        "    join Inventario on Ventas.id_Pan = Inventario.id_Pan" +
                                                                        "    where fecha = ?" +
                                                                        "    group by id_Pan ");
            
            pst.setDate(1, new java.sql.Date(seleccionaDiaDateChooser.getDate().getTime()));
            ResultSet resultado = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel)ventasPorDiajTable.getModel();
            modelo.setNumRows(0);
            float totalGananciasVentas = 0;
            while(resultado.next()){
                Object row []= new Object[3];
                row[0] = resultado.getString("nombre_pan");
                row[1] = resultado.getInt("T_cantidad");
                row[2] = resultado.getFloat("Total");
                totalGananciasVentas += resultado.getFloat("Total");
                modelo.addRow(row);
            }
            balancePorDia.gananciasVentas = totalGananciasVentas;
            diaVentasGananciasTotalesLabel.setText("$"+String.valueOf(totalGananciasVentas));
        } catch (SQLException ex) {
            System.out.println("Fallo en Query de ventas");
            System.out.println(ex.getMessage());
        }
        
        try {
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT 	Merma.id_Pan as idPan, " +
                                                                        "Inventario.nombre as nombre_pan, " +
                                                                        "sum(Merma.comidos) as T_comidos," +
                                                                        "sum(Merma.frios) as T_frios," +
                                                                        "sum(Merma.rotos) as T_rotos," +
                                                                        "sum(Merma.comidos + Merma.rotos + Merma.frios) as totalMerma," +
                                                                        "Inventario.costo * sum(Merma.comidos + Merma.rotos + Merma.frios) as totalPerdida " +
                                                                        "FROM panaderia.Merma " +
                                                                        "join Inventario on Merma.id_Pan = Inventario.id_Pan " +
                                                                        "where Merma.fecha = ? " +
                                                                        "group by Merma.id_Pan");
            
   
            pst.setDate(1,new java.sql.Date(seleccionaDiaDateChooser.getDate().getTime()));
            ResultSet resultado = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel)mermasPorDiajTable.getModel();
            modelo.setNumRows(0);
            float totalPerdidasMermas = 0;
            while(resultado.next()){
                Object row []= new Object[6];
                row[0] = resultado.getString("nombre_pan");
                row[1] = resultado.getInt("T_rotos");
                row[2] = resultado.getInt("T_comidos");
                row[3] = resultado.getInt("T_frios");
                row[4] = resultado.getInt("totalMerma");
                row[5] = resultado.getFloat("totalPerdida");
                totalPerdidasMermas += resultado.getFloat("totalPerdida");
                modelo.addRow(row);
            }
            balancePorDia.perdidasMermas  = totalPerdidasMermas * -1;
            diaMermasPerdidasTotalesLabel.setText("-$"+String.valueOf(totalPerdidasMermas));
        } catch (SQLException ex) {
            System.out.println("Fallo en Query de Mermas");
            System.out.println(ex.getMessage());
        }
        try {
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT Pedidos.id_Pedidos as id_Pedidos," +
                                                                "		concat(Cliente.paterno,\" \", Cliente.nombre) as cliente," +
                                                                "        Pedidos.fecha_creacion as creado," +
                                                                "        Pedidos.fecha_entrega as entrega," +
                                                                "        Pedidos.tipo as tipo," +
                                                                "        Pedidos.costo as total, " +
                                                                "        Pedidos.status as status " +
                                                                "	FROM panaderia.Pedidos " +
                                                                "	join Cliente on Cliente.id_Cliente = Pedidos.id_Cliente " +
                                                                "    where fecha_creacion = ? "
                                                                + (diaMuestraPendientesYExternosCheckBox.isSelected()?"":"and Pedidos.tipo = 1 and status = 2"));
            
   
            pst.setDate(1,new java.sql.Date(seleccionaDiaDateChooser.getDate().getTime()));
            ResultSet resultado = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) pedidosPorDiajTable.getModel();
            modelo.setNumRows(0);
            float totalPedidosGanancias = 0;
            while(resultado.next()){
                Object row []= new Object[5];
                row[0] = resultado.getString("cliente");
                row[1] = resultado.getDate("creado");
                row[2] = resultado.getDate("entrega");
                row[3] = resultado.getInt("tipo") == 1?"Normal":"Externo" ;
                row[4] = resultado.getFloat("total");
                totalPedidosGanancias += resultado.getFloat("total");
                if(resultado.getInt("tipo") == 1 && resultado.getInt("status") == 2){
                    balancePorDia.gananciasPedidos += resultado.getFloat("total");
                }
                modelo.addRow(row);
            }
            diaPedidosGananciasTotalesLabel.setText("$"+String.valueOf(totalPedidosGanancias));
        } catch (SQLException ex) {
            System.out.println("Fallo en Query de Pedidos");
            System.out.println(ex.getMessage());
        }
        
        //Empieza llenado de seccion balance 
        try {
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT sum(costo) as fugas FROM panaderia.Pedidos where tipo = 2 and Pedidos.status = 2 and fecha_creacion = ?");
            
   
            pst.setDate(1,new java.sql.Date(seleccionaDiaDateChooser.getDate().getTime()));
            ResultSet resultado = pst.executeQuery();
            if(resultado.next()){
                balancePorDia.fugasPedidosExternos = resultado.getFloat("fugas") * -1;
            }
            diaBalanceGananciasPorVentasLabel.setText("$"+String.valueOf(balancePorDia.gananciasVentas));
            diaBalancePasivosPorMermasLabel.setText("$"+String.valueOf(balancePorDia.perdidasMermas));
            diaBalanceGananciasPorPedidosLabael.setText("$"+String.valueOf(balancePorDia.gananciasPedidos));
            diaBalanceFugasPedidosExternosLabel.setText("$"+String.valueOf(balancePorDia.fugasPedidosExternos));
            diaBalanceTotalLabel.setText("$"+String.valueOf(balancePorDia.getBalanceTotal()));
            
        } catch (SQLException ex) {
            System.out.println("Fallo en Query de Pedidos");
            System.out.println(ex.getMessage());
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

        porDia = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        seleccionaDiaDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ventasPorDiajTable = new javax.swing.JTable();
        diaVentasGananciasTotalesLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        mermasPorDiajTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        diaMermasPerdidasTotalesLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        pedidosPorDiajTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        diaPedidosGananciasTotalesLabel = new javax.swing.JLabel();
        diaMuestraPendientesYExternosCheckBox = new javax.swing.JCheckBox();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        diaBalanceGananciasPorVentasLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        diaBalancePasivosPorMermasLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        diaBalanceGananciasPorPedidosLabael = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        diaBalanceFugasPedidosExternosLabel = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        diaBalanceTotalLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        ventasPorSemanajTable = new javax.swing.JTable();
        semanaVentasGananciasTotalesLabel = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane9 = new javax.swing.JScrollPane();
        mermasPorSemanajTable = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        semanaMermasPerdidasTotalesLabel = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        pedidosPorSemanajTable = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        semanaPedidosGananciasTotalesLabel = new javax.swing.JLabel();
        semanaMuestraPendientesYExternosCheckBox = new javax.swing.JCheckBox();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        semanaBalanceGananciasPorVentasLabel = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        semanaBalancePasivosPorMermasLabel = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        semanaBalanceGananciasPorPedidosLabael = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        semanaBalanceFugasPedidosExternosLabel = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        semanaBalanceTotalLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        ventasPorMesjTable = new javax.swing.JTable();
        mesVentasGananciasTotalesLabel = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane12 = new javax.swing.JScrollPane();
        MermasPorMesjTable = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        mesMermasPerdidasTotalesLabel = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel50 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        PedidosPorMesjTable = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        mesPedidosGananciasTotalesLabel = new javax.swing.JLabel();
        mesMuestraPendientesYExternosCheckBox = new javax.swing.JCheckBox();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        mesBalanceGananciasPorVentasLabel = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        mesBalancePasivosPorMermasLabel = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        mesBalanceGananciasPorPedidosLabael = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        mesBalanceFugasPedidosExternosLabel = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel62 = new javax.swing.JLabel();
        mesBalanceTotalLabel = new javax.swing.JLabel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        ventasPorCortejTable = new javax.swing.JTable();
        corteVentasGananciasTotalesLabel = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jScrollPane15 = new javax.swing.JScrollPane();
        mermasPorCortejTable = new javax.swing.JTable();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        corteMermasPerdidasTotalesLabel = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel71 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        pedidosPorCortejTable = new javax.swing.JTable();
        jLabel72 = new javax.swing.JLabel();
        cortePedidosGananciasTotalesLabel = new javax.swing.JLabel();
        corteMuestraPendientesYExternosCheckBox = new javax.swing.JCheckBox();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        corteGananciasPorVentasLabel = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        cortePasivosPorMermasLabel = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        corteGananciasPorPedidoLabel = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        corteFugasPorPedidosExternosLabel = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel83 = new javax.swing.JLabel();
        corteBalanceTotaljLabel = new javax.swing.JLabel();
        seleccionaCortePanel = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        listaCortesjTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        corteSeleccionadoPanel = new javax.swing.JPanel();
        corteSeleccionadoLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cortes");
        setPreferredSize(new java.awt.Dimension(936, 660));
        setResizable(false);

        porDia.setPreferredSize(new java.awt.Dimension(930, 650));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(930, 660));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 15)); // NOI18N
        jLabel1.setText("Selecciona Día");

        seleccionaDiaDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                seleccionaDiaDateChooserPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel2.setText("Ventas");

        ventasPorDiajTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(ventasPorDiajTable);

        diaVentasGananciasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        diaVentasGananciasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        diaVentasGananciasTotalesLabel.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel4.setText("Ganancias Totales");

        mermasPorDiajTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(mermasPorDiajTable);

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel5.setText("Mermas");

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel6.setText("Perdidas Totales");

        diaMermasPerdidasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        diaMermasPerdidasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        diaMermasPerdidasTotalesLabel.setText("jLabel3");

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel8.setText("Pedidos");

        pedidosPorDiajTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(pedidosPorDiajTable);

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel9.setText("Ganancias Totales");

        diaPedidosGananciasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        diaPedidosGananciasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        diaPedidosGananciasTotalesLabel.setText("jLabel3");

        diaMuestraPendientesYExternosCheckBox.setText("Muestra pedidos pendientes y  externos");
        diaMuestraPendientesYExternosCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                diaMuestraPendientesYExternosCheckBoxStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel11.setText("Balance");

        jLabel12.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel12.setText("Ganancias por ventas");

        diaBalanceGananciasPorVentasLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        diaBalanceGananciasPorVentasLabel.setForeground(new java.awt.Color(189, 5, 5));
        diaBalanceGananciasPorVentasLabel.setText("jLabel3");

        jLabel14.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel14.setText("Pasivos por mermas");

        diaBalancePasivosPorMermasLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        diaBalancePasivosPorMermasLabel.setForeground(new java.awt.Color(189, 5, 5));
        diaBalancePasivosPorMermasLabel.setText("jLabel3");

        jLabel16.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel16.setText("Ganancias por pedidos");

        diaBalanceGananciasPorPedidosLabael.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        diaBalanceGananciasPorPedidosLabael.setForeground(new java.awt.Color(189, 5, 5));
        diaBalanceGananciasPorPedidosLabael.setText("jLabel3");

        jLabel18.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel18.setText("Fugas por pedidos externos");

        diaBalanceFugasPedidosExternosLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        diaBalanceFugasPedidosExternosLabel.setForeground(new java.awt.Color(189, 5, 5));
        diaBalanceFugasPedidosExternosLabel.setText("jLabel3");

        jLabel20.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel20.setText("BALANCE TOTAL");

        diaBalanceTotalLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        diaBalanceTotalLabel.setForeground(new java.awt.Color(189, 5, 5));
        diaBalanceTotalLabel.setText("jLabel3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(diaPedidosGananciasTotalesLabel))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(diaMermasPerdidasTotalesLabel))
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                            .addComponent(jLabel2)
                                                            .addGap(539, 539, 539)
                                                            .addComponent(jLabel1)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(seleccionaDiaDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                            .addComponent(jLabel4)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(diaVentasGananciasTotalesLabel))
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                                                        .addComponent(jSeparator1))
                                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(diaMuestraPendientesYExternosCheckBox)))
                                    .addComponent(jLabel11))
                                .addGap(0, 4, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(309, 309, 309)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(96, 96, 96)
                                        .addComponent(diaBalanceTotalLabel))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel16)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(diaBalanceGananciasPorPedidosLabael))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel12)
                                                .addComponent(jLabel14))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(diaBalancePasivosPorMermasLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(diaBalanceGananciasPorVentasLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(diaBalanceFugasPedidosExternosLabel)))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(seleccionaDiaDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaVentasGananciasTotalesLabel)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaMermasPerdidasTotalesLabel)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(diaMuestraPendientesYExternosCheckBox))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaPedidosGananciasTotalesLabel)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaBalanceGananciasPorVentasLabel)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaBalancePasivosPorMermasLabel)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaBalanceGananciasPorPedidosLabael)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaBalanceFugasPedidosExternosLabel)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaBalanceTotalLabel)
                    .addComponent(jLabel20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel3);

        porDia.addTab("Por Dia", jScrollPane1);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(930, 660));

        jLabel22.setFont(new java.awt.Font("Liberation Sans", 0, 15)); // NOI18N
        jLabel22.setText("Selecciona semana del mes corriente");

        jLabel23.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel23.setText("Ventas");

        ventasPorSemanajTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(ventasPorSemanajTable);

        semanaVentasGananciasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        semanaVentasGananciasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        semanaVentasGananciasTotalesLabel.setText("jLabel3");

        jLabel25.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel25.setText("Ganancias Totales");

        mermasPorSemanajTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(mermasPorSemanajTable);

        jLabel26.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel26.setText("Mermas");

        jLabel27.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel27.setText("Perdidas Totales");

        semanaMermasPerdidasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        semanaMermasPerdidasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        semanaMermasPerdidasTotalesLabel.setText("jLabel3");

        jLabel29.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel29.setText("Pedidos");

        pedidosPorSemanajTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(pedidosPorSemanajTable);

        jLabel30.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel30.setText("Ganancias Totales");

        semanaPedidosGananciasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        semanaPedidosGananciasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        semanaPedidosGananciasTotalesLabel.setText("jLabel3");

        semanaMuestraPendientesYExternosCheckBox.setText("Muestra pedidos pendientes y  externos");

        jLabel32.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel32.setText("Balance");

        jLabel33.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel33.setText("Ganancias por ventas");

        semanaBalanceGananciasPorVentasLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        semanaBalanceGananciasPorVentasLabel.setForeground(new java.awt.Color(189, 5, 5));
        semanaBalanceGananciasPorVentasLabel.setText("jLabel3");

        jLabel35.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel35.setText("Pasivos por mermas");

        semanaBalancePasivosPorMermasLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        semanaBalancePasivosPorMermasLabel.setForeground(new java.awt.Color(189, 5, 5));
        semanaBalancePasivosPorMermasLabel.setText("jLabel3");

        jLabel37.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel37.setText("Ganancias por pedidos");

        semanaBalanceGananciasPorPedidosLabael.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        semanaBalanceGananciasPorPedidosLabael.setForeground(new java.awt.Color(189, 5, 5));
        semanaBalanceGananciasPorPedidosLabael.setText("jLabel3");

        jLabel39.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel39.setText("Fugas por pedidos externos");

        semanaBalanceFugasPedidosExternosLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        semanaBalanceFugasPedidosExternosLabel.setForeground(new java.awt.Color(189, 5, 5));
        semanaBalanceFugasPedidosExternosLabel.setText("jLabel3");

        jLabel41.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel41.setText("BALANCE TOTAL");

        semanaBalanceTotalLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        semanaBalanceTotalLabel.setForeground(new java.awt.Color(189, 5, 5));
        semanaBalanceTotalLabel.setText("jLabel3");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1er semana", "2da semana", "3er semana", "4ta semana" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6)
                            .addComponent(jSeparator7)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel30)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(semanaPedidosGananciasTotalesLabel))
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel26)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                    .addComponent(jLabel27)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(semanaMermasPerdidasTotalesLabel))
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                            .addComponent(jLabel23)
                                                            .addGap(391, 391, 391)
                                                            .addComponent(jLabel22)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                            .addComponent(jLabel25)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(semanaVentasGananciasTotalesLabel))
                                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                                                        .addComponent(jSeparator5))
                                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel29)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(semanaMuestraPendientesYExternosCheckBox)))
                                    .addComponent(jLabel32))
                                .addGap(0, 48, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel41)
                                .addGap(476, 476, 476))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(309, 309, 309)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(semanaBalanceTotalLabel)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel37)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(semanaBalanceGananciasPorPedidosLabael))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel33)
                                                .addComponent(jLabel35))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(semanaBalancePasivosPorMermasLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(semanaBalanceGananciasPorVentasLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(semanaBalanceFugasPedidosExternosLabel)))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(semanaVentasGananciasTotalesLabel)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(semanaMermasPerdidasTotalesLabel)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(semanaMuestraPendientesYExternosCheckBox))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(semanaPedidosGananciasTotalesLabel)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(semanaBalanceGananciasPorVentasLabel)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(semanaBalancePasivosPorMermasLabel)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(semanaBalanceGananciasPorPedidosLabael)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(semanaBalanceFugasPedidosExternosLabel)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(semanaBalanceTotalLabel)
                    .addComponent(jLabel41))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel7);

        porDia.addTab("Por Semana", jScrollPane3);

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(930, 660));

        jLabel43.setFont(new java.awt.Font("Liberation Sans", 0, 15)); // NOI18N
        jLabel43.setText("Selecciona Mes");

        jLabel44.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel44.setText("Ventas");

        ventasPorMesjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(ventasPorMesjTable);

        mesVentasGananciasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        mesVentasGananciasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        mesVentasGananciasTotalesLabel.setText("jLabel3");

        jLabel46.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel46.setText("Ganancias Totales");

        MermasPorMesjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(MermasPorMesjTable);

        jLabel47.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel47.setText("Mermas");

        jLabel48.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel48.setText("Perdidas Totales");

        mesMermasPerdidasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        mesMermasPerdidasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        mesMermasPerdidasTotalesLabel.setText("jLabel3");

        jLabel50.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel50.setText("Pedidos");

        PedidosPorMesjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(PedidosPorMesjTable);

        jLabel51.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel51.setText("Ganancias Totales");

        mesPedidosGananciasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        mesPedidosGananciasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        mesPedidosGananciasTotalesLabel.setText("jLabel3");

        mesMuestraPendientesYExternosCheckBox.setText("Muestra pedidos pendientes y  externos");

        jLabel53.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel53.setText("Balance");

        jLabel54.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel54.setText("Ganancias por ventas");

        mesBalanceGananciasPorVentasLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        mesBalanceGananciasPorVentasLabel.setForeground(new java.awt.Color(189, 5, 5));
        mesBalanceGananciasPorVentasLabel.setText("jLabel3");

        jLabel56.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel56.setText("Pasivos por mermas");

        mesBalancePasivosPorMermasLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        mesBalancePasivosPorMermasLabel.setForeground(new java.awt.Color(189, 5, 5));
        mesBalancePasivosPorMermasLabel.setText("jLabel3");

        jLabel58.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel58.setText("Ganancias por pedidos");

        mesBalanceGananciasPorPedidosLabael.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        mesBalanceGananciasPorPedidosLabael.setForeground(new java.awt.Color(189, 5, 5));
        mesBalanceGananciasPorPedidosLabael.setText("jLabel3");

        jLabel60.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel60.setText("Fugas por pedidos externos");

        mesBalanceFugasPedidosExternosLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        mesBalanceFugasPedidosExternosLabel.setForeground(new java.awt.Color(189, 5, 5));
        mesBalanceFugasPedidosExternosLabel.setText("jLabel3");

        jLabel62.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel62.setText("BALANCE TOTAL");

        mesBalanceTotalLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        mesBalanceTotalLabel.setForeground(new java.awt.Color(189, 5, 5));
        mesBalanceTotalLabel.setText("jLabel3");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10)
                            .addComponent(jSeparator11)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel51)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(mesPedidosGananciasTotalesLabel))
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel47)
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addComponent(jLabel48)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(mesMermasPerdidasTotalesLabel))
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                            .addComponent(jLabel44)
                                                            .addGap(539, 539, 539)
                                                            .addComponent(jLabel43)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(1, 1, 1))
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                            .addComponent(jLabel46)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(mesVentasGananciasTotalesLabel))
                                                        .addComponent(jScrollPane11)
                                                        .addComponent(jSeparator9))
                                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel50)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(mesMuestraPendientesYExternosCheckBox)))
                                    .addComponent(jLabel53))
                                .addGap(0, 45, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel62)
                                .addGap(476, 476, 476))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(309, 309, 309)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(mesBalanceTotalLabel)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel58)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(mesBalanceGananciasPorPedidosLabael))
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel54)
                                                .addComponent(jLabel56))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(mesBalancePasivosPorMermasLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(mesBalanceGananciasPorVentasLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(mesBalanceFugasPedidosExternosLabel)))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel43)
                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesVentasGananciasTotalesLabel)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesMermasPerdidasTotalesLabel)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(mesMuestraPendientesYExternosCheckBox))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesPedidosGananciasTotalesLabel)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesBalanceGananciasPorVentasLabel)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesBalancePasivosPorMermasLabel)
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesBalanceGananciasPorPedidosLabael)
                    .addComponent(jLabel58))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesBalanceFugasPedidosExternosLabel)
                    .addComponent(jLabel60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesBalanceTotalLabel)
                    .addComponent(jLabel62))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel8);

        porDia.addTab("Por Mes", jScrollPane4);

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setPreferredSize(new java.awt.Dimension(930, 660));

        jLabel65.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel65.setText("Ventas");

        ventasPorCortejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane14.setViewportView(ventasPorCortejTable);

        corteVentasGananciasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        corteVentasGananciasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        corteVentasGananciasTotalesLabel.setText("jLabel3");

        jLabel67.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel67.setText("Ganancias Totales");

        mermasPorCortejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane15.setViewportView(mermasPorCortejTable);

        jLabel68.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel68.setText("Mermas");

        jLabel69.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel69.setText("Perdidas Totales");

        corteMermasPerdidasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        corteMermasPerdidasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        corteMermasPerdidasTotalesLabel.setText("jLabel3");

        jLabel71.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel71.setText("Pedidos");

        pedidosPorCortejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane16.setViewportView(pedidosPorCortejTable);

        jLabel72.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel72.setText("Ganancias Totales");

        cortePedidosGananciasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        cortePedidosGananciasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        cortePedidosGananciasTotalesLabel.setText("jLabel3");

        corteMuestraPendientesYExternosCheckBox.setText("Muestra pedidos pendientes y  externos");

        jLabel74.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel74.setText("Balance");

        jLabel75.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel75.setText("Ganancias por ventas");

        corteGananciasPorVentasLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        corteGananciasPorVentasLabel.setForeground(new java.awt.Color(189, 5, 5));
        corteGananciasPorVentasLabel.setText("jLabel3");

        jLabel77.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel77.setText("Pasivos por mermas");

        cortePasivosPorMermasLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        cortePasivosPorMermasLabel.setForeground(new java.awt.Color(189, 5, 5));
        cortePasivosPorMermasLabel.setText("jLabel3");

        jLabel79.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel79.setText("Ganancias por pedidos");

        corteGananciasPorPedidoLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        corteGananciasPorPedidoLabel.setForeground(new java.awt.Color(189, 5, 5));
        corteGananciasPorPedidoLabel.setText("jLabel3");

        jLabel81.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel81.setText("Fugas por pedidos externos");

        corteFugasPorPedidosExternosLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        corteFugasPorPedidosExternosLabel.setForeground(new java.awt.Color(189, 5, 5));
        corteFugasPorPedidosExternosLabel.setText("jLabel3");

        jLabel83.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel83.setText("BALANCE TOTAL");

        corteBalanceTotaljLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        corteBalanceTotaljLabel.setForeground(new java.awt.Color(189, 5, 5));
        corteBalanceTotaljLabel.setText("jLabel3");

        seleccionaCortePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecciona un corte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(38, 113, 165))); // NOI18N

        listaCortesjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane17.setViewportView(listaCortesjTable);

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout seleccionaCortePanelLayout = new javax.swing.GroupLayout(seleccionaCortePanel);
        seleccionaCortePanel.setLayout(seleccionaCortePanelLayout);
        seleccionaCortePanelLayout.setHorizontalGroup(
            seleccionaCortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seleccionaCortePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, seleccionaCortePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        seleccionaCortePanelLayout.setVerticalGroup(
            seleccionaCortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seleccionaCortePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

        corteSeleccionadoLabel.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        corteSeleccionadoLabel.setText("Primero selecciona un corte");

        jButton2.setText("Selecciona un corte");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout corteSeleccionadoPanelLayout = new javax.swing.GroupLayout(corteSeleccionadoPanel);
        corteSeleccionadoPanel.setLayout(corteSeleccionadoPanelLayout);
        corteSeleccionadoPanelLayout.setHorizontalGroup(
            corteSeleccionadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(corteSeleccionadoPanelLayout.createSequentialGroup()
                .addComponent(corteSeleccionadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        corteSeleccionadoPanelLayout.setVerticalGroup(
            corteSeleccionadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(corteSeleccionadoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(corteSeleccionadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(corteSeleccionadoLabel)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator14)
                            .addComponent(jSeparator15)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addComponent(jLabel72)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cortePedidosGananciasTotalesLabel))
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel68)
                                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel9Layout.createSequentialGroup()
                                                    .addComponent(jLabel69)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(corteMermasPerdidasTotalesLabel))
                                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                                            .addComponent(jLabel67)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(corteVentasGananciasTotalesLabel))
                                                        .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                                                        .addComponent(jSeparator13))
                                                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                            .addComponent(jLabel71)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(corteMuestraPendientesYExternosCheckBox)))
                                    .addComponent(jLabel74))
                                .addGap(0, 48, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel83)
                                .addGap(476, 476, 476))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(309, 309, 309)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(corteBalanceTotaljLabel)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addComponent(jLabel79)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(corteGananciasPorPedidoLabel))
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel75)
                                                .addComponent(jLabel77))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cortePasivosPorMermasLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(corteGananciasPorVentasLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(corteFugasPorPedidosExternosLabel)))))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel65)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(seleccionaCortePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(corteSeleccionadoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seleccionaCortePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(corteSeleccionadoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(corteVentasGananciasTotalesLabel)
                    .addComponent(jLabel67))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel68)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(corteMermasPerdidasTotalesLabel)
                    .addComponent(jLabel69))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(corteMuestraPendientesYExternosCheckBox))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cortePedidosGananciasTotalesLabel)
                    .addComponent(jLabel72))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(corteGananciasPorVentasLabel)
                    .addComponent(jLabel75))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cortePasivosPorMermasLabel)
                    .addComponent(jLabel77))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(corteGananciasPorPedidoLabel)
                    .addComponent(jLabel79))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(corteFugasPorPedidosExternosLabel)
                    .addComponent(jLabel81))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(corteBalanceTotaljLabel)
                    .addComponent(jLabel83))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel9);

        porDia.addTab("Por Corte", jScrollPane5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(porDia, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(porDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        seleccionaCortePanel.setVisible(true);
        corteSeleccionadoPanel.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        seleccionaCortePanel.setVisible(false);
        corteSeleccionadoPanel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void seleccionaDiaDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_seleccionaDiaDateChooserPropertyChange
        // TODO add your handling code here:
        System.out.println("entre");
        currentDaySelected = seleccionaDiaDateChooser.getDate();
        if (currentDaySelected != null){
            getDataPorDia();
        }
    }//GEN-LAST:event_seleccionaDiaDateChooserPropertyChange

    private void diaMuestraPendientesYExternosCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_diaMuestraPendientesYExternosCheckBoxStateChanged
        // TODO add your handling code here:
        System.out.println("lo clickearon ");
        getDataPorDia();
    }//GEN-LAST:event_diaMuestraPendientesYExternosCheckBoxStateChanged

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
            java.util.logging.Logger.getLogger(CorteAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CorteAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CorteAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CorteAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CorteAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MermasPorMesjTable;
    private javax.swing.JTable PedidosPorMesjTable;
    private javax.swing.JLabel corteBalanceTotaljLabel;
    private javax.swing.JLabel corteFugasPorPedidosExternosLabel;
    private javax.swing.JLabel corteGananciasPorPedidoLabel;
    private javax.swing.JLabel corteGananciasPorVentasLabel;
    private javax.swing.JLabel corteMermasPerdidasTotalesLabel;
    private javax.swing.JCheckBox corteMuestraPendientesYExternosCheckBox;
    private javax.swing.JLabel cortePasivosPorMermasLabel;
    private javax.swing.JLabel cortePedidosGananciasTotalesLabel;
    private javax.swing.JLabel corteSeleccionadoLabel;
    private javax.swing.JPanel corteSeleccionadoPanel;
    private javax.swing.JLabel corteVentasGananciasTotalesLabel;
    private javax.swing.JLabel diaBalanceFugasPedidosExternosLabel;
    private javax.swing.JLabel diaBalanceGananciasPorPedidosLabael;
    private javax.swing.JLabel diaBalanceGananciasPorVentasLabel;
    private javax.swing.JLabel diaBalancePasivosPorMermasLabel;
    private javax.swing.JLabel diaBalanceTotalLabel;
    private javax.swing.JLabel diaMermasPerdidasTotalesLabel;
    private javax.swing.JCheckBox diaMuestraPendientesYExternosCheckBox;
    private javax.swing.JLabel diaPedidosGananciasTotalesLabel;
    private javax.swing.JLabel diaVentasGananciasTotalesLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable listaCortesjTable;
    private javax.swing.JTable mermasPorCortejTable;
    private javax.swing.JTable mermasPorDiajTable;
    private javax.swing.JTable mermasPorSemanajTable;
    private javax.swing.JLabel mesBalanceFugasPedidosExternosLabel;
    private javax.swing.JLabel mesBalanceGananciasPorPedidosLabael;
    private javax.swing.JLabel mesBalanceGananciasPorVentasLabel;
    private javax.swing.JLabel mesBalancePasivosPorMermasLabel;
    private javax.swing.JLabel mesBalanceTotalLabel;
    private javax.swing.JLabel mesMermasPerdidasTotalesLabel;
    private javax.swing.JCheckBox mesMuestraPendientesYExternosCheckBox;
    private javax.swing.JLabel mesPedidosGananciasTotalesLabel;
    private javax.swing.JLabel mesVentasGananciasTotalesLabel;
    private javax.swing.JTable pedidosPorCortejTable;
    private javax.swing.JTable pedidosPorDiajTable;
    private javax.swing.JTable pedidosPorSemanajTable;
    private javax.swing.JTabbedPane porDia;
    private javax.swing.JPanel seleccionaCortePanel;
    private com.toedter.calendar.JDateChooser seleccionaDiaDateChooser;
    private javax.swing.JLabel semanaBalanceFugasPedidosExternosLabel;
    private javax.swing.JLabel semanaBalanceGananciasPorPedidosLabael;
    private javax.swing.JLabel semanaBalanceGananciasPorVentasLabel;
    private javax.swing.JLabel semanaBalancePasivosPorMermasLabel;
    private javax.swing.JLabel semanaBalanceTotalLabel;
    private javax.swing.JLabel semanaMermasPerdidasTotalesLabel;
    private javax.swing.JCheckBox semanaMuestraPendientesYExternosCheckBox;
    private javax.swing.JLabel semanaPedidosGananciasTotalesLabel;
    private javax.swing.JLabel semanaVentasGananciasTotalesLabel;
    private javax.swing.JTable ventasPorCortejTable;
    private javax.swing.JTable ventasPorDiajTable;
    private javax.swing.JTable ventasPorMesjTable;
    private javax.swing.JTable ventasPorSemanajTable;
    // End of variables declaration//GEN-END:variables
}
