(ns gt1gv1.service.queue-item
  (:require [gt1gv1.repository.queue-items :as r]))

(defn create-queue-item [queue-item db]
  (-> queue-item
      (assoc :queues_id (read-string (:queue-id queue-item)))
      (dissoc :queue-id)
      (assoc :queue_items_status_id 1)
      (r/insert-queue-items db)
      first
      :id))

(defn delete-queue-item [queue-item-id db]
  (r/pop-queue-item queue-item-id db))
