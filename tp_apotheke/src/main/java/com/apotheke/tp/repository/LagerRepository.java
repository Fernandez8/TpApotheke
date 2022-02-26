package com.apotheke.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apotheke.tp.model.Lager;

/**
 * @author cliff
 *
 */
public interface LagerRepository extends JpaRepository<Lager, Integer> {
	
	/**
	 * @param idLager Parameter
	 * @return Ergebnisse
	 */
	List<Lager> findByIdLager(Integer idLager);

	// Enabling ignoring case for an individual property
	/**
	 * @param nomLager Parameter
	 * @return Ergebnisse
	 */
	List<Lager> findByNomLagerIgnoreCase(String nomLager);

	//find which names contain a value with Containing
	/**
	 * @param nomLager Parameter
	 * @return Ergebnisse
	 */
	List<Lager> findByNomLagerContaining(String nomLager);
}
