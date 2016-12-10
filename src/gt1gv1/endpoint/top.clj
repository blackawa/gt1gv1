(ns gt1gv1.endpoint.top
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [gt1gv1.view.top :as view]))

(defn index [req db]
  (view/index))

(defn top-endpoint [{{db :spec} :db}]
  (routes
   (GET "/" {:as req} (index req db))))
