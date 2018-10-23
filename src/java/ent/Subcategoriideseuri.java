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
@Table(name = "subcategoriideseuri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subcategoriideseuri.findAll", query = "SELECT s FROM Subcategoriideseuri s"),
    @NamedQuery(name = "Subcategoriideseuri.findByIdSubcategoriiDeseuri", query = "SELECT s FROM Subcategoriideseuri s WHERE s.idSubcategoriiDeseuri = :idSubcategoriiDeseuri")})
public class Subcategoriideseuri implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSubcategoriiDeseuri")
    private Integer idSubcategoriiDeseuri;
    @Lob
    @Size(max = 65535)
    @Column(name = "numeSubcategorie")
    private String numeSubcategorie;
    @Lob
    @Size(max = 65535)
    @Column(name = "codSubCategorie")
    private String codSubCategorie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subcategoriiDeseuriidSubcategoriiDeseuri")
    private List<Coddeseu> coddeseuList;
    @JoinColumn(name = "CategoriiDeseuri_idCategoriiDeseuri", referencedColumnName = "idCategoriiDeseuri")
    @ManyToOne(optional = false)
    private Categoriideseuri categoriiDeseuriidCategoriiDeseuri;

    public Subcategoriideseuri() {
    }

    public Subcategoriideseuri(Integer idSubcategoriiDeseuri) {
        this.idSubcategoriiDeseuri = idSubcategoriiDeseuri;
    }

    public Integer getIdSubcategoriiDeseuri() {
        return idSubcategoriiDeseuri;
    }

    public void setIdSubcategoriiDeseuri(Integer idSubcategoriiDeseuri) {
        this.idSubcategoriiDeseuri = idSubcategoriiDeseuri;
    }

    public String getNumeSubcategorie() {
        return numeSubcategorie;
    }

    public void setNumeSubcategorie(String numeSubcategorie) {
        this.numeSubcategorie = numeSubcategorie;
    }

    public String getCodSubCategorie() {
        return codSubCategorie;
    }

    public void setCodSubCategorie(String codSubCategorie) {
        this.codSubCategorie = codSubCategorie;
    }

    @XmlTransient
    public List<Coddeseu> getCoddeseuList() {
        return coddeseuList;
    }

    public void setCoddeseuList(List<Coddeseu> coddeseuList) {
        this.coddeseuList = coddeseuList;
    }

    public Categoriideseuri getCategoriiDeseuriidCategoriiDeseuri() {
        return categoriiDeseuriidCategoriiDeseuri;
    }

    public void setCategoriiDeseuriidCategoriiDeseuri(Categoriideseuri categoriiDeseuriidCategoriiDeseuri) {
        this.categoriiDeseuriidCategoriiDeseuri = categoriiDeseuriidCategoriiDeseuri;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubcategoriiDeseuri != null ? idSubcategoriiDeseuri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subcategoriideseuri)) {
            return false;
        }
        Subcategoriideseuri other = (Subcategoriideseuri) object;
        if ((this.idSubcategoriiDeseuri == null && other.idSubcategoriiDeseuri != null) || (this.idSubcategoriiDeseuri != null && !this.idSubcategoriiDeseuri.equals(other.idSubcategoriiDeseuri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Subcategoriideseuri[ idSubcategoriiDeseuri=" + idSubcategoriiDeseuri + " ]";
    }
    
}
