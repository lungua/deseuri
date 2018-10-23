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
@Table(name = "miscarivalorificare")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Miscarivalorificare.findAll", query = "SELECT m FROM Miscarivalorificare m"),
    @NamedQuery(name = "Miscarivalorificare.findByIdMiscariValorificare", query = "SELECT m FROM Miscarivalorificare m WHERE m.idMiscariValorificare = :idMiscariValorificare"),
    @NamedQuery(name = "Miscarivalorificare.findByCantitate", query = "SELECT m FROM Miscarivalorificare m WHERE m.cantitate = :cantitate"),
    @NamedQuery(name = "Miscarivalorificare.findByDataDoc", query = "SELECT m FROM Miscarivalorificare m WHERE m.dataDoc = :dataDoc")})
public class Miscarivalorificare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMiscariValorificare")
    private Integer idMiscariValorificare;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantitate")
    private Double cantitate;
    @Lob
    @Size(max = 65535)
    @Column(name = "adentEc")
    private String adentEc;
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
    @JoinColumn(name = "Anexa2B_idAnexa2B", referencedColumnName = "idAnexa2B")
    @ManyToOne(optional = false)
    private Anexa2b anexa2BidAnexa2B;

    public Miscarivalorificare() {
    }

    public Miscarivalorificare(Integer idMiscariValorificare) {
        this.idMiscariValorificare = idMiscariValorificare;
    }

    public Integer getIdMiscariValorificare() {
        return idMiscariValorificare;
    }

    public void setIdMiscariValorificare(Integer idMiscariValorificare) {
        this.idMiscariValorificare = idMiscariValorificare;
    }

    public Double getCantitate() {
        return cantitate;
    }

    public void setCantitate(Double cantitate) {
        this.cantitate = cantitate;
    }

    public String getAdentEc() {
        return adentEc;
    }

    public void setAdentEc(String adentEc) {
        this.adentEc = adentEc;
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

    public Anexa2b getAnexa2BidAnexa2B() {
        return anexa2BidAnexa2B;
    }

    public void setAnexa2BidAnexa2B(Anexa2b anexa2BidAnexa2B) {
        this.anexa2BidAnexa2B = anexa2BidAnexa2B;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMiscariValorificare != null ? idMiscariValorificare.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Miscarivalorificare)) {
            return false;
        }
        Miscarivalorificare other = (Miscarivalorificare) object;
        if ((this.idMiscariValorificare == null && other.idMiscariValorificare != null) || (this.idMiscariValorificare != null && !this.idMiscariValorificare.equals(other.idMiscariValorificare))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Miscarivalorificare[ idMiscariValorificare=" + idMiscariValorificare + " ]";
    }
    
}
