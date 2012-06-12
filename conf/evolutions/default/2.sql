# --- !Ups

ALTER TABLE account ADD is_admin boolean;

UPDATE account SET is_admin = FALSE;

# --- !Downs

ALTER TABLE account DROP is_admin;