(ns gt1gv1.repository.users
  (:require [clojure.java.jdbc :as j]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all]))

(defn insert-users [user db]
  (j/insert! db :users user))
