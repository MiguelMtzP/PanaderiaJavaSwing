/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author miguel
 */
public class ItemPedido {
    private int idItemPedido;
    private Pan pan;
    private int idPedido;
    private int cantidad;

    /**
     * @return the idItemPedido
     */
    public int getIdItemPedido() {
        return idItemPedido;
    }

    /**
     * @param idItemPedido the idItemPedido to set
     */
    public void setIdItemPedido(int idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    /**
     * @return the pan
     */
    public Pan getPan() {
        return pan;
    }

    /**
     * @param pan the pan to set
     */
    public void setPan(Pan pan) {
        this.pan = pan;
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
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
