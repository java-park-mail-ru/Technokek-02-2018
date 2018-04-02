CREATE TABLE users (
	id SERIAL primary key,
	nickname VARCHAR(255),
	email    VARCHAR(255),
	password VARCHAR(255),
	avatar VARCHAR(255),
	games_number INTEGER
);

CREATE TABLE sigleplayer (
	game_id SERIAL primary key,
	score   INTEGER,
	user_id INTEGER REFERENCES users(id)
);

CREATE TABLE multiplayer (
	game_id SERIAL primary key,
	score   INTEGER,
	user_first_id INTEGER REFERENCES users(id),
	user_second_id INTEGER REFERENCES users(id)
);