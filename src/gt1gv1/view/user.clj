(ns gt1gv1.view.user
  (:require [gt1gv1.view.layout :refer [layout]]))

(defn index [user]
  (println user)
  (let [{user-id :id user-name :name} (-> user vals first first)]
    (layout
     [:body
      [:h1 "Hello, " user-name]
      [:ul
       (map
        (fn [q]
          [:li [:a
                {:href (format "/users/%s/queues/%s" user-id (-> q first))}
                (-> q second first :name_2)]])
        user)]
      [:p [:a {:href "/sign/out"} "Sign out"]]
      [:p [:a {:href (format "/users/%s/queues" user-id)} "create new queue"]]])))
