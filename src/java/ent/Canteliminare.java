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
@Table(name = "canteliminare")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Canteliminare.findAll", query = "SELECT c FROM Canteliminare c"),
    @NamedQuery(name = "Canteliminare.findByIdcantEliminare", query = "SELECT c FROM Canteliminare c WHERE c.idcantEliminare = :idcantEliminare"),
    @NamedQuery(name = "Canteliminare.findByCantitate", query = "SELECT c FROM Canteliminare c WHERE c.cantitate = :cantitate"),
    @NamedQuery(name = "Canteliminare.findByDataDocument", query = "SELECT c FROM Canteliminare c WHERE c.dataDocument = :dataDocument")})
public class Canteliminare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcantEliminare")
    private Integer idcantEliminare;
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
    @Lob
    @Size(max = 65535)
    @Column(name = "agentEc")
    private String agentEc;
    @JoinColumn(name = "MiscariLuna_idMiscariLuna", referencedColumnName = "idMiscariLuna")
    @ManyToOne(optional = false)
    private Miscariluna miscariLunaidMiscariLuna;

    public Canteliminare() {
    }

    public Canteliminare(Integer idcantEliminare) {
        this.idcantEliminare = idcantEliminare;
    }

    public Integer getIdcantEliminare() {
        return idcantEliminare;
    }

    public void setIdcantEliminare(Integer idcantEliminare) {
        this.idcantEliminare = idcantEliminare;
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

    public String getAgentEc() {
        return agentEc;
    }

    public void setAgentEc(String agentEc) {
        this.agentEc = agentEc;
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
        hash += (idcantEliminare != null ? idcantEliminare.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Canteliminare)) {
            return false;
        }
        Canteliminare other = (Canteliminare) object;
        if ((this.idcantEliminare == null && other.idcantEliminare != null) || (this.idcantEliminare != null && !this.idcantEliminare.equals(other.idcantEliminare))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Canteliminare[ idcantEliminare=" + idcantEliminare + " ]";
    }
    
}
