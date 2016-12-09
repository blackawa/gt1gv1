(ns gt1gv1.service.user
  (:require [gt1gv1.repository.users :as r]))

(defn find-for-index [user-id db]
  (r/select-user-and-queue-by-user-id user-id db))

(defn exist? [user-id db]
  (not (empty? (r/select-user-by-id user-id db))))
