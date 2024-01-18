DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE IF NOT EXISTS users(
user_id SERIAL PRIMARY KEY,
login VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
email VARCHAR(255) UNIQUE,
created_at TIMESTAMP
);

INSERT INTO users(login, password, email) VALUES ('Aboba', 'Aboba', 'aboba@mail.com');
INSERT INTO users(login, password, email) VALUES ('Tom', 'Tom', 'tom@mail.com');
INSERT INTO users(login, password, email) VALUES ('Bob', 'Bob', 'bob@mail.com');
INSERT INTO users(login, password, email) VALUES ('Katy', 'Katy', 'katy@mail.com');
