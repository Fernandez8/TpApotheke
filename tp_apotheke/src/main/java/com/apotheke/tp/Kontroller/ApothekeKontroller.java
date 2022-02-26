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

import com.apotheke.tp.model.Apotheke;
import com.apotheke.tp.repository.ApothekeRepository;

//@CrossOrigin(origins = "http://localhost:8081")

/**
 * Kontrolle der Apothekenverwaltung
 * @author cliff
 *
 */
@RestController
@RequestMapping("/api")
public class ApothekeKontroller {
/******** These are APIs that we need to provide *******************************
	 * 
	 *  Methods	  	Urls	           									Actions
		POST	  	/api/apotheke										create new apotheke
		GET	      	/api/apotheke										retrieve all apothekes
		GET			/api/apotheke/:id									retrieve a apotheke by :id
		PUT			/api/apotheke/:id									update a apotheke by :id
		DELETE		/api/apotheke/:id									delete a apotheke by :id
		DELETE		/api/apotheke										delete all apothekes
		GET			/api/apotheke/{field_name}/{field_value}			find by any column name {field_name} in table name apotheke with is value {field_value}
		GET			/api/apotheke?id_apotheke={value_id_apotheke}	find all apothekes which id_apotheke contains value_id_apotheke
	 *	
     ***************************************************************************************************
	 */

	@Autowired
	ApothekeRepository apothekeRepository;

	/**
	 * poster alle apotheken 
	 * @param id_apotheke Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/apotheke")
	public ResponseEntity<List<Apotheke>> getAllApotheke(@RequestParam(required = false) Integer id_apotheke) {
		try {
			List<Apotheke> apothekes = new ArrayList<Apotheke>();
			if (id_apotheke == null)
				apothekeRepository.findAll().forEach(apothekes::add);
			else
				apothekes.add(apothekeRepository.findById(id_apotheke).get());
			if (apothekes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(apothekes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * poster eine apotheke
	 * @param id Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/apotheke/{id}")
	public ResponseEntity<Apotheke> getApothekeById(@PathVariable("id") Integer id) {
		Optional<Apotheke> apothekeData = apothekeRepository.findById(id);
		if (apothekeData.isPresent()) {
			return new ResponseEntity<>(apothekeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Anmeldung zur Apotheke
	 * @param apotheke Parameter
	 * @return Ergebnisse
	 */
	@PostMapping("/apotheke")
	public ResponseEntity<Apotheke> createApotheke(@RequestBody Apotheke apotheke) {
		try {
			Apotheke _apotheke = apothekeRepository.save(new Apotheke(apotheke.getNomApotheke()));
			return new ResponseEntity<>(_apotheke, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * eine Apotheke umbauen
	 * @param id Parameter
	 * @param apotheke Parameter
	 * @return Ergbnisse
	 */
	@PutMapping("/apotheke/{id}")
	public ResponseEntity<Apotheke> updateApotheke(@PathVariable("id") Integer id, @RequestBody Apotheke apotheke) {
		Optional<Apotheke> apothekeData = apothekeRepository.findById(id);
		if (apothekeData.isPresent()) {
			Apotheke _apotheke = apothekeData.get();
			_apotheke.setNomApotheke(apotheke.getNomApotheke());
			return new ResponseEntity<>(apothekeRepository.save(_apotheke), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * entfernt eine Apotheke
	 * @param id Parameter
	 * @return Ergebnisse
	 */
	@DeleteMapping("/apotheke/{id}")
	public ResponseEntity<HttpStatus> deleteApotheke(@PathVariable("id") Integer id) {
		try {
			apothekeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * alle Apotheken entfernen
	 * @return Ergebnisse
	 */
	@DeleteMapping("/apotheke")
	public ResponseEntity<HttpStatus> deleteAllApotheke() {
		try {
			apothekeRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Suche nach Apotheken basierend auf einer Spalte
	 * @param field_name Parameter
	 * @param field_value Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/apotheke/{field_name}/{field_value}")
	public ResponseEntity<List<Apotheke>> findByAnyParam(@PathVariable("field_name") String field_name,
			@PathVariable("field_value") String field_value) {
		try {
			List<Apotheke> apothekes = new ArrayList<Apotheke>();
			switch (field_name.toLowerCase()) {
			case "id_apotheke":
				apothekes.add(apothekeRepository.findById(Integer.parseInt(field_value)).get());
				break;
			case "nom_apotheke":
				apothekeRepository.findByNomApothekeIgnoreCase(field_value).forEach(apothekes::add);
				break;
			default:
				// code for default case
			}
			if (apothekes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(apothekes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
