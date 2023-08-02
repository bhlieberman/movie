(ns movie.common.event)

(defprotocol IEvent
  (on [this e]))