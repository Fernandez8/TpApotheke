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

import com.apotheke.tp.model.Stocklager;
import com.apotheke.tp.repository.StocklagerRepository;

//@CrossOrigin(origins = "http://localhost:8081")
/**
 * Controller für die Bestandsverwaltung von Apotheken
 *
 */
@RestController
@RequestMapping("/api")
public class StocklagerKontroller {
	/*
	 * ************************* These are APIs that we need to provide
	 * *******************************
	 * 
	 * Methods Urls Actions POST /api/stocklager create new stocklager GET /api/stocklager
	 * retrieve all stocklagers GET
	 * /api/stocklager/{value_id_lager}/{value_id_medikament} retrieve a stocklager by
	 * {value_id_lager} and {value_id_medikament} PUT
	 * /api/add/stocklager/{value_id_lager}/{value_id_medikament} update to add a
	 * stocklager by {value_id_lager} and {value_id_medikament} PUT
	 * /api/reduce/stocklager/{value_id_lager}/{value_id_medikament} update to reduce
	 * a stocklager by {value_id_lager} and {value_id_medikament} DELETE
	 * /api/stocklager/{value_id_lager}/{value_id_medikament} delete a stocklager by
	 * {value_id_lager} and {value_id_medikament} DELETE /api/stocklager delete all
	 * stocklagers GET /api/stocklager/{field_name}/{field_value} find by any column name
	 * {field_name} in table name stocklager with is value {field_value} GET
	 * /api/stocklager?id_lager={value_id_lager}&id_medikament={
	 * value_id_medikament} find all stocklagers which id_lager contains
	 * value_id_lager and id_medikament contains value_id_medikament
	 * 
	 ***************************************************************************************************
	 */

	@Autowired
	StocklagerRepository stocklagerRepository;

	/**
	 * zeigt alle Apothekenbestände an
	 * 
	 * @param id_lager Parameter
	 * @param id_medikament Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/stocklager")
	public ResponseEntity<List<Stocklager>> getAllStocklager(@RequestParam(required = false) Integer id_lager,
			@RequestParam(required = false) Integer id_medikament) {
		try {
			List<Stocklager> stocklagers = new ArrayList<Stocklager>();
			if (id_lager == null && id_medikament == null)
				stocklagerRepository.findAll().forEach(stocklagers::add);
			else if (id_lager != null && id_medikament != null)
				stocklagers.add(
						stocklagerRepository.findByIdIdLagerAndIdIdMedikament(id_lager, id_medikament));
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			if (stocklagers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(stocklagers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * zeigt einen Apothekenbestand an
	 * 
	 * @param id_lager Parameter
	 * @param id_medikament Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/stocklager/{id_lager}/{id_medikament}")
	public ResponseEntity<Stocklager> getStocklagerById(@PathVariable("id_lager") Integer id_lager,
			@PathVariable("id_medikament") Integer id_medikament) {
		Stocklager stocklager = stocklagerRepository.findByIdIdLagerAndIdIdMedikament(id_lager, id_medikament);
		if (Objects.nonNull(stocklager)) {
			return new ResponseEntity<>(stocklager, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * spart eine Apothekenaktie
	 * 
	 * @param stocklager Parameter
	 * @return Ergebnisse
	 */
	@PostMapping("/stocklager")
	public ResponseEntity<Stocklager> createStocklager(@RequestBody Stocklager stocklager) {
		try {
			Stocklager _stocklager = stocklagerRepository
					.save(new Stocklager(stocklager.getId(), stocklager.getQuantitaetVerfuegbar()));
			return new ResponseEntity<>(_stocklager, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * fügt eine Menge zu einem Apothekenvorrat hinzu
	 * 
	 * @param id_lager Parameter
	 * @param id_medikament Parameter
	 * @param stocklager Parameter
	 * @return Ergebnisse
	 */
	@PutMapping(value = {"/stocklager/add","/stocklager/add/{id_lager}/{id_medikament}"})
	public ResponseEntity<Stocklager> updateToAddStocklager(
			@PathVariable(name = "id_lager", required = false) Integer id_lager,
			@PathVariable(name = "id_medikament", required = false) Integer id_medikament,
			@RequestBody Stocklager stocklager) {
		
		if (id_lager == null && id_medikament == null) {
			id_lager = stocklager.getId().getIdLager();
			id_medikament = stocklager.getId().getIdMedikament();
		}else if((id_lager != null && id_medikament == null) || id_lager == null && id_medikament != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Stocklager _stocklager = stocklagerRepository.findByIdIdLagerAndIdIdMedikament(id_lager, id_medikament);
		if (Objects.nonNull(_stocklager)) {
			Integer new_stock = stocklager.getQuantitaetVerfuegbar() + _stocklager.getQuantitaetVerfuegbar(); // add new quantity
			_stocklager.setQuantitaetVerfuegbar(new_stock);
			return new ResponseEntity<>(stocklagerRepository.save(_stocklager), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * reduziert eine Menge auf einen Apothekenvorrat
	 * 
	 * @param id_lager Parameter
	 * @param id_medikament Parameter
	 * @param stocklager Parameter
	 * @return Ergebnisse
	 */
	@PutMapping(value = {"/stocklager/reduce","/stocklager/reduce/{id_lager}/{id_medikament}"})
	public ResponseEntity<Stocklager> updateToReduceStocklager(
			@PathVariable(name = "id_lager", required = false) Integer id_lager,
			@PathVariable(name = "id_medikament", required = false) Integer id_medikament,
			@RequestBody Stocklager stocklager) {
		
		if (id_lager == null && id_medikament == null) {
			id_lager = stocklager.getId().getIdLager();
			id_medikament = stocklager.getId().getIdMedikament();
		}else if((id_lager != null && id_medikament == null) || id_lager == null && id_medikament != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Stocklager _stocklager = stocklagerRepository.findByIdIdLagerAndIdIdMedikament(id_lager, id_medikament);
		if (Objects.nonNull(_stocklager) && _stocklager.getQuantitaetVerfuegbar() >= stocklager.getQuantitaetVerfuegbar()) {
			Integer new_stock = _stocklager.getQuantitaetVerfuegbar() - stocklager.getQuantitaetVerfuegbar(); // reduce new quantity
			_stocklager.setQuantitaetVerfuegbar(new_stock);
			return new ResponseEntity<>(stocklagerRepository.save(_stocklager), HttpStatus.OK);
		}else if (Objects.nonNull(_stocklager) && _stocklager.getQuantitaetVerfuegbar() < stocklager.getQuantitaetVerfuegbar()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * einen Vorrat eines Arzneimittels aus Apotheken entfernen 
	 * 
	 * @param id_lager Parameter
	 * @param id_medikament Parameter
	 * @return Ergebnisse
	 */
	@DeleteMapping("/stocklager/{id_lager}/{id_medikament}")
	public ResponseEntity<HttpStatus> deleteStocklager(@PathVariable("id_lager") Integer id_lager,
			@PathVariable("id_medikament") Integer id_medikament) {
		try {
			stocklagerRepository.deleteByIdIdLagerAndIdIdMedikament(id_lager, id_medikament);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * entfernt alle Bestände eines Arzneimittels aus Apotheken
	 * 
	 * @return Ergebnisse
	 */
	@DeleteMapping("/stocklager")
	public ResponseEntity<HttpStatus> deleteAllStocklager() {
		try {
			stocklagerRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * durchsucht die Bestände eines Medikaments aus Apotheken nach einer Spalte
	 * 
	 * @param field_name Parameter
	 * @param field_value Parameter
	 * @return Ergebnisse
	 */
	@GetMapping("/stocklager/customfield/{field_name}/{field_value}")
	public ResponseEntity<List<Stocklager>> findByAnyParam(@PathVariable("field_name") String field_name,
			@PathVariable("field_value") String field_value) {
		try {
			List<Stocklager> stocklagers = new ArrayList<Stocklager>();
			switch (field_name.toLowerCase()) {
			case "id_lager":
				stocklagerRepository.findByIdIdLager(Integer.parseInt(field_value)).forEach(stocklagers::add);
				break;
			case "id_medikament":
				stocklagerRepository.findByIdIdMedikament(Integer.parseInt(field_value)).forEach(stocklagers::add);
				break;
			case "quantitaet_verfuegbare":
				stocklagerRepository.findByQuantitaetVerfuegbar(Integer.parseInt(field_value)).forEach(stocklagers::add);
				break;
			default:
				// code for default case
			}
			if (stocklagers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(stocklagers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
