CREATE TABLE users (
  id SERIAL primary key,
  nickname VARCHAR(255),
  email    VARCHAR(255),
  password VARCHAR(255),
  avatar VARCHAR(255) DEFAULT NULL,
  games_number INTEGER DEFAULT 0,
  score INTEGER DEFAULT 0
);

CREATE TABLE singleplayer (
  game_id SERIAL primary key,
  score   INTEGER,
  user_id INTEGER REFERENCES users(id) ON DELETE CASCADE NOT NULL,
  date TIMESTAMP  DEFAULT NOW()
);

CREATE TABLE multiplayer (
  game_id SERIAL primary key,
  score   INTEGER,
  user_first_id INTEGER REFERENCES users(id) ON DELETE CASCADE NOT NULL,
  user_second_id INTEGER REFERENCES users(id) ON DELETE CASCADE NOT NULL,
  date TIMESTAMP DEFAULT NOW()
);
