package it.polito.tdp.rivers.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;

public class Simulator {
	
	private List<Event> event ;
	private double Q ; // CAPIENZA TOTALE 
	private double C ; // OCCUPAZIONE INIZIALE
	private double fout ;
	private double fmed ;
	private double fin ;
	private double fout_min; // flusso in uscita minimo garantito
	private double Cmed ; // OCCUPAZIONE MEDIA
	
	private List<Flow> flows ;
	
	public Simulator(double Q, double fmed, List<Flow> flows) {
		this.Q = Q ;
		this.fmed = fmed ;
		this.flows = flows ;
		Collections.sort(flows);
		
		C = Q/2 ;
		fout_min = 0.8*fmed ;
		
		Cmed = 0;
		
		this.event = new ArrayList<>();
	}
	
	public void addEvent(Event e) {
		this.event.add(e) ;
	}
	
	public void run() {
		while(!event.isEmpty())
			simulate();
	}

	private void simulate() {
		
	}
}
