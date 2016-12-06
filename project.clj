(defproject gt1gv1 "0.1.0-SNAPSHOT"
  :description "Get 1, Give 1"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.1"]
                 [duct "0.8.2"]
                 [duct/hikaricp-component "0.1.0"]
                 [duct/ragtime-component "0.1.4"]
                 [environ "1.1.0"]
                 [meta-merge "1.0.0"]
                 [compojure "1.5.1"]
                 [ring "1.5.0"]
                 [ring/ring-defaults "0.2.1"]
                 [ring-jetty-component "0.3.1"]
                 [hiccup "1.0.5"]
                 [garden "1.2.5"]
                 ;; db access
                 [org.clojure/java.jdbc "0.7.0-alpha1"]
                 [org.postgresql/postgresql "9.4.1212"]
                 [honeysql "0.8.1"]]
  :plugins [[lein-environ "1.1.0"]
            [lein-gen "0.2.1"]]
  :duct {:ns-prefix gt1gv1} ;; for lein-generator
  :main ^:skip-aot gt1gv1.core
  :target-path "target/%s/"
  :resource-paths ["resources"]
  :prep-tasks ["compile"]
  :aliases {"gen" ["generate"]
            "setup" ["do" ["generate locals"]]}
  :generators [[lein-gen/generators "0.2.1"]]
  :profiles {:dev  [:project/dev  :profiles/dev]
             :test [:project/test :profiles/test]
             :uberjar {:aot :all}
             :profiles/dev  {}
             :profiles/test {}
             :project/dev  {:source-paths ["dev"]
                            :repl-options {:init-ns user}
                            :dependencies [[reloaded.repl "0.2.3"]
                                           [org.clojure/tools.namespace "0.2.11"]]
                            :env {:port 3000}}
             :project/test {}})
