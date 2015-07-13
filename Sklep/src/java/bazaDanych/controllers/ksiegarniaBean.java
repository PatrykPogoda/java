/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazaDanych.controllers;


import bazaDanych.Ksiazki;
import klient.Klient;
import bazaDanych.menagerBD;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

/**
 *
 * @author Patryk
 */
public class ksiegarniaBean {
    
    private Ksiazki ksiazka = new Ksiazki();
    private Klient kl = new Klient("Jan", "Kowalski", BigDecimal.ZERO);
    
    public ksiegarniaBean (){}
    
    public Ksiazki getKsiazka(){
        return ksiazka;
    }
    
    public void setKsiazka(Ksiazki ksiazka){
        this.ksiazka = ksiazka;
    }
    
    public String dodaj(){
        EntityManager em = menagerBD.getMenager().creaEntityManager();
        em.getTransaction().begin();
        ksiazka.setId(null);
        em.persist(ksiazka);
        em.getTransaction().commit();
        this.ksiazka = new Ksiazki();
        return null;
    }
    
    public List<Ksiazki> getLista(){
        EntityManager em = menagerBD.getMenager().creaEntityManager();
        List list = em.createNamedQuery("Ksiazki.findAll").getResultList();
        em.close();
        return list;
    }
    
    public void ksiazkaListener(ActionEvent ae){
        String ids = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("ksiazkaID");
        int id = Integer.parseInt(ids);
        this.ksiazka.setId(id);
    }
    
    public String zaladujDoEdycji(){
        EntityManager em = menagerBD.getMenager().creaEntityManager();
        this.ksiazka = em.find(Ksiazki.class, ksiazka.getId());
        em.close();
        return "/edytujKsiazke.xhtml";
    }
    
    public String kup() throws SQLException{
        EntityManager em = menagerBD.getMenager().creaEntityManager();
        em.getTransaction().begin();
        if(kl.kupProdukt(ksiazka))
        {
        this.ksiazka = em.find(Ksiazki.class, ksiazka.getId());
        em.remove(this.ksiazka);
        this.ksiazka = new Ksiazki();
        em.getTransaction().commit();
        em.close();
        this.dodajInformacje("Kupiono ksiazke");
        return null;
        }
        return null;
    }
    
    public String edytuj(){
        EntityManager em = menagerBD.getMenager().creaEntityManager();
        em.getTransaction().begin();
        em.merge(ksiazka);
        em.getTransaction().commit();
        em.close();
        this.dodajInformacje("Zmieniono tytul!");
        this.ksiazka = new Ksiazki();
        return "/wyswietlKsiazki.xhtml";
    }

    private void dodajInformacje(String s) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, s, ""));
    }
}
