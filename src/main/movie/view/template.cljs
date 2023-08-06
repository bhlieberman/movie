(ns movie.view.template
  (:require [movie.view.html :as h])
  (:import [goog.soy.data SanitizedHtml]))

(deftype ViewTemplate [html])

(comment)