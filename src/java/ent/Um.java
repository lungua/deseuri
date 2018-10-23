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
@Table(name = "um")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Um.findAll", query = "SELECT u FROM Um u"),
    @NamedQuery(name = "Um.findByIdUM", query = "SELECT u FROM Um u WHERE u.idUM = :idUM")})
public class Um implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUM")
    private Integer idUM;
    @Lob
    @Size(max = 65535)
    @Column(name = "numeUM")
    private String numeUM;
    @Lob
    @Size(max = 65535)
    @Column(name = "codUM")
    private String codUM;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uMidUM")
    private List<Deseuripunctlucru> deseuripunctlucruList;

    public Um() {
    }

    public Um(Integer idUM) {
        this.idUM = idUM;
    }

    public Integer getIdUM() {
        return idUM;
    }

    public void setIdUM(Integer idUM) {
        this.idUM = idUM;
    }

    public String getNumeUM() {
        return numeUM;
    }

    public void setNumeUM(String numeUM) {
        this.numeUM = numeUM;
    }

    public String getCodUM() {
        return codUM;
    }

    public void setCodUM(String codUM) {
        this.codUM = codUM;
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
        hash += (idUM != null ? idUM.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Um)) {
            return false;
        }
        Um other = (Um) object;
        if ((this.idUM == null && other.idUM != null) || (this.idUM != null && !this.idUM.equals(other.idUM))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Um[ idUM=" + idUM + " ]";
    }
    
}
