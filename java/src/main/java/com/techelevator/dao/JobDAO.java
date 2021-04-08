package com.techelevator.dao;
import java.util.List;

import com.techelevator.model.Job;

public interface JobDAO {
	public List<Job> getFilteredListOfJobs(String orderBy);
	
	public List<Job> getAllJobs();
	
	public void createJob(Job newJob);
	
	public Job getJobByJobId(long jobId);
	
	public void updateJobByJobId(long jobId, Job job);
	
	public void deleteJobByJobId(long jobId);
	
}
