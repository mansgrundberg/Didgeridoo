package Backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IDList2 {
	private HashMap<String, Integer> map;
	
	public IDList2() {
		fillMap();
	}
	
	private void fillMap() {
		map = new HashMap<String, Integer>();
		map.put("p1", 132);
		map.put("p2", 163);
		map.put("p3", 164);
		map.put("p4halland", 220);
		map.put("p4göteborg", 212);
		map.put("p4gotland", 205);
		map.put("p4dalarna", 223);
		map.put("p4jämtland", 200);
		map.put("p4gävleborg", 210);
		map.put("p4blekinge", 213);
	}
	
	public int getID(String searchString) {
		return map.get(searchString.toLowerCase().replaceAll("\\s", ""));
	}
	
	public List<Integer> getIDs() {
		return (List<Integer>) map.values();
	}

}
