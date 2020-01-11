/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandaderia;

import Models.BalanceCorte;
import Models.CorteModel;
import Models.Empleado;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
    private CorteModel currentcorteSelected;
    private ArrayList<CorteModel> cortesExistentes;
    private BalanceCorte balancePorDia;
    private BalanceCorte balancePorSemana; 
    private BalanceCorte  balancePorMes;
    private BalanceCorte  balancePorCorte;
    private DecimalFormat formato = new DecimalFormat("#.0");
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
        cortesExistentes = new ArrayList<CorteModel>(0);
        initComponents();
        setLocationRelativeTo(null);
        initPorDiaComponents();
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
        diaBalancePasivosPorMermasLabel.setVisible(false);
        diaBalancePasivosPorMermasTitleLabel.setVisible(false);
        diaBalanceGananciasPorPedidosLabael.setText("$"+"0.0" );
        diaBalanceFugasPedidosExternosLabel.setText("$"+"0.0" );
        diaBalanceTotalLabel.setText("$"+"0.0" );
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
        pedidosPorMesjTable.setModel(modelo);
        mesPedidosGananciasTotalesLabel.setText("$" + "0.0");
        
        mesBalanceGananciasPorVentasLabel.setText("$"+"0.0" );
        mesBalancePasivosPorMermasLabel.setText("$"+"0.0" );
        mesBalancePasivosPorMermasLabel.setVisible(false);
        mesBalancePasivosPorMermasTitleLabel.setVisible(false);
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
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha");
        modelo.addColumn("Turno");
        listaCortesjTable.setModel(modelo);
        
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
        cortePasivosPorMermasLabel.setVisible(false);
        cortePasivosPorMermasTitleLabel.setVisible(false);
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
        
        //Empieza llenado de seccion balance por dia
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
    private void getDataPorMes(){
        //Ventas
        balancePorMes = new BalanceCorte();
        //balancePorCorte = new BalanceCorte();
        LocalDate lchoy = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaInit = LocalDate.of(lchoy.getYear(), seleccionaMesMC.getMonth()+1, 1);
        LocalDate fechaFin = LocalDate.of(lchoy.getYear(), seleccionaMesMC.getMonth()+1, fechaInit.getMonth().length(fechaInit.isLeapYear()));
        System.out.println("fecha inicial ---> "+fechaInit.toString());
        System.out.println("fecha fin --->"+fechaFin.toString());
        try {
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT Ventas.id_Pan, Inventario.nombre as nombre_pan ,SUM(Ventas.cantidad) as T_cantidad , sum(Ventas.Total) as Total" +
                                                                        "	FROM panaderia.Ventas " +
                                                                        "    join Inventario on Ventas.id_Pan = Inventario.id_Pan " +
                                                                        "    where fecha between ? and ? " +
                                                                        "    group by id_Pan ");
            
            
            pst.setDate(1, new java.sql.Date(Date.from(fechaInit.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            pst.setDate(2, new java.sql.Date(Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            ResultSet resultado = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel)ventasPorMesjTable.getModel();
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
            balancePorMes.gananciasVentas = totalGananciasVentas;
            mesVentasGananciasTotalesLabel.setText("$"+String.valueOf(totalGananciasVentas));
        } catch (SQLException ex) {
            System.out.println("Fallo en Query de ventas");
            System.out.println(ex.getMessage());
        }
        //Mermas
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
                                                                        "where Merma.fecha  between ? and ? " +
                                                                        "group by Merma.id_Pan");
            
            
           
            pst.setDate(1, new java.sql.Date(Date.from(fechaInit.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            pst.setDate(2, new java.sql.Date(Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            ResultSet resultado = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel)MermasPorMesjTable.getModel();
            
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
            balancePorMes.perdidasMermas  = totalPerdidasMermas * -1;
            mesMermasPerdidasTotalesLabel.setText("-$"+String.valueOf(totalPerdidasMermas));
        } catch (SQLException ex) {
            System.out.println("Fallo en Query de Mermas por mes");
            System.out.println(ex.getMessage());
        }
        //Pedidos
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
                                                                "    where fecha_creacion  between ? and ? "
                                                                + (mesMuestraPendientesYExternosCheckBox.isSelected()?"":"and Pedidos.tipo = 1 and status = 2"));
            
            
            
           
            pst.setDate(1, new java.sql.Date(Date.from(fechaInit.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            pst.setDate(2, new java.sql.Date(Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            ResultSet resultado = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel)pedidosPorMesjTable.getModel();

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
                    balancePorMes.gananciasPedidos += resultado.getFloat("total");
                }
                modelo.addRow(row);
            }
            mesPedidosGananciasTotalesLabel.setText("$"+String.valueOf(totalPedidosGanancias));
        } catch (SQLException ex) {
            System.out.println("Fallo en Query de Pedidos por mes");
            System.out.println(ex.getMessage());
        }
        
        //Empieza llenado de seccion balance 
        try {
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT sum(costo) as fugas FROM panaderia.Pedidos where tipo = 2 and Pedidos.status = 2 and fecha_creacion between ? and ? ");
            
   
            pst.setDate(1, new java.sql.Date(Date.from(fechaInit.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
            pst.setDate(2, new java.sql.Date(Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));            
            ResultSet resultado = pst.executeQuery();
            if(resultado.next()){
                balancePorMes.fugasPedidosExternos = resultado.getFloat("fugas") * -1;
            }
            mesBalanceGananciasPorVentasLabel.setText("$"+String.valueOf(balancePorMes.gananciasVentas));
            mesBalancePasivosPorMermasLabel.setText("$"+String.valueOf(balancePorMes.perdidasMermas));
            mesBalanceGananciasPorPedidosLabael.setText("$"+String.valueOf(balancePorMes.gananciasPedidos));
            mesBalanceFugasPedidosExternosLabel.setText("$"+String.valueOf(balancePorMes.fugasPedidosExternos));
            mesBalanceTotalLabel.setText("$"+String.valueOf(balancePorMes.getBalanceTotal()));
            
        } catch (SQLException ex) {
            System.out.println("Fallo en Query de Pedidos");
            System.out.println(ex.getMessage());
        }  
    }
    private void getDataPorCorte(){
        //Ventas
        balancePorCorte = new BalanceCorte();
        

        try {
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT Ventas.id_Pan, Inventario.nombre as nombre_pan ,SUM(Ventas.cantidad) as T_cantidad , sum(Ventas.Total) as Total" +
                                                                        "	FROM panaderia.Ventas " +
                                                                        "    join Inventario on Ventas.id_Pan = Inventario.id_Pan " +
                                                                        "    where id_Corte = ? " +
                                                                        "    group by id_Pan ");
            pst.setInt(1, currentcorteSelected.getIdCorte());
            ResultSet resultado = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel)ventasPorCortejTable.getModel();
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
            balancePorCorte.gananciasVentas = totalGananciasVentas;
            corteVentasGananciasTotalesLabel.setText("$"+String.valueOf(totalGananciasVentas));
        } catch (SQLException ex) {
            System.out.println("Fallo en Query de ventas");
            System.out.println(ex.getMessage());
        }
        //Mermas
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
                                                                        "where id_Corte = ? " +
                                                                        "group by Merma.id_Pan");
            
            
           pst.setInt(1, currentcorteSelected.getIdCorte());
            ResultSet resultado = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel)mermasPorCortejTable.getModel();
            
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
            balancePorCorte.perdidasMermas  = totalPerdidasMermas * -1;
            corteMermasPerdidasTotalesLabel.setText("-$"+String.valueOf(totalPerdidasMermas));
        } catch (SQLException ex) {
            System.out.println("Fallo en Query de Mermas por mes");
            System.out.println(ex.getMessage());
        }
        //Pedidos
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
                                                                "    where id_Corte = ? "
                                                                + (corteMuestraPendientesYExternosCheckBox.isSelected()?"":"and Pedidos.tipo = 1 and status = 2"));
            
            pst.setInt(1, currentcorteSelected.getIdCorte());
            
           
            ResultSet resultado = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel)pedidosPorCortejTable.getModel();

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
                    balancePorCorte.gananciasPedidos += resultado.getFloat("total");
                }
                modelo.addRow(row);
            }
            cortePedidosGananciasTotalesLabel.setText("$"+String.valueOf(totalPedidosGanancias));
        } catch (SQLException ex) {
            System.out.println("Fallo en Query de Pedidos por mes");
            System.out.println(ex.getMessage());
        }
        
        //Empieza llenado de seccion balance 
        try {
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT sum(costo) as fugas FROM panaderia.Pedidos where tipo = 2 and Pedidos.status = 2 and id_Corte = ? ");
            
            pst.setInt(1, currentcorteSelected.getIdCorte());
   
            ResultSet resultado = pst.executeQuery();
            if(resultado.next()){
                balancePorCorte.fugasPedidosExternos = resultado.getFloat("fugas") * -1;
            }
            corteGananciasPorVentasLabel.setText("$"+String.valueOf(balancePorCorte.gananciasVentas));
            cortePasivosPorMermasLabel.setText("$"+String.valueOf(balancePorCorte.perdidasMermas));
            corteGananciasPorPedidoLabel.setText("$"+String.valueOf(balancePorCorte.gananciasPedidos));
            corteFugasPorPedidosExternosLabel.setText("$"+String.valueOf(balancePorCorte.fugasPedidosExternos));
            corteBalanceTotaljLabel.setText("$"+String.valueOf(balancePorCorte.getBalanceTotal()));
            
        } catch (SQLException ex) {
            System.out.println("Fallo en Query de Pedidos");
            System.out.println(ex.getMessage());
        }  
    }
    
    public void getCortesEnExistencia(){
        try {
            DefaultTableModel modelo = (DefaultTableModel) listaCortesjTable.getModel();
            modelo.setRowCount(0);
            PreparedStatement pst = conexion.conectar.prepareStatement("SELECT 	Corte.id_Corte as idCorte, " +
                    "		concat(Usuarios.paterno,\" \",Usuarios.nombre) as nombreEmpleado,  " +
                    "		Corte.fecha_inicio as inicio, " +
                    "        Corte.turno  as turno " +
                    "FROM panaderia.Corte " +
                    "join Usuarios on Corte.id_Trabajador = Usuarios.id_Trabajador " +
                    "order by inicio,nombreEmpleado;");
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                CorteModel nuevoCorte = new CorteModel();
                nuevoCorte.setIdCorte(rs.getInt("idCorte"));
                Empleado emp = new Empleado();
                emp.setNombre(rs.getString("nombreEmpleado"));
                nuevoCorte.setIdTrabajador(emp);
                nuevoCorte.setFechaInicio(rs.getDate("inicio"));
                nuevoCorte.setTurno(rs.getString("turno"));
                
                Object[] obj = new Object[3];
                obj[0] = nuevoCorte.getIdTrabajador().getNombre();
                obj[1] = nuevoCorte.getFechaInicio();
                obj[2] = nuevoCorte.getTurno();
                modelo.addRow(obj);
                cortesExistentes.add(nuevoCorte);
            }
            confirmaSeleccionCortejButton.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(CorteAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
        diaBalancePasivosPorMermasTitleLabel = new javax.swing.JLabel();
        diaBalancePasivosPorMermasLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        diaBalanceGananciasPorPedidosLabael = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        diaBalanceFugasPedidosExternosLabel = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        diaBalanceTotalLabel = new javax.swing.JLabel();
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
        pedidosPorMesjTable = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        mesPedidosGananciasTotalesLabel = new javax.swing.JLabel();
        mesMuestraPendientesYExternosCheckBox = new javax.swing.JCheckBox();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        mesBalanceGananciasPorVentasLabel = new javax.swing.JLabel();
        mesBalancePasivosPorMermasTitleLabel = new javax.swing.JLabel();
        mesBalancePasivosPorMermasLabel = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        mesBalanceGananciasPorPedidosLabael = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        mesBalanceFugasPedidosExternosLabel = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel62 = new javax.swing.JLabel();
        mesBalanceTotalLabel = new javax.swing.JLabel();
        seleccionaMesMC = new com.toedter.calendar.JMonthChooser();
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
        cortePasivosPorMermasTitleLabel = new javax.swing.JLabel();
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
        confirmaSeleccionCortejButton = new javax.swing.JButton();
        corteSeleccionadoPanel = new javax.swing.JPanel();
        corteSeleccionadoLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cortes");
        setPreferredSize(new java.awt.Dimension(936, 710));
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
        diaMuestraPendientesYExternosCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diaMuestraPendientesYExternosCheckBoxMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel11.setText("Balance");

        jLabel12.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel12.setText("Ganancias por ventas");

        diaBalanceGananciasPorVentasLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        diaBalanceGananciasPorVentasLabel.setForeground(new java.awt.Color(189, 5, 5));
        diaBalanceGananciasPorVentasLabel.setText("jLabel3");

        diaBalancePasivosPorMermasTitleLabel.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        diaBalancePasivosPorMermasTitleLabel.setText("Pasivos por mermas");

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
                        .addGap(20, 20, 20)
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
                                .addGap(20, 20, 20))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(diaBalancePasivosPorMermasTitleLabel)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(diaBalancePasivosPorMermasLabel)))
                                .addGap(85, 85, 85)
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
                                            .addComponent(jLabel12)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(diaBalanceGananciasPorVentasLabel))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(diaBalanceFugasPedidosExternosLabel))))))
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
                    .addComponent(jLabel12)
                    .addComponent(diaBalancePasivosPorMermasTitleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diaBalancePasivosPorMermasLabel)
                .addGap(1, 1, 1)
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

        pedidosPorMesjTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane13.setViewportView(pedidosPorMesjTable);

        jLabel51.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel51.setText("Ganancias Totales");

        mesPedidosGananciasTotalesLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        mesPedidosGananciasTotalesLabel.setForeground(new java.awt.Color(189, 5, 5));
        mesPedidosGananciasTotalesLabel.setText("jLabel3");

        mesMuestraPendientesYExternosCheckBox.setText("Muestra pedidos pendientes y  externos");
        mesMuestraPendientesYExternosCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mesMuestraPendientesYExternosCheckBoxMouseClicked(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel53.setText("Balance");

        jLabel54.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel54.setText("Ganancias por ventas");

        mesBalanceGananciasPorVentasLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        mesBalanceGananciasPorVentasLabel.setForeground(new java.awt.Color(189, 5, 5));
        mesBalanceGananciasPorVentasLabel.setText("jLabel3");

        mesBalancePasivosPorMermasTitleLabel.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        mesBalancePasivosPorMermasTitleLabel.setText("Pasivos por mermas");

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

        seleccionaMesMC.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                seleccionaMesMCPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
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
                                                            .addComponent(seleccionaMesMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addGap(0, 20, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel62)
                                .addGap(476, 476, 476))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mesBalanceTotalLabel)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel58)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mesBalanceGananciasPorPedidosLabael))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel54)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mesBalanceGananciasPorVentasLabel))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(mesBalanceFugasPedidosExternosLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mesBalancePasivosPorMermasLabel)
                    .addComponent(mesBalancePasivosPorMermasTitleLabel))
                .addGap(78, 78, 78))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel43)
                    .addComponent(seleccionaMesMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jLabel54)
                    .addComponent(mesBalancePasivosPorMermasTitleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mesBalancePasivosPorMermasLabel)
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
                .addContainerGap(219, Short.MAX_VALUE))
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
        corteMuestraPendientesYExternosCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                corteMuestraPendientesYExternosCheckBoxMouseClicked(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        jLabel74.setText("Balance");

        jLabel75.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel75.setText("Ganancias por ventas");

        corteGananciasPorVentasLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        corteGananciasPorVentasLabel.setForeground(new java.awt.Color(189, 5, 5));
        corteGananciasPorVentasLabel.setText("jLabel3");

        cortePasivosPorMermasTitleLabel.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        cortePasivosPorMermasTitleLabel.setText("Pasivos por mermas");

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
        listaCortesjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaCortesjTableMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(listaCortesjTable);

        confirmaSeleccionCortejButton.setText("Ok");
        confirmaSeleccionCortejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmaSeleccionCortejButtonActionPerformed(evt);
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
                .addComponent(confirmaSeleccionCortejButton)
                .addContainerGap())
        );
        seleccionaCortePanelLayout.setVerticalGroup(
            seleccionaCortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seleccionaCortePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmaSeleccionCortejButton))
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
                        .addGap(20, 20, 20)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(corteBalanceTotaljLabel)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel79)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(corteGananciasPorPedidoLabel))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel75)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(corteGananciasPorVentasLabel))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(corteFugasPorPedidosExternosLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cortePasivosPorMermasLabel)
                    .addComponent(cortePasivosPorMermasTitleLabel))
                .addGap(89, 89, 89))
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
                    .addComponent(jLabel75)
                    .addComponent(cortePasivosPorMermasTitleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cortePasivosPorMermasLabel)
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
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel9);

        porDia.addTab("Por Corte", jScrollPane5);

        jButton3.setText("Regresar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(801, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(porDia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(porDia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        seleccionaCortePanel.setVisible(true);
        corteSeleccionadoPanel.setVisible(false);
        
        getCortesEnExistencia();
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void confirmaSeleccionCortejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmaSeleccionCortejButtonActionPerformed
        
        seleccionaCortePanel.setVisible(false);
        corteSeleccionadoPanel.setVisible(true);
        corteSeleccionadoLabel.setText(currentcorteSelected.getIdTrabajador().getNombre().toUpperCase());
        getDataPorCorte();
    }//GEN-LAST:event_confirmaSeleccionCortejButtonActionPerformed

    private void seleccionaDiaDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_seleccionaDiaDateChooserPropertyChange
        // TODO add your handling code here:
        System.out.println("entre");
        currentDaySelected = seleccionaDiaDateChooser.getDate();
        if (currentDaySelected != null){
            getDataPorDia();
        }
    }//GEN-LAST:event_seleccionaDiaDateChooserPropertyChange

    private void seleccionaMesMCPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_seleccionaMesMCPropertyChange
       
       getDataPorMes();
    }//GEN-LAST:event_seleccionaMesMCPropertyChange

    private void mesMuestraPendientesYExternosCheckBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mesMuestraPendientesYExternosCheckBoxMouseClicked
       getDataPorMes();

    }//GEN-LAST:event_mesMuestraPendientesYExternosCheckBoxMouseClicked

    private void diaMuestraPendientesYExternosCheckBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diaMuestraPendientesYExternosCheckBoxMouseClicked
        getDataPorDia();
    }//GEN-LAST:event_diaMuestraPendientesYExternosCheckBoxMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ventanaMenu.thismissCorteAdminViewer();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void listaCortesjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaCortesjTableMouseClicked

        int selectedIndex = listaCortesjTable.getSelectedRow();
        if(selectedIndex >= 0 ){
            currentcorteSelected = cortesExistentes.get(selectedIndex);
            confirmaSeleccionCortejButton.setEnabled(true);
            System.out.println("seleccionado "+ currentcorteSelected);
        }
    }//GEN-LAST:event_listaCortesjTableMouseClicked

    private void corteMuestraPendientesYExternosCheckBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_corteMuestraPendientesYExternosCheckBoxMouseClicked
        getDataPorCorte();
    }//GEN-LAST:event_corteMuestraPendientesYExternosCheckBoxMouseClicked

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
    private javax.swing.JButton confirmaSeleccionCortejButton;
    private javax.swing.JLabel corteBalanceTotaljLabel;
    private javax.swing.JLabel corteFugasPorPedidosExternosLabel;
    private javax.swing.JLabel corteGananciasPorPedidoLabel;
    private javax.swing.JLabel corteGananciasPorVentasLabel;
    private javax.swing.JLabel corteMermasPerdidasTotalesLabel;
    private javax.swing.JCheckBox corteMuestraPendientesYExternosCheckBox;
    private javax.swing.JLabel cortePasivosPorMermasLabel;
    private javax.swing.JLabel cortePasivosPorMermasTitleLabel;
    private javax.swing.JLabel cortePedidosGananciasTotalesLabel;
    private javax.swing.JLabel corteSeleccionadoLabel;
    private javax.swing.JPanel corteSeleccionadoPanel;
    private javax.swing.JLabel corteVentasGananciasTotalesLabel;
    private javax.swing.JLabel diaBalanceFugasPedidosExternosLabel;
    private javax.swing.JLabel diaBalanceGananciasPorPedidosLabael;
    private javax.swing.JLabel diaBalanceGananciasPorVentasLabel;
    private javax.swing.JLabel diaBalancePasivosPorMermasLabel;
    private javax.swing.JLabel diaBalancePasivosPorMermasTitleLabel;
    private javax.swing.JLabel diaBalanceTotalLabel;
    private javax.swing.JLabel diaMermasPerdidasTotalesLabel;
    private javax.swing.JCheckBox diaMuestraPendientesYExternosCheckBox;
    private javax.swing.JLabel diaPedidosGananciasTotalesLabel;
    private javax.swing.JLabel diaVentasGananciasTotalesLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
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
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable listaCortesjTable;
    private javax.swing.JTable mermasPorCortejTable;
    private javax.swing.JTable mermasPorDiajTable;
    private javax.swing.JLabel mesBalanceFugasPedidosExternosLabel;
    private javax.swing.JLabel mesBalanceGananciasPorPedidosLabael;
    private javax.swing.JLabel mesBalanceGananciasPorVentasLabel;
    private javax.swing.JLabel mesBalancePasivosPorMermasLabel;
    private javax.swing.JLabel mesBalancePasivosPorMermasTitleLabel;
    private javax.swing.JLabel mesBalanceTotalLabel;
    private javax.swing.JLabel mesMermasPerdidasTotalesLabel;
    private javax.swing.JCheckBox mesMuestraPendientesYExternosCheckBox;
    private javax.swing.JLabel mesPedidosGananciasTotalesLabel;
    private javax.swing.JLabel mesVentasGananciasTotalesLabel;
    private javax.swing.JTable pedidosPorCortejTable;
    private javax.swing.JTable pedidosPorDiajTable;
    private javax.swing.JTable pedidosPorMesjTable;
    private javax.swing.JTabbedPane porDia;
    private javax.swing.JPanel seleccionaCortePanel;
    private com.toedter.calendar.JDateChooser seleccionaDiaDateChooser;
    private com.toedter.calendar.JMonthChooser seleccionaMesMC;
    private javax.swing.JTable ventasPorCortejTable;
    private javax.swing.JTable ventasPorDiajTable;
    private javax.swing.JTable ventasPorMesjTable;
    // End of variables declaration//GEN-END:variables
}
