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
import javax.persistence.OneToMany;

/**
 * @author cliff
 *
 */
@Entity
@Table(name="apotheke")
public class Apotheke implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@Column(name="ID_PHARMACIE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id_pharmacie")
	private int idApotheke;

	/**
	 * 
	 */
	@Column(name="NOM_PHARMACIE")
	@JsonProperty("nom_pharmacie")
	private String nomApotheke;

	//bi-directional many-to-one association to Stockapotheke
	/**
	 * 
	 */
	@OneToMany(mappedBy="apotheke")
	private List<Stockapotheke> stockapothekes;
	
	
	/**
	 * 
	 */
	public Apotheke() {
		super();
	}

	/**
	 * @param idApotheke Parameter
	 * @param nomApotheke Parameter
	 * @param stockapothekes Parameter
	 */
	public Apotheke(int idApotheke, String nomApotheke, List<Stockapotheke> stockapothekes) {
		super();
		this.idApotheke = idApotheke;
		this.nomApotheke = nomApotheke;
		this.stockapothekes = stockapothekes;
	}

	/**
	 * @param nomApotheke Parameter
	 */
	public Apotheke(String nomApotheke) {
		super();
		this.nomApotheke = nomApotheke;
	}

	/**
	 * @return Ergebnisse
	 */
	public int getIdApotheke() {
		return idApotheke;
	}

	/**
	 * @param idApotheke Parameter
	 */
	public void setIdApotheke(int idApotheke) {
		this.idApotheke = idApotheke;
	}

	/**
	 * @return Ergebnisse
	 */
	public String getNomApotheke() {
		return nomApotheke;
	}

	/**
	 * @param nomApotheke Parameter
	 */
	public void setNomApotheke(String nomApotheke) {
		this.nomApotheke = nomApotheke;
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
}
