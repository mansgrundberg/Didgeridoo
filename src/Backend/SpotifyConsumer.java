package Backend;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.options.Options;

import org.json.JSONObject;

/*
 * This class handles all the consumption of the Spotify Web API.
 */

public class SpotifyConsumer {
	private static final String embedLink = "https://open.spotify.com/embed/track/";
	private static final String client_id = "YOUR SPOTIFY CLIENT ID";
	private static final String client_secret = "YOUR SPOTIFY CLIENT SECRET";

	// Trades id and password for an authorization token
	private static String fetchToken(String client_id, String client_secret) {
		try {
			Options.refresh();
			HttpResponse<JsonNode> response = Unirest.post("https://accounts.spotify.com/api/token")
					.basicAuth(client_id, client_secret)
					.field("grant_type","client_credentials")
					.asJson();

			JsonNode json = response.getBody();
			JSONObject obj = json.getObject();

			return obj.getString("access_token");
		} catch (UnirestException e) {
			return null;
		}
	}

	/*
	 * Fetches the search result for the given track and artist, returns an link to an embedabble spotify player
	 */
	public static String fetchResult(String track, String artist) {
		try {
			Options.refresh();
			String token = fetchToken(client_id, client_secret);
			HttpResponse<JsonNode> response = Unirest.get("https://api.spotify.com/v1/search?q=" + "track:" + track.replaceAll(" ", "%20") + "%20artist:" + artist.replaceAll(" ", "%20"))
					.header("Accept", "application/json")
					.header("Authorization", "Bearer " + token)
					.queryString("type", "track")
					.queryString("limit", 1)
					.asJson();
			
			JsonNode json = response.getBody();
			JSONObject obj = json.getObject();
			Unirest.shutdown();
			return embedLink + obj.getJSONObject("tracks").getJSONArray("items").getJSONObject(0).getString("id");
		} catch (Exception e) {
			System.out.println(e + " Spotify");
			return null;
		}
	}
}
