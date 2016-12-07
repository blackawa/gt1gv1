(defproject gt1gv1 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.1"]
                 [compojure "1.5.1"]
                 [duct "0.8.2"]
                 [environ "1.1.0"]
                 ;; web application
                 [ring "1.5.0"]
                 [ring/ring-defaults "0.2.1"]
                 [ring-jetty-component "0.3.1"]
                 [ring-webjars "0.1.1"]
                 [org.webjars/normalize.css "3.0.2"]
                 ;; db access
                 [duct/hikaricp-component "0.1.0"]
                 [hanami "0.1.0"]
                 [org.postgresql/postgresql "9.4.1212"]
                 [honeysql "0.8.1"]
                 [duct/ragtime-component "0.1.4"]
                 ;; logging
                 [org.slf4j/slf4j-nop "1.7.21"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.slf4j/slf4j-api "1.7.21"]
                 [ch.qos.logback/logback-classic "1.1.7"]
                 ;; other libraries
                 [hiccup "1.0.5"]
                 [garden "1.2.5"]
                 [buddy "1.2.0"]
                 [bouncer "1.0.0"]]
  :plugins [[lein-environ "1.0.3"]
            [lein-gen "0.2.1"]
            [lein-garden "0.3.0"]]
  :main ^:skip-aot gt1gv1.main
  :uberjar-name "gt1gv1-standalone.jar"
  :target-path "target/%s/"
  :aliases {"setup"  ["run" "-m" "duct.util.repl/setup"]
            "deploy" ["do"
                      ["vcs" "assert-committed"]
                      ["vcs" "push" "heroku" "master"]]}
  :repl-options {:port 51551}
  :profiles
  {:dev  [:project/dev  :profiles/dev]
   :test [:project/test :profiles/test]
   :uberjar {:aot :all}
   :profiles/dev  {}
   :profiles/test {}
   :project/dev   {:dependencies [[duct/generate "0.8.2"]
                                  [reloaded.repl "0.2.3"]
                                  [org.clojure/tools.namespace "0.2.11"]
                                  [org.clojure/tools.nrepl "0.2.12"]
                                  [eftest "0.1.1"]
                                  [com.gearswithingears/shrubbery "0.4.1"]
                                  [kerodon "0.8.0"]]
                   :source-paths   ["dev/src"]
                   :resource-paths ["dev/resources"]
                   :repl-options {:init-ns user}
                   :env {:port "3000"}}
   :project/test  {}})
