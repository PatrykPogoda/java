package klient;
import bazaDanych.Ksiazki;
import java.io.Serializable;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "Klient")
public class Klient implements Serializable{


	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Imie", nullable = false)
	private String imie;
	@Column(name = "Nazwisko", nullable = false)
	private String nazwisko;
	@Column(name = "Stan", nullable = false, precision = 10, scale = 2)
	private BigDecimal stan;

	public Klient(String imie, String nazwisko, BigDecimal stan)
	{
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.stan  = stan;
	}	
	
	
	
	/*
	 * setery pozostawione mimo konstruktora na wypadek 
	 * konieczności dokonywania zmian np. stanie konta 
	 * (co ma miejsce przy zakupie ksiazki)
	 */
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public BigDecimal getStan() {
		return stan;
	}
	public void setStan(BigDecimal stan) {
		this.stan = stan;
	}

	
	public boolean kupProdukt(Ksiazki ksiazka) throws SQLException
	{

		double kasa = this.stan.doubleValue();
		BigDecimal cena = ksiazka.getCena();
		double koszt = cena.doubleValue();
		if(kasa >= koszt)
		{
			kasa = kasa - koszt;
			this.stan = new BigDecimal(kasa);
			return true;
		}
		return false;
		
	}
}