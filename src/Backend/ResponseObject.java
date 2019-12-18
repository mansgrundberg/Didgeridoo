package Backend;

public class ResponseObject {
	private Song previoussong;
	private Song nextsong;
	private Song song;
	private String stream;
	
	public ResponseObject() {
		 
	}
	
	public Song getPreviousSong() {
		return this.previoussong;
	}
	
	public Song getNextSong() {
		return this.nextsong;
	}
	
	public Song getSong() {
		return this.song;
	}
	
	// För testning. Kan huttas senare.
	public void setPreviousSong(Song previousSong) {
		this.previoussong = previousSong;
	}
	
	// För testning. Kan huttas senare.
	public void setNextSong(Song nextSong) {
		this.nextsong = nextSong;
	}
	
	public void setStream(String stream) {
		this.stream = stream;
	}
	
	public String getStream() {
		return this.stream;
	}
}
