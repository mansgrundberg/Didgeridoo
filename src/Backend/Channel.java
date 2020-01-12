package Backend;

/*
 * Represents a channel.
 */

public class Channel {
	private String name;
	private String image;
	private String liveaudio; // Ljudlänk till livestreamning av kanalen
	
	public Channel(String name, String image, String liveaudio) {
		this.name = name;
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
	
	public void setName(String name) {
		this.name = name;
	}

}
