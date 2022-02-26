package com.apotheke.tp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author cliff
 *
 */
@Embeddable
public class StocklagerPK implements Serializable {
	//default serial version id, required for serializable classes.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	@Column(name="ID_LAGER", insertable=false, updatable=false)
	@JsonProperty("id_lager")
	private int idLager;

	/**
	 * 
	 */
	@Column(name="ID_MEDIKAMENT", insertable=false, updatable=false)
	@JsonProperty("id_medikament")
	private int idMedikament;

	/**
	 * 
	 */
	public StocklagerPK() {
	}
	/**
	 * @return Ergebnisse
	 */
	public int getIdLager() {
		return this.idLager;
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
	public int getIdMedikament() {
		return this.idMedikament;
	}
	/**
	 * @param idMedikament Parameter
	 */
	public void setIdMedikament(int idMedikament) {
		this.idMedikament = idMedikament;
	}

	/**
	 *
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StocklagerPK)) {
			return false;
		}
		StocklagerPK castOther = (StocklagerPK)other;
		return 
			(this.idLager == castOther.idLager)
			&& (this.idMedikament == castOther.idMedikament);
	}

	/**
	 *
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idLager;
		hash = hash * prime + this.idMedikament;
		
		return hash;
	}

}
