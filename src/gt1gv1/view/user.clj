(ns gt1gv1.view.user
  (:require [gt1gv1.view.layout :refer [layout]]))

(defn index [user]
  (let [{user-id :id user-name :name queues :queues} user]
    (layout
     [:body
      [:h1 "Hello, " user-name]
      (if (empty? queues)
        [:p "Create your first "
         [:a {:href (format "/users/%s/queues" user-id)} "queue"]]
        [:ul
         (map
          (fn [q]
            [:li [:a
                  {:href (format "/users/%s/queues/%s" user-id (:id q))}
                  (format "Get %s, Give %" (:get_title q) (:give_title q))]])
          queues)])
      [:p [:a {:href "/sign/out"} "Sign out"]]
      [:p [:a {:href (format "/users/%s/queues" user-id)} "create new queue"]]])))
