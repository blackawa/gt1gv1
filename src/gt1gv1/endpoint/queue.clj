(ns gt1gv1.endpoint.queue
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.page :refer [html5]]))

(defn- index [req]
  (html5
   [:body
    [:h1 "hello, duct from scratch!"]]))

(defn queue-endpoint [config]
  (routes
   (GET "/" {:as req} (index req))))
