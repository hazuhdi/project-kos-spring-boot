package id.ist.fileio.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import id.ist.fileio.exception.FacilityException;
import id.ist.fileio.exception.FacilityNotFoundException;
import id.ist.fileio.model.Facility;
import id.ist.fileio.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("finally")
@Slf4j
@Service
public class FacilityServiceImpl implements FacilityService {
	private static final String KEY_APP_DIR = "FACILITY_SPRING";
	private static final String DEFAULT_DIR = "C:/FacilitySpring";

	private List<Facility> facils = new ArrayList<>();

	public File checkFiles() {
		String dirPath = System.getProperty(KEY_APP_DIR, DEFAULT_DIR);
		File dir = new File(dirPath);
		File file = new File(dir, "facilityItemSpring.txt");
		if (!file.exists()) {
			dir.mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new FacilityException(e.getMessage(), e);
			}
		}
		return file;
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public List<Facility> readFile() {
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(checkFiles()))) {
			facils = (List<Facility>) is.readObject();
		} catch (IOException e) {
			throw new FacilityException(e.getMessage(), e);
		} finally {
			log.info("finally ~ read data");
			return facils;
		}
	}
	
	public Boolean addFile(Facility facil) {
		Long lastId = facils.stream().map(Facility::getId).max(Long::compare).orElse(1L);
		facil.setId(lastId+1);
		return facils.add(facil);
	}

	public Facility editFile(Long id, Facility facilNew) {
		Facility facil = findById(id);
		facilNew.setId(facil.getId());
		ObjectUtils.copyProperties(facil, facilNew);
		return facilNew;
	}

	public void deleteOne(Long id) {
		Facility facility = findById(id);
		facils.remove(facility);
	}
	
	public List<Facility> findAll(){
		return facils;
	}
	
	public Facility findById(Long id) {
		return facils.stream()
				.filter(e -> e.getId().equals(id))
				.findFirst()
				.orElseThrow(FacilityNotFoundException::new);
	}

}
