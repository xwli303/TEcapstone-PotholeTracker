package com.techelevator.model;

import java.time.LocalDate;

public class Job {
	private long jobId;
	private int potholeId;
	private int employeeId;
	private int jobStatus;
	private LocalDate openedDate;
	private LocalDate closedDate;
	
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public int getPotholeId() {
		return potholeId;
	}
	public void setPotholeId(int potholeId) {
		this.potholeId = potholeId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(int jobStatus) {
		this.jobStatus = jobStatus;
	}
	public LocalDate getOpenedDate() {
		return openedDate;
	}
	public void setOpenedDate(LocalDate openedDate) {
		this.openedDate = openedDate;
	}
	public LocalDate getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(LocalDate closedDate) {
		this.closedDate = closedDate;
	}
	
	
}
