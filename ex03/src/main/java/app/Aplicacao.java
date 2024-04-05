package app;

import static spark.Spark.*;
import service.AnimeService;

public class Aplicacao {

	private static AnimeService animeService = new AnimeService();

	public static void main(String[] args) {
		port(6789);

		enableCORS("*", "*", "*");

		post("/anime", (request, response) -> animeService.add(request, response));
		get("/anime/:id", (request, response) -> animeService.get(request, response));
		put("/anime/:id", (request, response) -> animeService.update(request, response));
		delete("/anime/:id", (request, response) -> animeService.remove(request, response));
		get("/anime", (request, response) -> animeService.getAll(request, response));
	}

	private static void enableCORS(final String origin, final String methods, final String headers) {
		before((request, response) -> {
			response.header("Access-Control-Allow-Origin", origin);
			response.header("Access-Control-Allow-Methods", methods);
			response.header("Access-Control-Allow-Headers", headers);
			response.header("Access-Control-Allow-Credentials", "true");
		});
		options("/*", (request, response) -> {
			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}
			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}
			return "OK";
		});
	}
}
