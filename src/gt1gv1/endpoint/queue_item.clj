(ns gt1gv1.endpoint.queue-item
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [gt1gv1.util.session :as session]
            [gt1gv1.util.response :refer [forbidden-response bad-request-response]]
            [gt1gv1.view.queue :as view]
            [gt1gv1.service.queue :as service]
            [gt1gv1.validator.queue :as validator]
            [ring.util.response :refer [redirect]]))

(defn- create-queue-item [req db]
  (if-let [user-id (session/check-user-id req db)]
    '()
    (forbidden-response)))

(defn queue-endpoint [{{db :spec} :db}]
  (routes
   (context "/users/:user-id/queues/:queue-id/items" _
            (POST "/" {:as req} (create-queue-item req db)))))
