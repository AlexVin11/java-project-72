package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();
        app.start(getPort());
    }
    public static Javalin getApp() throws IOException, SQLException {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");
        var dataSource = new HikariDataSource(hikariConfig);
        app.get(NamedRoutes.rootPath(), ctx ->
                ctx.result("Hello, world!"));
        return app;
    }

    public static int getPort() {
        var port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }
}
