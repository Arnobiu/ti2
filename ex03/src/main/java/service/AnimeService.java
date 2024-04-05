package service;

import java.time.LocalDate;
import spark.Request;
import spark.Response;
import dao.AnimeDAO;
import model.Anime;

public class AnimeService {

    private AnimeDAO animeDAO;

    public AnimeService() {
        animeDAO = new AnimeDAO();
    }

    public Object add(Request request, Response response) {
        String descricao = request.queryParams("descricao");
        int eps = Integer.parseInt(request.queryParams("eps"));
        LocalDate inicio = LocalDate.parse(request.queryParams("inicio"));
        LocalDate fim = LocalDate.parse(request.queryParams("fim"));

        Anime anime = new Anime(0, descricao, eps, inicio, fim);
        animeDAO.add(anime);

        response.status(201); // 201 Created
        return anime.getId();
    }

    public Object get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Anime anime = animeDAO.get(id);
        if (anime != null) {
            return anime;
        } else {
            response.status(404); // 404 Not found
            return "Anime not found.";
        }
    }

    public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        String descricao = request.queryParams("descricao");
        int eps = Integer.parseInt(request.queryParams("eps"));
        LocalDate inicio = LocalDate.parse(request.queryParams("inicio"));
        LocalDate fim = LocalDate.parse(request.queryParams("fim"));

        Anime anime = new Anime(id, descricao, eps, inicio, fim);
        animeDAO.update(anime);

        return id;
    }

    public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        animeDAO.remove(id);
        return id;
    }

    public Object getAll(Request request, Response response) {
        return animeDAO.getAll();
    }
}
