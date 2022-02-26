package com.apotheke.tp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

/**
 * @author cliff
 *
 */
@Entity
@Table(name = "medikament")
public class Medikament implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@Column(name="ID_MEDIKAMENT")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id_medikament")
	private int idMedikament;

	/**
	 * 
	 */
	@Column(name="NOM_MEDIKAMENT")
	@JsonProperty("nom_medikament")
	private String nomMedikament;

	/**
	 * 
	 */
	@Column(name="SUBSTANCE_ACTIVE")
	@JsonProperty("substance_active")
	private String substanceActive;

	//bi-directional many-to-one association to Stockapotheke
	/**
	 * 
	 */
	@OneToMany(mappedBy="medikament")
	private List<Stockapotheke> stockapothekes;

	//bi-directional many-to-one association to Fabrikant
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name="ID_FABRIKANT")
	private Fabrikant fabrikant;

	//bi-directional many-to-one association to Stocklager
	/**
	 * 
	 */
	@OneToMany(mappedBy="medikament")
	private List<Stocklager> stocklagers;
	
	/**
	 * 
	 */
	public Medikament() {
		super();
	}

	/**
	 * @param idMedikament Parameter
	 * @param nomMedikament Parameter
	 * @param substanceActive Parameter
	 * @param stockapothekes Parameter
	 * @param fabrikant Parameter
	 * @param stocklagers Parameter
	 */
	public Medikament(int idMedikament, String nomMedikament, String substanceActive, List<Stockapotheke> stockapothekes,
			Fabrikant fabrikant, List<Stocklager> stocklagers) {
		super();
		this.idMedikament = idMedikament;
		this.nomMedikament = nomMedikament;
		this.substanceActive = substanceActive;
		this.stockapothekes = stockapothekes;
		this.fabrikant = fabrikant;
		this.stocklagers = stocklagers;
	}

	/**
	 * @param nomMedikament Parameter
	 * @param substanceActive Parameter
	 * @param fabrikant Parameter
	 */
	public Medikament(String nomMedikament, String substanceActive, Fabrikant fabrikant) {
		super();
		this.nomMedikament = nomMedikament;
		this.substanceActive = substanceActive;
		this.fabrikant = fabrikant;
	}

	/**
	 * @return Ergebnisse
	 */
	public int getIdMedikament() {
		return idMedikament;
	}

	/**
	 * @param idMedikament Parameter
	 */
	public void setIdMedikament(int idMedikament) {
		this.idMedikament = idMedikament;
	}

	/**
	 * @return Ergebnisse
	 */
	public String getNomMedikament() {
		return nomMedikament;
	}

	/**
	 * @param nomMedikament Parameter
	 */
	public void setNomMedikament(String nomMedikament) {
		this.nomMedikament = nomMedikament;
	}

	/**
	 * @return Ergebnisse
	 */
	public String getSubstanceActive() {
		return substanceActive;
	}

	/**
	 * @param substanceActive Parameter
	 */
	public void setSubstanceActive(String substanceActive) {
		this.substanceActive = substanceActive;
	}

	/**
	 * @return Ergebnisse
	 */
	public List<Stockapotheke> getStockapothekes() {
		return stockapothekes;
	}

	/**
	 * @param stockapothekes Parameter
	 */
	public void setStockapothekes(List<Stockapotheke> stockapothekes) {
		this.stockapothekes = stockapothekes;
	}

	/**
	 * @return Parameter
	 */
	public Fabrikant getFabrikant() {
		return fabrikant;
	}

	/**
	 * @param fabrikant Parameter
	 */
	public void setFabrikant(Fabrikant fabrikant) {
		this.fabrikant = fabrikant;
	}

	/**
	 * @return Ergebnisse
	 */
	public List<Stocklager> getStocklagers() {
		return stocklagers;
	}

	/**
	 * @param stocklagers Parameter
	 */
	public void setStocklagers(List<Stocklager> stocklagers) {
		this.stocklagers = stocklagers;
	}
}
