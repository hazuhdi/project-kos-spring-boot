package id.ist.fileio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@GetMapping(value = "/facilities")
	public ResponseEntity<List<Facility>> getAllFacility() {
		log.info("tes controller");
		List<Facility> facils = facilityService.readFile();
		if (facils.isEmpty()) {
			log.info("Data Not Found");
		}
		HttpStatus responseHTTPStatus = (!facils.isEmpty()) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
		return new ResponseEntity<>(facils, responseHTTPStatus);
	}

	@GetMapping(value = "/facilities/get/{id}")
	public ResponseEntity<Facility> getFacilById(@PathVariable int id) {
		Facility facil = facilityService.get(id);
		HttpStatus responseHTTPStatus = (facil != null) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
		return new ResponseEntity<>(facil, responseHTTPStatus);
	}

	@DeleteMapping(value = "facilities/delete/{id}")
	public ResponseEntity<HttpStatus> deleteOneFacility(@PathVariable int id) {
		facilityService.deleteOne(id);
		return ResponseEntity.ok(HttpStatus.OK);

	}

	@PostMapping(value = "/facilities/add")
	public ResponseEntity<Facility> addFacility(@RequestBody @Valid Facility facil) {
		Boolean add = facilityService.addFile(facil);
		HttpStatus responseHTTPStatus = (add == Boolean.TRUE) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(facil, responseHTTPStatus);
	}
	

	@PutMapping(value = "/facilities/update/{id}")
	public ResponseEntity<Facility> updateFacility(@PathVariable int id, @RequestBody @Valid Facility facil) {
//		Facility facilCheck = facilityService.get(id);
//		List<Facility> facilUpdt = ;
		facilityService.editFile(id, facil);
//		HttpStatus responseHTTPStatus = (facilCheck != null) ? HttpStatus.OK: HttpStatus.NO_CONTENT;
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
