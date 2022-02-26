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
public class StockapothekePK implements Serializable {
	//default serial version id, required for serializable classes.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	@Column(name="ID_PHARMACIE", insertable=false, updatable=false)
	@JsonProperty("id_pharmacie")
	private int idApotheke;

	/**
	 * 
	 */
	@Column(name="ID_MEDIKAMENT", insertable=false, updatable=false)
	@JsonProperty("id_medikament")
	private int idMedikament;

	/**
	 * 
	 */
	public StockapothekePK() {
	}
	/**
	 * @return Ergebnisse
	 */
	public int getIdApotheke() {
		return this.idApotheke;
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
		if (!(other instanceof StockapothekePK)) {
			return false;
		}
		StockapothekePK castOther = (StockapothekePK)other;
		return 
			(this.idApotheke == castOther.idApotheke)
			&& (this.idMedikament == castOther.idMedikament);
	}

	/**
	 *
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idApotheke;
		hash = hash * prime + this.idMedikament;
		
		return hash;
	}

}
