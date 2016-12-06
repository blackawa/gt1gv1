(ns gt1gv1.system
  (:require [com.stuartsierra.component :as component]
            [duct.component.endpoint :refer [endpoint-component]]
            [duct.component.handler :refer [handler-component]]
            [duct.middleware.not-found :refer [wrap-not-found]]
            [meta-merge.core :refer [meta-merge]]
            [ring.component.jetty :refer [jetty-server]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
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
         :queue (endpoint-component queue-endpoint))
        (component/system-using
         {:http [:app]
          :app [:queue]}))))
