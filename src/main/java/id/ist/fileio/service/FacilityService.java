package id.ist.fileio.service;

import java.io.File;
import java.util.List;

import id.ist.fileio.model.Facility;

public interface FacilityService {
	public File checkFiles();
	public List<Facility> addFile(Facility facil);
	public List<Facility> editFile(long id, Facility facil);
	public List<Facility> deleteOne(int index);
	public Facility get(long id);
	public List<Facility> readFile();
}
