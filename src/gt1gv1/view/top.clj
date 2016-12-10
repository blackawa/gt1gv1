(ns gt1gv1.view.top
  (:require [gt1gv1.view.layout :refer [layout]]))

(defn index []
  (layout
   [:body
    [:header
     [:h1 "Get 1 XXX, then Give 1 XXX!"]]
    [:div
     [:p
      [:a {:href "/sign/in"} "Sign in"]]
     [:p
      [:a {:href "/sign/up"} "Sign up"]]]]))
