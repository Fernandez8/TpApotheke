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
	@Column(name="QUANTITAET_VERFUEGBARE_APOTHEKE")
	@JsonProperty("quantitaet_verfuegbare_apotheke")
	private int quantitaetVerfuegbareApotheke;

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
	@JoinColumn(name="ID_APOTHEKE", insertable=false, updatable=false)
	private Apotheke apotheke;
	
	/**
	 * 
	 */
	public Stockapotheke() {
		super();
	}
	
	/**
	 * @param id Parameter
	 * @param quantitaetVerfuegbareApotheke Parameter
	 */
	public Stockapotheke(StockapothekePK id, int quantitaetVerfuegbareApotheke) {
		super();
		this.id = id;
		this.quantitaetVerfuegbareApotheke = quantitaetVerfuegbareApotheke;
	}

	/*
	 * public Stockapotheke(int quantitaetVerfuegbareApotheke, Medikament medikament,
	 * Apotheke apotheke) { super();
	 * this.quantitaetVerfuegbareApotheke = quantitaetVerfuegbareApotheke;
	 * this.medikament = medikament; this.apotheke = apotheke;
	 * }
	 * 
	 * public Stockapotheke(StockapothekePK id, int quantitaetVerfuegbareApotheke, Medikament
	 * medikament, Apotheke apotheke) { super(); this.id = id;
	 * this.quantitaetVerfuegbareApotheke = quantitaetVerfuegbareApotheke;
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
	public int getQuantitaetVerfuegbareApotheke() {
		return quantitaetVerfuegbareApotheke;
	}

	/**
	 * @param quantitaetVerfuegbareApotheke Parameter
	 */
	public void setQuantitaetVerfuegbareApotheke(int quantitaetVerfuegbareApotheke) {
		this.quantitaetVerfuegbareApotheke = quantitaetVerfuegbareApotheke;
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
