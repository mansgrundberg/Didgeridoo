package Backend;

public class ResponseObject {
	private Song previoussong;
	private Song nextsong;
	
	public ResponseObject() {
		 
	}
	
	public Song getPreviousSong() {
		return this.previoussong;
	}
	
	public Song getNextSong() {
		return this.nextsong;
	}

	public void setPreviousSong(Song previousSong) {
		this.previoussong = previousSong;
	}

	public void setNextSong(Song nextSong) {
		this.nextsong = nextSong;
	}

}
