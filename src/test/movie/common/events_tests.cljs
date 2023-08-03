(ns movie.common.events-tests
  (:require [cljs.test :refer [deftest is testing run-tests]]
            [goog.dom :as gdom] 
            [goog.testing.events :as t]
            [movie.common.event :as e])
  (:import [goog.testing.events Event]))

(deftest events-are-triggered-by-dom-manipulation
  (testing "that DOM manipulation triggers Events as defined in movie.common.event"
    (let [button (gdom/createElement "button")
          event (Event. "click" button)]
      (is (t/fireClickEvent button)))))

(run-tests)