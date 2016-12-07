(ns gt1gv1.endpoint.user
  (:require [compojure.core :refer :all]
            [compojure.route :as route]))

(defn- index [req]
  (hiccup.page/html5
   [:body
    [:h1 "user page"]
    [:p (str "user-id: " (-> req :params :user-id))]]))

(defn user-endpoint [{{db :spec} :db}]
  (routes
   (GET "/users/:user-id" {:as req} (index req))))
