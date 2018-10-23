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
@Table(name = "miscariluna")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Miscariluna.findAll", query = "SELECT m FROM Miscariluna m"),
    @NamedQuery(name = "Miscariluna.findByIdMiscariLuna", query = "SELECT m FROM Miscariluna m WHERE m.idMiscariLuna = :idMiscariLuna"),
    @NamedQuery(name = "Miscariluna.findByStocInceput", query = "SELECT m FROM Miscariluna m WHERE m.stocInceput = :stocInceput"),
    @NamedQuery(name = "Miscariluna.findByStocSfirsit", query = "SELECT m FROM Miscariluna m WHERE m.stocSfirsit = :stocSfirsit"),
    @NamedQuery(name = "Miscariluna.findByCantRezultata", query = "SELECT m FROM Miscariluna m WHERE m.cantRezultata = :cantRezultata"),
    @NamedQuery(name = "Miscariluna.findByComponenteComercializate", query = "SELECT m FROM Miscariluna m WHERE m.componenteComercializate = :componenteComercializate"),
    @NamedQuery(name = "Miscariluna.findByCantReciclate", query = "SELECT m FROM Miscariluna m WHERE m.cantReciclate = :cantReciclate"),
    @NamedQuery(name = "Miscariluna.findByCantValorificate", query = "SELECT m FROM Miscariluna m WHERE m.cantValorificate = :cantValorificate"),
    @NamedQuery(name = "Miscariluna.findByCantEliminate", query = "SELECT m FROM Miscariluna m WHERE m.cantEliminate = :cantEliminate"),
    @NamedQuery(name = "Miscariluna.findByVsuNr", query = "SELECT m FROM Miscariluna m WHERE m.vsuNr = :vsuNr")})
public class Miscariluna implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miscariLunaidMiscariLuna")
    private List<Canteliminare> canteliminareList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMiscariLuna")
    private Integer idMiscariLuna;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "StocInceput")
    private Double stocInceput;
    @Column(name = "stocSfirsit")
    private Double stocSfirsit;
    @Column(name = "CantRezultata")
    private Double cantRezultata;
    @Column(name = "ComponenteComercializate")
    private Double componenteComercializate;
    @Column(name = "CantReciclate")
    private Double cantReciclate;
    @Column(name = "CantValorificate")
    private Double cantValorificate;
    @Column(name = "CantEliminate")
    private Double cantEliminate;
    
    
    @Column(name = "miscValorificare")
    private Double miscValorificare;
    
    @Column(name = "miscEliminare")
    private Double miscEliminare;
    
    @Column(name = "miscGenerare")
    private Double miscGenerare;
    
    @Column(name = "miscStocare")
    private Double miscStocare;
    
     
    
    
    @Lob
    @Size(max = 65535)
    @Column(name = "AgentElconomic")
    private String agentElconomic;
    @Column(name = "vsuNr")
    private Double vsuNr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miscariLunaidMiscariLuna")
    private List<Cantreciclata> cantreciclataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miscariLunaidMiscariLuna")
    private List<Miscaristocare> miscaristocareList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miscariLunaidMiscariLuna")
    private List<Miscarieliminare> miscarieliminareList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miscariLunaidMiscariLuna")
    private List<Cantvalorificata> cantvalorificataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miscariLunaidMiscariLuna")
    private List<Miscarivalorificare> miscarivalorificareList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miscariLunaidMiscariLuna")
    private List<Cantcomercializata> cantcomercializataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miscariLunaidMiscariLuna")
    private List<Cantrezultata> cantrezultataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miscariLunaidMiscariLuna")
    private List<Miscarigenerare> miscarigenerareList;
    @JoinColumn(name = "Luna_idLuna", referencedColumnName = "idLuna")
    @ManyToOne(optional = false)
    private Luna lunaidLuna;
    @JoinColumn(name = "DeseuriPunctLucru_idDeseuri", referencedColumnName = "idDeseuri")
    @ManyToOne(optional = false)
    private Deseuripunctlucru deseuriPunctLucruidDeseuri;

    public Miscariluna() {
    }

    public Double getMiscValorificare() {
        return miscValorificare;
    }

    public void setMiscValorificare(Double miscValorificare) {
        this.miscValorificare = miscValorificare;
    }

    public Double getMiscEliminare() {
        return miscEliminare;
    }

    public void setMiscEliminare(Double miscEliminare) {
        this.miscEliminare = miscEliminare;
    }

    public Double getMiscGenerare() {
        return miscGenerare;
    }

    public void setMiscGenerare(Double miscGenerare) {
        this.miscGenerare = miscGenerare;
    }

    public Double getMiscStocare() {
        return miscStocare;
    }

    public void setMiscStocare(Double miscStocare) {
        this.miscStocare = miscStocare;
    }

    public Miscariluna(Integer idMiscariLuna) {
        this.idMiscariLuna = idMiscariLuna;
    }

    public Integer getIdMiscariLuna() {
        return idMiscariLuna;
    }

    public void setIdMiscariLuna(Integer idMiscariLuna) {
        this.idMiscariLuna = idMiscariLuna;
    }

    public Double getStocInceput() {
        return stocInceput;
    }

    public void setStocInceput(Double stocInceput) {
        this.stocInceput = stocInceput;
    }

    public Double getStocSfirsit() {
        return stocSfirsit;
    }

    public void setStocSfirsit(Double stocSfirsit) {
        this.stocSfirsit = stocSfirsit;
    }

    public Double getCantRezultata() {
        return cantRezultata;
    }

    public void setCantRezultata(Double cantRezultata) {
        this.cantRezultata = cantRezultata;
    }

    public Double getComponenteComercializate() {
        return componenteComercializate;
    }

    public void setComponenteComercializate(Double componenteComercializate) {
        this.componenteComercializate = componenteComercializate;
    }

    public Double getCantReciclate() {
        return cantReciclate;
    }

    public void setCantReciclate(Double cantReciclate) {
        this.cantReciclate = cantReciclate;
    }

    public Double getCantValorificate() {
        return cantValorificate;
    }

    public void setCantValorificate(Double cantValorificate) {
        this.cantValorificate = cantValorificate;
    }

    public Double getCantEliminate() {
        return cantEliminate;
    }

    public void setCantEliminate(Double cantEliminate) {
        this.cantEliminate = cantEliminate;
    }

    public String getAgentElconomic() {
        return agentElconomic;
    }

    public void setAgentElconomic(String agentElconomic) {
        this.agentElconomic = agentElconomic;
    }

    public Double getVsuNr() {
        return vsuNr;
    }

    public void setVsuNr(Double vsuNr) {
        this.vsuNr = vsuNr;
    }

    @XmlTransient
    public List<Cantreciclata> getCantreciclataList() {
        return cantreciclataList;
    }

    public void setCantreciclataList(List<Cantreciclata> cantreciclataList) {
        this.cantreciclataList = cantreciclataList;
    }

    @XmlTransient
    public List<Miscaristocare> getMiscaristocareList() {
        return miscaristocareList;
    }

    public void setMiscaristocareList(List<Miscaristocare> miscaristocareList) {
        this.miscaristocareList = miscaristocareList;
    }

    @XmlTransient
    public List<Miscarieliminare> getMiscarieliminareList() {
        return miscarieliminareList;
    }

    public void setMiscarieliminareList(List<Miscarieliminare> miscarieliminareList) {
        this.miscarieliminareList = miscarieliminareList;
    }

    @XmlTransient
    public List<Cantvalorificata> getCantvalorificataList() {
        return cantvalorificataList;
    }

    public void setCantvalorificataList(List<Cantvalorificata> cantvalorificataList) {
        this.cantvalorificataList = cantvalorificataList;
    }

    @XmlTransient
    public List<Miscarivalorificare> getMiscarivalorificareList() {
        return miscarivalorificareList;
    }

    public void setMiscarivalorificareList(List<Miscarivalorificare> miscarivalorificareList) {
        this.miscarivalorificareList = miscarivalorificareList;
    }

    @XmlTransient
    public List<Cantcomercializata> getCantcomercializataList() {
        return cantcomercializataList;
    }

    public void setCantcomercializataList(List<Cantcomercializata> cantcomercializataList) {
        this.cantcomercializataList = cantcomercializataList;
    }

    @XmlTransient
    public List<Cantrezultata> getCantrezultataList() {
        return cantrezultataList;
    }

    public void setCantrezultataList(List<Cantrezultata> cantrezultataList) {
        this.cantrezultataList = cantrezultataList;
    }

    @XmlTransient
    public List<Miscarigenerare> getMiscarigenerareList() {
        return miscarigenerareList;
    }

    public void setMiscarigenerareList(List<Miscarigenerare> miscarigenerareList) {
        this.miscarigenerareList = miscarigenerareList;
    }

    public Luna getLunaidLuna() {
        return lunaidLuna;
    }

    public void setLunaidLuna(Luna lunaidLuna) {
        this.lunaidLuna = lunaidLuna;
    }

    public Deseuripunctlucru getDeseuriPunctLucruidDeseuri() {
        return deseuriPunctLucruidDeseuri;
    }

    public void setDeseuriPunctLucruidDeseuri(Deseuripunctlucru deseuriPunctLucruidDeseuri) {
        this.deseuriPunctLucruidDeseuri = deseuriPunctLucruidDeseuri;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMiscariLuna != null ? idMiscariLuna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Miscariluna)) {
            return false;
        }
        Miscariluna other = (Miscariluna) object;
        if ((this.idMiscariLuna == null && other.idMiscariLuna != null) || (this.idMiscariLuna != null && !this.idMiscariLuna.equals(other.idMiscariLuna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Miscariluna[ idMiscariLuna=" + idMiscariLuna + " ]";
    }

    @XmlTransient
    public List<Canteliminare> getCanteliminareList() {
        return canteliminareList;
    }

    public void setCanteliminareList(List<Canteliminare> canteliminareList) {
        this.canteliminareList = canteliminareList;
    }
    
}
