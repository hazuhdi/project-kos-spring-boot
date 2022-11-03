package id.ist.fileio.service;

import java.io.File;
import java.util.List;

import id.ist.fileio.model.Facility;

public interface FacilityService {
	public File checkFiles();
	public boolean addFile(Facility facil);
	public Facility editFile(int id, Facility facil);
	public void deleteOne(int index);
	public Facility get(int id);
	public List<Facility> readFile();
}
