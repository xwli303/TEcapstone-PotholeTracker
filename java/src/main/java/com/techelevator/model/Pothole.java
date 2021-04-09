package com.techelevator.model;

import java.time.LocalDate;

public class Pothole {
	private long id;
	private int statusCode;
	private int severity;
	private String address;
	private double longitude;
	private double latitude;
	private LocalDate dateReported;
	private LocalDate dateInspected;
	private LocalDate dateRepaired;

	private int user_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public LocalDate getDateReported() {
		return dateReported;
	}
	public void setDateReported(LocalDate dateReported) {
		this.dateReported = dateReported;
	}
	public LocalDate getDateInspected() {
		return dateInspected;
	}
	public void setDateInspected(LocalDate dateInspected) {
		this.dateInspected = dateInspected;
	}
	public LocalDate getDateRepaired() {
		return dateRepaired;
	}
	public void setDateRepaired(LocalDate dateRepaired) {
		this.dateRepaired = dateRepaired;
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
