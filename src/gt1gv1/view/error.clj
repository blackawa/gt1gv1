(ns gt1gv1.view.error
  (:require [gt1gv1.view.layout :refer [layout]]))

(defn forbidden []
  (layout [:body
           [:h1 "sign in, please."]
           [:a {:href "/sign/in"} "Sign in from here"]]))
