package com.apotheke.tp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author cliff
 *
 */
@Entity
@Table(name = "fabrikant")
public class Fabrikant implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@Column(name="ID_FABRIKANT")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id_fabrikant")
	private int idFabrikant;

	/**
	 * 
	 */
	@Column(name="NOM_FABRIKANT")
	@JsonProperty("nom_fabrikant")
	private String nomFabrikant;
	
	/**
	 * 
	 */
	public Fabrikant() {
		super();
	}

	/**
	 * @param idFabrikant Parameter
	 * @param nomFabrikant Parameter
	 */
	public Fabrikant(int idFabrikant, String nomFabrikant) {
		super();
		this.idFabrikant = idFabrikant;
		this.nomFabrikant = nomFabrikant;
	}

	/**
	 * @param nomFabrikant Parameter
	 */
	public Fabrikant(String nomFabrikant) {
		//super();
		this.nomFabrikant = nomFabrikant;
	}

	/**
	 * @return Ergbenisse
	 */
	public int getIdFabrikant() {
		return idFabrikant;
	}

	/**
	 * @param idFabrikant Parameter
	 */
	public void setIdFabrikant(int idFabrikant) {
		this.idFabrikant = idFabrikant;
	}

	/**
	 * @return Ergebnisse
	 */
	public String getNomFabrikant() {
		return nomFabrikant;
	}

	/**
	 * @param nomFabrikant Parameter
	 */
	public void setNomFabrikant(String nomFabrikant) {
		this.nomFabrikant = nomFabrikant;
	}
}
