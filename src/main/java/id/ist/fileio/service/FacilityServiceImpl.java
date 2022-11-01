package id.ist.fileio.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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

	private static List<Facility> facils = new ArrayList<>();

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

	public List<Facility> addFile(Facility facil) {

		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(checkFiles()))) {
			facils.add(facil);
			os.writeObject(facils);
		} catch (IOException e) {
			throw new FacilityException(e.getMessage(), e);
		} finally {
			return facils;
		}
	}

	public List<Facility> editFile(int index, Facility facil) {

		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(checkFiles()))) {
			int size = readFile().size() - 1;
			index = index - 1;

			if (index <= size) {
				facils.set(index, facil);
				os.writeObject(facils);
			} else {
				System.out.println();
				System.out.println("Unable to find id (index)!");
				System.out.println();
			}
		} catch (IOException e) {
			throw new FacilityException(e.getMessage(), e);
		} finally {
			return facils;
		}
	}

	public List<Facility> deleteOne(int index) {
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(checkFiles()))) {
			int size = readFile().size() - 1;
			index = index - 1;

			if (index <= size) {
				facils.remove(index);
				os.writeObject(facils);
			} else {
				System.out.println();
				System.out.println("Unable to find id (index)");
				System.out.println();
			}
		} catch (IOException e) {
			throw new FacilityException(e.getMessage(), e);
		} finally {
			return facils;
		}
	}

	@Override
	public Facility get(long index) {
		Facility getFacil = null;
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(checkFiles()))) {
			int size = readFile().size() - 1;
			index = index - 1;

			if (index <= size) {
				getFacil = facils.get((int) index);
			} else {
				log.info("Unable to find id (index) - get one facility");
			}
			os.writeObject(facils);
		} catch (IOException e) {
			throw new FacilityException(e.getMessage(), e);
		} finally {
			return getFacil;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Facility> readFile() {
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(checkFiles()))) {
			log.info("inside try");
			facils = (List<Facility>) is.readObject();
			File file = checkFiles();
			if (file.exists()) {
				log.info("There is no data input yet : " + file);
			} else {
				log.info("File Found");
			}
		} catch (IOException e) {
			throw new FacilityException(e.getMessage(), e);
		} finally {
			log.info("finally read data");
			return facils;
		}
	}

	@Override
	public List<Facility> editFile(long id, Facility facil) {
		// TODO Auto-generated method stub
		return null;
	}

}
