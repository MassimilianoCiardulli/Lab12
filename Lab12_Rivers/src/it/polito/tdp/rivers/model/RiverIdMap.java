package it.polito.tdp.rivers.model;

import java.util.HashMap;
import java.util.Map;

public class RiverIdMap {
	private Map<Integer, River> map ;
	
	public RiverIdMap() {
		map = new HashMap<>();
	}
	
	public River get(int id) {
		return map.get(id);
	}
	
	public River get(River river) {
		River old = map.get(river.getId());
		if(old == null) {
			map.put(river.getId(), river);
			return river ;
		} else {
			return old ;
		}
	}
	
	public River get(String river) {
		for(River c:map.values()) {
			if(c.getName().equals(river))
				return c ;
		}
		return null ;
	}
	
	public void put(int id, River object) {
		map.put(id, object);
	}
}
