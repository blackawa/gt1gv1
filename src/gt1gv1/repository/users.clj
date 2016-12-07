(ns gt1gv1.repository.users
  (:require [clojure.java.jdbc :as j]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all]))

(defn insert-users [user db]
  (j/insert! db :users user))

(defn select-user-by-name [user-name db]
  (-> (j/query db (sql/format {:select [:*] :from [:users] :where [:= :name user-name]}))
      first))

(defn select-user-and-queue-by-user-id [user-id db]
  (j/query db (sql/format {:select [:users.id :users.name
                                    :queues.id :queues.name]
                           :from [:users]
                           :left-join [:queues [:= :users.id :queues.users_id]]
                           :where [:and
                                   [:= :users.id user-id]
                                   [:= :users.id :queues.users_id]]})))
