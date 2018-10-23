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
@Table(name = "deseuripunctlucru")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deseuripunctlucru.findAll", query = "SELECT d FROM Deseuripunctlucru d"),
    @NamedQuery(name = "Deseuripunctlucru.findByIdDeseuri", query = "SELECT d FROM Deseuripunctlucru d WHERE d.idDeseuri = :idDeseuri"),
    @NamedQuery(name = "Deseuripunctlucru.findByPericulozitate", query = "SELECT d FROM Deseuripunctlucru d WHERE d.periculozitate = :periculozitate"),
    @NamedQuery(name = "Deseuripunctlucru.findByStocInitial", query = "SELECT d FROM Deseuripunctlucru d WHERE d.stocInitial = :stocInitial"),
    @NamedQuery(name = "Deseuripunctlucru.findByStocInceputLuna", query = "SELECT d FROM Deseuripunctlucru d WHERE d.stocInceputLuna = :stocInceputLuna")})
public class Deseuripunctlucru implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDeseuri")
    private Integer idDeseuri;
    @Lob
    @Size(max = 65535)
    @Column(name = "numeDeseu")
    private String numeDeseu;
    @Column(name = "periculozitate")
    private Boolean periculozitate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "stocInitial")
    private Double stocInitial;
    @Column(name = "stocInceputLuna")
    private Double stocInceputLuna;
    @JoinColumn(name = "ClasificareDin_idClasificareDin", referencedColumnName = "idClasificareDin")
    @ManyToOne(optional = false)
    private Clasificaredin clasificareDinidClasificareDin;
    @JoinColumn(name = "PunctLucru_idPunctLucru", referencedColumnName = "idPunctLucru")
    @ManyToOne(optional = false)
    private Punctlucru punctLucruidPunctLucru;
    @JoinColumn(name = "CodDeseu_idCodDeseu", referencedColumnName = "idCodDeseu")
    @ManyToOne(optional = false)
    private Coddeseu codDeseuidCodDeseu;
    @JoinColumn(name = "UM_idUM", referencedColumnName = "idUM")
    @ManyToOne(optional = false)
    private Um uMidUM;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deseuriPunctLucruidDeseuri")
    private List<Miscariluna> miscarilunaList;

    public Deseuripunctlucru() {
    }

    public Deseuripunctlucru(Integer idDeseuri) {
        this.idDeseuri = idDeseuri;
    }

    public Integer getIdDeseuri() {
        return idDeseuri;
    }

    public void setIdDeseuri(Integer idDeseuri) {
        this.idDeseuri = idDeseuri;
    }

    public String getNumeDeseu() {
        return numeDeseu;
    }

    public void setNumeDeseu(String numeDeseu) {
        this.numeDeseu = numeDeseu;
    }

    public Boolean getPericulozitate() {
        return periculozitate;
    }

    public void setPericulozitate(Boolean periculozitate) {
        this.periculozitate = periculozitate;
    }

    public Double getStocInitial() {
        return stocInitial;
    }

    public void setStocInitial(Double stocInitial) {
        this.stocInitial = stocInitial;
    }

    public Double getStocInceputLuna() {
        return stocInceputLuna;
    }

    public void setStocInceputLuna(Double stocInceputLuna) {
        this.stocInceputLuna = stocInceputLuna;
    }

    public Clasificaredin getClasificareDinidClasificareDin() {
        return clasificareDinidClasificareDin;
    }

    public void setClasificareDinidClasificareDin(Clasificaredin clasificareDinidClasificareDin) {
        this.clasificareDinidClasificareDin = clasificareDinidClasificareDin;
    }

    public Punctlucru getPunctLucruidPunctLucru() {
        return punctLucruidPunctLucru;
    }

    public void setPunctLucruidPunctLucru(Punctlucru punctLucruidPunctLucru) {
        this.punctLucruidPunctLucru = punctLucruidPunctLucru;
    }

    public Coddeseu getCodDeseuidCodDeseu() {
        return codDeseuidCodDeseu;
    }

    public void setCodDeseuidCodDeseu(Coddeseu codDeseuidCodDeseu) {
        this.codDeseuidCodDeseu = codDeseuidCodDeseu;
    }

    public Um getUMidUM() {
        return uMidUM;
    }

    public void setUMidUM(Um uMidUM) {
        this.uMidUM = uMidUM;
    }

    @XmlTransient
    public List<Miscariluna> getMiscarilunaList() {
        return miscarilunaList;
    }

    public void setMiscarilunaList(List<Miscariluna> miscarilunaList) {
        this.miscarilunaList = miscarilunaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDeseuri != null ? idDeseuri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deseuripunctlucru)) {
            return false;
        }
        Deseuripunctlucru other = (Deseuripunctlucru) object;
        if ((this.idDeseuri == null && other.idDeseuri != null) || (this.idDeseuri != null && !this.idDeseuri.equals(other.idDeseuri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Deseuripunctlucru[ idDeseuri=" + idDeseuri + " ]";
    }
    
}
