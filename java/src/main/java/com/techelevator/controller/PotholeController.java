package com.techelevator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Pothole;

import com.techelevator.services.PotholeServices;

@RestController
@CrossOrigin
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
	
	@RequestMapping(path = "/report-pothole", method = RequestMethod.POST)
	public void reportPothole(@RequestBody Pothole newPothole) {
		potholeServices.reportPothole(newPothole);
	}

	@RequestMapping(path = "/all-potholes/get-pothole-by-id/{id}", method = RequestMethod.GET)
    public Pothole getPotholeById(@PathVariable long id) {
        return potholeServices.getPotholeById(id);
    }
	
	@RequestMapping(path = "/all-potholes/update-pothole/{id}", method = RequestMethod.PUT)
    public void updatePotholeById(@PathVariable long id, @RequestBody Pothole pothole) {
       potholeServices.updatePotholeById(id, pothole);
    }
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/delete-pothole/{id}", method = RequestMethod.DELETE)
    public void deletePotholeById(@PathVariable int id) {
    	potholeServices.deletePotholeById(id);
    }
	
	
}
