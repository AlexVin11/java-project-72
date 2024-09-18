package hexlet.code;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        var app = getApp();
        app.start();
    }
    public static Javalin getApp() {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        return app;
    }

}
