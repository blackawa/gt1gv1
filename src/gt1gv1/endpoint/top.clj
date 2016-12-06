(ns gt1gv1.endpoint.top
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.page :refer [html5]]))

(defn index [req db]
  (html5
   [:body
    [:header
     [:h1 "Get 1, Give 1"]]
    [:div
     [:p
      [:a {:href "/sign/in"} "Sign in"]]
     [:p
      [:a {:href "/sign/up"} "Sign up"]]]]))

(defn top-endpoint [{{db :spec} :db}]
  (routes
   (GET "/" {:as req} (index req db))))
