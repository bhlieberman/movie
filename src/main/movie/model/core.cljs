(ns movie.model.core)

(defprotocol IModel
  (-create [this args])
  (-read [this k])
  (-update [this k])
  (-delete [this] [this k]))