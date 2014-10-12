(ns annotator.core
  (:require [reagent.core :as reagent]))

(defn app []
  [:div "It worked!"])

(defn by-id [id]
  (.getElementById js/document id))

(defn main []
  (reagent/render-component [app] (by-id "app")))

(set! (.-onload js/window) main)
