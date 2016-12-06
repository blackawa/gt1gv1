(ns gt1gv1.endpoint.queue
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.page :refer [html5]]))

(defn- index [req db]
  (let [{:keys [user-id queue-id]} (:params req)]
    (html5
     [:body
      [:h1 "hello, duct from scratch!"]
      [:p (str "user-id: " user-id)]
      [:p (str "queue-id: " queue-id)]])))

(defn queue-endpoint [{{db :spec} :db}]
  (routes
   (GET "/users/:user-id/queues/:queue-id" {:as req} (index req db))))
