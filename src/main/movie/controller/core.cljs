(ns movie.controller.core
  (:import [goog.net XhrIo]
           [goog.storage Storage]))

(defprotocol IController
  (fetch [this model]))

(extend-protocol IController
  XhrIo
  (fetch [this _]
    (doto this
      (.setResponseType "text/plain")
      (.send "http://icanhazip.com"))
    (.getResponse this))
  Storage
  (fetch [this model]
    (.get this model)))