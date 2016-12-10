(ns gt1gv1.service.queue
  (:require [gt1gv1.repository.queues :as queue]
            [gt1gv1.repository.queue-items :as queue-item]))

(defn create-queue [queue user-id db]
  (-> queue
      ;; rename key
      (assoc :get_title (:get-title queue))
      (assoc :give_title (:give-title queue))
      (dissoc :get-title :give-title)
      ;; add foreign key
      (assoc :users_id user-id)
      (assoc :queues_status_id 1)
      (queue/insert-queues db)
      first
      :id))

(defn find-queue-by-id [user-id queue-id db]
  (let [queue-id (read-string queue-id)
        queue (first (queue/find-queue-by-id user-id queue-id db))
        queue-items (queue-item/find-by-queue-id queue-id db)]
    (assoc queue :queue-items queue-items)))
