/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author miguel
 */
public class AbonoPedido {
    private int idPedidosAbono;
    private int idCorte;
    private int idPedido;
    private Date createdAt;
    private float cantidad;

    /**
     * @return the idPedidosAbono
     */
    public int getIdPedidosAbono() {
        return idPedidosAbono;
    }

    /**
     * @param idPedidosAbono the idPedidosAbono to set
     */
    public void setIdPedidosAbono(int idPedidosAbono) {
        this.idPedidosAbono = idPedidosAbono;
    }

    /**
     * @return the idCorte
     */
    public int getIdCorte() {
        return idCorte;
    }

    /**
     * @param idCorte the idCorte to set
     */
    public void setIdCorte(int idCorte) {
        this.idCorte = idCorte;
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
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the cantidad
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
}
