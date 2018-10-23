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
@Table(name = "cantrezultata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cantrezultata.findAll", query = "SELECT c FROM Cantrezultata c"),
    @NamedQuery(name = "Cantrezultata.findByIdCantRezultata", query = "SELECT c FROM Cantrezultata c WHERE c.idCantRezultata = :idCantRezultata"),
    @NamedQuery(name = "Cantrezultata.findByCantitate", query = "SELECT c FROM Cantrezultata c WHERE c.cantitate = :cantitate"),
    @NamedQuery(name = "Cantrezultata.findByDataDocument", query = "SELECT c FROM Cantrezultata c WHERE c.dataDocument = :dataDocument")})
public class Cantrezultata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCantRezultata")
    private Integer idCantRezultata;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantitate")
    private Double cantitate;
    @Lob
    @Size(max = 65535)
    @Column(name = "document")
    private String document;
    @Column(name = "dataDocument")
    @Temporal(TemporalType.DATE)
    private Date dataDocument;
    @Lob
    @Size(max = 65535)
    @Column(name = "observatii")
    private String observatii;
    @JoinColumn(name = "MiscariLuna_idMiscariLuna", referencedColumnName = "idMiscariLuna")
    @ManyToOne(optional = false)
    private Miscariluna miscariLunaidMiscariLuna;

    public Cantrezultata() {
    }

    public Cantrezultata(Integer idCantRezultata) {
        this.idCantRezultata = idCantRezultata;
    }

    public Integer getIdCantRezultata() {
        return idCantRezultata;
    }

    public void setIdCantRezultata(Integer idCantRezultata) {
        this.idCantRezultata = idCantRezultata;
    }

    public Double getCantitate() {
        return cantitate;
    }

    public void setCantitate(Double cantitate) {
        this.cantitate = cantitate;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Date getDataDocument() {
        return dataDocument;
    }

    public void setDataDocument(Date dataDocument) {
        this.dataDocument = dataDocument;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
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
        hash += (idCantRezultata != null ? idCantRezultata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cantrezultata)) {
            return false;
        }
        Cantrezultata other = (Cantrezultata) object;
        if ((this.idCantRezultata == null && other.idCantRezultata != null) || (this.idCantRezultata != null && !this.idCantRezultata.equals(other.idCantRezultata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Cantrezultata[ idCantRezultata=" + idCantRezultata + " ]";
    }
    
}
