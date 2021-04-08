package com.techelevator.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.JobDAO;
import com.techelevator.model.Job;

@Service
public class JobServices {
	private JobDAO jobDAO;
	public JobServices(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}
	public List<Job> getFilteredListOfJobs(String orderBy){
		return jobDAO.getFilteredListOfJobs(orderBy);
	}
	public List<Job> getAllJobs(){
		return jobDAO.getAllJobs();
	}
	public void createJob(Job newJob) {
		jobDAO.createJob(newJob);
	}
	public Job getJobByJobId(long jobId) {
		return jobDAO.getJobByJobId(jobId);
	}
	public void updateJobByJobId(long jobId, Job job) {
		jobDAO.updateJobByJobId(jobId, job);
	}
	public void deleteJobByJobId(long jobId) {
		jobDAO.deleteJobByJobId(jobId);
	}
}
