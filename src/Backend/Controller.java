package Backend;

import com.google.gson.Gson;

public class Controller {

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

	private void getLinks(Song song) {
		song.setSpotifyLink(SpotifyConsumer.fetchResult(song.getTitle(), song.getArtist()));
		song.setYoutubeLink(YoutubeConsumer.searchVideo(song.getTitle(), song.getArtist()));
	}

	public static void main(String[] args) {
		Controller c = new Controller();
		System.out.println(new Gson().toJson(c.fetchResult(220)));
	}

}
