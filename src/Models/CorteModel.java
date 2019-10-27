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
public class CorteModel {
    
    private int idCorte;
    private Empleado trabajador;
    private Date fechaInicio;
    private Date fechaFin;
    private int estatus;
    private String turno;
    private int totalVentas;
    private int totalMermas;

    public int getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(int idCorte) {
        this.idCorte = idCorte;
    }

    public Empleado getIdTrabajador() {
        return trabajador;
    }

    public void setIdTrabajador(Empleado trabajador) {
        this.trabajador = trabajador;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fecha) {
        this.fechaFin = fecha;
    }
    public Date getFechaFin() {
        return fechaInicio;
    }

    public void setFechaFin(Date fecha) {
        this.fechaFin = fecha;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(int totalVentas) {
        this.totalVentas = totalVentas;
    }

    public int getTotalMermas() {
        return totalMermas;
    }

    public void setTotalMermas(int totalMermas) {
        this.totalMermas = totalMermas;
    }
    
}
