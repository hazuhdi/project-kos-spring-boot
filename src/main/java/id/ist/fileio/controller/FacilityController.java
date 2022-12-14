package id.ist.fileio.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import id.ist.fileio.dto.FacilityDto;
import id.ist.fileio.model.Facility;
import id.ist.fileio.service.FacilityService;
import id.ist.fileio.utils.ObjectUtils;

@RestController
@RequestMapping(path = "/facilities")
public class FacilityController {

	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping
	public ResponseEntity<List<Facility>> getAllFacility() {
		List<Facility> facils = facilityService.findAll();	
		HttpStatus responseHTTPStatus = (!facils.isEmpty()) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
		return new ResponseEntity<>(facils, responseHTTPStatus);
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Facility> getFacilById(@PathVariable Long id) {
		Facility facil = facilityService.findById(id);
		return new ResponseEntity<>(facil,HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<HttpStatus> deleteOneFacility(@PathVariable Long id) {
		facilityService.deleteOne(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Facility> addFacility(@RequestBody @Valid FacilityDto facilDto) {
		Objects.requireNonNull(facilDto);
		Facility facility = new Facility();
		ObjectUtils.copyProperties(facility, facilDto);
		facilityService.addFile(facility);
		return new ResponseEntity<>(facility, HttpStatus.OK);
	}
	

	@PutMapping(path = "/update/{id}")
	public ResponseEntity<Facility> updateFacility(@PathVariable Long id, @RequestBody @Valid FacilityDto facilDto) {
		Objects.requireNonNull(facilDto);
		Facility facility = new Facility();
		ObjectUtils.copyProperties(facility, facilDto);
		facilityService.editFile(id, facility);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping(path = "/patch/{id}", consumes = "application/json-patch+json")
	public Facility patchFacility(@PathVariable Long id, @RequestBody JsonPatch patch) {
	    try {
	        Facility facility = facilityService.findById(id);
	        JsonNode patched = patch.apply(objectMapper.convertValue(facility, JsonNode.class));
	        Facility patchedFacility = objectMapper.treeToValue(patched, Facility.class);
	        facilityService.editFile(id, patchedFacility);
	        return patchedFacility;
	    } catch (JsonPatchException | JsonProcessingException e) {
	        throw new InvalidDataException();
	    }
	}

}
