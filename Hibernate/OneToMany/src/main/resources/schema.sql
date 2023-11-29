DROP TABLE IF EXISTS producers;
DROP TABLE IF EXISTS movies;

CREATE TABLE IF NOT EXISTS producers(
    producer_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    age INTEGER CHECK (age > 10)
);

CREATE TABLE IF NOT EXISTS movies(
    movie_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    producer_id INTEGER NOT NULL REFERENCES producers(producer_id),
    name VARCHAR(200) NOT NULL,
    year_of_production INTEGER CHECK(year_of_production > 1900)
);