package com.apotheke.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apotheke.tp.model.Fabrikant;

/**
 * @author cliff
 *
 */
public interface FabrikantRepository extends JpaRepository<Fabrikant, Integer> {

	/**
	 * @param idFabrikant Parameter
	 * @return Ergebnisse
	 */
	List<Fabrikant> findByIdFabrikant(Integer idFabrikant);

	// Enabling ignoring case for an individual property
	/**
	 * @param nomFabrikant Parameter
	 * @return Ergebnisse
	 */
	List<Fabrikant> findByNomFabrikantIgnoreCase(String nomFabrikant);

	//find which names contain a value with Containing
	/**
	 * @param nomFabrikant Parameter
	 * @return Ergebnisse
	 */
	List<Fabrikant> findByNomFabrikantContaining(String nomFabrikant);
}
