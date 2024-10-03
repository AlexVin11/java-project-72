package hexlet.code.controller;

import hexlet.code.dto.urls.UrlsPage;
import io.javalin.http.Context;
import repository.UrlRepository;

import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlController {
    public static void show(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var page = new UrlsPage(urls);
        ctx.render("urls/show.jte", model("page", page));
    }
}
