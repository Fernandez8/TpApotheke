package com.apotheke.tp.Kontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apotheke.tp.model.Fabrikant;
import com.apotheke.tp.repository.FabrikantRepository;


/**
 * Controller der Geschäftsführung von Arzneimittelherstellern
 *
 */
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FabrikantKontroller {
	
	/*
	 * ************************* These are APIs that we need to provide *******************************
	 * 
	 *  Methods	  	Urls	           									Actions
		POST	  	/api/fabrikant										create new fabrikant
		GET	      	/api/fabrikant										retrieve all fabrikants
		GET			/api/fabrikant/:id									retrieve a fabrikant by :id
		PUT			/api/fabrikant/:id									update a fabrikant by :id
		DELETE		/api/fabrikant/:id									delete a fabrikant by :id
		DELETE		/api/fabrikant										delete all fabrikants
		GET			/api/fabrikant/{field_name}/{field_value}			find by any column name {field_name} in table name fabrikant with is value {field_value}
		GET			/api/fabrikant?id_fabrikant={value_id_fabrikant}	find all fabrikants which id_fabrikant contains value_id_fabrikant
	 *	
     ***************************************************************************************************
	 */

	@Autowired
	FabrikantRepository fabrikantRepository;

	/**
	 * alle Arzneimittelhersteller anzeigen
	 * 
	 * @param id_fabrikant Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/fabrikant")
	public ResponseEntity<List<Fabrikant>> getAllFabrikant(@RequestParam(required = false) Integer id_fabrikant) {
		try {
			List<Fabrikant> fabrikants = new ArrayList<Fabrikant>();
			if (id_fabrikant == null)
				fabrikantRepository.findAll().forEach(fabrikants::add);
			else
				fabrikants.add(fabrikantRepository.findById(id_fabrikant).get());
			if (fabrikants.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(fabrikants, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Poster-Drogenhersteller
	 * 
	 * @param id Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/fabrikant/{id}")
	public ResponseEntity<Fabrikant> getFabrikantById(@PathVariable("id") Integer id) {
		Optional<Fabrikant> fabrikantData = fabrikantRepository.findById(id);
		if (fabrikantData.isPresent()) {
			return new ResponseEntity<>(fabrikantData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Registrierung von Arzneimittelherstellern
	 * 
	 * @param fabrikant Parameter
	 * @return Ergebnisse
	 */
	@PostMapping("/fabrikant")
	public ResponseEntity<Fabrikant> createFabrikant(@RequestBody Fabrikant fabrikant) {
		try {
			Fabrikant _fabrikant = fabrikantRepository.save(new Fabrikant(fabrikant.getNomFabrikant()));
			return new ResponseEntity<>(_fabrikant, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * einen Arzneimittelhersteller modifizieren
	 * 
	 * @param id Parameter
	 * @param fabrikant Parameter
	 * @return Ergebnisse
	 */
	@PutMapping("/fabrikant/{id}")
	public ResponseEntity<Fabrikant> updateFabrikant(@PathVariable("id") Integer id, @RequestBody Fabrikant fabrikant) {
		Optional<Fabrikant> fabrikantData = fabrikantRepository.findById(id);
		if (fabrikantData.isPresent()) {
			Fabrikant _fabrikant = fabrikantData.get();
			_fabrikant.setNomFabrikant(fabrikant.getNomFabrikant());
			return new ResponseEntity<>(fabrikantRepository.save(_fabrikant), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * entfernt einen Arzneimittelhersteller
	 * 
	 * @param id Parameter
	 * @return Ergebnisse
	 */
	@DeleteMapping("/fabrikant/{id}")
	public ResponseEntity<HttpStatus> deleteFabrikant(@PathVariable("id") Integer id) {
		try {
			fabrikantRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Entfernen Sie alle Arzneimittelhersteller
	 * 
	 * @return Ergebnisse
	 */
	@DeleteMapping("/fabrikant")
	public ResponseEntity<HttpStatus> deleteAllFabrikant() {
		try {
			fabrikantRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Suche nach Arzneimittelherstellern basierend auf einer Spalte
	 * 
	 * @param field_name Parameter
	 * @param field_value Parameter
	 * @return Ergbnisse
	 */
	@GetMapping("/fabrikant/{field_name}/{field_value}")
	public ResponseEntity<List<Fabrikant>> findByAnyParam(@PathVariable("field_name") String field_name,
			@PathVariable("field_value") String field_value) {
		try {
			List<Fabrikant> fabrikants = new ArrayList<Fabrikant>();
			switch (field_name.toLowerCase()) {
			case "id_fabrikant":
				fabrikants.add(fabrikantRepository.findById(Integer.parseInt(field_value)).get());
				break;
			case "nom_fabrikant":
				fabrikantRepository.findByNomFabrikantIgnoreCase(field_value).forEach(fabrikants::add);
				break;
			default:
				// code for default case
			}
			if (fabrikants.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(fabrikants, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
