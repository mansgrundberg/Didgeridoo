package Backend;

import static spark.Spark.get;
import static spark.Spark.port;

import com.google.gson.Gson;

public class APIRunner {

	public static void main(String[] args) throws Exception {
		port(5000);

		IDList idList = new IDList();
		Controller controller = new Controller(idList);

		// Returnera array med resultat för alla kanaler
		get("/v1/", (req, res) -> {
			res.type("application/json");
			res.status(200);
			return new Gson().toJson(controller.fetchAll());
		});

		// Returnerar resultat för givet id
		get("/v1/:id", (req, res) -> {
			res.type("application/json");
			res.status(200);
			return new Gson().toJson(controller.fetchResult(Integer.parseInt(req.params(":id"))));
		});

		// TODO: Returnerar channel id för given radiokanal
		get("/v1/search/:searchString", (req, res) -> {
			res.type("application/json");
			res.status(200);
			return new Gson().toJson(controller.fetchResult((idList.getID(req.params(":searchString")))));
		});
	}
}
