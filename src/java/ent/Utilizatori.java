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
@Table(name = "utilizatori")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilizatori.findAll", query = "SELECT u FROM Utilizatori u"),
    @NamedQuery(name = "Utilizatori.findByIdUtilizatori", query = "SELECT u FROM Utilizatori u WHERE u.idUtilizatori = :idUtilizatori")})
public class Utilizatori implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUtilizatori")
    private Integer idUtilizatori;
    @Lob
    @Size(max = 65535)
    @Column(name = "utilizator")
    private String utilizator;
    @Lob
    @Size(max = 65535)
    @Column(name = "parola")
    private String parola;
    @Lob
    @Size(max = 65535)
    @Column(name = "nume")
    private String nume;
    @Lob
    @Size(max = 65535)
    @Column(name = "prenume")
    private String prenume;
    @Lob
    @Size(max = 65535)
    @Column(name = "adresa")
    private String adresa;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Lob
    @Size(max = 65535)
    @Column(name = "email")
    private String email;
    @Lob
    @Size(max = 65535)
    @Column(name = "telefon")
    private String telefon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilizatoriidUtilizatori")
    private List<Utilizatorisocietate> utilizatorisocietateList;

    public Utilizatori() {
    }

    public Utilizatori(Integer idUtilizatori) {
        this.idUtilizatori = idUtilizatori;
    }

    public Integer getIdUtilizatori() {
        return idUtilizatori;
    }

    public void setIdUtilizatori(Integer idUtilizatori) {
        this.idUtilizatori = idUtilizatori;
    }

    public String getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(String utilizator) {
        this.utilizator = utilizator;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @XmlTransient
    public List<Utilizatorisocietate> getUtilizatorisocietateList() {
        return utilizatorisocietateList;
    }

    public void setUtilizatorisocietateList(List<Utilizatorisocietate> utilizatorisocietateList) {
        this.utilizatorisocietateList = utilizatorisocietateList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilizatori != null ? idUtilizatori.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilizatori)) {
            return false;
        }
        Utilizatori other = (Utilizatori) object;
        if ((this.idUtilizatori == null && other.idUtilizatori != null) || (this.idUtilizatori != null && !this.idUtilizatori.equals(other.idUtilizatori))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Utilizatori[ idUtilizatori=" + idUtilizatori + " ]";
    }
    
}
