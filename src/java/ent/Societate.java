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
@Table(name = "societate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Societate.findAll", query = "SELECT s FROM Societate s"),
    @NamedQuery(name = "Societate.findByIdSocietate", query = "SELECT s FROM Societate s WHERE s.idSocietate = :idSocietate")})
public class Societate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSocietate")
    private Integer idSocietate;
    @Lob
    @Size(max = 65535)
    @Column(name = "numeSocietate")
    private String numeSocietate;
    @Lob
    @Size(max = 65535)
    @Column(name = "adresa")
    private String adresa;
    @Lob
    @Size(max = 65535)
    @Column(name = "telefon")
    private String telefon;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Lob
    @Size(max = 65535)
    @Column(name = "email")
    private String email;
    @Lob
    @Size(max = 65535)
    @Column(name = "semnatura1")
    private String semnatura1;
    @Lob
    @Size(max = 65535)
    @Column(name = "semnatura2")
    private String semnatura2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "societateidSocietate")
    private List<Utilizatorisocietate> utilizatorisocietateList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "societateidSocietate")
    private List<Punctlucru> punctlucruList;

    public Societate() {
    }

    public Societate(Integer idSocietate) {
        this.idSocietate = idSocietate;
    }

    public Integer getIdSocietate() {
        return idSocietate;
    }

    public void setIdSocietate(Integer idSocietate) {
        this.idSocietate = idSocietate;
    }

    public String getNumeSocietate() {
        return numeSocietate;
    }

    public void setNumeSocietate(String numeSocietate) {
        this.numeSocietate = numeSocietate;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSemnatura1() {
        return semnatura1;
    }

    public void setSemnatura1(String semnatura1) {
        this.semnatura1 = semnatura1;
    }

    public String getSemnatura2() {
        return semnatura2;
    }

    public void setSemnatura2(String semnatura2) {
        this.semnatura2 = semnatura2;
    }

    @XmlTransient
    public List<Utilizatorisocietate> getUtilizatorisocietateList() {
        return utilizatorisocietateList;
    }

    public void setUtilizatorisocietateList(List<Utilizatorisocietate> utilizatorisocietateList) {
        this.utilizatorisocietateList = utilizatorisocietateList;
    }

    @XmlTransient
    public List<Punctlucru> getPunctlucruList() {
        return punctlucruList;
    }

    public void setPunctlucruList(List<Punctlucru> punctlucruList) {
        this.punctlucruList = punctlucruList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSocietate != null ? idSocietate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Societate)) {
            return false;
        }
        Societate other = (Societate) object;
        if ((this.idSocietate == null && other.idSocietate != null) || (this.idSocietate != null && !this.idSocietate.equals(other.idSocietate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Societate[ idSocietate=" + idSocietate + " ]";
    }
    
}
