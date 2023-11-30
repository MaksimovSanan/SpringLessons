INSERT INTO producers(name, age) VALUES ('Quentin Tarantino', 57);
INSERT INTO producers(name, age) VALUES ('Martin Scorsese', 78);
INSERT INTO producers(name, age) VALUES ('Guy Ritchie', 52);
INSERT INTO producers(name, age) VALUES ('Woody Allen', 85);
INSERT INTO producers(name, age) VALUES ('David Lynch', 74);
INSERT INTO producers(name, age) VALUES ('Christopher Nolan', 50);


INSERT INTO movies(producer_id, name, year_of_production) VALUES (1, 'Reservoir Dogs', 1992),
                                                          (1, 'Pulp Fiction', 1994);
INSERT INTO movies(producer_id, name, year_of_production) VALUES (1, 'The Hateful Eight', 2015);
INSERT INTO movies(producer_id, name, year_of_production) VALUES (1, 'Once Upon a Time in Hollywood', 2019);
INSERT INTO movies(producer_id, name, year_of_production) VALUES (2, 'Taxi Driver', 1976);
INSERT INTO movies(producer_id, name, year_of_production) VALUES (2, 'Goodfellas', 1990);
INSERT INTO movies(producer_id, name, year_of_production) VALUES (2, 'The Wolf of Wall Street', 2013);
INSERT INTO movies(producer_id, name, year_of_production) VALUES (3, 'Lock, Stock and Two Smoking Barrels', 1998);
INSERT INTO movies(producer_id, name, year_of_production) VALUES (3, 'Snatch', 2000);
INSERT INTO movies(producer_id, name, year_of_production) VALUES (4, 'Midnight in Paris', 2011);
INSERT INTO movies(producer_id, name, year_of_production) VALUES (6, 'Inception', 2010);

INSERT INTO actors(name, age) VALUES ('Harvey Keitel', 81),
                              ('Samuel L. Jackson', 72);

INSERT INTO actors_movies(actor_id, movie_id) VALUES (1, 2),
                                                     (2, 2),
                                                     (1, 1);