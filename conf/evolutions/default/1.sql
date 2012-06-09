# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  id                        bigint not null,
  value                     varchar(1024) not null,
  creation_date             timestamp not null,
  photo_id                  bigint,
  user_id                   bigint,
  route_id                  bigint,
  constraint pk_comment primary key (id))
;

create table direction (
  id                        bigint not null,
  step_number               integer not null,
  instruction               varchar(1024) not null,
  route_id                  bigint,
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
  value                     integer not null,
  creation_date             timestamp not null,
  user_id                   bigint,
  route_id                  bigint,
  constraint pk_rating primary key (id))
;

create table region (
  id                        bigint not null,
  name                      varchar(128) not null,
  url_friendly_name         varchar(128) not null,
  constraint uq_region_url_friendly_name unique (url_friendly_name),
  constraint pk_region primary key (id))
;

create table region_subscription (
  id                        bigint not null,
  user_id                   bigint,
  region_id                 bigint,
  last_send                 timestamp,
  constraint pk_region_subscription primary key (id))
;

create table route (
  id                        bigint not null,
  name                      varchar(128) not null,
  url_friendly_name         varchar(128) not null,
  description               varchar(255) not null,
  distance_in_miles         float not null,
  location                  varchar(255) not null,
  region_id                 bigint,
  photo_id                  bigint,
  map_url                   varchar(255),
  creation_date             timestamp,
  constraint pk_route primary key (id))
;

create table account (
  id                        bigint not null,
  token                     varchar(255),
  email_address             varchar(256) not null,
  sha_password              bytea not null,
  full_name                 varchar(256) not null,
  creation_date             timestamp not null,
  constraint uq_account_email_address unique (email_address),
  constraint pk_account primary key (id))
;

create sequence comment_seq;

create sequence direction_seq;

create sequence photo_seq;

create sequence rating_seq;

create sequence region_seq;

create sequence region_subscription_seq;

create sequence route_seq;

create sequence account_seq;

alter table comment add constraint fk_comment_photo_1 foreign key (photo_id) references photo (id);
create index ix_comment_photo_1 on comment (photo_id);
alter table comment add constraint fk_comment_user_2 foreign key (user_id) references account (id);
create index ix_comment_user_2 on comment (user_id);
alter table comment add constraint fk_comment_route_3 foreign key (route_id) references route (id);
create index ix_comment_route_3 on comment (route_id);
alter table direction add constraint fk_direction_route_4 foreign key (route_id) references route (id);
create index ix_direction_route_4 on direction (route_id);
alter table rating add constraint fk_rating_user_5 foreign key (user_id) references account (id);
create index ix_rating_user_5 on rating (user_id);
alter table rating add constraint fk_rating_route_6 foreign key (route_id) references route (id);
create index ix_rating_route_6 on rating (route_id);
alter table region_subscription add constraint fk_region_subscription_user_7 foreign key (user_id) references account (id);
create index ix_region_subscription_user_7 on region_subscription (user_id);
alter table region_subscription add constraint fk_region_subscription_region_8 foreign key (region_id) references region (id);
create index ix_region_subscription_region_8 on region_subscription (region_id);
alter table route add constraint fk_route_region_9 foreign key (region_id) references region (id);
create index ix_route_region_9 on route (region_id);
alter table route add constraint fk_route_photo_10 foreign key (photo_id) references photo (id);
create index ix_route_photo_10 on route (photo_id);



# --- !Downs

drop table if exists comment cascade;

drop table if exists direction cascade;

drop table if exists photo cascade;

drop table if exists rating cascade;

drop table if exists region cascade;

drop table if exists region_subscription cascade;

drop table if exists route cascade;

drop table if exists account cascade;

drop sequence if exists comment_seq;

drop sequence if exists direction_seq;

drop sequence if exists photo_seq;

drop sequence if exists rating_seq;

drop sequence if exists region_seq;

drop sequence if exists region_subscription_seq;

drop sequence if exists route_seq;

drop sequence if exists account_seq;

