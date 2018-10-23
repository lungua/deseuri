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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "punctlucru")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Punctlucru.findAll", query = "SELECT p FROM Punctlucru p"),
    @NamedQuery(name = "Punctlucru.findByIdPunctLucru", query = "SELECT p FROM Punctlucru p WHERE p.idPunctLucru = :idPunctLucru")})
public class Punctlucru implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPunctLucru")
    private Integer idPunctLucru;
    @Lob
    @Size(max = 65535)
    @Column(name = "numePunct")
    private String numePunct;
    @Lob
    @Size(max = 65535)
    @Column(name = "telefon")
    private String telefon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "punctLucruidPunctLucru")
    private List<Vsu> vsuList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "punctLucruidPunctLucru")
    private List<Luna> lunaList;
    @JoinColumn(name = "Societate_idSocietate", referencedColumnName = "idSocietate")
    @ManyToOne(optional = false)
    private Societate societateidSocietate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "punctLucruidPunctLucru")
    private List<Deseuripunctlucru> deseuripunctlucruList;

    public Punctlucru() {
    }

    public Punctlucru(Integer idPunctLucru) {
        this.idPunctLucru = idPunctLucru;
    }

    public Integer getIdPunctLucru() {
        return idPunctLucru;
    }

    public void setIdPunctLucru(Integer idPunctLucru) {
        this.idPunctLucru = idPunctLucru;
    }

    public String getNumePunct() {
        return numePunct;
    }

    public void setNumePunct(String numePunct) {
        this.numePunct = numePunct;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @XmlTransient
    public List<Vsu> getVsuList() {
        return vsuList;
    }

    public void setVsuList(List<Vsu> vsuList) {
        this.vsuList = vsuList;
    }

    @XmlTransient
    public List<Luna> getLunaList() {
        return lunaList;
    }

    public void setLunaList(List<Luna> lunaList) {
        this.lunaList = lunaList;
    }

    public Societate getSocietateidSocietate() {
        return societateidSocietate;
    }

    public void setSocietateidSocietate(Societate societateidSocietate) {
        this.societateidSocietate = societateidSocietate;
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
        hash += (idPunctLucru != null ? idPunctLucru.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Punctlucru)) {
            return false;
        }
        Punctlucru other = (Punctlucru) object;
        if ((this.idPunctLucru == null && other.idPunctLucru != null) || (this.idPunctLucru != null && !this.idPunctLucru.equals(other.idPunctLucru))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Punctlucru[ idPunctLucru=" + idPunctLucru + " ]";
    }
    
}
