(ns annotator.core
  (:require [annotator.config :as config]
            [compojure.core :refer [defroutes GET]]
            [compojure.handler :refer [site]]
            [compojure.route :refer [resources not-found]]
            [ring.util.response :refer [resource-response]]
            [net.cgrand.enlive-html :refer [deftemplate set-attr]]
            [taoensso.timbre :as timbre]))

(deftemplate main-template "templates/index.html" []
  [:#reactjs] (if config/production
               (set-attr :src "/js/react.min.js")
               (set-attr :src "/js/react.js"))
  [:#mainjs] (if config/production
               (set-attr :src "/js/main.min.js")
               (set-attr :src "/js/main.js")))

(defroutes app-routes
  (GET "/" [] (main-template))
  (GET "/js/react.js" [] (resource-response "reagent/react.js"))
  (GET "/js/react.min.js" [] (resource-response "reagent/react.min.js"))
  (resources "/")
  (not-found "Page not found"))

(defn init []
  (timbre/set-level! :info)
  (timbre/info "Starting in" (if config/production "production" "debug") "mode"))

(def handler (site app-routes))
