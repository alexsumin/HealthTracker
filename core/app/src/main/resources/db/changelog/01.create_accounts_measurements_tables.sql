DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS measurements;

CREATE TABLE users(
    id BIGINT PRIMARY KEY
);


CREATE TABLE measurements(
    id BIGSERIAL PRIMARY KEY,
    data NUMERIC NOT NULL,
    creation_date TIMESTAMP,
    user_id BIGINT,
    FOREIGN KEY ( user_id ) REFERENCES users( id )
);
