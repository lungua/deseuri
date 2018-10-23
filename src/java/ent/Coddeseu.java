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
@Table(name = "coddeseu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coddeseu.findAll", query = "SELECT c FROM Coddeseu c"),
    @NamedQuery(name = "Coddeseu.findByIdCodDeseu", query = "SELECT c FROM Coddeseu c WHERE c.idCodDeseu = :idCodDeseu")})
public class Coddeseu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCodDeseu")
    private Integer idCodDeseu;
    @Lob
    @Size(max = 65535)
    @Column(name = "codDeseu")
    private String codDeseu;
    @Lob
    @Size(max = 65535)
    @Column(name = "numeCodDeseu")
    private String numeCodDeseu;
    @JoinColumn(name = "SubcategoriiDeseuri_idSubcategoriiDeseuri", referencedColumnName = "idSubcategoriiDeseuri")
    @ManyToOne(optional = false)
    private Subcategoriideseuri subcategoriiDeseuriidSubcategoriiDeseuri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codDeseuidCodDeseu")
    private List<Deseuripunctlucru> deseuripunctlucruList;

    public Coddeseu() {
    }

    public Coddeseu(Integer idCodDeseu) {
        this.idCodDeseu = idCodDeseu;
    }

    public Integer getIdCodDeseu() {
        return idCodDeseu;
    }

    public void setIdCodDeseu(Integer idCodDeseu) {
        this.idCodDeseu = idCodDeseu;
    }

    public String getCodDeseu() {
        return codDeseu;
    }

    public void setCodDeseu(String codDeseu) {
        this.codDeseu = codDeseu;
    }

    public String getNumeCodDeseu() {
        return numeCodDeseu;
    }

    public void setNumeCodDeseu(String numeCodDeseu) {
        this.numeCodDeseu = numeCodDeseu;
    }

    public Subcategoriideseuri getSubcategoriiDeseuriidSubcategoriiDeseuri() {
        return subcategoriiDeseuriidSubcategoriiDeseuri;
    }

    public void setSubcategoriiDeseuriidSubcategoriiDeseuri(Subcategoriideseuri subcategoriiDeseuriidSubcategoriiDeseuri) {
        this.subcategoriiDeseuriidSubcategoriiDeseuri = subcategoriiDeseuriidSubcategoriiDeseuri;
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
        hash += (idCodDeseu != null ? idCodDeseu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coddeseu)) {
            return false;
        }
        Coddeseu other = (Coddeseu) object;
        if ((this.idCodDeseu == null && other.idCodDeseu != null) || (this.idCodDeseu != null && !this.idCodDeseu.equals(other.idCodDeseu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Coddeseu[ idCodDeseu=" + idCodDeseu + " ]";
    }
    
}
