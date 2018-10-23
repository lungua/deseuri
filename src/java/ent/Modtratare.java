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
@Table(name = "modtratare")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modtratare.findAll", query = "SELECT m FROM Modtratare m"),
    @NamedQuery(name = "Modtratare.findByIdModTratare", query = "SELECT m FROM Modtratare m WHERE m.idModTratare = :idModTratare")})
public class Modtratare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idModTratare")
    private Integer idModTratare;
    @Lob
    @Size(max = 65535)
    @Column(name = "cod")
    private String cod;
    @Lob
    @Size(max = 65535)
    @Column(name = "denumire")
    private String denumire;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modTratareidModTratare")
    private List<Miscaristocare> miscaristocareList;

    public Modtratare() {
    }

    public Modtratare(Integer idModTratare) {
        this.idModTratare = idModTratare;
    }

    public Integer getIdModTratare() {
        return idModTratare;
    }

    public void setIdModTratare(Integer idModTratare) {
        this.idModTratare = idModTratare;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    @XmlTransient
    public List<Miscaristocare> getMiscaristocareList() {
        return miscaristocareList;
    }

    public void setMiscaristocareList(List<Miscaristocare> miscaristocareList) {
        this.miscaristocareList = miscaristocareList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModTratare != null ? idModTratare.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modtratare)) {
            return false;
        }
        Modtratare other = (Modtratare) object;
        if ((this.idModTratare == null && other.idModTratare != null) || (this.idModTratare != null && !this.idModTratare.equals(other.idModTratare))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Modtratare[ idModTratare=" + idModTratare + " ]";
    }
    
}
