(ns gt1gv1.validator.sign
  (:require [bouncer.core :as b]
            [bouncer.validators :as v]))

(defn validate-sign-up [params]
  (b/validate
   params
   :name [v/required [v/matches #"^[a-zA-Z0-9_\-]+$"] [v/max-count 32]]
   :passwd [v/required [v/matches #"^[a-zA-Z0-9_\-!&%$#@\+]+"] [v/min-count 8] [v/max-count 32]]))
