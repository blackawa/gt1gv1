(ns gt1gv1.core
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [duct.middleware.errors :refer [wrap-hide-errors]]
            [meta-merge.core :refer [meta-merge]]
            [gt1gv1.config :as config]
            [gt1gv1.system :refer [new-system]]))

(def prod-config
  {:app {:middleware [[wrap-hide-errors :internal-error]]
         :internal-error "Internal Server Error!"}})

(def config
  (meta-merge config/defaults
              config/environ
              prod-config))

(defn -main [& args]
  (let [system (new-system config)]
    (println "starting http server on port" (-> system :http :port))
    (component/start system)))
