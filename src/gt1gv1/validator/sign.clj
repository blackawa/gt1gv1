(ns gt1gv1.validator.sign
  (:require [bouncer.core :as b]
            [bouncer.validators :as v]))

(def users-name
  [v/required [v/matches #"^[a-zA-Z0-9_\-]+$"] [v/max-count 32]])
(def users-passwd
  [v/required [v/matches #"^[a-zA-Z0-9_\-!&%$#@\+]+"] [v/min-count 8] [v/max-count 32]])

(defn validate-sign-up [params]
  (b/validate
   params
   :name users-name
   :passwd users-passwd))

(defn validate-sign-in [params]
  (b/validate
   params
   :name users-name
   :passwd users-passwd))
