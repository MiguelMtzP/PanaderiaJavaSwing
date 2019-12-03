/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author miguel
 */
public class Pedido {
    
    private int idPedido;
    private Empleado trabajador;
    private CorteModel corte;
    private Clientes cliente;
    private Date fechaCreacion;
    private Date fechaEntrega;
    private float costo;
    private int status;
    private float abono;
    private float resta;

    public float getResta() {
        return resta;
    }

    public void setResta(float resta) {
        this.resta = resta;
    }
    private int tipo;
    private ArrayList<ItemPedido> itemsEnPedido;
     
    public Pedido(){
        costo = abono = 0;
        itemsEnPedido = new ArrayList(0);
    }

    /**
     * @return the idPedido
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return the trabajador
     */
    public Empleado getTrabajador() {
        return trabajador;
    }
    public void setStatus(int status) {
        this.status=status;
    }

    /**
     * @return the trabajador
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * @param trabajador the trabajador to set
     */
    public void setTrabajador(Empleado trabajador) {
        this.trabajador = trabajador;
    }

    /**
     * @return the corte
     */
    public CorteModel getCorte() {
        return corte;
    }

    /**
     * @param corte the corte to set
     */
    public void setCorte(CorteModel corte) {
        this.corte = corte;
    }

    /**
     * @return the cliente
     */
    public Clientes getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaEntrega
     */
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * @return the costo
     */
    public float getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(float costo) {
        this.costo = costo;
    }

    /**
     * @return the abono
     */
    public float getAbono() {
        return abono;
    }

    /**
     * @param abono the abono to set
     */
    public void setAbono(float abono) {
        this.abono = abono;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the itemsEnPedido
     */
    public ArrayList<ItemPedido> getItemsEnPedido() {
        return itemsEnPedido;
    }

    /**
     * @param itemsEnPedido the itemsEnPedido to set
     */
    public void setItemsEnPedido(ArrayList<ItemPedido> itemsEnPedido) {
        this.itemsEnPedido = itemsEnPedido;
    }
    
    
}
