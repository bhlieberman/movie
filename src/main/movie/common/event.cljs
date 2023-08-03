(ns movie.common.event
  (:refer-clojure :exclude [pop peek])
  (:require [goog.log :as glog])
  (:import [goog.events Event]
           [goog.debug Console]
           [goog.pubsub PubSub]))

(def ^:dynamic logging-on? glog/ENABLED)

(when logging-on?
  (.setCapturing (Console.) true))

(defprotocol IEvent
  (on [this e]))

(defprotocol IEventQueue
  (push [this e args])
  (pop [this])
  (peek [this]))

(defn- push-event [queue event & args]
  (push queue event args))

(extend-type PubSub
  IEventQueue
  (push [this e args]
    (glog/info (glog/getLogger "goog.debug.Console") "publishing to channel")
    (.publish this e args)))

(extend-protocol IEvent
  Event
  (on [this _] (.-currentTarget this))
  PersistentArrayMap
  (on [this _e] (keys this)))

(comment
  (let [e (Event. "click" (.createElement js/document "div"))
        chan (PubSub. true)]
    (.subscribe chan "developing an MVC framework" (fn [args] (doall (map println args))))
    (push-event chan "developing an MVC framework" "create models" "manipulate DOM efficiently")))