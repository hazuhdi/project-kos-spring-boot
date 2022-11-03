package id.ist.fileio.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import id.ist.fileio.exception.FacilityException;
import id.ist.fileio.model.Facility;
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

	public boolean addFile(Facility facil) {
		boolean facilAdd = false;
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(checkFiles()))) {
			facilAdd = facils.add(facil);
			os.writeObject(facils);
		} catch (IOException e) {
			throw new FacilityException(e.getMessage(), e);
		} finally {
			return facilAdd;
		}
	}

	public Facility editFile(int index, Facility facil) {
		Facility facilUpdt = null;
		index = index - 1;
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(checkFiles()))) {
			facilUpdt = facils.set(index, facil);
			os.writeObject(facils);
		} catch (IOException e) {
			throw new FacilityException(e.getMessage(), e);
		} finally {
			return facilUpdt;
		}
	}

	public void deleteOne(int index) {
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(checkFiles()))) {
			Facility facil1 = get(index);

			if (facil1 != null) {
				facils.remove(facil1);
				os.writeObject(facils);
			} else {
				log.error("Unable to find id (index)");
			}
		} catch (IOException e) {
			throw new FacilityException(e.getMessage(), e);
		}
	}

	@Override
	public Facility get(int index) {
		Facility getFacil = null;
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(checkFiles()))) {
			int size = readFile().size() - 1;
			index = index - 1;

			if (index > size) {
				log.info("Out of bond (index) - get one facility");
				return getFacil;
			}
			
			getFacil = facils.get(index);
			return getFacil;
			
		} catch (IOException e) {
			throw new FacilityException(e.getMessage(), e);
		}
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

}
