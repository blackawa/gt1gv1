(ns gt1gv1.graphite
  (:require [clojure.tools.logging :as log]
            [metrics.reporters.graphite :as graphite])
  (:import [java.util.concurrent TimeUnit]
           [com.codahale.metrics MetricFilter]))

(def GR (graphite/reporter {:host "localhost"
                            :prefix "gt1gv1"
                            :rate-unit TimeUnit/SECONDS
                            :duration-unit TimeUnit/MILLISECONDS
                            :filter MetricFilter/ALL}))

(defn start-report []
  (graphite/start GR 10)
  (log/infof "Started Graphite Reporting"))

(defn stop-report []
  (graphite/stop GR))
