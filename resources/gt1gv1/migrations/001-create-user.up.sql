create table users (
  id serial primary key,
  name varchar(32) not null,
  passwd varchar(128) not null
);
