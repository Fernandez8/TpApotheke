package com.apotheke.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apotheke.tp.model.Apotheke;

/**
 * @author cliff
 *
 */
public interface ApothekeRepository extends JpaRepository<Apotheke, Integer> {
	
	/**
	 * @param idMedikament Parameter
	 * @return Ergebnisse
	 */
	List<Apotheke> findByIdApotheke(Integer idMedikament);

	// Enabling ignoring case for an individual property
	/**
	 * @param nomApotheke Parameter
	 * @return Ergebnisse
	 */
	List<Apotheke> findByNomApothekeIgnoreCase(String nomApotheke);

	//find which names contain a value with Containing
	/**
	 * @param nomApotheke Parameter
	 * @return Ergebnisse
	 */
	List<Apotheke> findByNomApothekeContaining(String nomApotheke);
}
