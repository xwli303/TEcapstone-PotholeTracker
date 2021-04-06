package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Pothole;

import Services.PotholeServices;

@RestController
public class PotholeController {
	private PotholeServices potholeServices;
	
	public PotholeController(PotholeServices potholeServices) {
		this.potholeServices = potholeServices;
	}
	
	@RequestMapping(path = "/pothole")
	public List<Pothole> getAllPotholes(){
		return potholeServices.getAllPotholes();
	}
}
