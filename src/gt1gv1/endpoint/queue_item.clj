(ns gt1gv1.endpoint.queue-item
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [gt1gv1.util.session :as session]
            [gt1gv1.util.response :refer [forbidden-response bad-request-response]]
            [gt1gv1.service.queue :as queue-service]
            [gt1gv1.service.queue-item :as service]
            [gt1gv1.validator.queue-item :as validator]
            [gt1gv1.view.queue :as queue-view]
            [ring.util.response :refer [redirect]]))

(defn- create-queue-item [req db]
  (if-let [user-id (session/check-user-id req db)]
    (let [queue-item (-> req :params (select-keys [:content :queue-id]))
          msg (first (validator/validate-queue-item queue-item))]
      (if (nil? msg)
        (do (service/create-queue-item queue-item db)
            (redirect (format "/users/%s/queues/%s" user-id (:queue-id queue-item))))
        (bad-request-response (queue-view/index
                               (first (queue-service/find-queue-by-id user-id (:queue-id queue-item) db))
                               msg))))
    (forbidden-response)))

(defn- delete-queue-item [req db]
  (if-let [user-id (session/check-user-id req db)]
    (let [{:keys [queue-id queue-item-id]} (-> req :params)]
      (service/delete-queue-item queue-item-id db)
      (redirect (format "/users/%s/queues/%s" user-id queue-id)))
    (forbidden-response)))

(defn queue-item-endpoint [{{db :spec} :db}]
  (routes
   (context "/users/:user-id/queues/:queue-id/items" _
            (POST "/" {:as req} (create-queue-item req db))
            (DELETE "/:queue-item-id" {:as req} (delete-queue-item req db)))))
