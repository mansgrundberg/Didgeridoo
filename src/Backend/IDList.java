package Backend;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * This class connects channel IDs with their corresponding channel names. 
 * Makes it possible to call the API GET-methods with channel names instead of the SR channel-ids.
 */

public class IDList {
	private HashMap<String, Integer> map;
	
	public IDList() {
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
		map.put("p4", 212);
	}
	
	public int getID(String searchString) {
		return map.get(searchString.toLowerCase().replaceAll("\\s", ""));
	}
	
	public ArrayList<Integer> getAll() {
		return new ArrayList<Integer>(map.values());
	}
	
	/*
	 * Check if channel name or ID is present
	 */
	public boolean checkId(String channel) {
		if (map.containsKey(channel.toLowerCase().replaceAll("\\s", ""))) {
			return true;
		} else if (map.values().contains(Integer.parseInt(channel))) {
			return true;
		}
		return false;
	}
}
