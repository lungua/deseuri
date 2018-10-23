/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ent;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lungu
 */
@Entity
@Table(name = "vsu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vsu.findAll", query = "SELECT v FROM Vsu v"),
    @NamedQuery(name = "Vsu.findByIdVSU", query = "SELECT v FROM Vsu v WHERE v.idVSU = :idVSU"),
    @NamedQuery(name = "Vsu.findByVSUColectate", query = "SELECT v FROM Vsu v WHERE v.vSUColectate = :vSUColectate"),
    @NamedQuery(name = "Vsu.findByVSURabla", query = "SELECT v FROM Vsu v WHERE v.vSURabla = :vSURabla"),
    @NamedQuery(name = "Vsu.findByVSUNonRabla", query = "SELECT v FROM Vsu v WHERE v.vSUNonRabla = :vSUNonRabla")})
public class Vsu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVSU")
    private Integer idVSU;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VSUColectate")
    private Double vSUColectate;
    @Column(name = "VSURabla")
    private Double vSURabla;
    @Column(name = "VSUNonRabla")
    private Double vSUNonRabla;
    @JoinColumn(name = "Luna_idLuna", referencedColumnName = "idLuna")
    @ManyToOne(optional = false)
    private Luna lunaidLuna;
    @JoinColumn(name = "PunctLucru_idPunctLucru", referencedColumnName = "idPunctLucru")
    @ManyToOne(optional = false)
    private Punctlucru punctLucruidPunctLucru;

    public Vsu() {
    }

    public Vsu(Integer idVSU) {
        this.idVSU = idVSU;
    }

    public Integer getIdVSU() {
        return idVSU;
    }

    public void setIdVSU(Integer idVSU) {
        this.idVSU = idVSU;
    }

    public Double getVSUColectate() {
        return vSUColectate;
    }

    public void setVSUColectate(Double vSUColectate) {
        this.vSUColectate = vSUColectate;
    }

    public Double getVSURabla() {
        return vSURabla;
    }

    public void setVSURabla(Double vSURabla) {
        this.vSURabla = vSURabla;
    }

    public Double getVSUNonRabla() {
        return vSUNonRabla;
    }

    public void setVSUNonRabla(Double vSUNonRabla) {
        this.vSUNonRabla = vSUNonRabla;
    }

    public Luna getLunaidLuna() {
        return lunaidLuna;
    }

    public void setLunaidLuna(Luna lunaidLuna) {
        this.lunaidLuna = lunaidLuna;
    }

    public Punctlucru getPunctLucruidPunctLucru() {
        return punctLucruidPunctLucru;
    }

    public void setPunctLucruidPunctLucru(Punctlucru punctLucruidPunctLucru) {
        this.punctLucruidPunctLucru = punctLucruidPunctLucru;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVSU != null ? idVSU.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vsu)) {
            return false;
        }
        Vsu other = (Vsu) object;
        if ((this.idVSU == null && other.idVSU != null) || (this.idVSU != null && !this.idVSU.equals(other.idVSU))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Vsu[ idVSU=" + idVSU + " ]";
    }
    
}
