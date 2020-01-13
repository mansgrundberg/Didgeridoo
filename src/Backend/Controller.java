package Backend;

import java.util.ArrayList;

public class Controller {
	private IDList idList;
	
	public Controller (IDList idList) {
		this.idList = idList;
	}

	/*
	 * Method which fetches the playlist that is currently playing on the chosen
	 * radio channel and returns it as a ResponseObject.
	 */
	public ResponseObject fetchResult(int channelID) {
		ResponseObject res = SRConsumer.fetchPlaylist(channelID);
		res.setChannel(SRConsumer.fetchStream(channelID));
		getLinks(res);
		convertDates(res);
		return res;
	}

	/*
	 * Fetches links for all Songs present in the given ResponseObject.
	 */
	private void getLinks(ResponseObject res) {
		if (res.getPreviousSong() != null) {
		getLink(res.getPreviousSong());
		}
		
		if (res.getNextSong() != null) {
		getLink(res.getNextSong());
		}

		if (res.getSong() != null) {
			getLink(res.getSong());
		}
	}

	/*
	 * Fetches both the Spotify link and the Youtube link for the given Song.
	 */
	
	private void getLink(Song song) {
		song.setSpotifyLink(SpotifyConsumer.fetchResult(song.getTitle(), song.getArtist()));
		song.setYoutubeLink(YoutubeConsumer.searchVideo(song.getTitle(), song.getArtist()));
	}

	private void convertDates(ResponseObject res) {
		if (res.getPreviousSong() != null) {
		res.getPreviousSong().convertDates();
		}
		
		if (res.getNextSong() != null) {
		res.getNextSong().convertDates();
		}

		if (res.getSong() != null) {
			res.getSong().convertDates();
		}
	}
	
	/*
	 * Calls fetchResult() for every available channel, returning an array of ResponseObjects. 
	 */
	
	public ResponseObject[] fetchAll() {
		ArrayList<Integer> temp = idList.getAll();
		ResponseObject[] res = new ResponseObject[temp.size()];
		
		for (int i = 0; i < temp.size(); i++) {
			res[i] = fetchResult(temp.get(i));
		}
		return res;
	}
}
