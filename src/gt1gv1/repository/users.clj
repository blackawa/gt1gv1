(ns gt1gv1.repository.users
  (:require [clojure.java.jdbc :as j]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all]))

(defn insert-users [user db]
  (j/insert! db :users user))

(defn select-user-by-name [user-name db]
  (-> (j/query db (sql/format {:select [:*] :from [:users] :where [:= :name user-name]}))
      first))
