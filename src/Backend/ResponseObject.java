package Backend;

public class ResponseObject {
	private Song previousSong;
	private Song nextSong;
	
	public ResponseObject() {
		 
	}
	
	public Song getPreviousSong() {
		return this.previousSong;
	}
	
	public Song getNextSong() {
		return this.nextSong;
	}

	public void setPreviousSong(Song previousSong) {
		this.previousSong = previousSong;
	}

	public void setNextSong(Song nextSong) {
		this.nextSong = nextSong;
	}

}
