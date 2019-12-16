package Backend;

public class Controller {

	public ResponseObject fetchResult(int channelID) {
		ResponseObject res = SRConsumer.fetchPlaylist(channelID);
		getLinks(res.getPreviousSong());
		getLinks(res.getNextSong());
		return null;
	}

	private void getLinks(Song song) {
		song.setSpotifyLink(SpotifyConsumer.fetchResult(song.getTitle(), song.getArtist()));
		song.setYoutubeLink(YoutubeConsumer.searchVideo(song.getTitle(), song.getArtist()));
		System.out.println(song.getTitle() + " " + song.getSpotifyLink() + " " + song.getYoutubeLink());
	}

	public static void main(String[] args) {
		Controller c = new Controller();
		c.fetchResult(212);
	}

}
