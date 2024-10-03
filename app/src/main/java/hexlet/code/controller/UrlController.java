package hexlet.code.controller;

import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import repository.UrlRepository;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlController {
    public static void show(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var page = new UrlsPage(urls);
        String flash = ctx.consumeSessionAttribute("flash");
        page.setFlash(flash);
        ctx.render("urls/show.jte", model("page", page));
    }

    public static void add(Context ctx) throws SQLException,
            MalformedURLException,
            URISyntaxException {
        var name = ctx.formParam("url");
        try {
            URL absoluteUrl = new URI(name).toURL();
            String schema = absoluteUrl.getProtocol();
            String host = absoluteUrl.getHost();
            String port = String.valueOf(absoluteUrl.getPort());
            Url url = new Url(schema + "//" + host + ":" + port);
            UrlRepository.save(url);
            if (UrlRepository.urlExists(url)) {
                ctx.sessionAttribute("flash", "Страница уже существует");
            } else {
                ctx.sessionAttribute("flash", "Страница успешно добавлена");
            }
            ctx.redirect(NamedRoutes.urlsPath());
        } catch (URISyntaxException | MalformedURLException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.redirect(NamedRoutes.urlsPath());
        }
    }
}
