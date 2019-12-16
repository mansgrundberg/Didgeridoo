package Backend;

public class Song {
	private String title;
	private String artist;
	private String album;
	private String description;
	private String startTime;
	private String stopTime;
	private String spotifyLink; // Inbäddningsbar länk som visar spotifyspelare för given låt. Bäddas in i <iframe></iframe>
	private String youtubeLink; // Inbäddningsbar länk som visar youtubespelare för given låt. Bäddas in i <iframe></iframe>
	
	
	public Song () {
		
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}

	public String getDescription() {
		return description;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getStopTime() {
		return stopTime;
	}
	
	public void setSpotifyLink(String spotifyLink) {
		this.spotifyLink = spotifyLink;
	}
	
	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}
}
