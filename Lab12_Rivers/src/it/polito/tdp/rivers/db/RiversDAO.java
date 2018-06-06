package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.RiverIdMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

public class RiversDAO {

	public List<River> getAllRivers(RiverIdMap riverIdMap) {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				River r = new River(res.getInt("id"), res.getString("name"));
				rivers.add(riverIdMap.get(r));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}

	public List<Flow> getFlows(River r, RiverIdMap riverIdMap) {
		final String sql = "SELECT * " + 
				"FROM flow " + 
				"WHERE river = ? ";
		
		List<Flow> result = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();
			River river = riverIdMap.get(r);
			
			while (res.next()) {
				
				LocalDate date = res.getDate("day").toLocalDate();
				
				result.add(new Flow(date, res.getDouble("flow"), river));
				result.get(result.size()-1).setId(res.getInt("id"));
			}
			
			river.setFlows(result);
			
			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return result;
	}

	/*private River getRiver(int id) {
		final String sql = "SELECT * FROM river WHERE id = ?";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();

			if (res.next()) {
				River river = new River();
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return null;
	}*/
}
