(ns gt1gv1.view.layout
  (:require [hiccup.page :refer [html5]]))

(defn layout [body]
  (html5
   [:head]
   body))
