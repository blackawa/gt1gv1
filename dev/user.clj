(ns user
  (:require [clojure.repl :refer :all]
            [clojure.tools.namespace.repl :refer [refresh]]
            [clojure.java.io :as io]
            [duct.util.repl :refer [migrate rollback]]
            [meta-merge.core :refer [meta-merge]]
            [reloaded.repl :refer [system init start stop go reset]]
            [ring.middleware.stacktrace :refer [wrap-stacktrace]]
            [gt1gv1.config :as config]
            [gt1gv1.system :as system]))

(def dev-config
  {:app {:middleware [wrap-stacktrace]}
   :db  {:uri "jdbc:postgresql://localhost:5432/gt1gv1"}})

(def config
  (meta-merge config/defaults
              config/environ
              dev-config))

(defn new-system []
  (system/new-system config))

(when (io/resource "local.clj")
  (load "local"))

(reloaded.repl/set-init! new-system)
