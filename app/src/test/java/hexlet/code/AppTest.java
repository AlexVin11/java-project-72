package hexlet.code;


import hexlet.code.model.Url;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UrlRepository;

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
    public void getRootPageReturnSuccess() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string().contains("Анализатор страниц"));
        });
    }

    @Test
    public void getUrlsListPageReturnSuccess() {
        JavalinTest.test(app, (server, client) -> {
            assertThat(client.get("/urls").code()).isEqualTo(200);
        });
    }

    @Test
    public void postCorrectUrlNameReturnSuccess() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://www.example.com";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://www.example.com");
        });
    }

    @Test
    public void saveCorrectUrlTwiceReturnSuccess() throws SQLException {
        JavalinTest.test(app, (server, client) -> {
            Url firstUrl = new Url("https://www.example.com");
            UrlRepository.save(firstUrl);
            Url firstUrlCopy = new Url("https://www.example.com");
            UrlRepository.save(firstUrlCopy);
            var response = client.get("/urls/" + firstUrl.getId());
            assertThat(UrlRepository.getEntities().size() == 1);
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void getIncorrectUrlIdReturnNotFound() throws SQLException {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/9999");
            assertThat(response.code()).isEqualTo(404);
        });
    }
}
