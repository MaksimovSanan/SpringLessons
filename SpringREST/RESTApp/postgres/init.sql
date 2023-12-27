DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users(
id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL,
age INTEGER check (age > 0),
email VARCHAR(255) UNIQUE,
created_at TIMESTAMP
);

INSERT INTO users(name, age, email) VALUES ('Aboba', 99, 'aboba@mail.com');
INSERT INTO users(name, age, email) VALUES ('Tom', 25, 'tom@mail.com');
INSERT INTO users(name, age, email) VALUES ('Bob', 51, 'bob@mail.com');
INSERT INTO users(name, age, email) VALUES ('Katy', 38, 'katy@mail.com');
