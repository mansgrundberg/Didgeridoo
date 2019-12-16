package Backend;

import org.json.JSONObject;

public class Controller {
	
	public void fetchResult(int channelID) {
		JSONObject playlist = SRConsumer.fetchPlaylist(channelID);
		ResponseObject res = initResponse(playlist);
	}
	
	private ResponseObject initResponse(JSONObject playlist) {
		ResponseObject res = new ResponseObject();
		res.setPreviousSong(addSong(playlist.getJSONObject("previoussong")));
		res.setNextSong(addSong(playlist.getJSONObject("nextsong")));
		return res;
	}
	
	private Song addSong(JSONObject song) {
		Song s = new Song();
		s.setTitle(song.getString("title"));
		s.setArtist(song.getString("artist"));
		s.setAlbum(song.getString("albumname"));
		s.setDescription(song.getString("description"));
		s.setStartTime(song.getString("starttimeutc"));
		s.setStopTime(song.getString("stoptimeutc"));
		return s;
	}
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.fetchResult(163);
	}

}
