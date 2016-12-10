(ns gt1gv1.validator.queue-item
  (:require [bouncer.core :as b]
            [bouncer.validators :as v]))

(def queue-id
  [v/required [v/integer]])

(def queue-item-content
  [v/required [v/string]])

(defn validate-queue-item [queue]
  (b/validate
   (assoc queue :queue-id (read-string (:queue-id queue)))
   :content queue-item-content
   :queue-id ))
