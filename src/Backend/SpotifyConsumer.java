package Backend;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.options.Options;

import java.io.IOException;

import org.json.JSONObject;

public class SpotifyConsumer {
	private static final String embedLink = "https://open.spotify.com/embed/track/";

    private static String fetchToken(String client_id, String client_secret) {
        try {
            HttpResponse<JsonNode> response = Unirest.post("https://accounts.spotify.com/api/token")
            		.basicAuth(client_id, client_secret)
                    .field("grant_type","client_credentials")
                    .asJson();
            JsonNode json = response.getBody();
            System.out.println(response.getBody());
            JSONObject obj = json.getObject();
            Unirest.shutdown();
            return obj.getString("access_token");
        } catch (UnirestException | IOException e) {
        	return null;
        }
    }
    
    // Fixa till söksträngen den e katastrof. Returnerar länk till player som går att bädda in i html 
    private static String fetchResult(String auth, String track, String artist) {
    	try {
    		Options.refresh();
    		HttpResponse<JsonNode> response = Unirest.get("https://api.spotify.com/v1/search?q=" + "track:" + track.replaceAll(" ", "%20") + "%20artist:" + artist.replaceAll(" ", "%20"))
    			.header("Accept", "application/json")
        		.header("Authorization", "Bearer " + auth)
        		.queryString("type", "track")
        		.queryString("limit", 1)
                .asJson();
    	
    	JsonNode json = response.getBody();
    	JSONObject obj = json.getObject();
    	Unirest.shutdown();
    	return embedLink + obj.getJSONObject("tracks").getJSONArray("items").getJSONObject(0).getString("id");
    	} catch (Exception e) {
			System.out.println(e);
			return null;
		}
    }

    public static void main(String[] args) {
        String client_id = "fb435518dac2475388699e465c3ad740";
        String client_secret = "e4f91cb42afc40cbba822bd73b81801b";
        String token  = fetchToken(client_id,client_secret);
        System.out.println(fetchResult(token, "Folsom Prison Blues", "Johnny Cash"));
    }

}
