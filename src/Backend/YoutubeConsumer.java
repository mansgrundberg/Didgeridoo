package Backend;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.options.Options;

/*
 * Handles all the consumption of the Youtube API.
 */

public class YoutubeConsumer {
	private static final String key = "AIzaSyCOHy5891gKLEv-KnOzC15k-z0tt6vZbWQ";
	private static final String embedLink = "https://www.youtube.com/embed/";

	// Fetches the search result for the given track title and artist. Returns a link to an embedabble youtube player for the given video.
	public static String searchVideo(String track, String artist) {
		try {
			Options.refresh();
			HttpResponse<JsonNode> response = Unirest.get("https://www.googleapis.com/youtube/v3/search?")
					.header("Accept", "application/json").queryString("part", "snippet").queryString("maxResults", 1)
					.queryString("q", track + " " + artist).queryString("type", "video").queryString("key", key)
					.queryString("order", "viewCount").asJson();

			JsonNode json = response.getBody();
			JSONObject obj = json.getObject();
			JSONArray items = obj.getJSONArray("items");
			Unirest.shutdown();
			return embedLink + items.getJSONObject(0).getJSONObject("id").getString("videoId");
		} catch (Exception e) {
			System.out.println(e + " Youtube");
			return "null";
		}
	}
}
