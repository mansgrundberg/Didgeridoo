package Backend;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import com.google.gson.Gson;


public class APIRunner {
	public static void main(String[] args) throws Exception {
		port(5000);
		
		get("/:id", (req, res) -> {
			
			return "";
		});
	}
}
