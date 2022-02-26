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

import com.apotheke.tp.model.Lager;
import com.apotheke.tp.repository.LagerRepository;


/**
 * Controller, der die Lager verwaltet
 *
 */
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LagerKontroller {
	/*
	 * ************************* These are APIs that we need to provide *******************************
	 * 
	 *  Methods	  	Urls	           									Actions
		POST	  	/api/lager										create new lager
		GET	      	/api/lager										retrieve all lagers
		GET			/api/lager/:id									retrieve a lager by :id
		PUT			/api/lager/:id									update a lager by :id
		DELETE		/api/lager/:id									delete a lager by :id
		DELETE		/api/lager										delete all lagers
		GET			/api/lager/{field_name}/{field_value}			find by any column name {field_name} in table name lager with is value {field_value}
		GET			/api/lager?id_lager={value_id_lager}	find all lagers which id_lager contains value_id_lager
	 *	
     ***************************************************************************************************
	 */

	@Autowired
	LagerRepository lagerRepository;

	/**
	 * @param id_lager Parameter
	 * @return Gibt die Liste alles Lagers zurück
	 */
	@GetMapping("/lager")
	public ResponseEntity<List<Lager>> getAllLager(@RequestParam(required = false) Integer id_lager) {
		try {
			List<Lager> lagers = new ArrayList<Lager>();
			if (id_lager == null)
				lagerRepository.findAll().forEach(lagers::add);
			else
				lagers.add(lagerRepository.findById(id_lager).get());
			if (lagers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(lagers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @param id Parameter
	 * @return Geben Sie ein Lager zurück
	 */
	@GetMapping("/lager/{id}")
	public ResponseEntity<Lager> getLagerById(@PathVariable("id") Integer id) {
		Optional<Lager> lagerData = lagerRepository.findById(id);
		if (lagerData.isPresent()) {
			return new ResponseEntity<>(lagerData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Registrieren Sie ein Lager
	 * 
	 * @param lager Parameter
	 * @return Ergrbnisse
	 */
	@PostMapping("/lager")
	public ResponseEntity<Lager> createLager(@RequestBody Lager lager) {
		try {
			Lager _lager = lagerRepository.save(new Lager(lager.getNomLager()));
			return new ResponseEntity<>(_lager, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Ändern Sie ein Lager
	 * 
	 * @param id Parameter
	 * @param lager Parameter
	 * @return Ergebnisse
	 */
	@PutMapping("/lager/{id}")
	public ResponseEntity<Lager> updateLager(@PathVariable("id") Integer id, @RequestBody Lager lager) {
		Optional<Lager> lagerData = lagerRepository.findById(id);
		if (lagerData.isPresent()) {
			Lager _lager = lagerData.get();
			_lager.setNomLager(lager.getNomLager());
			return new ResponseEntity<>(lagerRepository.save(_lager), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Lager löschen
	 * 
	 * @param id Parameter
	 * @return Ergebnisse
	 */
	@DeleteMapping("/lager/{id}")
	public ResponseEntity<HttpStatus> deleteLager(@PathVariable("id") Integer id) {
		try {
			lagerRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * alle Depots löschen
	 * 
	 * @return Ergebnisse
	 */
	@DeleteMapping("/lager")
	public ResponseEntity<HttpStatus> deleteAllLager() {
		try {
			lagerRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Suche nach Lagern basierend auf einer Spalte
	 * 
	 * @param field_name Parameter
	 * @param field_value Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/lager/{field_name}/{field_value}")
	public ResponseEntity<List<Lager>> findByAnyParam(@PathVariable("field_name") String field_name,
			@PathVariable("field_value") String field_value) {
		try {
			List<Lager> lagers = new ArrayList<Lager>();
			switch (field_name.toLowerCase()) {
			case "id_lager":
				lagers.add(lagerRepository.findById(Integer.parseInt(field_value)).get());
				break;
			case "nom_lager":
				lagerRepository.findByNomLagerIgnoreCase(field_value).forEach(lagers::add);
				break;
			default:
				// code for default case
			}
			if (lagers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(lagers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
