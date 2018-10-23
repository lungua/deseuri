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
@Table(name = "scopultratarii")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scopultratarii.findAll", query = "SELECT s FROM Scopultratarii s"),
    @NamedQuery(name = "Scopultratarii.findByIdScopulTratarii", query = "SELECT s FROM Scopultratarii s WHERE s.idScopulTratarii = :idScopulTratarii")})
public class Scopultratarii implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idScopulTratarii")
    private Integer idScopulTratarii;
    @Lob
    @Size(max = 65535)
    @Column(name = "cod")
    private String cod;
    @Lob
    @Size(max = 65535)
    @Column(name = "denumire")
    private String denumire;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scopulTratariiidScopulTratarii")
    private List<Miscaristocare> miscaristocareList;

    public Scopultratarii() {
    }

    public Scopultratarii(Integer idScopulTratarii) {
        this.idScopulTratarii = idScopulTratarii;
    }

    public Integer getIdScopulTratarii() {
        return idScopulTratarii;
    }

    public void setIdScopulTratarii(Integer idScopulTratarii) {
        this.idScopulTratarii = idScopulTratarii;
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
        hash += (idScopulTratarii != null ? idScopulTratarii.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scopultratarii)) {
            return false;
        }
        Scopultratarii other = (Scopultratarii) object;
        if ((this.idScopulTratarii == null && other.idScopulTratarii != null) || (this.idScopulTratarii != null && !this.idScopulTratarii.equals(other.idScopulTratarii))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Scopultratarii[ idScopulTratarii=" + idScopulTratarii + " ]";
    }
    
}
