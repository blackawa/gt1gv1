(ns gt1gv1.service.queue
  (:require [gt1gv1.repository.queues :as r]))

(defn create-queue [queue user-id db]
  (-> queue
      ;; rename key
      (assoc :get_title (:get-title queue))
      (assoc :give_title (:give-title queue))
      (dissoc :get-title :give-title)
      ;; add foreign key
      (assoc :users_id user-id)
      (assoc :queues_status_id 1)
      (r/insert-queues db)
      first
      :id))

(defn find-queue-by-id [user-id queue-id db]
  (r/find-queue-by-id user-id (read-string queue-id) db))
