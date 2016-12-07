(ns gt1gv1.service.sign
  (:require [buddy.hashers :as hs]
            [gt1gv1.repository.users :as repo]))

(defn sign-up [user db]
  (let [ret (repo/insert-users (select-keys (update user :passwd #(hs/encrypt %)) [:name :passwd])  db)]
    ;; ({:id 1, :name a, :passwd bcrypt+sha59...})
    (-> ret first :id)))
