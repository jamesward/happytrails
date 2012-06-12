# --- !Ups

DROP TABLE IF EXISTS photo CASCADE;

DROP sequence IF EXISTS photo_seq;

DROP INDEX IF EXISTS ix_comment_photo_1;
DROP INDEX IF EXISTS ix_comment_photo_10;

ALTER TABLE comment DROP CONSTRAINT IF EXISTS fk_comment_photo_1;
ALTER TABLE route DROP CONSTRAINT IF EXISTS fk_comment_photo_10;

CREATE TABLE s3photo (
  id                        bigint not null,
  bucket                    varchar(255),
  key                       varchar(255),
  CONSTRAINT pk_s3photo primary KEY (id))
;

CREATE sequence s3photo_seq;

ALTER TABLE region ADD photo_id bigint;

ALTER TABLE comment ADD CONSTRAINT fk_comment_photo_1 FOREIGN KEY (photo_id) REFERENCES s3photo (id);
CREATE INDEX ix_comment_photo_1 ON comment (photo_id);

ALTER TABLE region ADD CONSTRAINT fk_region_photo_7 FOREIGN KEY (photo_id) REFERENCES s3photo (id);
CREATE INDEX ix_region_photo_7 ON region (photo_id);

ALTER TABLE route ADD CONSTRAINT fk_route_photo_11 FOREIGN KEY (photo_id) REFERENCES s3photo (id);
CREATE INDEX ix_route_photo_11 ON route (photo_id);


# --- !Downs

DROP INDEX IF EXISTS ix_comment_photo_1;
ALTER TABLE comment DROP CONSTRAINT IF EXISTS fk_comment_photo_1;

DROP INDEX IF EXISTS ix_comment_photo_7;
ALTER TABLE region DROP CONSTRAINT IF EXISTS fk_region_photo_7;

DROP INDEX IF EXISTS ix_comment_photo_10;
ALTER TABLE route DROP CONSTRAINT IF EXISTS fk_comment_photo_10;

DROP sequence IF EXISTS s3photo_seq;

DROP TABLE IF EXISTS s3photo CASCADE;
