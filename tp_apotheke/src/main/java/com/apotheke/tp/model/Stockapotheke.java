package com.apotheke.tp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.EmbeddedId;

/**
 * @author cliff
 *
 */
@Entity
@Table(name = "stockapotheke")
public class Stockapotheke implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@EmbeddedId
	private StockapothekePK id;

	/**
	 * 
	 */
	@Column(name="QUANTITE_DISPONIBLE_PHARMACIE")
	@JsonProperty("quantite_disponible_pharmacie")
	private int quantiteDisponibleApotheke;

	//bi-directional many-to-one association to Medikament
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name="ID_MEDIKAMENT", insertable=false, updatable=false)
	private Medikament medikament;

	//bi-directional many-to-one association to Apotheke
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name="ID_PHARMACIE", insertable=false, updatable=false)
	private Apotheke apotheke;
	
	/**
	 * 
	 */
	public Stockapotheke() {
		super();
	}
	
	/**
	 * @param id Parameter
	 * @param quantiteDisponibleApotheke Parameter
	 */
	public Stockapotheke(StockapothekePK id, int quantiteDisponibleApotheke) {
		super();
		this.id = id;
		this.quantiteDisponibleApotheke = quantiteDisponibleApotheke;
	}

	/*
	 * public Stockapotheke(int quantiteDisponibleApotheke, Medikament medikament,
	 * Apotheke apotheke) { super();
	 * this.quantiteDisponibleApotheke = quantiteDisponibleApotheke;
	 * this.medikament = medikament; this.apotheke = apotheke;
	 * }
	 * 
	 * public Stockapotheke(StockapothekePK id, int quantiteDisponibleApotheke, Medikament
	 * medikament, Apotheke apotheke) { super(); this.id = id;
	 * this.quantiteDisponibleApotheke = quantiteDisponibleApotheke;
	 * this.medikament = medikament; this.apotheke = apotheke;
	 * }
	 */
	
	/**
	 * @return Ergebnisse
	 */
	public StockapothekePK getId() {
		return id;
	}

	/**
	 * @param id Parameter
	 */
	public void setId(StockapothekePK id) {
		this.id = id;
	}

	/**
	 * @return Ergebnisse
	 */
	public int getQuantiteDisponibleApotheke() {
		return quantiteDisponibleApotheke;
	}

	/**
	 * @param quantiteDisponibleApotheke Parameter
	 */
	public void setQuantiteDisponibleApotheke(int quantiteDisponibleApotheke) {
		this.quantiteDisponibleApotheke = quantiteDisponibleApotheke;
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

	/**
	 * @return Ergebnisse
	 */
	public Apotheke getApotheke() {
		return apotheke;
	}

	/**
	 * @param apotheke Parameter
	 */
	public void setApotheke(Apotheke apotheke) {
		this.apotheke = apotheke;
	}
}
