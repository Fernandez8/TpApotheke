package com.apotheke.tp.Kontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

import com.apotheke.tp.model.Stockapotheke;
import com.apotheke.tp.repository.StockapothekeRepository;


/**
 * Controller, der die Bestellungen verwaltet
 *
 */
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StockapothekeKontroller {
	/*
	 * ************************* These are APIs that we need to provide
	 * *******************************
	 * 
	 * Methods 		Urls 																					Actions 
	 * POST 		/api/stockapotheke 																			create new stockapotheke 
	 * GET 			/api/stockapotheke 																			retrieve all stockapothekes 
	 * GET 			/api/stockapotheke/{value_id_apotheke}/{value_id_medikament} 								retrieve a stockapotheke by {value_id_apotheke} and {value_id_medikament} 
	 * PUT 			/api/stockapotheke/add/{value_id_apotheke}/{value_id_medikament} 							update to add a stockapotheke by {value_id_apotheke} and {value_id_medikament} 
	 * PUT 			/api/stockapotheke/add 							                                            update to add a stockapotheke by {value_id_apotheke} and {value_id_medikament} 
	 * PUT 			/api/stockapotheke/reduce/{value_id_apotheke}/{value_id_medikament} 						update to add a stockapotheke by {value_id_apotheke} and {value_id_medikament} 
	 * PUT 			/api/stockapotheke/reduce																	update to add a stockapotheke by {value_id_apotheke} and {value_id_medikament} 
	 * PUT 			/api/reduce/stockapotheke/{value_id_apotheke}/{value_id_medikament} 						update to reduce a stockapotheke by {value_id_apotheke} and {value_id_medikament} 
	 * DELETE  		/api/stockapotheke/{value_id_apotheke}/{value_id_medikament} 								delete a stockapotheke by {value_id_apotheke} and {value_id_medikament} 
	 * DELETE 		/api/stockapotheke 																			delete all stockapothekes GET /api/stockapotheke/{field_name}/{field_value} find by any column name {field_name} in table name stockapotheke with is value {field_value} 
	 * GET 			/api/stockapotheke?id_apotheke={value_id_apotheke}&id_medikament={value_id_medikament} 	find all stockapothekes which id_apotheke contains value_id_apotheke and id_medikament contains value_id_medikament
	 * 
	 ***************************************************************************************************
	 */

	@Autowired
	StockapothekeRepository stockapothekeRepository;

	/**
	 * @param id_apotheke Parameter
	 * @param id_medikament Parameter
	 * @return Alle Apothekenbestellungen
	 */
	@GetMapping("/stockapotheke")
	public ResponseEntity<List<Stockapotheke>> getAllStockapotheke(@RequestParam(required = false) Integer id_apotheke,
			@RequestParam(required = false) Integer id_medikament) {
		try {
			List<Stockapotheke> stockapothekes = new ArrayList<Stockapotheke>();
			if (id_apotheke == null && id_medikament == null)
				stockapothekeRepository.findAll().forEach(stockapothekes::add);
			else if (id_apotheke != null && id_medikament != null)
				stockapothekes.add(stockapothekeRepository
						.findByIdIdApothekeAndIdIdMedikament(id_apotheke, id_medikament));
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			if (stockapothekes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(stockapothekes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @param id_apotheke Parameter
	 * @param id_medikament Parameter
	 * @return Bestellungen von Apotheken, die einer Apotheke und einem Arzneimittel entsprechen
	 */
	@GetMapping("/stockapotheke/{id_apotheke}/{id_medikament}")
	public ResponseEntity<Stockapotheke> getStockapothekeById(@PathVariable("id_apotheke") Integer id_apotheke,
			@PathVariable("id_medikament") Integer id_medikament) {
		Stockapotheke stockapotheke = stockapothekeRepository
				.findByIdIdApothekeAndIdIdMedikament(id_apotheke, id_medikament);
		if (Objects.nonNull(stockapotheke)) {
			return new ResponseEntity<>(stockapotheke, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Befehl hinzufügen
	 * 
	 * @param stockapotheke Parameter
	 * @return Ergebnisse
	 */
	@PostMapping("/stockapotheke")
	public ResponseEntity<Stockapotheke> createStockapotheke(@RequestBody Stockapotheke stockapotheke) {
		try {
			Stockapotheke _stockapotheke = stockapothekeRepository
					.save(new Stockapotheke(stockapotheke.getId(), stockapotheke.getQuantitaetVerfuegbareApotheke()));
			return new ResponseEntity<>(_stockapotheke, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Hinzufügen der Menge einer Bestellung
	 * 
	 * @param id_apotheke Parameter
	 * @param id_medikament Parameter
	 * @param stockapotheke Parameter
	 * @return Ergebnisse
	 */
	@PutMapping(value = {"/stockapotheke/add","/stockapotheke/add/{id_apotheke}/{id_medikament}"})
	public ResponseEntity<Stockapotheke> updateToAddStockapotheke(
			@PathVariable(name = "id_apotheke", required = false) Integer id_apotheke,
			@PathVariable(name = "id_medikament", required = false) Integer id_medikament,
			@RequestBody Stockapotheke stockapotheke) {
		
		if (id_apotheke == null && id_medikament == null) {
			id_apotheke = stockapotheke.getId().getIdApotheke();
			id_medikament = stockapotheke.getId().getIdMedikament();
		}else if((id_apotheke != null && id_medikament == null) || id_apotheke == null && id_medikament != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Stockapotheke _stockapotheke = stockapothekeRepository.findByIdIdApothekeAndIdIdMedikament(id_apotheke, id_medikament);
		if (Objects.nonNull(_stockapotheke)) {
			Integer new_stock = stockapotheke.getQuantitaetVerfuegbareApotheke()
					+ _stockapotheke.getQuantitaetVerfuegbareApotheke(); // add new quantity
			_stockapotheke.setQuantitaetVerfuegbareApotheke(new_stock);
			return new ResponseEntity<>(stockapothekeRepository.save(_stockapotheke), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Reduzierung der Bestellmenge
	 * 
	 * @param id_apotheke Parameter
	 * @param id_medikament Parameter
	 * @param stockapotheke Parameter
	 * @return Ergebnisse
	 */
	@PutMapping(value = {"/stockapotheke/reduce","/stockapotheke/reduce/{id_apotheke}/{id_medikament}"})
	public ResponseEntity<Stockapotheke> updateToReduceStockapotheke(
			@PathVariable(name = "id_apotheke", required = false) Integer id_apotheke,
			@PathVariable(name = "id_medikament", required = false) Integer id_medikament,
			@RequestBody Stockapotheke stockapotheke) {
		
		if (id_apotheke == null && id_medikament == null) {
			id_apotheke = stockapotheke.getId().getIdApotheke();
			id_medikament = stockapotheke.getId().getIdMedikament();
		}else if((id_apotheke != null && id_medikament == null) || id_apotheke == null && id_medikament != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Stockapotheke _stockapotheke = stockapothekeRepository
				.findByIdIdApothekeAndIdIdMedikament(id_apotheke, id_medikament);
		if (Objects.nonNull(_stockapotheke)
				&& _stockapotheke.getQuantitaetVerfuegbareApotheke() >= stockapotheke.getQuantitaetVerfuegbareApotheke()) {
			Integer new_stock = _stockapotheke.getQuantitaetVerfuegbareApotheke()
					- stockapotheke.getQuantitaetVerfuegbareApotheke(); // reduce new quantity
			_stockapotheke.setQuantitaetVerfuegbareApotheke(new_stock);
			return new ResponseEntity<>(stockapothekeRepository.save(_stockapotheke), HttpStatus.OK);
		} else if (Objects.nonNull(_stockapotheke)
				&& _stockapotheke.getQuantitaetVerfuegbareApotheke() < stockapotheke.getQuantitaetVerfuegbareApotheke()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * eine Bestellung löschen
	 * 
	 * @param id_apotheke Parameter
	 * @param id_medikament Parameter
	 * @return Ergebnisse
	 */
	@DeleteMapping("/stockapotheke/{id_apotheke}/{id_medikament}")
	public ResponseEntity<HttpStatus> deleteStockapotheke(@PathVariable("id_apotheke") Integer id_apotheke,
			@PathVariable("id_medikament") Integer id_medikament) {
		try {
			stockapothekeRepository.deleteByIdIdApothekeAndIdIdMedikament(id_apotheke,
					id_medikament);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * alle Bestellungen löschen
	 * 
	 * @return Ergebnisse
	 */
	@DeleteMapping("/stockapotheke")
	public ResponseEntity<HttpStatus> deleteAllStockapotheke() {
		try {
			stockapothekeRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Suchaufträge basierend auf einer Spalte
	 * 
	 * @param field_name Parameter
	 * @param field_value Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/stockapotheke/customfield/{field_name}/{field_value}")
	public ResponseEntity<List<Stockapotheke>> getAllCustomParam(@PathVariable("field_name") String field_name,
			@PathVariable("field_value") String field_value) {
		try {
			List<Stockapotheke> stockapothekes = new ArrayList<Stockapotheke>();
			switch (field_name.toLowerCase()) {
			case "id_apotheke":
				stockapothekeRepository.findByIdIdApotheke(Integer.parseInt(field_value))
						.forEach(stockapothekes::add);
				break;
			case "id_medikament":
				stockapothekeRepository.findByIdIdMedikament(Integer.parseInt(field_value))
						.forEach(stockapothekes::add);
				break;
			case "quantitaet_verfuegbare_apotheke":
				stockapothekeRepository.findByQuantitaetVerfuegbareApotheke(Integer.parseInt(field_value))
						.forEach(stockapothekes::add);
				break;
			default:
				// code for default case
			}
			if (stockapothekes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(stockapothekes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
