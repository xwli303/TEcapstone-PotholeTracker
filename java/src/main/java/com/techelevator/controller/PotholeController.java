package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Pothole;
import com.techelevator.services.PotholeServices;

@RestController
public class PotholeController {
	private PotholeServices potholeServices;
	
	public PotholeController(PotholeServices potholeServices) {
		this.potholeServices = potholeServices;
	}
	
	@RequestMapping(path = "/all-potholes", method = RequestMethod.GET)
	public List<Pothole> getAllPotholes(){
		return potholeServices.getAllPotholes();
	}
	
	@RequestMapping(path = "/all-potholes/ordered-by-{orderBy}", method = RequestMethod.GET)
	public List<Pothole> getFilteredListOfPotholes(@PathVariable String orderBy){
		return potholeServices.getFilteredListOfPotholes(orderBy);
	}
}
