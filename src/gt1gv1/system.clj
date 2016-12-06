(ns gt1gv1.system
  (:require [com.stuartsierra.component :as component]
            [duct.component.endpoint :refer [endpoint-component]]
            [duct.component.handler :refer [handler-component]]
            [duct.component.hikaricp :refer [hikaricp]]
            [duct.component.ragtime :refer [ragtime]]
            [duct.middleware.not-found :refer [wrap-not-found]]
            [meta-merge.core :refer [meta-merge]]
            [ring.component.jetty :refer [jetty-server]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [gt1gv1.endpoint.top :refer [top-endpoint]]
            [gt1gv1.endpoint.sign :refer [sign-endpoint]]
            [gt1gv1.endpoint.queue :refer [queue-endpoint]]))

(def base-config
  {:app {:middleware [[wrap-not-found :not-found]
                      [wrap-defaults :defaults]]
         :not-found "Resource not found"
         :defaults (meta-merge site-defaults)}
   :http {:port 3000}})

(defn new-system [config]
  (let [config (meta-merge base-config config)]
    (-> (component/system-map
         :app (handler-component (:app config))
         :http (jetty-server (:http config))
         :db (hikaricp (:db config))
         :ragtime (ragtime {:resource-path "migrations"})
         :top (endpoint-component top-endpoint)
         :sign (endpoint-component sign-endpoint)
         :queue (endpoint-component queue-endpoint))
        (component/system-using
         {:ragtime [:db]
          :http [:app]
          :app [:top :sign :queue]
          :top [:db]
          :sign [:db]
          :queue [:db]}))))
