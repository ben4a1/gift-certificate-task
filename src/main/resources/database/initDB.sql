DROP TABLE IF EXISTS gift_certificate, tag, certificate_tag;

CREATE TABLE IF NOT EXISTS gift_certificate
(
    gift_certificate_id BIGSERIAL PRIMARY KEY,
    name                VARCHAR(64)    NOT NULL,
    description         VARCHAR(255)   NOT NULL,
    price               DECIMAL(10, 2) NOT NULL,
    create_date         TIMESTAMP      NOT NULL,
    last_update_date    TIMESTAMP,
    duration            INTERVAL       NOT NULL
);

CREATE TABLE IF NOT EXISTS tag
(
    tag_id BIGSERIAL PRIMARY KEY,
    name   VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS certificate_tag
(
    gift_certificate_id BIGSERIAL NOT NULL REFERENCES gift_certificate ON DELETE CASCADE,
    tag_id              BIGSERIAL NOT NULL REFERENCES tag ON DELETE CASCADE,
    UNIQUE (gift_certificate_id, tag_id)
);

