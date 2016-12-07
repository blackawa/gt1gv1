create table queue_items (
  id serial primary key,
  content text not null,
  queue_items_status_id integer references queue_items_status (id) not null
);
