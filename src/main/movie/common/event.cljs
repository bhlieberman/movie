(ns movie.common.event)

(defprotocol IEvent
  (on [this e]))

(extend-protocol IEvent
  object
  (on [this e] (js/console.log "listening to this object?")))