(ns gt1gv1.component.graphite
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [metrics.reporters.graphite :as graphite])
  (:import [java.util.concurrent TimeUnit]
           [com.codahale.metrics MetricFilter]))

(defrecord Graphite []
  component/Lifecycle
  (start [component]
    (if (:graphite component)
      component
      (let [reporter (graphite/reporter {:host "localhost"
                                         :prefix "gt1gv1"
                                         :rate-unit TimeUnit/SECONDS
                                         :duration-unit TimeUnit/MILLISECONDS
                                         :filter MetricFilter/ALL})]
        (graphite/start reporter 10)
        (log/info "Started Graphite Reporting")
        (assoc component :graphite reporter))))
  (stop [component]
    (if (:graphite component)
      (do
        (graphite/stop (:graphite component))
        (log/info "Stopped Graphite Reporting")
        (dissoc component :graphite))
      component)))

(defn graphite [options]
  (map->Graphite options))
