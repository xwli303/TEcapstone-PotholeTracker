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

import com.techelevator.model.Job;
import com.techelevator.services.JobServices;


@RestController
@CrossOrigin
public class JobController {
	private JobServices jobServices;
	
	public JobController(JobServices jobServices) {
		this.jobServices = jobServices;
	}
	
	@RequestMapping(path = "/all-jobs", method = RequestMethod.GET)
	public List<Job> getAllJobs(){
		return jobServices.getAllJobs();
	}
	
	@RequestMapping(path = "/all-jobs/ordered-by/{orderBy}", method = RequestMethod.GET)
	public List<Job> getFilteredListOfJobs(@PathVariable String orderBy) {
		return jobServices.getFilteredListOfJobs(orderBy);
	}
	
	@RequestMapping(path = "/create-job", method = RequestMethod.POST)
	public void createJob(@RequestBody Job newJob) {
		jobServices.createJob(newJob);
	}
	
	@RequestMapping(path = "/get-job-by-job-id/{jobId}", method = RequestMethod.GET)
	public Job getJobByJobId(@PathVariable long jobId) {
		return jobServices.getJobByJobId(jobId);
	}
	
	@RequestMapping(path = "/update-job/{jobId}", method = RequestMethod.PUT)
	public void updateJobByJobId(@PathVariable long jobId, @RequestBody Job job) {
		jobServices.updateJobByJobId(jobId, job);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/delete-job/{jobId}", method = RequestMethod.DELETE)
	public void deleteJobByJobId(@PathVariable int jobId) {
		jobServices.deleteJobByJobId(jobId);
	}
}
