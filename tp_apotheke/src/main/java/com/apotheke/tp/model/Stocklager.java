package com.apotheke.tp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;



/**
 * @author cliff
 *
 */
@Entity
@Table(name = "stocklager")
public class Stocklager implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@EmbeddedId
	private StocklagerPK id;

	/**
	 * 
	 */
	@Column(name="QUANTITAET_VERFUEGBARE")
	@JsonProperty("quantitaet_verfuegbare")
	private int quantitaetVerfuegbar;

	//bi-directional many-to-one association to Lager
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name="ID_LAGER", insertable=false, updatable=false)
	private Lager lager;

	//bi-directional many-to-one association to Medikament
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name="ID_MEDIKAMENT", insertable=false, updatable=false)
	private Medikament medikament;
	
	
	/**
	 * 
	 */
	public Stocklager() {
		super();
	}
	
	/**
	 * @param id Parameter
	 * @param quantitaetVerfuegbar Parameter
	 */
	public Stocklager(StocklagerPK id, int quantitaetVerfuegbar) {
		super();
		this.id = id;
		this.quantitaetVerfuegbar = quantitaetVerfuegbar;
	}

	/*
	 * public Stocklager(StocklagerPK id, int quantitaetVerfuegbar, Lager lager,
	 * Medikament medikament) { super(); this.id = id; this.quantitaetVerfuegbar =
	 * quantitaetVerfuegbar; this.lager = lager; this.medikament = medikament; }
	 * 
	 * public Stocklager(int quantitaetVerfuegbar, Lager lager, Medikament
	 * medikament) { super(); this.quantitaetVerfuegbar = quantitaetVerfuegbar;
	 * this.lager = lager; this.medikament = medikament; }
	 */

	/**
	 * @return Ergebnisse
	 */
	public StocklagerPK getId() {
		return id;
	}

	/**
	 * @param id Parameter
	 */
	public void setId(StocklagerPK id) {
		this.id = id;
	}

	/**
	 * @return Ergebnisse
	 */
	public int getQuantitaetVerfuegbar() {
		return quantitaetVerfuegbar;
	}

	/**
	 * @param quantitaetVerfuegbar Parameter
	 */
	public void setQuantitaetVerfuegbar(int quantitaetVerfuegbar) {
		this.quantitaetVerfuegbar = quantitaetVerfuegbar;
	}

	/**
	 * @return Ergebnisse
	 */
	public Lager getLager() {
		return lager;
	}

	/**
	 * @param lager Parameter
	 */
	public void setLager(Lager lager) {
		this.lager = lager;
	}

	/**
	 * @return Ergebnisse
	 */
	public Medikament getMedikament() {
		return medikament;
	}

	/**
	 * @param medikament Parameter
	 */
	public void setMedikament(Medikament medikament) {
		this.medikament = medikament;
	}
}
