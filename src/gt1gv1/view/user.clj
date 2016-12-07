(ns gt1gv1.view.user
  (:require [gt1gv1.view.layout :refer [layout]]))

(defn index [user-id]
  (layout
   [:body
    [:h1 "user page"]
    [:p "your user id: " user-id]
    [:p [:a {:href "/sign/out"} "Sign out"]]
    [:p [:a {:href (format "/users/%s/queues" user-id)} "create new queue"]]]))
