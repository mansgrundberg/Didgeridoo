package Backend;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.options.Options;

public class YoutubeConsumer {
	private static final String key = "AIzaSyA-suXDusGt8FrwpFXpackliwj2fK0wjJ4";
	private static final String embedLink = "https://www.youtube.com/embed/";

	// Returns and embeddable player for the most popular video given the search string
	private static String searchVideo(String track, String artist) {
		try {
			Options.refresh();
			HttpResponse<JsonNode> response = Unirest.get("https://www.googleapis.com/youtube/v3/search?")
					.header("Accept", "application/json").queryString("part", "snippet").queryString("maxResults", 1)
					.queryString("q", track + artist).queryString("type", "video").queryString("key", key)
					.queryString("order", "viewCount").asJson();

			JsonNode json = response.getBody();
			JSONObject obj = json.getObject();
			JSONArray items = obj.getJSONArray("items");
			Unirest.shutdown();
			return embedLink + items.getJSONObject(0).getJSONObject("id").getString("videoId");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static void main(String[] args) {
		String id = searchVideo("Smoke on the water", "Deep Purple");
		System.out.println(id);
	}

}
