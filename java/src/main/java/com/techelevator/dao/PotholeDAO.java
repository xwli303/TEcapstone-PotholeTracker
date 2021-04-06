package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Pothole;

public interface PotholeDAO {
	public List<Pothole> getFilteredListOfPotholes(String orderBy);
	
	public List<Pothole> getAllPotholes();
	
	public void reportPothole(Pothole newPothole);
	
	public Pothole getPotholeById(long id);
	
	public void updatePotholeById(Pothole updatedPothole, long id);
	
	public void deletePotholeById(long id);
	
}
