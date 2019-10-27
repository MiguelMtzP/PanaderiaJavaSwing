/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author alexis
 */
@Entity
@Table(name = "Merma", catalog = "panaderia", schema = "")
@NamedQueries({
    @NamedQuery(name = "Merma.findAll", query = "SELECT m FROM Merma m")
    , @NamedQuery(name = "Merma.findByIdMerma", query = "SELECT m FROM Merma m WHERE m.idMerma = :idMerma")
    , @NamedQuery(name = "Merma.findByFrios", query = "SELECT m FROM Merma m WHERE m.frios = :frios")
    , @NamedQuery(name = "Merma.findByComidos", query = "SELECT m FROM Merma m WHERE m.comidos = :comidos")
    , @NamedQuery(name = "Merma.findByRotos", query = "SELECT m FROM Merma m WHERE m.rotos = :rotos")
    , @NamedQuery(name = "Merma.findByFecha", query = "SELECT m FROM Merma m WHERE m.fecha = :fecha")})
public class Merma implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_Merma")
    private Integer idMerma;
    @Column(name = "frios")
    private Integer frios;
    @Column(name = "comidos")
    private Integer comidos;
    @Column(name = "rotos")
    private Integer rotos;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(mappedBy = "idCorte")
    private List<Merma> mermaList;
    @JoinColumn(name = "id_Corte", referencedColumnName = "id_Merma")
    @ManyToOne
    private Merma idCorte;
    @OneToMany(mappedBy = "idPan")
    private List<Merma> mermaList1;
    @JoinColumn(name = "id_Pan", referencedColumnName = "id_Merma")
    @ManyToOne
    private Merma idPan;

    public Merma() {
    }

    public Merma(Integer idMerma) {
        this.idMerma = idMerma;
    }

    public Integer getIdMerma() {
        return idMerma;
    }

    public void setIdMerma(Integer idMerma) {
        Integer oldIdMerma = this.idMerma;
        this.idMerma = idMerma;
        changeSupport.firePropertyChange("idMerma", oldIdMerma, idMerma);
    }

    public Integer getFrios() {
        return frios;
    }

    public void setFrios(Integer frios) {
        Integer oldFrios = this.frios;
        this.frios = frios;
        changeSupport.firePropertyChange("frios", oldFrios, frios);
    }

    public Integer getComidos() {
        return comidos;
    }

    public void setComidos(Integer comidos) {
        Integer oldComidos = this.comidos;
        this.comidos = comidos;
        changeSupport.firePropertyChange("comidos", oldComidos, comidos);
    }

    public Integer getRotos() {
        return rotos;
    }

    public void setRotos(Integer rotos) {
        Integer oldRotos = this.rotos;
        this.rotos = rotos;
        changeSupport.firePropertyChange("rotos", oldRotos, rotos);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        Date oldFecha = this.fecha;
        this.fecha = fecha;
        changeSupport.firePropertyChange("fecha", oldFecha, fecha);
    }

    public List<Merma> getMermaList() {
        return mermaList;
    }

    public void setMermaList(List<Merma> mermaList) {
        this.mermaList = mermaList;
    }

    public Merma getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(Merma idCorte) {
        Merma oldIdCorte = this.idCorte;
        this.idCorte = idCorte;
        changeSupport.firePropertyChange("idCorte", oldIdCorte, idCorte);
    }

    public List<Merma> getMermaList1() {
        return mermaList1;
    }

    public void setMermaList1(List<Merma> mermaList1) {
        this.mermaList1 = mermaList1;
    }

    public Merma getIdPan() {
        return idPan;
    }

    public void setIdPan(Merma idPan) {
        Merma oldIdPan = this.idPan;
        this.idPan = idPan;
        changeSupport.firePropertyChange("idPan", oldIdPan, idPan);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMerma != null ? idMerma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Merma)) {
            return false;
        }
        Merma other = (Merma) object;
        if ((this.idMerma == null && other.idMerma != null) || (this.idMerma != null && !this.idMerma.equals(other.idMerma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pandaderia.Merma[ idMerma=" + idMerma + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
