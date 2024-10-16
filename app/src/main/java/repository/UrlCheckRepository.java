package repository;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.util.TimestampFormatter;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UrlCheckRepository extends BaseRepository {

    public static void save(UrlCheck urlCheck, Url url) throws SQLException {
        String sql = "INSERT INTO url_check (status_code, title, h1, description)"
                + " VALUES (?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, urlCheck.getStatusCode());
            preparedStatement.setString(2, urlCheck.getTitle());
            preparedStatement.setString(3, urlCheck.getH1());
            preparedStatement.setString(4, urlCheck.getDescription());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getLong(1));
                urlCheck.setCreatedAt(generatedKeys.getTimestamp(2));
                urlCheck.setFormattedTimestamp(TimestampFormatter.dateFormatter(urlCheck.getCreatedAt()));
                url.addCheck(urlCheck);
            } else {
                throw new SQLException("DB have not returned an id or createdAt after saving an entity");
            }
        }
    }

    /*public static Optional<Url> findById(Long id) throws SQLException {
        String sql = "SELECT * FROM urls WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                var resultName = resultSet.getString("name");
                var resultCreatedAt = resultSet.getTimestamp("created_at");
                var resultId = resultSet.getLong("id");
                Url resUrl = new Url(resultName);
                resUrl.setId(resultId);
                resUrl.setCreatedAt(resultCreatedAt);
                resUrl.setFormattedTimestamp(TimestampFormatter.dateFormatter(resUrl.getCreatedAt()));
                return Optional.of(resUrl);
            }
            return Optional.empty();
        }
    }*/

    public static List<UrlCheck> getEntities() throws SQLException {
        String sql = "SELECT * FROM url_checks";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();
            var result = new LinkedList<UrlCheck>();
            while (resultSet.next()) {
                int statusCode = resultSet.getInt("status_code");
                String title = resultSet.getString("title");
                String h1 = resultSet.getString("h1");
                String description = resultSet.getString("description");
                UrlCheck urlCheck = new UrlCheck(statusCode, title, h1, description);
                urlCheck.setId(resultSet.getLong("id"));
                urlCheck.setCreatedAt(resultSet.getTimestamp("created_at"));
                urlCheck.setUrlId(resultSet.getLong("url_id"));
                result.add(urlCheck);
            }
            return result;
        }
    }
}
