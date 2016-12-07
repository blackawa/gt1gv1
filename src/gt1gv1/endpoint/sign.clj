(ns gt1gv1.endpoint.sign
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [gt1gv1.validator.sign :as validator]
            [gt1gv1.view.sign :as view]
            [gt1gv1.service.sign :as service]
            [gt1gv1.util.response :refer [html-response bad-request-response]]
            [ring.util.response :refer [redirect response status header]]))

(defn- sign-in-page [req]
  (view/sign-in-page))

(defn- sign-in [req db]
  (let [user (select-keys (:params req) [:name :passwd])
        msg (first (validator/validate-sign-in user))]
    (if (nil? msg)
      (if-let [user-id (service/sign-in-success? user db)]
        (-> (redirect (format "/users/%s" user-id) :see-other)
            (assoc :session {:user-id user-id}))
        (bad-request-response (view/sign-in-page user '("invalid username or password."))))
      (bad-request-response (view/sign-in-page user msg)))))

(defn- sign-up-page [req]
  (view/sign-up-page))

(defn- sign-up [req db]
  (let [user (select-keys (:params req) [:name :passwd])
        ;; TODO: unique name validation
        rs (first (validator/validate-sign-up user))]
    (if (not (nil? rs))
      (bad-request-response (view/sign-up-page user rs))
      (let [user-id (service/sign-up user db)]
        (-> (redirect (format "/users/%s" user-id) :see-other)
            (assoc :session {:user-id user-id}))))))

(defn- sign-out [req]
  (-> (redirect "/" :see-other)
      (html-response)
      (assoc :session nil)))

(defn sign-endpoint [{{db :spec} :db}]
  (routes
   (context "/sign" _
            (GET  "/in" {:as req} (sign-in-page req))
            (POST "/in" {:as req} (sign-in req db))
            (GET "/up"  {:as req} (sign-up-page req))
            (POST "/up" {:as req} (sign-up req db))
            (GET "/out" {:as req} (sign-out req)))))
