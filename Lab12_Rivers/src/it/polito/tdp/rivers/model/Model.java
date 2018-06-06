package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;
import javafx.scene.control.TextField;

public class Model {
	
	private RiversDAO dao ;
	private RiverIdMap riverIdMap ;
	
	public Model() {
		riverIdMap = new RiverIdMap();
	}

	public List<River> getRivers() {
		List<River> rivers = new ArrayList<>();
		dao = new RiversDAO();
		
		rivers = dao.getAllRivers(riverIdMap);
		
		if(!rivers.isEmpty())
			return rivers;
		
		return new ArrayList<River>();
	}

	public List<Flow> getFlows(String river) {
		River r = riverIdMap.get(river) ;
		if(r!=null) {
			List<Flow> flows = dao.getFlows(r, riverIdMap) ;
			return flows ;
		}
		return null;
	}

	public double getAvgFlows(List<Flow> flows) {
		
		double avg = 0;
		if(!flows.isEmpty()) {
			
			for(Flow f : flows)
				avg += f.getFlow();
			
			avg /= flows.size();
		}
		return avg;
	}

	public void simula(int k, String river, double fmed) {
		
		River r = riverIdMap.get(river) ;

		double q = calcolaQ(k, r, fmed);
		
		
		
	}

	private double calcolaQ(int k, River r, double fmed) {
		double fmedgg = fmed*60*60*24;
				
		double q = k*fmedgg*30;
		
		return q;
	}

	
	
}
