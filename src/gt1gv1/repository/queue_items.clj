(ns gt1gv1.repository.queue-items
  (:require [clojure.java.jdbc :as j]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all]))

(defn insert-queue-items [queue-items db]
  (j/insert! db :queue_items queue-items))

(defn find-by-queue-id [queue-id db]
  (j/query db (sql/format {:select [:*]
                           :from [:queue_items]
                           :where [:and
                                   [:= :queues_id queue-id]
                                   [:= :queue_items_status_id 1]]
                           :order-by [:id]})))

(defn pop-queue-item [queue-item-id db]
  (j/update! db :queue_items {:queue_items_status_id 2} ["id = ?" (read-string queue-item-id)]))
