(ns gt1gv1.service.user
  (:require [gt1gv1.repository.users :as r]))

(defn find-for-index [user-id db]
  ;; TODO: use 'as' for column name
  (group-by :id_2 (r/select-user-and-queue-by-user-id user-id db)))
