package Backend;

import java.io.IOException;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.options.Options;

/*
 * Handles all consumption of the SR API.
 */

public class SRConsumer {

	/*
	 * Method which fetches the chosen channels name, logo and an URL to the live stream. 
	 * Creates a Channel object with this information and returns it.
	 */
	public static Channel fetchStream(int channelID) {
		try {
			Options.refresh();
			HttpResponse<JsonNode> response = Unirest.get("http://api.sr.se/api/v2/channels/" + channelID)
					.queryString("format", "json")
					.asJson();

			JsonNode json = response.getBody();
			JSONObject obj = json.getObject().getJSONObject("channel");
			Unirest.shutdown();
			return new Channel(obj.getString("name"), obj.getString("image"), obj.getJSONObject("liveaudio").getString("url"));
		} catch (UnirestException | IOException e) {
			System.out.println(e);
			return null;
		}
	}

	/*
	 * Method which fetches a playlist from the specified channel with the previous, next song and if there is a currently playing song, the current song as well.
	 * Returns the information as a ResponsObject.
	 */
	public static ResponseObject fetchPlaylist(int channelID) {
		try {
			Options.refresh();
			HttpResponse<JsonNode> response = Unirest.get("http://api.sr.se/api/v2/playlists/rightnow?")
					.queryString("channelid", channelID)
					.queryString("format", "json")
					.asJson();
			
			JsonNode json = response.getBody();
			Unirest.shutdown();
			return new Gson().fromJson(json.getObject().getJSONObject("playlist").toString(), ResponseObject.class);
		} catch (UnirestException | IOException e) {
			return null;
		}
	}
}