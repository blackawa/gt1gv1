(ns gt1gv1.view.user
  (:require [gt1gv1.view.layout :refer [layout]]))

(defn index [user-id]
  (layout
   [:body
    [:h1 "user page"]
    [:p "your user id: " user-id]]))
