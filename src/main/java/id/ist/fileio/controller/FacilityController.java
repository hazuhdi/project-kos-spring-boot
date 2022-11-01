package id.ist.fileio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.ist.fileio.model.Facility;
import id.ist.fileio.service.FacilityService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FacilityController {
	
	@Autowired
	FacilityService facilityService;
	
	/*issue get
	 * cant retrieve data from file
	 */
	@GetMapping(value="/facilities")
	public ResponseEntity<Object> getAllFacility(){
		log.info("tes controller");
		List<Facility> facils = facilityService.readFile();
		if(facils.isEmpty()) {
			log.info("Data Not Found");
		}
		HttpStatus responseHTTPStatus = (!facils.isEmpty()) ? HttpStatus.OK: HttpStatus.NO_CONTENT;
		return new ResponseEntity<>(facils, responseHTTPStatus);
	}
	
	@GetMapping(value="/facility/get/{id}")
	public ResponseEntity<Object> getFacilById(@PathVariable long id){
		log.info("tes controller");
		Facility facil = facilityService.get(id);
		HttpStatus responseHTTPStatus = (facil != null) ? HttpStatus.OK: HttpStatus.NO_CONTENT;
		return new ResponseEntity<>(facil, responseHTTPStatus);
	}
	
	@PostMapping(value="/facility/add")
	public ResponseEntity<Object> addFacility(@RequestBody Facility facil){
		facilityService.addFile(facil);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@PutMapping(value="/facility/update/{id}")
	public ResponseEntity<Object> updateFacility(@PathVariable long id,@RequestBody Facility facil){
		Facility facilUpd = (Facility) facilityService.editFile(id, facil);
		HttpStatus responseHTTPStatus = (facilUpd != null) ? HttpStatus.OK: HttpStatus.NO_CONTENT;
		return new ResponseEntity<>(facilUpd, responseHTTPStatus);
	}
}
