package Models;

import java.util.Date;

/**
 *
 * @author alexis
 */
public class RegVenta {
    
    private int idVentas;
    private int idTrabajador;
    private int idPan;
    private int idCorte;
    private Date fecha;
    private int cantidad;
    private float total;

    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public int getIdPan() {
        return idPan;
    }

    public void setIdPan(int idPan) {
        this.idPan = idPan;
    }

    public int getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(int idCorte) {
        this.idCorte = idCorte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    @Override
    public String toString() {
        return "--->"+idVentas+", "+idTrabajador+", "+idPan+", "+idCorte+", "+fecha+", "+cantidad+", "+total;
    }
    
    
}
