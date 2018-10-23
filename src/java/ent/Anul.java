/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ent;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lungu
 */
@Entity
@Table(name = "anul")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anul.findAll", query = "SELECT a FROM Anul a"),
    @NamedQuery(name = "Anul.findByIdAnul", query = "SELECT a FROM Anul a WHERE a.idAnul = :idAnul"),
    @NamedQuery(name = "Anul.findByAnul", query = "SELECT a FROM Anul a WHERE a.anul = :anul")})
public class Anul implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAnul")
    private Integer idAnul;
    @Column(name = "anul")
    private Integer anul;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anulidAnul")
    private List<Luna> lunaList;

    public Anul() {
    }

    public Anul(Integer idAnul) {
        this.idAnul = idAnul;
    }

    public Integer getIdAnul() {
        return idAnul;
    }

    public void setIdAnul(Integer idAnul) {
        this.idAnul = idAnul;
    }

    public Integer getAnul() {
        return anul;
    }

    public void setAnul(Integer anul) {
        this.anul = anul;
    }

    @XmlTransient
    public List<Luna> getLunaList() {
        return lunaList;
    }

    public void setLunaList(List<Luna> lunaList) {
        this.lunaList = lunaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnul != null ? idAnul.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anul)) {
            return false;
        }
        Anul other = (Anul) object;
        if ((this.idAnul == null && other.idAnul != null) || (this.idAnul != null && !this.idAnul.equals(other.idAnul))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Anul[ idAnul=" + idAnul + " ]";
    }
    
}
