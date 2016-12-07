(ns gt1gv1.endpoint.sign
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [gt1gv1.view.sign :as view]
            [gt1gv1.service.sign :as service]
            [ring.util.response :refer [redirect]]))

(defn- sign-in-page [req]
  (view/sign-in-page))

(defn- sign-in [req db])

(defn- sign-up-page [req]
  (view/sign-up-page))

(defn- sign-up [req db]
  (let [user-id (service/sign-up (:params req) db)]
    (redirect (format "/users/%s" user-id) :see-other)))

(defn sign-endpoint [{{db :spec} :db}]
  (routes
   (context "/sign" _
            (GET  "/in" {:as req} (sign-in-page req))
            (POST "/in" {:as req} (sign-in req db))
            (GET "/up"  {:as req} (sign-up-page req))
            (POST "/up" {:as req} (sign-up req db)))))
