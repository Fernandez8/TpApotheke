package com.apotheke.tp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * @author cliff
 *
 */
@Entity
@Table(name = "lager")
public class Lager implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@Column(name = "ID_LAGER")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id_lager")
	private int idLager;

	/**
	 * 
	 */
	@Column(name = "NOM_LAGER")
	@JsonProperty("nom_lager")
	private String nomLager;
	

	/**
	 * 
	 */
	public Lager() {
		super();
	}

	/**
	 * @param idLager Parameter
	 * @param nomLager Parameter
	 */
	public Lager(int idLager, String nomLager) {
		super();
		this.idLager = idLager;
		this.nomLager = nomLager;
	}

	/**
	 * @param nomLager Parameter
	 */
	public Lager(String nomLager) {
		super();
		this.nomLager = nomLager;
	}

	/**
	 * @return Ergebnisse
	 */
	public int getIdLager() {
		return idLager;
	}

	/**
	 * @param idLager Parameter
	 */
	public void setIdLager(int idLager) {
		this.idLager = idLager;
	}

	/**
	 * @return Ergebnisse
	 */
	public String getNomLager() {
		return nomLager;
	}

	/**
	 * @param nomLager Parameter
	 */
	public void setNomLager(String nomLager) {
		this.nomLager = nomLager;
	}
}
