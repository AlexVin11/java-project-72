package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

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

    public Url(String name) {
        this.name = name;
    }
}
