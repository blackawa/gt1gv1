(ns gt1gv1.validator.queue
  (:require [bouncer.core :as b]
            [bouncer.validators :as v]))

(def queue-name
  [v/required [v/max-count 32]])

(defn validate-queue [queue]
  (b/validate
   queue
   :get-title queue-name
   :give-title queue-name))
