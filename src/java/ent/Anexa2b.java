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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lungu
 */
@Entity
@Table(name = "anexa2b")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anexa2b.findAll", query = "SELECT a FROM Anexa2b a"),
    @NamedQuery(name = "Anexa2b.findByIdAnexa2B", query = "SELECT a FROM Anexa2b a WHERE a.idAnexa2B = :idAnexa2B"),
    @NamedQuery(name = "Anexa2b.findByCod", query = "SELECT a FROM Anexa2b a WHERE a.cod = :cod")})
public class Anexa2b implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAnexa2B")
    private Integer idAnexa2B;
    @Column(name = "cod")
    private Integer cod;
    @Lob
    @Size(max = 65535)
    @Column(name = "denumirea")
    private String denumirea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anexa2BidAnexa2B")
    private List<Miscarivalorificare> miscarivalorificareList;

    public Anexa2b() {
    }

    public Anexa2b(Integer idAnexa2B) {
        this.idAnexa2B = idAnexa2B;
    }

    public Integer getIdAnexa2B() {
        return idAnexa2B;
    }

    public void setIdAnexa2B(Integer idAnexa2B) {
        this.idAnexa2B = idAnexa2B;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDenumirea() {
        return denumirea;
    }

    public void setDenumirea(String denumirea) {
        this.denumirea = denumirea;
    }

    @XmlTransient
    public List<Miscarivalorificare> getMiscarivalorificareList() {
        return miscarivalorificareList;
    }

    public void setMiscarivalorificareList(List<Miscarivalorificare> miscarivalorificareList) {
        this.miscarivalorificareList = miscarivalorificareList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnexa2B != null ? idAnexa2B.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anexa2b)) {
            return false;
        }
        Anexa2b other = (Anexa2b) object;
        if ((this.idAnexa2B == null && other.idAnexa2B != null) || (this.idAnexa2B != null && !this.idAnexa2B.equals(other.idAnexa2B))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Anexa2b[ idAnexa2B=" + idAnexa2B + " ]";
    }
    
}
