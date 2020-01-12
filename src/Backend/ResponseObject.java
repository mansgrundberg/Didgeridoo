package Backend;

/*
 * The API response object. 
 */

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
	
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
