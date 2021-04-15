package com.techelevator.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Pothole;

@Component
public class JdbcPotholeDAO implements PotholeDAO {
	
	private JdbcTemplate jdbcTemplate;
	public JdbcPotholeDAO(JdbcTemplate jdbcTemplate) {
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
		String sqlReportPothole = "INSERT INTO potholes ( address, latitude, longitude, severity_id, status_id, date_reported, user_id ) " + ""
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ? )";
		jdbcTemplate.update(sqlReportPothole, newPothole.getAddress(), newPothole.getLatitude(), newPothole.getLongitude(), newPothole.getSeverity(), 
				1, newPothole.getDateReported(), newPothole.getUser_id());
		
	}

	@Override
	public Pothole getPotholeById(long id) {
		String sqlGetPothole = "SELECT * FROM potholes WHERE pothole_id = ?";
		
		Pothole pothole = new Pothole();
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPothole, id);
		
		while(results.next()) {
			pothole = mapRowToPothole(results);
		}
		return pothole;
	}

	@Override
	public void updatePotholeById(long id, Pothole pothole) {
		String sqlUpdatePothole = "UPDATE potholes " + 
				"SET address = ?, latitude = ?, longitude = ?, status_id = ?, severity_id = ?, date_inspected = ?, date_repaired = ?" + 
				"WHERE pothole_id = ? ";
		jdbcTemplate.update(sqlUpdatePothole, pothole.getAddress(), pothole.getLatitude(), pothole.getLongitude(), pothole.getStatusCode(), pothole.getSeverity(), pothole.getDateInspected(), pothole.getDateRepaired(), id);

	}

	@Override
	public void deletePotholeById(long id) {
		String sqlDeletePothole = "DELETE FROM potholes WHERE pothole_id = ?";
		jdbcTemplate.update(sqlDeletePothole, id);
	}
	
	private Pothole mapRowToPothole(SqlRowSet row) {
		Pothole pothole = new Pothole();
		pothole.setId(row.getLong("pothole_id"));
		pothole.setAddress(row.getString("address"));
		pothole.setLatitude(row.getDouble("latitude"));
		pothole.setLongitude(row.getDouble("longitude"));
		pothole.setSeverity(row.getInt("severity_id"));
		pothole.setStatusCode(row.getInt("status_id"));
		if(row.getDate("date_inspected") != null) {
			pothole.setDateInspected(row.getDate("date_inspected").toLocalDate());
		}
		if(row.getDate("date_repaired") != null) {
			pothole.setDateRepaired(row.getDate("date_repaired").toLocalDate());
		}
		pothole.setDateReported(row.getDate("date_reported").toLocalDate());
		pothole.setUser_id(row.getInt("user_id"));
		return pothole;
	}

}
