package Backend;

import static spark.Spark.get;
import static spark.Spark.port;


public class APIRunner {
	public static void main(String[] args) throws Exception {
		port(5000);
		
		get("/:id", (req, res) -> {
			
			return "";
		});
	}
}
