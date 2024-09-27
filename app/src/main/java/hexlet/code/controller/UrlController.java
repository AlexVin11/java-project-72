package hexlet.code.controller;

import hexlet.code.dto.url.*;
import io.javalin.http.Context;
import repository.UrlRepository;

import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlController {
    public static void index(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var page = new UrlsPage(urls);
        ctx.render("index.jte", model("page", page));
    }
}
