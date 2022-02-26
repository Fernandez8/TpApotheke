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
	@Column(name="QUANTITE_DISPONIBLE")
	@JsonProperty("quantite_disponible")
	private int quantiteDisponible;

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
	 * @param quantiteDisponible Parameter
	 */
	public Stocklager(StocklagerPK id, int quantiteDisponible) {
		super();
		this.id = id;
		this.quantiteDisponible = quantiteDisponible;
	}

	/*
	 * public Stocklager(StocklagerPK id, int quantiteDisponible, Lager lager,
	 * Medikament medikament) { super(); this.id = id; this.quantiteDisponible =
	 * quantiteDisponible; this.lager = lager; this.medikament = medikament; }
	 * 
	 * public Stocklager(int quantiteDisponible, Lager lager, Medikament
	 * medikament) { super(); this.quantiteDisponible = quantiteDisponible;
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
	public int getQuantiteDisponible() {
		return quantiteDisponible;
	}

	/**
	 * @param quantiteDisponible Parameter
	 */
	public void setQuantiteDisponible(int quantiteDisponible) {
		this.quantiteDisponible = quantiteDisponible;
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
