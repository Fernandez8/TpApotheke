package com.apotheke.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apotheke.tp.model.Stockapotheke;

/**
 * @author cliff
 *
 */
public interface StockapothekeRepository extends JpaRepository<Stockapotheke, Integer> {
	
	/**
	 * @param idApotheke Parameter
	 * @return Ergebnisse
	 */
	List<Stockapotheke> findByIdIdApotheke(Integer idApotheke);
	
	/**
	 * @param idMedikament Parameter
	 * @return Ergebnisse
	 */
	List<Stockapotheke> findByIdIdMedikament(Integer idMedikament);
	
	/**
	 * @param quantitaetVerfuegbareApotheke Parameter
	 * @return Ergebnisse
	 */
	List<Stockapotheke> findByQuantitaetVerfuegbareApotheke(Integer quantitaetVerfuegbareApotheke);
	
	/**
	 * @param idApotheke Parameter
	 * @param idMedikament Parameter
	 * @return Ergebnisse
	 */
	Stockapotheke findByIdIdApothekeAndIdIdMedikament(Integer idApotheke, Integer idMedikament);
	
	/**
	 * @param idApotheke Parameter
	 * @param idMedikament Parameter
	 */
	void deleteByIdIdApothekeAndIdIdMedikament(Integer idApotheke, Integer idMedikament);
}
