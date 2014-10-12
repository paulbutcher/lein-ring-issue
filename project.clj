(defproject annotator "0.1"
  :source-paths ["src-clj"]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371"]
                 [reagent "0.4.2"]
                 [compojure "1.2.0"]
                 [enlive "1.1.5"]
                 [com.taoensso/timbre "3.3.1"]]
  :plugins [[lein-ancient "0.5.5"]
            [lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.11"]]
            ; [lein-ring "0.8.13-SNAPSHOT"]]
  :profiles {:production {:resource-paths ["target/production"]
                          :source-paths ["production/src-clj"]}
             :dev {:resource-paths ["target/dev"]
                   :source-paths ["dev/src-clj"]}
             :uberjar [:production {:aot :all}]}
  :cljsbuild {:builds {:dev {:source-paths ["src-cljs"]
                             :compiler {:output-dir "target/dev/public/js"
                                        :output-to "target/dev/public/js/main.js"
                                        :optimizations :whitespace
                                        :pretty-print true
                                        :source-map "target/dev/public/js/main.map"}}
                       :production {:source-paths ["src-cljs"]
                                    :compiler {:output-to "target/production/public/js/main.min.js"
                                               :optimizations :advanced
                                               :externs ["react/react.js"]
                                               :closure-warnings {:check-useless-code :off}
                                               :pretty-print false}}}}
  :ring {:init annotator.core/init
         :handler annotator.core/handler})
