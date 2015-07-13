/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazaDanych;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Patryk
 */
@Entity
@Table(name = "ksiazki")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ksiazki.findAll", query = "SELECT k FROM Ksiazki k"),
    @NamedQuery(name = "Ksiazki.findById", query = "SELECT k FROM Ksiazki k WHERE k.id = :id"),
    @NamedQuery(name = "Ksiazki.findByAutor", query = "SELECT k FROM Ksiazki k WHERE k.autor = :autor"),
    @NamedQuery(name = "Ksiazki.findByCena", query = "SELECT k FROM Ksiazki k WHERE k.cena = :cena"),
    @NamedQuery(name = "Ksiazki.findByTytul", query = "SELECT k FROM Ksiazki k WHERE k.tytul = :tytul")})
public class Ksiazki implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "Autor", length = 255)
    private String autor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Cena", nullable = false, precision = 11, scale = 2)
    private BigDecimal cena;
    @Basic(optional = false)
    @Column(name = "Tytul", nullable = false, length = 255)
    private String tytul;
    

    public Ksiazki() {
    }

    public Ksiazki(Integer id) {
        this.id = id;
    }

    public Ksiazki(String autor, String nazwa, BigDecimal cena)
    {   
		this.autor = autor;
		this.tytul = nazwa;
		this.cena  = cena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ksiazki)) {
            return false;
        }
        Ksiazki other = (Ksiazki) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bazaDanych.Ksiazki[ id=" + id + " ]";
    }
    
}
