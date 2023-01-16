(defproject metosin/compojure-api "1.2.0-SHADED-4"
  :description "Compojure Api"
  :url "https://github.com/metosin/compojure-api"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments "same as Clojure"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [potemkin "0.4.3"]
                 [cheshire "5.6.3"]
                 [compojure "1.5.1"]
                 [prismatic/schema "1.1.3"]
                 [prismatic/plumbing "0.5.3"]
                 [org.tobereplaced/lettercase "1.0.0"]
                 [frankiesardo/linked "1.2.9"]
                 
                 ^:inline-dep [metosin/muuntaja "0.2.0-20161031.085120-3" :upgrade false]
                 [metosin/ring-http-response "0.8.0"]
                 [metosin/ring-swagger "0.22.11"]
                 [metosin/ring-swagger-ui "2.2.5-0"]
                 [org.flatland/ordered "1.15.10"]]
  :mranderson {:unresolved-tree true
               :included-source-paths :source-paths
               :overrides {[metosin/muuntaja org.yaml/snakeyaml] [org.yaml/snakeyaml "1.33"]}
               :expositions [[metosin/muuntaja org.yaml/snakeyaml]]}
  :profiles {:uberjar {:aot :all}
            ;;  :dev {:repl-options {:init-ns user}
            ;;        :plugins [[lein-clojars "0.9.1"]
            ;;                  [lein-midje "3.2.1"]
            ;;                  [lein-ring "0.9.7"]
            ;;                  [funcool/codeina "0.5.0"]]
            ;;        :dependencies [[org.clojure/clojure "1.8.0"]
            ;;                       [slingshot "0.12.2"]
            ;;                       [peridot "0.4.4"]
            ;;                       [javax.servlet/servlet-api "2.5"]
            ;;                       [midje "1.8.3"]
            ;;                       [com.stuartsierra/component "0.3.1"]
            ;;                       [reloaded.repl "0.2.3"]
            ;;                       [http-kit "2.2.0"]
            ;;                       [criterium "0.4.4"]
            ;;                       ; Required when using with Java 1.6
            ;;                       [org.codehaus.jsr166-mirror/jsr166y "1.7.0"]]
            ;;        :ring {:handler examples.thingie/app
            ;;               :reload-paths ["src" "examples/thingie/src"]}
            ;;        :source-paths ["examples/thingie/src" "examples/thingie/dev-src"]
            ;;        :main examples.server}
             :perf {:jvm-opts ^:replace ["-server"
                                         "-Xmx4096m"
                                         "-Dclojure.compiler.direct-linking=true"]}
             :logging {:dependencies [[org.clojure/tools.logging "0.3.1"]]}
             :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}}
  :eastwood {:namespaces [:source-paths]
             :add-linters [:unused-namespaces]}
  :codeina {:sources ["src"]
            :target "gh-pages/doc"
            :src-uri "http://github.com/metosin/compojure-api/blob/master/"
            :src-uri-prefix "#L"}
  :uber-shade {:relocations [{:from muuntaja :to muuntaja.shade}]}
  :plugins [[thomasa/mranderson "0.5.3"]]
  :aliases {"all" ["with-profile" "dev:dev,logging:dev,1.7"]
            "start-thingie" ["run"]
            "aot-uberjar" ["with-profile" "uberjar" "do" "clean," "ring" "uberjar"]
            "test-ancient" ["midje"]
            "perf" ["with-profile" "default,dev,perf"]
            "deploy!" ^{:doc "Recompile sources, then deploy if tests succeed."}
                      ["do" ["clean"] ["midje"] ["deploy" "clojars"]]})
