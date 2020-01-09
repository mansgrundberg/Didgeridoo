package Backend;

import com.google.gson.Gson;

public class Controller {

	/*
	 * Method which fetches the playlist that is currently playing on the choosen radio channel as a ResponseObject. 
	 * The ResponseObject contains the previous song and the next song.
	 * Sets the channel in a ResponseObject as the fetched channel from the SRConsumer with the name of the channel, image and live audio.
	 * Gets the previous and the next song from the ResponseObject and if there currently is a song playing, the current song. 
	 * Then calls the getLinks()-method with the songs as parameters.
	 * Returns the ResponseObject created
	 */
	public ResponseObject fetchResult(int channelID) {
		ResponseObject res = SRConsumer.fetchPlaylist(channelID);
		res.setChannel(SRConsumer.fetchStream(channelID));
		getLinks(res.getPreviousSong());
		getLinks(res.getNextSong());
		if (res.getSong() != null) {
		getLinks(res.getSong());
		}
		return res;
	}

	/*
	 * Calls the SpotifyConsumer fetchResult()-method and the YoutubeConsumer searchVideo()-method, 
	 * with a Song object which is either the previous, next or current song.
	 * Then sets the Song objects Spotify-link and Youtube-link with the returned information.
	 */
	private void getLinks(Song song) {
		song.setSpotifyLink(SpotifyConsumer.fetchResult(song.getTitle(), song.getArtist()));
		song.setYoutubeLink(YoutubeConsumer.searchVideo(song.getTitle(), song.getArtist()));
	}

	public static void main(String[] args) {
		Controller c = new Controller();
		System.out.println(new Gson().toJson(c.fetchResult(220)));
	}

}
