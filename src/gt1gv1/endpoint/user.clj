(ns gt1gv1.endpoint.user
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [gt1gv1.view.error :as error-view]
            [gt1gv1.view.user :as view]
            [gt1gv1.util.response :refer [forbidden-response]]
            [gt1gv1.util.session :as session]
            [ring.util.response :refer [response header status]]))

(defn- index [req]
  (if-let [user-id (session/check-user-id req)]
    (view/index user-id)
    (forbidden-response)))

(defn user-endpoint [{{db :spec} :db}]
  (routes
   (GET "/users/:user-id" {:as req} (index req))))
