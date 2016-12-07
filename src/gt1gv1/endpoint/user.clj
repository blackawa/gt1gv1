(ns gt1gv1.endpoint.user
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [gt1gv1.view.error :as error-view]
            [gt1gv1.view.user :as view]
            [ring.util.response :refer [response header status]]))

(defn- index [req]
  (let [session-user-id (:session req)
        param-user-id (-> req :params :user-id)]
    (if (= session-user-id param-user-id)
      (view/index param-user-id)
      (-> (response (error-view/forbidden))
          (header "Content-Type" "text/html; charset=utf-8")
          (status 403)))))

(defn user-endpoint [{{db :spec} :db}]
  (routes
   (GET "/users/:user-id" {:as req} (index req))))
