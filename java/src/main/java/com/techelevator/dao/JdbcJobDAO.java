package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Job;

@Component
public class JdbcJobDAO implements JobDAO {
	
	private JdbcTemplate jdbcTemplate;
	public JdbcJobDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Job> getFilteredListOfJobs(String orderBy) {
		List<Job> jobsList = new ArrayList<Job>();
		
		String sqlInquiry = "SELECT * FROM jobs ORDER BY " + orderBy + " DESC";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlInquiry);
		
		while(results.next()) {
			Job job = mapRowToJob(results);
			jobsList.add(job);
		}
		return jobsList;
	}

	@Override
	public List<Job> getAllJobs() {
		List<Job> jobsList = new ArrayList<Job>();
		
		String sqlInquiry = "SELECT * FROM jobs";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlInquiry);
		
		while(results.next()) {
			Job job = mapRowToJob(results);
			jobsList.add(job);
		}
		return jobsList;
	}

	@Override
	public void createJob(Job newJob) { 
		String sqlInquiry = "INSERT INTO jobs ( job_id, pothole_id, employee_id, job_status, opened_date, closed_date ) "
							+ "VALUES ( ?, ?, ?, ?, ?, ? )";
		jdbcTemplate.update(sqlInquiry, newJob.getJobId(), newJob.getPotholeId(), newJob.getEmployeeId(),
				newJob.getJobStatus(), newJob.getOpenedDate(), newJob.getClosedDate());
	}

	@Override
	public Job getJobByJobId(long jobId) {
		String sqlInquiry = "SELECT * FROM jobs WHERE job_id = ?";
		
		Job job = new Job();
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlInquiry, jobId);
		
		while(results.next()) {
			job = mapRowToJob(results);
		}
		return job;
	}

	@Override
	public void updateJobByJobId(long jobId, Job job) {
		String sqlInquiry = "UPDATE jobs " +
				"SET employee_id = ?, job_status = ?, closed_date = ? " +
				"WHERE job_id = ?";
		jdbcTemplate.update(sqlInquiry, job.getEmployeeId(), job.getJobStatus(), job.getClosedDate(), jobId);

	}

	@Override
	public void deleteJobByJobId(long jobId) {
		String sqlInquiry = "DELETE FROM jobs WHERE job_id = ?";
		jdbcTemplate.update(sqlInquiry, jobId);

	}
	
	private Job mapRowToJob(SqlRowSet row) {
		Job job = new Job();
		job.setClosedDate(row.getDate("closed_date").toLocalDate());
		job.setOpenedDate(row.getDate("opened_date").toLocalDate());
		job.setEmployeeId(row.getInt("employee_id"));
		job.setJobId(row.getInt("job_id"));
		job.setJobStatus(row.getInt("job_status"));
		job.setPotholeId(row.getInt("pothole_id"));
		return job;
	}

}
