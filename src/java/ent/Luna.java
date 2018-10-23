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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lungu
 */
@Entity
@Table(name = "luna")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Luna.findAll", query = "SELECT l FROM Luna l"),
    @NamedQuery(name = "Luna.findByIdLuna", query = "SELECT l FROM Luna l WHERE l.idLuna = :idLuna"),
    @NamedQuery(name = "Luna.findByLuna", query = "SELECT l FROM Luna l WHERE l.luna = :luna")})
public class Luna implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLuna")
    private Integer idLuna;
    @Column(name = "luna")
    private Integer luna;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lunaidLuna")
    private List<Vsu> vsuList;
    @JoinColumn(name = "PunctLucru_idPunctLucru", referencedColumnName = "idPunctLucru")
    @ManyToOne(optional = false)
    private Punctlucru punctLucruidPunctLucru;
    @JoinColumn(name = "Anul_idAnul", referencedColumnName = "idAnul")
    @ManyToOne(optional = false)
    private Anul anulidAnul;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lunaidLuna")
    private List<Miscariluna> miscarilunaList;

    public Luna() {
    }

    public Luna(Integer idLuna) {
        this.idLuna = idLuna;
    }

    public Integer getIdLuna() {
        return idLuna;
    }

    public void setIdLuna(Integer idLuna) {
        this.idLuna = idLuna;
    }

    public Integer getLuna() {
        return luna;
    }

    public void setLuna(Integer luna) {
        this.luna = luna;
    }

    @XmlTransient
    public List<Vsu> getVsuList() {
        return vsuList;
    }

    public void setVsuList(List<Vsu> vsuList) {
        this.vsuList = vsuList;
    }

    public Punctlucru getPunctLucruidPunctLucru() {
        return punctLucruidPunctLucru;
    }

    public void setPunctLucruidPunctLucru(Punctlucru punctLucruidPunctLucru) {
        this.punctLucruidPunctLucru = punctLucruidPunctLucru;
    }

    public Anul getAnulidAnul() {
        return anulidAnul;
    }

    public void setAnulidAnul(Anul anulidAnul) {
        this.anulidAnul = anulidAnul;
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
        hash += (idLuna != null ? idLuna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Luna)) {
            return false;
        }
        Luna other = (Luna) object;
        if ((this.idLuna == null && other.idLuna != null) || (this.idLuna != null && !this.idLuna.equals(other.idLuna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ent.Luna[ idLuna=" + idLuna + " ]";
    }
    
}
