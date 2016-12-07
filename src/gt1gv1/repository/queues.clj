(ns gt1gv1.repository.queues
  (:require [clojure.java.jdbc :as j]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all]))

(defn insert-queues [queue db]
  (j/insert! db :queues queue))

(defn find-queue-by-id [user-id queue-id db]
  (j/query db (sql/format {:select [:*]
                           :from [:queues]
                           :where [:and
                                   [:= :id queue-id]
                                   [:= :users_id user-id]]})))
