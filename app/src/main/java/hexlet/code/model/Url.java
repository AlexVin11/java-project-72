package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import static hexlet.code.util.TimestampFormatter.dateFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public final class Url {
    private Long id;
    @ToString.Include
    private String name;
    private Timestamp createdAt;
    @ToString.Exclude
    private String formattedTimestamp;
    private List<UrlCheck> checks;

    public Url(String name) {
        this.name = name;
        checks = new LinkedList<>();
    }

    public void addCheck(UrlCheck urlCheck) {
        urlCheck.setUrlId(this.id);
        checks.add(urlCheck);
    }

    public String getLastCheckTimeStamp() {
        UrlCheck lastCheck = checks.getLast();
        return dateFormatter(lastCheck.getCreatedAt());
    }

    public int getLastCheckStatusCode() {
        UrlCheck lastCheck = checks.getLast();
        return lastCheck.getStatusCode();
    }
}
