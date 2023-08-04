(ns movie.user
  (:require [movie.view.core :refer [->View -render render-dom
                                     remove-element]]
            [movie.common.event :refer [on]]
            [goog.dom :as gdom]))

(def v (->View (gdom/getElement "highlight-text")))

(set! (.. v -element -textContent) "Change font size")

(on v "click")

;; expect an element
(set! (.-element v) (gdom/getElement "app"))

(comment
  (let [els (apply array (repeatedly 10 #(gdom/createElement "div")))] 
   (.forEach els (fn [e] (set! (.-textContent e) "foo"))) 
   (gdom/append (.-element v) els)))