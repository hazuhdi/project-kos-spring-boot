package id.ist.fileio.service;

import java.io.File;
import java.util.List;

import id.ist.fileio.model.Facility;

public interface FacilityService {
	public File checkFiles();
	public Boolean addFile(Facility facil);
	public Facility editFile(Long id, Facility facil);
	public void deleteOne(Long index);
	public Facility findById(Long id);
	public List<Facility> readFile();
	public List<Facility> findAll();
}
