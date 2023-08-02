(ns movie.view.dom
  (:require [goog.dom :as gdom])
  (:import [goog.dom TagName]))

(defn create-dom []
  (gdom/createDom TagName.DIV nil))

(comment
  (let [div (create-dom)]
    (gdom/setTextContent div "testing 1 2 3")
    (gdom/appendChild (.-body js/document) div)))