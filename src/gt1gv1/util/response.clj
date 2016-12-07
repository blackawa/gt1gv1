(ns gt1gv1.util.response
  (:require [gt1gv1.view.error :as error-view]
            [ring.util.response :refer [header response status]]))

(defn html-response [res]
  (header res "Content-Type" "text/html; charset=utf-8"))

(defn forbidden-response []
  (-> (response (error-view/forbidden))
        (html-response)
        (status 403)))

(defn bad-request-response [body]
  (-> (response body)
      (status 400)
      (html-response)))
