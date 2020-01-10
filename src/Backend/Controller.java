package Backend;

import java.util.ArrayList;
import java.util.List;

public class Controller {
	private IDList idList;
	
	public Controller (IDList idList) {
		this.idList = idList;
	}

	/*
	 * Method which fetches the playlist that is currently playing on the chosen
	 * radio channel as a ResponseObject. The ResponseObject contains the previous
	 * song and the next song. Sets the channel in a ResponseObject as the fetched
	 * channel from the SRConsumer with the name of the channel, image and live
	 * audio. Gets the previous and the next song from the ResponseObject and if
	 * there currently is a song playing, the current song. Then calls the
	 * getLinks() and convertDates-method with the songs as parameters. Returns the
	 * ResponseObject created
	 */
	public ResponseObject fetchResult(int channelID) {
		ResponseObject res = SRConsumer.fetchPlaylist(channelID);
		res.setChannel(SRConsumer.fetchStream(channelID));
		getLinks(res);
		convertDates(res);
		return res;
	}

	private void getLinks(ResponseObject res) {
		getLink(res.getPreviousSong());
		getLink(res.getNextSong());

		if (res.getSong() != null) {
			getLink(res.getSong());
		}
	}

	/*
	 * Calls the SpotifyConsumer fetchResult()-method and the YoutubeConsumer
	 * searchVideo()-method, with a Song object which is either the previous, next
	 * or current song. Then sets the Song objects Spotify-link and Youtube-link
	 * with the returned information.
	 */
	private void getLink(Song song) {
		song.setSpotifyLink(SpotifyConsumer.fetchResult(song.getTitle(), song.getArtist()));
		song.setYoutubeLink(YoutubeConsumer.searchVideo(song.getTitle(), song.getArtist()));
	}

	private void convertDates(ResponseObject res) {
		res.getPreviousSong().convertDates();
		res.getNextSong().convertDates();

		if (res.getSong() != null) {
			res.getSong().convertDates();
		}
	}
	
	public ResponseObject[] fetchAll() {
		ArrayList<Integer> temp = idList.getAll();
		System.out.println(temp.size());
		System.out.println(temp.get(1));
		ResponseObject[] res = new ResponseObject[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i));
			res[i] = fetchResult(temp.get(i));
		}
		System.out.println("Returnerar");
		return res;
	}
}
