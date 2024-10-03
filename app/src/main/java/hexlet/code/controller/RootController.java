package hexlet.code.controller;

import io.javalin.http.Context;

import java.sql.SQLException;

public class RootController {
    public static void index(Context ctx) throws SQLException {
        ctx.render("index.jte");
    }
}
