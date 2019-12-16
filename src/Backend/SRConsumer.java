package Backend;

import java.io.IOException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SRConsumer {

	// Change method to return suitable object
	private static void fetchResult(int channelID) {
		try {
			HttpResponse<JsonNode> response = Unirest.get("http://api.sr.se/api/v2/channels/132")
					//.queryString("channelid", channelID)
					.queryString("format", "json")
					.asJson();
			System.out.println(response.getBody());
			Unirest.shutdown();
		} catch (UnirestException | IOException e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		fetchResult(132);
	}
}