package com.apotheke.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apotheke.tp.model.Stocklager;

/**
 * @author cliff
 *
 */
public interface StocklagerRepository extends JpaRepository<Stocklager, Integer> {
	
	/**
	 * @param quantiteDisponible Parameter
	 * @return Ergebnisse
	 */
	List<Stocklager> findByQuantiteDisponible(Integer quantiteDisponible);

	/**
	 * @param idMedikament Parameter
	 * @return Ergebnisse
	 */
	List<Stocklager> findByIdIdMedikament(Integer idMedikament);
	
	/**
	 * @param idLager Parameter
	 * @return Ergebnisse
	 */
	List<Stocklager> findByIdIdLager(Integer idLager);
	
	/**
	 * @param idLager Parameter
	 * @param idMedikament Parameter
	 * @return Ergebnisse
	 */
	Stocklager findByIdIdLagerAndIdIdMedikament(Integer idLager, Integer idMedikament);
	
	/**
	 * @param idLager Parameter
	 * @param idMedikament Parameter
	 */
	void deleteByIdIdLagerAndIdIdMedikament(Integer idLager, Integer idMedikament);

}
