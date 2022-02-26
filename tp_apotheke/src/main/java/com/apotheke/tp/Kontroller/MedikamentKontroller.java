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

import com.apotheke.tp.model.Medikament;
import com.apotheke.tp.repository.MedikamentRepository;


/**
 * Medikationsmanagement-Controller
 *
 */
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MedikamentKontroller {
	/*
	 * ************************* These are APIs that we need to provide *******************************
	 * 
	 *  Methods	  	Urls	           									Actions
		POST	  	/api/medikament										create new medikament
		GET	      	/api/medikament										retrieve all medikaments
		GET			/api/medikament/:id									retrieve a medikament by :id
		PUT			/api/medikament/:id									update a medikament by :id
		DELETE		/api/medikament/:id									delete a medikament by :id
		DELETE		/api/medikament										delete all medikaments
		GET			/api/medikament/{field_name}/{field_value}			find by any column name {field_name} in table name medikament with is value {field_value}
		GET			/api/medikament?id_medikament={value_id_medikament}	find all medikaments which id_medikament contains value_id_medikament
	 *	
     ***************************************************************************************************
	 */

	@Autowired
	MedikamentRepository medikamentRepository;

	/**
	 * poster alle drogen
	 * 
	 * @param id_medikament Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/medikament")
	public ResponseEntity<List<Medikament>> getAllMedikament(@RequestParam(required = false) Integer id_medikament) {
		try {
			List<Medikament> medikaments = new ArrayList<Medikament>();
			if (id_medikament == null)
				medikamentRepository.findAll().forEach(medikaments::add);
			else
				medikaments.add(medikamentRepository.findById(id_medikament).get());
			if (medikaments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(medikaments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * poster ein drogen
	 * 
	 * @param id Parameter Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/medikament/{id}")
	public ResponseEntity<Medikament> getMedikamentById(@PathVariable("id") Integer id) {
		Optional<Medikament> medikamentData = medikamentRepository.findById(id);
		if (medikamentData.isPresent()) {
			return new ResponseEntity<>(medikamentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * ein Medikament registrieren
	 * 
	 * @param medikament Parameter
	 * @return Ergebnisse
	 */
	@PostMapping("/medikament")
	public ResponseEntity<Medikament> createMedikament(@RequestBody Medikament medikament) {
		try {
			Medikament _medikament = medikamentRepository.save(new Medikament(medikament.getNomMedikament(), medikament.getSubstanceActive(), medikament.getFabrikant()));
			return new ResponseEntity<>(_medikament, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * ein Medikament wechseln
	 * 
	 * @param id Parameter
	 * @param medikament Parameter
	 * @return Ergebnisse
	 */
	@PutMapping("/medikament/{id}")
	public ResponseEntity<Medikament> updateMedikament(@PathVariable("id") Integer id, @RequestBody Medikament medikament) {
		Optional<Medikament> medikamentData = medikamentRepository.findById(id);
		if (medikamentData.isPresent()) {
			Medikament _medikament = medikamentData.get();
			_medikament.setNomMedikament(medikament.getNomMedikament());
			return new ResponseEntity<>(medikamentRepository.save(_medikament), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * ein Medikament entfernen
	 * 
	 * @param id Parameter
	 * @return Ergebnisse
	 */
	@DeleteMapping("/medikament/{id}")
	public ResponseEntity<HttpStatus> deleteMedikament(@PathVariable("id") Integer id) {
		try {
			medikamentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * alle Medikamente entfernen
	 * 
	 * @return Ergebnisse
	 */
	@DeleteMapping("/medikament")
	public ResponseEntity<HttpStatus> deleteAllMedikament() {
		try {
			medikamentRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Suche nach Medikamenten basierend auf einer Spalte
	 * 
	 * @param field_name Parameter
	 * @param field_value Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/medikament/{field_name}/{field_value}")
	public ResponseEntity<List<Medikament>> findByAnyParam(@PathVariable("field_name") String field_name,
			@PathVariable("field_value") String field_value) {
		try {
			List<Medikament> medikaments = new ArrayList<Medikament>();
			switch (field_name.toLowerCase()) {
			case "id_medikament":
				medikaments.add(medikamentRepository.findById(Integer.parseInt(field_value)).get());
				break;
			case "id_fabrikant":
				medikamentRepository.findByFabrikantIdFabrikant(Integer.parseInt(field_value)).forEach(medikaments::add);
				break;
			case "nom_medikament":
				medikamentRepository.findByNomMedikamentIgnoreCase(field_value).forEach(medikaments::add);
				break;
			case "substance_active":
				medikamentRepository.findBySubstanceActiveContaining(field_value).forEach(medikaments::add);
				break;
			default:
				// code for default case
			}
			if (medikaments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(medikaments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
