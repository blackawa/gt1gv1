(ns gt1gv1.service.sign
  (:require [buddy.hashers :as hs]
            [gt1gv1.repository.users :as repo]))

(defn sign-up [user db]
  (let [ret (repo/insert-users (update user :passwd #(hs/encrypt %)) db)]
    (println "user created!")
    (println ret)
    ret))
