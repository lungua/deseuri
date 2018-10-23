/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ent;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lungu
 */
@Entity
@Table(name = "miscarigenerare")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Miscarigenerare.findAll", query = "SELECT m FROM Miscarigenerare m"),
    @NamedQuery(name = "Miscarigenerare.findByIdMiscariGenerare", query = "SELECT m FROM Miscarigenerare m WHERE m.idMiscariGenerare = :idMiscariGenerare"),
    @NamedQuery(name = "Miscarigenerare.findByCantitate", query = "SELECT m FROM Miscarigenerare m WHERE m.cantitate = :cantitate"),
    @NamedQuery(name = "Miscarigenerare.findByStocInceputLuna", query = "SELECT m FROM Miscarigenerare m WHERE m.stocInceputLuna = :stocInceputLuna"),
    @NamedQuery(name = "Miscarigenerare.findByDataDoc", query = "SELECT m FROM Miscarigenerare m WHERE m.dataDoc = :dataDoc")})
public class Miscarigenerare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMiscariGenerare")
    private Integer idMiscariGenerare;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantitate")
    private Double cantitate;
    @Column(name = "stocInceputLuna")
    private Double stocInceputLuna;
    @Lob
    @Size(max = 65535)
    @Column(name = "tipDoc")
    private String tipDoc;
    @Column(name = "dataDoc")
    @Temporal(TemporalType.DATE)
    private Date dataDoc;
    @JoinColumn(name = "MiscariLuna_idMiscariLuna", referencedColumnName = "idMiscariLuna")
    @ManyToOne(optional = false)
    private Miscariluna miscariLunaidMiscariLuna;

    public Miscarigenerare() {
    }

    public Miscarigenerare(Integer idMiscariGenerare) {
        this.idMiscariGenerare = idMiscariGenerare;
    }

    public Integer getIdMiscariGenerare() {
        return idMiscariGenerare;
    }

    public void setIdMiscariGenerare(Integer idMiscariGenerare) {
        this.idMiscariGenerare = idMiscariGenerare;
    }

    public Double getCantitate() {
        return cantitate;
    }

    public void setCantitate(Double cantitate) {
        this.cantitate = cantitate;
    }

    public Double getStocInceputLuna() {
        return stocInceputLuna;
    }

    public void setStocInceputLuna(Double stocInceputLuna) {
        this.stocInceputLuna = stocInceputLuna;
    }

    public String getTipDoc() {
        return tipDoc;
    }

    public void setTipDoc(String tipDoc) {
        this.tipDoc = tipDoc;
    }

    public Date getDataDoc() {
        return dataDoc;
    }

    public void setDataDoc(Date dataDoc) {
        this.dataDoc = dataDoc;
    }

    public Miscariluna getMiscariLunaidMiscariLuna() {
        return miscariLunaidMiscariLuna;
    }

    public void setMiscariLunaidMiscariLuna(Miscariluna miscariLunaidMiscariLuna) {
        this.miscariLunaidMiscariLuna = miscariLunaidMiscariLuna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMiscariGenerare != null ? idMiscariGenerare.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Miscarigenerare)) {
            return false;
        }
        Miscarigenerare other = (Miscarigenerare) object;
        if ((this.idMiscariGenerare == null && other.idMiscariGenerare != null) || (this.idMiscariGenerare != null && !this.idMiscariGenerare.equals(other.idMiscariGenerare))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Miscarigenerare[ idMiscariGenerare=" + idMiscariGenerare + " ]";
    }
    
}
