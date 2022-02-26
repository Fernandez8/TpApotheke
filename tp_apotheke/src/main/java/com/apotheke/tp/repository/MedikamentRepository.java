package com.apotheke.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apotheke.tp.model.Medikament;

/**
 * @author cliff
 *
 */
public interface MedikamentRepository extends JpaRepository<Medikament, Integer> {

	/**
	 * @param idMedikament Parameter
	 * @return Ergebnisse
	 */
	List<Medikament> findByIdMedikament(Integer idMedikament);
	
	/**
	 * @param idFabrikant Parameter
	 * @return Ergebnisse
	 */
	List<Medikament> findByFabrikantIdFabrikant(Integer idFabrikant);

	// Enabling ignoring case for an individual property
	/**
	 * @param nomMedikament Parameter
	 * @return Ergebnisse
	 */
	List<Medikament> findByNomMedikamentIgnoreCase(String nomMedikament);

	// find which names contain a value with Containing
	/**
	 * @param nomMedikament Parameter
	 * @return Ergebnisse
	 */
	List<Medikament> findByNomMedikamentContaining(String nomMedikament);

	// find which names contain a value with Containing
	/**
	 * @param substanceActive Parameter
	 * @return Ergebnisse
	 */
	List<Medikament> findBySubstanceActiveContaining(String substanceActive);

}
