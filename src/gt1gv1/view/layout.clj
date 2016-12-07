(ns gt1gv1.view.layout
  (:require [hiccup.page :refer [html5]]
            [ring.util.response :refer [header]]))

(defn layout [body]
  (html5
   [:head
    [:title "Remember your owe | Get 1, Give 1"]]
   body))
