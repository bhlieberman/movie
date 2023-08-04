(ns movie.view.core
  (:require [goog.dom :as gdom]
            [goog.events :refer [listen]]
            [movie.common.event :as e]
            [movie.view.dom :as d]))

(defprotocol IRenderable
  (-render [this el]))

(defn render-dom [this & els]
  (-render this els))

(defprotocol IView
  (remove-element [this])
  (update-element [this el]))

(deftype View [^{:mutable true} element]
  e/IEvent
  ;; should having event on single parent DOM element suffice? 
  (on [this e] (listen (.-element this) e (fn [_] (js/console.log "listening to event"))))
  IView
  (remove-element [this] (set! (.-element this) nil))
  (update-element [this el] (set! (.-element this) el))
  IRenderable
  (-render [this el] (if el (gdom/appendChild (.-body js/document) el)
                         (gdom/appendChild (.-body js/document) (.-element this)))))