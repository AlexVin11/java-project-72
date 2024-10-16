DROP TABLE IF EXISTS url_checks;
DROP TABLE IF EXISTS urls;

CREATE TABLE urls (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE url_checks (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    status_code INT,
    title TEXT,
    h1 TEXT,
    description TEXT,
    url_id BIGINT REFERENCES urls(id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
