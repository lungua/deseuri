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
@Table(name = "operatia2a")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operatia2a.findAll", query = "SELECT o FROM Operatia2a o"),
    @NamedQuery(name = "Operatia2a.findByIdOperatia2A", query = "SELECT o FROM Operatia2a o WHERE o.idOperatia2A = :idOperatia2A"),
    @NamedQuery(name = "Operatia2a.findByCod", query = "SELECT o FROM Operatia2a o WHERE o.cod = :cod")})
public class Operatia2a implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOperatia2A")
    private Integer idOperatia2A;
    @Column(name = "cod")
    private Integer cod;
    @Lob
    @Size(max = 65535)
    @Column(name = "denumire")
    private String denumire;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operatia2AidOperatia2A")
    private List<Miscarieliminare> miscarieliminareList;

    public Operatia2a() {
    }

    public Operatia2a(Integer idOperatia2A) {
        this.idOperatia2A = idOperatia2A;
    }

    public Integer getIdOperatia2A() {
        return idOperatia2A;
    }

    public void setIdOperatia2A(Integer idOperatia2A) {
        this.idOperatia2A = idOperatia2A;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    @XmlTransient
    public List<Miscarieliminare> getMiscarieliminareList() {
        return miscarieliminareList;
    }

    public void setMiscarieliminareList(List<Miscarieliminare> miscarieliminareList) {
        this.miscarieliminareList = miscarieliminareList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperatia2A != null ? idOperatia2A.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operatia2a)) {
            return false;
        }
        Operatia2a other = (Operatia2a) object;
        if ((this.idOperatia2A == null && other.idOperatia2A != null) || (this.idOperatia2A != null && !this.idOperatia2A.equals(other.idOperatia2A))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Operatia2a[ idOperatia2A=" + idOperatia2A + " ]";
    }
    
}
