(ns movie.view.core
  (:require [goog.dom :as gdom]
            [goog.events :refer [listen]]
            [goog.object :as gobj]
            [goog.style :as gstyle]
            [goog.soy :as soy]
            [movie.common.event :as e]
            [movie.view.html :as h]
            [movie.view.dom :as d]))

(defprotocol IRenderable
  (-render [this el]))

(defn render-dom [this & els]
  (-render this els))

(defprotocol IView
  (remove-element [this])
  (update-element [this el]))

(deftype View [^{:mutable true} element]
  IWithMeta
  (-with-meta [o m]
    (doseq [[k v] m]
      (gobj/set o k v)))
  e/IEvent
  ;; should having event on single parent DOM element suffice? 
  (on [this e] (listen (.-element this) e (fn [evt] (js/console.log (.-type evt)))))
  IView
  (remove-element [this] (set! (.-element this) nil))
  (update-element [this el] (set! (.-element this) el))
  IRenderable
  (-render [this el] (if-some [element (.-element this)]
                       (gdom/appendChild element el)
                       (update-element this el))))


;; possible approach to setting styles on View creation?
(def v (let [view-with-meta (->View (gdom/getElement "app"))]
         (-with-meta view-with-meta {"style" #js {"backgroundColor" "grey"}})
         view-with-meta))

(defn set-style [v]
  (let [element (.-element v)]
    (when-some [style (.-style v)]
      (gstyle/setStyle element style))))

;; ok this works, probably can be improved
(comment (h/compile-template "simple.soy" "examples.simple.helloWorld"))