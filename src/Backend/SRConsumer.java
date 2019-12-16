package Backend;

import java.io.IOException;


import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SRConsumer {

	/*
	 * CHANNEL IDS:
	 * P1: 132
	 * P2: 163
	 * P3: 164
	 * P4 Blekinge: 213
	 * P4 Dalarna: 223
	 * P4 Gotland: 205
	 * P4 Gävleborg: 210
	 * P4 Göteborg: 212
	 * P4 Halland: 220
	 * P4 Jämtland: 200
	 */

	// Change method to return suitable object
	public static ResponseObject fetchPlaylist(int channelID) {
		try {
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