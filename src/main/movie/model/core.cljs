(ns movie.model.core)

(defprotocol IModel
  (-create [args])
  (-read [this k])
  (-update [this k v])
  (-delete [this] [this k]))

(extend-protocol IModel
  cljs.core/PersistentVector
  (-create [args] (apply hash-map args))
  (-read [this k] (if-not (even? k) 
                    (throw (ex-info "key to vector model must be an even integer" {}))
                    (nth this k)))
  (-update [this k v] (if-not (even? k)
                        (throw (ex-info "key to vector model must be an even integer" {}))
                        (assoc this k v)))
  (-delete 
    ([_] nil)
    ([this k] (into [] cat [(subvec this 0 k) (subvec this (+ 2 k))])))
  cljs.core/PersistentArrayMap
  (-create [this] this)
  (-read [this k] (get this k "no such field in the model"))
  (-update [this k v] (assoc this k v))
  (-delete 
    ([_] nil)
    ([this k] (dissoc this k))))