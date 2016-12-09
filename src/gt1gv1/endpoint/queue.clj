(ns gt1gv1.endpoint.queue
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [gt1gv1.util.session :as session]
            [gt1gv1.util.response :refer [forbidden-response bad-request-response]]
            [gt1gv1.view.queue :as view]
            [gt1gv1.service.queue :as service]
            [gt1gv1.validator.queue :as validator]
            [ring.util.response :refer [redirect]]))

(defn- new-queue-page [req db]
  (if-let [user-id (session/check-user-id req db)]
    (view/new-queue-page user-id)
    (forbidden-response)))

(defn- create-queue [req db]
  (if-let [user-id (session/check-user-id req db)]
    (let [queue (-> req :params (select-keys [:get-title :give-title]))
          msg (first (validator/validate-queue queue))]
      (if (nil? msg)
        (let [queue-id (service/create-queue queue user-id db)]
          (-> (redirect (format "/users/%s/queues/%s" user-id queue-id))))
        (bad-request-response (view/new-queue-page user-id queue msg))))
    (forbidden-response)))

(defn- index [req db]
  (if-let [user-id (session/check-user-id req db)]
    (let [queue-id (-> req :params :queue-id)
          queue (first (service/find-queue-by-id user-id queue-id db))]
      (view/index queue))
    (forbidden-response)))

(defn queue-endpoint [{{db :spec} :db}]
  (routes
   (context "/users/:user-id/queues" _
            (GET  "/" {:as req} (new-queue-page req db))
            (POST "/" {:as req} (create-queue req db))
            (GET  "/:queue-id" {:as req} (index req db)))))
