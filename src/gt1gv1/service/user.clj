(ns gt1gv1.service.user
  (:require [gt1gv1.repository.users :as user]
            [gt1gv1.repository.queues :as queue]))

(defn find-for-index [user-id db]
  (let [user (user/select-user-by-id user-id db)
        queues (queue/find-queues-by-users-id user-id db)]
    (assoc user :queues queues)))

(defn exist? [user-id db]
  (not (empty? (user/select-user-by-id user-id db))))
