(ns gt1gv1.endpoint.sign
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.page :refer [html5]]))

(defn sign-in-page [req]
  (html5
   [:body
    [:header
     [:h1 "Sign in"]]
    [:div
     [:form {:action "/sign/in" :method "post"}
      [:p
       [:input {:type "text"
                :placeholder "username"
                :name "username"}]]
      [:p [:input {:type "password"
                   :placeholer "password"
                   :name "password"}]]]]]))

(defn sign-in [req db])

(defn sign-up-page [req]
  (html5
   [:body
    [:header
     [:h1 "Sign up"]]
    [:div
     [:form {:action "/sign/up" :method "post"}
      [:p
       [:input {:type "text"
                :placeholder "username"
                :name "username"}]]
      [:p [:input {:type "password"
                   :placeholer "password"
                   :name "password"}]]]]]))

(defn sign-up [req db])

(defn sign-endpoint [{{db :spec} :db}]
  (routes
   (context "/sign" _
            (GET  "/in" {:as req} (sign-in-page req))
            (POST "/in" {:as req} (sign-in req db))
            (GET "/up"  {:as req} (sign-up-page req))
            (POST "/up" {:as req} (sign-up req)))))
