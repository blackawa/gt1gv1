(ns gt1gv1.view.queue
  (:require [gt1gv1.view.layout :refer [layout]]
            [hiccup.form :refer [form-to]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]))

(defn index [queue]
  (println queue)
  (layout
   [:body
    [:h1 (:name queue)]
    [:p "queue items below..."]
    (form-to
     [:post (format "/users/%s/queues/%s/items" (:users_id queue) (:id queue))]
     [:input {:type "text"
              :placeholder "item"
              :name "content"}]
     (anti-forgery-field)
     [:button {:type "submit"} "アイテム追加"])
    [:p
     [:a {:href (format "/users/%s" (:users_id queue))} "back to user page"]]]))

(defn new-queue-page
  ([user-id]
   (new-queue-page user-id {} {}))
  ([user-id param msg]
   (layout
   [:body
    [:h1 "create new queue"]
    [:ul.msg
     (map (fn [m] [:li m]) msg)]
    (form-to
     [:post (format "/users/%s/queues" user-id)]
     [:input {:type "text"
              :placeholder "queue name"
              :name "name"
              :value (:name param)}]
     (anti-forgery-field)
     [:button {:type "submit"} "登録"])])))
