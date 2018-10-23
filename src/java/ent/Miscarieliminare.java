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
@Table(name = "miscarieliminare")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Miscarieliminare.findAll", query = "SELECT m FROM Miscarieliminare m"),
    @NamedQuery(name = "Miscarieliminare.findByIdMiscariEliminare", query = "SELECT m FROM Miscarieliminare m WHERE m.idMiscariEliminare = :idMiscariEliminare"),
    @NamedQuery(name = "Miscarieliminare.findByCantitatea", query = "SELECT m FROM Miscarieliminare m WHERE m.cantitatea = :cantitatea"),
    @NamedQuery(name = "Miscarieliminare.findByDataDoc", query = "SELECT m FROM Miscarieliminare m WHERE m.dataDoc = :dataDoc")})
public class Miscarieliminare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMiscariEliminare")
    private Integer idMiscariEliminare;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantitatea")
    private Double cantitatea;
    @Lob
    @Size(max = 65535)
    @Column(name = "agentEc")
    private String agentEc;
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
    @JoinColumn(name = "Operatia2A_idOperatia2A", referencedColumnName = "idOperatia2A")
    @ManyToOne(optional = false)
    private Operatia2a operatia2AidOperatia2A;

    public Miscarieliminare() {
    }

    public String getAgentEc() {
        return agentEc;
    }

    public void setAgentEc(String agentEc) {
        this.agentEc = agentEc;
    }
    
    public Miscarieliminare(Integer idMiscariEliminare) {
        this.idMiscariEliminare = idMiscariEliminare;
    }

    public Integer getIdMiscariEliminare() {
        return idMiscariEliminare;
    }

    public void setIdMiscariEliminare(Integer idMiscariEliminare) {
        this.idMiscariEliminare = idMiscariEliminare;
    }

    public Double getCantitatea() {
        return cantitatea;
    }

    public void setCantitatea(Double cantitatea) {
        this.cantitatea = cantitatea;
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

    public Operatia2a getOperatia2AidOperatia2A() {
        return operatia2AidOperatia2A;
    }

    public void setOperatia2AidOperatia2A(Operatia2a operatia2AidOperatia2A) {
        this.operatia2AidOperatia2A = operatia2AidOperatia2A;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMiscariEliminare != null ? idMiscariEliminare.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Miscarieliminare)) {
            return false;
        }
        Miscarieliminare other = (Miscarieliminare) object;
        if ((this.idMiscariEliminare == null && other.idMiscariEliminare != null) || (this.idMiscariEliminare != null && !this.idMiscariEliminare.equals(other.idMiscariEliminare))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Miscarieliminare[ idMiscariEliminare=" + idMiscariEliminare + " ]";
    }
    
}
