(ns gt1gv1.view.sign
  (:require [gt1gv1.view.layout :refer [layout]]
            [hiccup.form :refer [form-to]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]))

(defn sign-in-page
  ([]
   (layout
   [:body [:header
           [:h1 "Sign in"]]
    [:div
     (form-to
       [:post "/sign/in"]
       [:input {:type "text"
                :placeholder "username"
                :name "name"}]
       [:input {:type "password"
                :placeholer "password"
                :name "passwd"}]
       (anti-forgery-field)
       [:button {:type "submit"} "Sign up"])]]))
  ([user msg]
   (layout
    [:body [:header
            [:h1 "Sign in"]]
     [:div
      [:ul.msg
       (map (fn [m] [:li m]) msg)]
      (form-to
       [:post "/sign/in"]
       [:input {:type "text"
                :placeholder "username"
                :name "name"
                :value (:name user)}]
       [:input {:type "password"
                :placeholer "password"
                :name "passwd"}]
       (anti-forgery-field)
       [:button {:type "submit"} "Sign up"])]])))

(defn sign-up-page
  ([]
   (layout
    [:body
     [:header
      [:h1 "Sign up"]]
     [:div
      (form-to
       [:post "/sign/up"]
       [:input {:type "text"
                :placeholder "username"
                :name "name"}]
       [:input {:type "password"
                :placeholer "password"
                :name "passwd"}]
       (anti-forgery-field)
       [:button {:type "submit"} "Sign up"])]]))
  ([user msg]
   (layout
    [:body
     [:header
      [:h1 "Sign up"]]
     [:div
      [:ul.msg
       (map (fn [m] [:li m]) msg)]
      (form-to
       [:post "/sign/up"]
       [:input {:type "text"
                :placeholder "username"
                :name "name"
                :value (:name user)}]
       [:input {:type "password"
                :placeholer "password"
                :name "passwd"}]
       (anti-forgery-field)
       [:button {:type "submit"} "Sign up"])]])))
