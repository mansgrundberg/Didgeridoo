package Backend;

public class ResponseObject {
	private Channel channel;
	private Song previoussong;
	private Song nextsong;
	private Song song;
	
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
	
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
