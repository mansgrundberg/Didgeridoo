package Backend;

import static spark.Spark.get;
import static spark.Spark.port;

import com.google.gson.Gson;

public class APIRunner {

	public static void main(String[] args) throws Exception {
		port(5000);

		IDList idList = new IDList();
		Controller controller = new Controller(idList);

		/*
		 * Returns the result for every available channel as an array of ResponseObjects.
		 */
		get("/v1/channels", (req, res) -> {
			res.type("application/json");
			res.status(200);
			return new Gson().toJson(controller.fetchAll());
		});

		/*
		 * Returns a ReponseObject for the given id or channel name
		 */
		get("/v1/channels/:channel", (req, res) -> {
			res.type("application/json");
			
			if (!idList.checkId(req.params(":channel"))) {
				res.status(400);
			} else {
				res.status(200);
			}
			
			if (req.params(":channel").matches("[0-9]+")) {
				return new Gson().toJson(controller.fetchResult(Integer.parseInt(req.params(":channel"))));
			} else {
				return new Gson().toJson(controller.fetchResult((idList.getID(req.params(":channel")))));
			}
		});
	}
}
