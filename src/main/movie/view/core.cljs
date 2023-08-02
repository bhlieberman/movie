(ns movie.view.core
  (:require [goog.dom :as gdom]
            [goog.events :refer [listen]]
            [movie.common.event :as e]
            [movie.view.dom :as d])
  (:import [goog.string Const]))

(defprotocol IRenderable
  (-render [this el]))

(deftype View [^{:mutable true} el]
  e/IEvent
  (on [this e] (listen this e (fn [_] (js/console.log "listening to event"))))
  IRenderable
  (-render [this el]
    (let [div (d/create-dom)
          node (cond 
                 (string? el)
                 (-> el
                     Const/from
                     gdom/constHtmlToNode)
                 (= "object" (goog/typeOf el))
                 el)]
      (gdom/appendChild div node)
      (set! (.-el this) div)
      (gdom/appendChild (.-body js/document) div)
      this)))