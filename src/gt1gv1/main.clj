(ns gt1gv1.main
    (:gen-class)
    (:require [clojure.tools.logging :as log]
              [com.stuartsierra.component :as component]
              [duct.util.runtime :refer [add-shutdown-hook]]
              [duct.util.system :refer [load-system]]
              [environ.core :refer [env]]
              [clojure.java.io :as io]
              [hanami.core :as hanami]))

(defn -main [& args]
  (let [bindings {'http-port (Integer/parseInt (:port env "3000"))
                  'db-uri    (:database-url env) ;; (hanami/jdbc-uri (:database-url env))
                  }
        system   (->> (load-system [(io/resource "gt1gv1/system.edn")] bindings)
                      (component/start))]
    (add-shutdown-hook ::stop-system #(component/stop system))
    (log/infof "Started HTTP server on port %s" (-> system :http :port))
    (start-report)))
