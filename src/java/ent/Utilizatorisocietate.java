/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ent;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lungu
 */
@Entity
@Table(name = "utilizatorisocietate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilizatorisocietate.findAll", query = "SELECT u FROM Utilizatorisocietate u"),
    @NamedQuery(name = "Utilizatorisocietate.findByIdUtilizatoriSocietate", query = "SELECT u FROM Utilizatorisocietate u WHERE u.idUtilizatoriSocietate = :idUtilizatoriSocietate")})
public class Utilizatorisocietate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUtilizatoriSocietate")
    private Integer idUtilizatoriSocietate;
    @JoinColumn(name = "Utilizatori_idUtilizatori", referencedColumnName = "idUtilizatori")
    @ManyToOne(optional = false)
    private Utilizatori utilizatoriidUtilizatori;
    @JoinColumn(name = "Societate_idSocietate", referencedColumnName = "idSocietate")
    @ManyToOne(optional = false)
    private Societate societateidSocietate;

    public Utilizatorisocietate() {
    }

    public Utilizatorisocietate(Integer idUtilizatoriSocietate) {
        this.idUtilizatoriSocietate = idUtilizatoriSocietate;
    }

    public Integer getIdUtilizatoriSocietate() {
        return idUtilizatoriSocietate;
    }

    public void setIdUtilizatoriSocietate(Integer idUtilizatoriSocietate) {
        this.idUtilizatoriSocietate = idUtilizatoriSocietate;
    }

    public Utilizatori getUtilizatoriidUtilizatori() {
        return utilizatoriidUtilizatori;
    }

    public void setUtilizatoriidUtilizatori(Utilizatori utilizatoriidUtilizatori) {
        this.utilizatoriidUtilizatori = utilizatoriidUtilizatori;
    }

    public Societate getSocietateidSocietate() {
        return societateidSocietate;
    }

    public void setSocietateidSocietate(Societate societateidSocietate) {
        this.societateidSocietate = societateidSocietate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilizatoriSocietate != null ? idUtilizatoriSocietate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilizatorisocietate)) {
            return false;
        }
        Utilizatorisocietate other = (Utilizatorisocietate) object;
        if ((this.idUtilizatoriSocietate == null && other.idUtilizatoriSocietate != null) || (this.idUtilizatoriSocietate != null && !this.idUtilizatoriSocietate.equals(other.idUtilizatoriSocietate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Utilizatorisocietate[ idUtilizatoriSocietate=" + idUtilizatoriSocietate + " ]";
    }
    
}
