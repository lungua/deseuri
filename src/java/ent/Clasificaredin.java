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
@Table(name = "clasificaredin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clasificaredin.findAll", query = "SELECT c FROM Clasificaredin c"),
    @NamedQuery(name = "Clasificaredin.findByIdClasificareDin", query = "SELECT c FROM Clasificaredin c WHERE c.idClasificareDin = :idClasificareDin")})
public class Clasificaredin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idClasificareDin")
    private Integer idClasificareDin;
    @Lob
    @Size(max = 65535)
    @Column(name = "codClasificare")
    private String codClasificare;
    @Lob
    @Size(max = 65535)
    @Column(name = "denumireClasificare")
    private String denumireClasificare;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clasificareDinidClasificareDin")
    private List<Deseuripunctlucru> deseuripunctlucruList;

    public Clasificaredin() {
    }

    public Clasificaredin(Integer idClasificareDin) {
        this.idClasificareDin = idClasificareDin;
    }

    public Integer getIdClasificareDin() {
        return idClasificareDin;
    }

    public void setIdClasificareDin(Integer idClasificareDin) {
        this.idClasificareDin = idClasificareDin;
    }

    public String getCodClasificare() {
        return codClasificare;
    }

    public void setCodClasificare(String codClasificare) {
        this.codClasificare = codClasificare;
    }

    public String getDenumireClasificare() {
        return denumireClasificare;
    }

    public void setDenumireClasificare(String denumireClasificare) {
        this.denumireClasificare = denumireClasificare;
    }

    @XmlTransient
    public List<Deseuripunctlucru> getDeseuripunctlucruList() {
        return deseuripunctlucruList;
    }

    public void setDeseuripunctlucruList(List<Deseuripunctlucru> deseuripunctlucruList) {
        this.deseuripunctlucruList = deseuripunctlucruList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClasificareDin != null ? idClasificareDin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clasificaredin)) {
            return false;
        }
        Clasificaredin other = (Clasificaredin) object;
        if ((this.idClasificareDin == null && other.idClasificareDin != null) || (this.idClasificareDin != null && !this.idClasificareDin.equals(other.idClasificareDin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Clasificaredin[ idClasificareDin=" + idClasificareDin + " ]";
    }
    
}
