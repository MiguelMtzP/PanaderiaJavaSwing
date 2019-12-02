/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author miguel
 */
public class GastoMateriaPrima {
    private int idGastoMateriaPrima;
    private float totalCosto;
    private Date fecha;
    private int cantProducto;

    private int idCorte;
    private Proveedores proveedor;
    private MateriaPrima materiaPrima;
    
    
    public int getIdGastoMateriaPrima() {
        return idGastoMateriaPrima;
    }

    public void setIdGastoMateriaPrima(int idGastoMateriaPrima) {
        this.idGastoMateriaPrima = idGastoMateriaPrima;
    }

    public float getTotalCosto() {
        return totalCosto;
    }

    public void setTotalCosto(float totalCosto) {
        this.totalCosto = totalCosto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantProducto() {
        return cantProducto;
    }

    public void setCantProducto(int cantProducto) {
        this.cantProducto = cantProducto;
    }

    public int getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(int idCorte) {
        this.idCorte = idCorte;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores idProveedor) {
        this.proveedor = idProveedor;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima idMateriaPrima) {
        this.materiaPrima = idMateriaPrima;
    }
}
