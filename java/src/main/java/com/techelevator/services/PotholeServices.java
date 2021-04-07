package com.techelevator.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.techelevator.dao.PotholeDAO;
import com.techelevator.model.Pothole;

@Service
public class PotholeServices {
	private PotholeDAO potholeDAO;
	public PotholeServices( PotholeDAO potholeDAO) {
		this.potholeDAO = potholeDAO;
	}
	public List<Pothole> getAllPotholes(){
		return potholeDAO.getAllPotholes();
	}
	
	public List<Pothole> getFilteredListOfPotholes(String orderBy){
		return potholeDAO.getFilteredListOfPotholes(orderBy);
	}
	
	public void reportPothole(Pothole newPothole) {
		potholeDAO.reportPothole(newPothole);
	}
}
