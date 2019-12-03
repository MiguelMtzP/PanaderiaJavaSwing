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

public class MateriaPrima {
    
    private int idMateriPrima;
    private String nombre;
    private String unidadMedida;
    private float costoUnitario;

    public int getIdMateriPrima() {
        return idMateriPrima;
    }

    public void setIdMateriPrima(int idMateriPrima) {
        this.idMateriPrima = idMateriPrima;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(float costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
}
