package Backend;

import java.sql.Date;

public class Song {
	private String title;
	private String artist;
	private String album;
	private String description;
	private String starttimeutc;
	private String stoptimeutc;
	private String spotifyLink; // Inbäddningsbar länk som visar spotifyspelare för given låt. Bäddas in i <iframe></iframe>
	private String youtubeLink; // Inbäddningsbar länk som visar youtubespelare för given låt. Bäddas in i <iframe></iframe>
	
	public Song () {
		
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}
	
	public void setSpotifyLink(String spotifyLink) {
		this.spotifyLink = spotifyLink;
	}
	
	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}
	
	public String getSpotifyLink() {
		return this.spotifyLink;
	}
	
	public String getYoutubeLink() {
		return this.youtubeLink;
	}
	
	public String getStarttimeutc() {
		return this.starttimeutc;
	}
}
