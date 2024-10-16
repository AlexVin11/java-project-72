package hexlet.code.controller;

import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import repository.UrlCheckRepository;
import repository.UrlRepository;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlController {
    public static void index(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var page = new UrlsPage(urls);
        String flash = ctx.consumeSessionAttribute("flash");
        page.setFlash(flash);
        String flashType = ctx.consumeSessionAttribute("flashType");
        page.setFlashType(flashType);
        ctx.render("urls/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("Url not found"));
        var page = new UrlPage(url);
        ctx.render("urls/show.jte", model("page", page));
    }

    public static void add(Context ctx) throws SQLException,
            MalformedURLException,
            URISyntaxException,
            IllegalArgumentException {
        var name = ctx.formParam("url");
        try {
            URL absoluteUrl = new URI(name).toURL();
            String schema = absoluteUrl.toURI().getScheme();
            String authority = absoluteUrl.toURI().getAuthority();
            Url url = new Url(schema + "://" + authority);
            Optional<Url> foundedUrl = UrlRepository.findByName(url.getName());
            if (foundedUrl.isEmpty()) {
                UrlRepository.save(url);
                ctx.sessionAttribute("flash", "Страница успешно добавлена");
                ctx.sessionAttribute("flashType", "alert-success");
            } else {
                ctx.sessionAttribute("flash", "Страница уже существует");
                ctx.sessionAttribute("flashType", "alert-info");
            }
            ctx.redirect(NamedRoutes.urlsPath());
        } catch (URISyntaxException | MalformedURLException | IllegalArgumentException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flashType", "alert-danger");
            ctx.redirect(NamedRoutes.rootPath());
        }
    }

    public static void doCheck(Context ctx) throws SQLException, UnirestException {
        var urlId = ctx.pathParamAsClass("id", Long.class).get();
        Url url = UrlRepository.findById(urlId)
                .orElseThrow(() -> new NotFoundResponse("Url not found"));
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url.getName()).asJson();
            int statusCode = jsonResponse.getStatus();
            String h1 = jsonResponse.getBody().getObject().getString("h1");
            String title = jsonResponse.getBody().getObject().getString("title");
            String description = jsonResponse.getBody().getObject().getString("description");
            UrlCheck urlCheck = new UrlCheck();
            urlCheck.setStatusCode(statusCode);
            urlCheck.setH1(h1);
            urlCheck.setTitle(title);
            urlCheck.setDescription(description);
            UrlCheckRepository.save(urlCheck, url);
        } catch (UnirestException e) {
            throw new UnirestException(e);
        }
    }
}
