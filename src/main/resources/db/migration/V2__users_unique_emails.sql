ALTER TABLE users ADD CONSTRAINT user_email_uq UNIQUE (email);




CREATE OR REPLACE FUNCTION update_userscore() RETURNS TRIGGER AS
$$
DECLARE
  scoreUp INTEGER;
BEGIN
  IF TG_OP = 'INSERT' THEN
    IF TG_TABLE_NAME = 'Singleplayer' THEN
      scoreUp := (select score from users where id = NOW.user_id);
      IF NEW.score > scoreUp THEN
        UPDATE users SET score = NEW.score where id = NEW.user_id;
        RETURN NEW;
      END IF;
    END IF;
  END IF;
  RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER scoreboard
AFTER INSERT ON singleplayer
FOR EACH ROW EXECUTE PROCEDURE update_userscore ();
