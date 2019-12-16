package Backend;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;

public class SpotifyConsumer {

    private static void fetchResult(String client_id, String client_secret) {
        try {
            HttpResponse<JsonNode> response = Unirest.post("https://accounts.spotify.com/api/token")
                    .header("Authorization","Basic <base64 encoded " + client_id + ":"+client_secret+">")
                    .field("grant_type","client_credentials")
                    .asJson();
            System.out.println(response.getBody());
            Unirest.shutdown();
        } catch (UnirestException | IOException e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) {
        String client_id = "fb435518dac2475388699e465c3ad740";
        String client_secret = "e4f91cb42afc40cbba822bd73b81801b";
        fetchResult(client_id,client_secret);
    }

}
