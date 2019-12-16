package Backend;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.options.Options;

import java.io.IOException;

import org.json.JSONObject;

public class SpotifyConsumer {

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
    
    private static void fetchResult(String auth) {
    	try {
    		Options.refresh();
    		System.out.println("HEj");
    	HttpResponse<JsonNode> response = Unirest.get("https://api.spotify.com/v1/tracks/2TpxZ7JUBn3uw46aR7qd6V")
    			.header("Accept", "application/json")
        		.header("Authorization", "Bearer " + auth)
                .asJson();
    	System.out.println("lol");
    	System.out.println(response.getBody() + "hej");
    	Unirest.shutdown();
    	} catch (Exception e) {
			System.out.println(e);
		}
    	
    }

    public static void main(String[] args) {
        String client_id = "fb435518dac2475388699e465c3ad740";
        String client_secret = "e4f91cb42afc40cbba822bd73b81801b";
        String token  = fetchToken(client_id,client_secret);
        fetchResult(token);
    }

}
