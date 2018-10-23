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
@Table(name = "tipstocare")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipstocare.findAll", query = "SELECT t FROM Tipstocare t"),
    @NamedQuery(name = "Tipstocare.findByIdTipStocare", query = "SELECT t FROM Tipstocare t WHERE t.idTipStocare = :idTipStocare")})
public class Tipstocare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipStocare")
    private Integer idTipStocare;
    @Lob
    @Size(max = 65535)
    @Column(name = "cod")
    private String cod;
    @Lob
    @Size(max = 65535)
    @Column(name = "denumire")
    private String denumire;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipStocareidTipStocare")
    private List<Miscaristocare> miscaristocareList;

    public Tipstocare() {
    }

    public Tipstocare(Integer idTipStocare) {
        this.idTipStocare = idTipStocare;
    }

    public Integer getIdTipStocare() {
        return idTipStocare;
    }

    public void setIdTipStocare(Integer idTipStocare) {
        this.idTipStocare = idTipStocare;
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
        hash += (idTipStocare != null ? idTipStocare.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipstocare)) {
            return false;
        }
        Tipstocare other = (Tipstocare) object;
        if ((this.idTipStocare == null && other.idTipStocare != null) || (this.idTipStocare != null && !this.idTipStocare.equals(other.idTipStocare))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Tipstocare[ idTipStocare=" + idTipStocare + " ]";
    }
    
}
