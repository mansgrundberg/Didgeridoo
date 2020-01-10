package Backend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

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
	
	public void convertDates() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("CET"));
		
		calendar.setTimeInMillis(Long.parseLong(starttimeutc.replaceAll("[^0-9]", "")));
		starttimeutc = format.format(calendar.getTime());
		
		calendar.setTimeInMillis(Long.parseLong(stoptimeutc.replaceAll("[^0-9]", "")));
		stoptimeutc = format.format(calendar.getTime());
	}
}
