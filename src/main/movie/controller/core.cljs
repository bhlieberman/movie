(ns movie.controller.core
  (:import [goog.net XhrIo]))

(defprotocol IController
  (fetch [this model]))

(extend-protocol IController
  XhrIo
  (fetch [this _] (.send this "http://icanhazip.com")))