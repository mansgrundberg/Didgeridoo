package Backend;

public class Controller {

	public ResponseObject fetchResult(int channelID) {
		ResponseObject res = SRConsumer.fetchPlaylist(channelID);
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
		System.out.println(song.getTitle() + " " + song.getSpotifyLink() + " " + song.getYoutubeLink() + " " + song.getStarttimeutc()); // Testing 123
	}

	public static void main(String[] args) {
		Controller c = new Controller();
		c.fetchResult(220);
	}

}
