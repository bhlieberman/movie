(ns movie.controller.local
  (:require [movie.controller.core :as c])
  (:import [goog.storage Storage]
           [goog.storage.mechanism HTML5LocalStorage]))

(defn fetch [k]
  (let [s (Storage. (HTML5LocalStorage.))] 
    (c/fetch s k)))

(comment
  (fetch "myName"))