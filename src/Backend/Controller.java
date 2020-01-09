package Backend;

public class Controller {

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
}
