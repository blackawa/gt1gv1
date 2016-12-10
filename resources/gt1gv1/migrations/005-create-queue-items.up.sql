create table queue_items (
  id serial primary key,
  content text not null,
  queues_id integer not null,
  queue_items_status_id integer not null,
  foreign key (queues_id) references queues (id),
  foreign key (queue_items_status_id) references queue_items_status (id)
);
