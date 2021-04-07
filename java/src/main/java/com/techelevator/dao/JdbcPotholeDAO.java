package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Pothole;

@Component
public class JdbcPotholeDAO implements PotholeDAO {
	
	private JdbcTemplate jdbcTemplate;
	public JdbcPotholeDAO(JdbcTemplate jdbcTemplate  ) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Pothole> getFilteredListOfPotholes(String orderBy) {
		List<Pothole> potholeList = new ArrayList<Pothole>();
		
		String sqlInquiry = "SELECT * FROM potholes ORDER BY " + orderBy + " DESC";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlInquiry);
		
		while (results.next()) {
			Pothole pothole = mapRowToPothole(results);
			potholeList.add(pothole);
		}
		
		return potholeList;
	}

	@Override
	public List<Pothole> getAllPotholes() {
		List<Pothole> potholeList = new ArrayList<Pothole>();
		
		String sqlGetAllPotholes = "SELECT * FROM potholes";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllPotholes);
		
		while (results.next()) {
			Pothole pothole = mapRowToPothole(results);
			potholeList.add(pothole);
		}
		
		return potholeList;
	}

	@Override
	public void reportPothole(Pothole newPothole) {
		
	}

	@Override
	public Pothole getPotholeById(long id) {
		String sqlGetPothole = "SELECT * FROM potholes WHERE pothole_id = ?";
		
		return null;
	}

	@Override
	public void updatePotholeById(Pothole updatedPothole, long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePotholeById(long id) {
		// TODO Auto-generated method stub

	}
	
	private Pothole mapRowToPothole(SqlRowSet row) {
		Pothole pothole = new Pothole();
		pothole.setId(row.getLong("pothole_id"));
		pothole.setLatitude(row.getDouble("latitude"));
		pothole.setLongitude(row.getDouble("longitude"));
		pothole.setSeverity(row.getInt("severity_id"));
		pothole.setStatusCode(row.getInt("status_id"));
		//pothole.setDateInspected(row.getDate("date_inspected").toLocalDate());
		//pothole.setDateRepaired(row.getDate("date_repaired").toLocalDate());
		pothole.setDateReported(row.getDate("date_reported").toLocalDate());
		pothole.setUser_id(row.getInt("user_id"));
		return pothole;
	}

}
