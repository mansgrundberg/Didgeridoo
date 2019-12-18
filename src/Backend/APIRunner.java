package Backend;

import static spark.Spark.get;
import static spark.Spark.port;

import com.google.gson.Gson;


public class APIRunner {
	
	public static void main(String[] args) throws Exception {
		port(5000);
		
		Controller controller = new Controller();
		
		get("/:id", (req, res) -> {
			res.type("application/json");
			return new Gson().toJson(controller.fetchResult(Integer.parseInt(req.params(":id"))));
		});
	}
}
