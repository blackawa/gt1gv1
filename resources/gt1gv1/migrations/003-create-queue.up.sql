create table queues (
  id serial primary key,
  get_title varchar(64) not null,
  give_title varchar(64) not null,
  users_id integer not null,
  queues_status_id integer not null,
  foreign key (users_id) references users(id),
  foreign key (queues_status_id) references queues_status (id)
);
