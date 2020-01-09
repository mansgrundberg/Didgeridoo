package Backend;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.notFound;


import com.google.gson.Gson;


public class APIRunner {
	
	public static void main(String[] args) throws Exception {
		port(5000);
		
		Controller controller = new Controller();
		
		get("/:id", (req, res) -> {
			res.type("application/json");
			res.status(200);
			return new Gson().toJson(controller.fetchResult(Integer.parseInt(req.params(":id"))));
		});
		
		// Returnerar channel id för given radiokanal
		get("/channels/:searchString", (req, res) -> {
			res.type("application/json");
			return null;
		});
		
		notFound((req, res) -> {
			res.type("application/json");
			res.status(404);
			return "Det finns inget här";
		});
	}
}
