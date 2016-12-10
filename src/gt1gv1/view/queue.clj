(ns gt1gv1.view.queue
  (:require [gt1gv1.view.layout :refer [layout]]
            [hiccup.form :refer [form-to]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]))

(defn index [queue]
  (let [user-id (:users_id queue)
        queue-id (:id queue)
        get-title (:get_title queue)
        give-title (:give_title queue)]
    (layout
     [:body
      [:h1 (format "Get 1 %s, Give 1 %s!" get-title give-title)]
      (form-to
       [:post (format "/users/%s/queues/%s/items" user-id queue-id)]
       [:input {:type "text"
                :placeholder "item"
                :name "content"}]
       (anti-forgery-field)
       [:button {:type "submit"} "アイテム追加"])
      [:p [:a {:href (format "/users/%s" user-id)} "back to user page"]]
      [:ul
       (map
        (fn [qi]
          [:li
           [:span (str (:content qi))]
           (form-to
            [:delete (format "/users/%s/queues/%s/items/%s" user-id queue-id (:id qi))]
            (anti-forgery-field)
            [:button {:type "submit"} "取り出す"])])
        (:queue-items queue))]])))

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
     [:span "Get"]
     [:input {:type "text"
              :placeholder "what you get"
              :name "get-title"
              :value (:get-title param)}]
     [:span "Give"]
     [:input {:type "text"
              :placeholder "what you give"
              :name "give-title"
              :value (:give-title param)}]
     (anti-forgery-field)
     [:button {:type "submit"} "登録"])])))
