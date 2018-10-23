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
@Table(name = "miscaristocare")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Miscaristocare.findAll", query = "SELECT m FROM Miscaristocare m"),
    @NamedQuery(name = "Miscaristocare.findByIdMiscariStocare", query = "SELECT m FROM Miscaristocare m WHERE m.idMiscariStocare = :idMiscariStocare"),
    @NamedQuery(name = "Miscaristocare.findByCantitate", query = "SELECT m FROM Miscaristocare m WHERE m.cantitate = :cantitate"),
    @NamedQuery(name = "Miscaristocare.findByDataDoc", query = "SELECT m FROM Miscaristocare m WHERE m.dataDoc = :dataDoc")})
public class Miscaristocare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMiscariStocare")
    private Integer idMiscariStocare;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantitate")
    private Double cantitate;
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
    @JoinColumn(name = "Destinatia_idDestinatia", referencedColumnName = "idDestinatia")
    @ManyToOne(optional = false)
    private Destinatia destinatiaidDestinatia;
    @JoinColumn(name = "MijlocTransport_idMijlocTransport", referencedColumnName = "idMijlocTransport")
    @ManyToOne(optional = false)
    private Mijloctransport mijlocTransportidMijlocTransport;
    @JoinColumn(name = "ScopulTratarii_idScopulTratarii", referencedColumnName = "idScopulTratarii")
    @ManyToOne(optional = false)
    private Scopultratarii scopulTratariiidScopulTratarii;
    @JoinColumn(name = "ModTratare_idModTratare", referencedColumnName = "idModTratare")
    @ManyToOne(optional = false)
    private Modtratare modTratareidModTratare;
    @JoinColumn(name = "TipStocare_idTipStocare", referencedColumnName = "idTipStocare")
    @ManyToOne(optional = false)
    private Tipstocare tipStocareidTipStocare;

    public Miscaristocare() {
    }

    public Miscaristocare(Integer idMiscariStocare) {
        this.idMiscariStocare = idMiscariStocare;
    }

    public Integer getIdMiscariStocare() {
        return idMiscariStocare;
    }

    public void setIdMiscariStocare(Integer idMiscariStocare) {
        this.idMiscariStocare = idMiscariStocare;
    }

    public Double getCantitate() {
        return cantitate;
    }

    public void setCantitate(Double cantitate) {
        this.cantitate = cantitate;
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

    public Destinatia getDestinatiaidDestinatia() {
        return destinatiaidDestinatia;
    }

    public void setDestinatiaidDestinatia(Destinatia destinatiaidDestinatia) {
        this.destinatiaidDestinatia = destinatiaidDestinatia;
    }

    public Mijloctransport getMijlocTransportidMijlocTransport() {
        return mijlocTransportidMijlocTransport;
    }

    public void setMijlocTransportidMijlocTransport(Mijloctransport mijlocTransportidMijlocTransport) {
        this.mijlocTransportidMijlocTransport = mijlocTransportidMijlocTransport;
    }

    public Scopultratarii getScopulTratariiidScopulTratarii() {
        return scopulTratariiidScopulTratarii;
    }

    public void setScopulTratariiidScopulTratarii(Scopultratarii scopulTratariiidScopulTratarii) {
        this.scopulTratariiidScopulTratarii = scopulTratariiidScopulTratarii;
    }

    public Modtratare getModTratareidModTratare() {
        return modTratareidModTratare;
    }

    public void setModTratareidModTratare(Modtratare modTratareidModTratare) {
        this.modTratareidModTratare = modTratareidModTratare;
    }

    public Tipstocare getTipStocareidTipStocare() {
        return tipStocareidTipStocare;
    }

    public void setTipStocareidTipStocare(Tipstocare tipStocareidTipStocare) {
        this.tipStocareidTipStocare = tipStocareidTipStocare;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMiscariStocare != null ? idMiscariStocare.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Miscaristocare)) {
            return false;
        }
        Miscaristocare other = (Miscaristocare) object;
        if ((this.idMiscariStocare == null && other.idMiscariStocare != null) || (this.idMiscariStocare != null && !this.idMiscariStocare.equals(other.idMiscariStocare))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Miscaristocare[ idMiscariStocare=" + idMiscariStocare + " ]";
    }
    
}
