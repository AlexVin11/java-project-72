package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

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
    private List<UrlCheck> checks;

    public Url(String name) {
        this.name = name;
        this.checks = new LinkedList<>();
    }
}
