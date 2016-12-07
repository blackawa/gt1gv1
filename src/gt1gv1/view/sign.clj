(ns gt1gv1.view.sign
  (:require [gt1gv1.view.layout :refer [layout]]
            [hiccup.form :refer [form-to]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]))

(defn sign-in-page
  ([]
   (sign-in-page {} {}))
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
       [:button {:type "submit"} "Sign up"])
      [:p [:a {:href "/sign/up"} "or sign up"]]]])))

(defn sign-up-page
  ([]
   (sign-up-page {} {}))
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
