package it.polito.tdp.rivers.simulator;

import it.polito.tdp.rivers.model.Flow;

public class Event {
	
	public enum EventType {
		USCITA, //evento flusso in uscita
		ECCESSOENTRATA, //fin > fout
		TRACIMAZIONE //C>Q		
	}
	
	private EventType eventType ;
	private Flow flow ;
	
	public Event(EventType etype, Flow flow) {
		this.setEventType(etype) ;
		this.setFlow(flow) ;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}
	
}
