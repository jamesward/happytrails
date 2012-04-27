# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  id                        bigint not null,
  route_id                  bigint not null,
  value                     varchar(255),
  creation_date             timestamp,
  constraint pk_comment primary key (id))
;

create table direction (
  id                        bigint not null,
  route_id                  bigint not null,
  step_number               integer,
  instruction               varchar(255),
  constraint pk_direction primary key (id))
;

create table photo (
  id                        bigint not null,
  thumbnail_url             varchar(255),
  full_url                  varchar(255),
  constraint pk_photo primary key (id))
;

create table rating (
  id                        bigint not null,
  route_id                  bigint not null,
  value                     integer,
  creation_date             timestamp,
  constraint pk_rating primary key (id))
;

create table region (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_region primary key (id))
;

create table route (
  id                        bigint not null,
  name                      varchar(255),
  distance                  varchar(255),
  location                  varchar(255),
  map_url                   varchar(255),
  creation_date             timestamp,
  constraint pk_route primary key (id))
;

create table user (
  id                        bigint not null,
  email_address             varchar(255),
  password                  varchar(255),
  name                      varchar(255),
  creation_date             timestamp,
  constraint pk_user primary key (id))
;

create sequence comment_seq;

create sequence direction_seq;

create sequence photo_seq;

create sequence rating_seq;

create sequence region_seq;

create sequence route_seq;

create sequence user_seq;

alter table comment add constraint fk_comment_route_1 foreign key (route_id) references route (id) on delete restrict on update restrict;
create index ix_comment_route_1 on comment (route_id);
alter table direction add constraint fk_direction_route_2 foreign key (route_id) references route (id) on delete restrict on update restrict;
create index ix_direction_route_2 on direction (route_id);
alter table rating add constraint fk_rating_route_3 foreign key (route_id) references route (id) on delete restrict on update restrict;
create index ix_rating_route_3 on rating (route_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists comment;

drop table if exists direction;

drop table if exists photo;

drop table if exists rating;

drop table if exists region;

drop table if exists route;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists comment_seq;

drop sequence if exists direction_seq;

drop sequence if exists photo_seq;

drop sequence if exists rating_seq;

drop sequence if exists region_seq;

drop sequence if exists route_seq;

drop sequence if exists user_seq;

