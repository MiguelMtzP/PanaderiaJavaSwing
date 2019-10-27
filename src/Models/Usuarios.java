/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author alexis
 */
@Entity
@Table(name = "Usuarios", catalog = "panaderia", schema = "")
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByIdTrabajador", query = "SELECT u FROM Usuarios u WHERE u.idTrabajador = :idTrabajador")
    , @NamedQuery(name = "Usuarios.findByNombre", query = "SELECT u FROM Usuarios u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuarios.findByPaterno", query = "SELECT u FROM Usuarios u WHERE u.paterno = :paterno")
    , @NamedQuery(name = "Usuarios.findByMaterno", query = "SELECT u FROM Usuarios u WHERE u.materno = :materno")
    , @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password")
    , @NamedQuery(name = "Usuarios.findByNumero", query = "SELECT u FROM Usuarios u WHERE u.numero = :numero")
    , @NamedQuery(name = "Usuarios.findByTurno", query = "SELECT u FROM Usuarios u WHERE u.turno = :turno")})
public class Usuarios implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Trabajador")
    private Integer idTrabajador;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "paterno")
    private String paterno;
    @Column(name = "materno")
    private String materno;
    @Column(name = "password")
    private String password;
    @Column(name = "numero")
    private String numero;
    @Column(name = "turno")
    private String turno;

    public Usuarios() {
    }

    public Usuarios(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        Integer oldIdTrabajador = this.idTrabajador;
        this.idTrabajador = idTrabajador;
        changeSupport.firePropertyChange("idTrabajador", oldIdTrabajador, idTrabajador);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        String oldPaterno = this.paterno;
        this.paterno = paterno;
        changeSupport.firePropertyChange("paterno", oldPaterno, paterno);
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        String oldMaterno = this.materno;
        this.materno = materno;
        changeSupport.firePropertyChange("materno", oldMaterno, materno);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String oldPassword = this.password;
        this.password = password;
        changeSupport.firePropertyChange("password", oldPassword, password);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        String oldNumero = this.numero;
        this.numero = numero;
        changeSupport.firePropertyChange("numero", oldNumero, numero);
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        String oldTurno = this.turno;
        this.turno = turno;
        changeSupport.firePropertyChange("turno", oldTurno, turno);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrabajador != null ? idTrabajador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idTrabajador == null && other.idTrabajador != null) || (this.idTrabajador != null && !this.idTrabajador.equals(other.idTrabajador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pandaderia.Usuarios[ idTrabajador=" + idTrabajador + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
