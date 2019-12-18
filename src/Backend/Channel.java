package Backend;

public class Channel {
	private String image;
	private String liveaudio;
	
	public Channel(String image, String liveaudio) {
		this.image = image;
		this.liveaudio = liveaudio;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLiveaudio() {
		return liveaudio;
	}

	public void setLiveaudio(String liveaudio) {
		this.liveaudio = liveaudio;
	}

}
