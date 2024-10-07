package hexlet.code;


import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class AppTest {
    private final Context ctx = mock(Context.class);
    private static Javalin app;

    @BeforeEach
    public void runApp() throws SQLException, IOException {
        app = App.getApp();
    }

    @Test
    public void indexRootHandlerReturnSuccess() {
        JavalinTest.test(app, (server, client) -> {
            assertThat(client.get("/").code()).isEqualTo(200);
        });
    }

    @Test
    public void indexUrlHandlerReturnSuccess() {
        JavalinTest.test(app, (server, client) -> {
            assertThat(client.get("/urls").code()).isEqualTo(200);
        });
    }
}
