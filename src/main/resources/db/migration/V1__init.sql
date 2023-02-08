CREATE TABLE IF NOT EXISTS source
(
    id          SERIAL           PRIMARY KEY,
    name        VARCHAR(255)     NOT NULL
);

CREATE TABLE IF NOT EXISTS topic
(
    id          SERIAL          PRIMARY KEY,
    title       VARCHAR(255)    NOT NULL,
    source_id   INTEGER         NOT NULL,

    FOREIGN KEY (source_id) REFERENCES source (id)
);

CREATE TABLE IF NOT EXISTS news
(
    id          SERIAL          PRIMARY KEY,
    topic_id    INTEGER         NOT NULL,
    content     VARCHAR(2048)   NOT NULL,

    FOREIGN KEY (topic_id) REFERENCES topic (id)
);