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
@Table(name = "categoriideseuri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoriideseuri.findAll", query = "SELECT c FROM Categoriideseuri c"),
    @NamedQuery(name = "Categoriideseuri.findByIdCategoriiDeseuri", query = "SELECT c FROM Categoriideseuri c WHERE c.idCategoriiDeseuri = :idCategoriiDeseuri")})
public class Categoriideseuri implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCategoriiDeseuri")
    private Integer idCategoriiDeseuri;
    @Lob
    @Size(max = 65535)
    @Column(name = "numeCategorie")
    private String numeCategorie;
    @Lob
    @Size(max = 65535)
    @Column(name = "codCategorie")
    private String codCategorie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriiDeseuriidCategoriiDeseuri")
    private List<Subcategoriideseuri> subcategoriideseuriList;

    public Categoriideseuri() {
    }

    public Categoriideseuri(Integer idCategoriiDeseuri) {
        this.idCategoriiDeseuri = idCategoriiDeseuri;
    }

    public Integer getIdCategoriiDeseuri() {
        return idCategoriiDeseuri;
    }

    public void setIdCategoriiDeseuri(Integer idCategoriiDeseuri) {
        this.idCategoriiDeseuri = idCategoriiDeseuri;
    }

    public String getNumeCategorie() {
        return numeCategorie;
    }

    public void setNumeCategorie(String numeCategorie) {
        this.numeCategorie = numeCategorie;
    }

    public String getCodCategorie() {
        return codCategorie;
    }

    public void setCodCategorie(String codCategorie) {
        this.codCategorie = codCategorie;
    }

    @XmlTransient
    public List<Subcategoriideseuri> getSubcategoriideseuriList() {
        return subcategoriideseuriList;
    }

    public void setSubcategoriideseuriList(List<Subcategoriideseuri> subcategoriideseuriList) {
        this.subcategoriideseuriList = subcategoriideseuriList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriiDeseuri != null ? idCategoriiDeseuri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoriideseuri)) {
            return false;
        }
        Categoriideseuri other = (Categoriideseuri) object;
        if ((this.idCategoriiDeseuri == null && other.idCategoriiDeseuri != null) || (this.idCategoriiDeseuri != null && !this.idCategoriiDeseuri.equals(other.idCategoriiDeseuri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Categoriideseuri[ idCategoriiDeseuri=" + idCategoriiDeseuri + " ]";
    }
    
}
