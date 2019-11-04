/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import pandaderia.Conexion;

/**
 *
 * @author miguel
 */
public class MermaModel {

    private int idMerma;
    private int idCorte;
    private int idPan;
    private String nombrePan;
    private int frios;
    private int comidos;
    private int rotos;
    private Date fecha;

    /**
     * @return the idMerma
     */
    public int getIdMerma() {
        return idMerma;
    }

    /**
     * @param idMerma the idMerma to set
     */
    public void setIdMerma(int idMerma) {
        this.idMerma = idMerma;
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
     * @return the idPan
     */
    public int getIdPan() {
        return idPan;
    }

    /**
     * @param idPan the idPan to set
     */
    public void setIdPan(int idPan) {
        this.idPan = idPan;
    }

    /**
     * @return the frios
     */
    public int getFrios() {
        return frios;
    }

    /**
     * @param frios the frios to set
     */
    public void setFrios(int frios) {
        this.frios = frios;
    }

    /**
     * @return the comidos
     */
    public int getComidos() {
        return comidos;
    }

    /**
     * @param comidos the comidos to set
     */
    public void setComidos(int comidos) {
        this.comidos = comidos;
    }

    /**
     * @return the rotos
     */
    public int getRotos() {
        return rotos;
    }

    /**
     * @param rotos the rotos to set
     */
    public void setRotos(int rotos) {
        this.rotos = rotos;
    }
    public void setNombrePan(String nombre) {
        this.nombrePan = nombre;
    }
    public String getNombrePan() {
        return nombrePan;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public static ArrayList<MermaModel> getMermasPorCorte(CorteModel corte, Connection conexion) throws SQLException{
        PreparedStatement stm = conexion.prepareStatement("Select Merma.*, Inventario.nombre as nombrePan " +
                                                            "from Merma " +
                                                            "join Inventario on Merma.id_Pan = Inventario.id_Pan "+
                                                            "where Merma.id_Corte = ?");
        stm.setInt(1, corte.getIdCorte());
        ArrayList<MermaModel> mermas = new ArrayList();
        ResultSet res = stm.executeQuery();
        System.out.println("si entro a traer mermas:  "+corte.getIdCorte());
        while (res.next()) {            
            MermaModel merma = new MermaModel();
            merma.setIdMerma(res.getInt("id_Merma"));
            merma.setIdCorte(res.getInt("id_Corte"));
            merma.setIdPan(res.getInt("id_Pan"));
            merma.setFrios(res.getInt("frios"));
            merma.setComidos(res.getInt("comidos"));
            merma.setRotos(res.getInt("rotos"));
            merma.setNombrePan(res.getString("nombrePan"));
            merma.setFecha(res.getDate("fecha"));
            mermas.add(merma);
            System.out.println("Entro a merma: "+merma.getNombrePan());
        }
        return mermas;
        
    }
    
}
