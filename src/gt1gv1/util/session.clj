(ns gt1gv1.util.session
  (:require [clojure.tools.logging :as log]
            [gt1gv1.service.user :as user]))

(defn check-user-id [req db]
  (let [session-user-id (-> req :session :user-id)
        param-user-id (-> req :params :user-id)
        rs (and (not (nil? session-user-id))
                (not (nil? param-user-id))
                (= session-user-id (read-string param-user-id))
                (user/exist? session-user-id db))]
    (log/info (format "user-id validating. session[%s[%s]]:query-tring[%s[%s]]. match %s"
                      (type session-user-id)
                      session-user-id
                      (type param-user-id)
                      param-user-id
                      (if rs "succeeded" "failed")))
    (when rs session-user-id)))
