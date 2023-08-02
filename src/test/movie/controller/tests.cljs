(ns movie.controller.tests
  (:require [cljs.test :refer [deftest is testing run-tests]]
            [movie.controller.core :as c])
  (:import [goog.net XhrIo]))

(deftest can-implement-protocol
  (testing "that goog.net.XhrIo can implement IController"
    (let [xhr (XhrIo.)]
      (is (implements? c/IController xhr)))))

(deftest can-send-http-requests
  (testing "that the controller instance can retrieve data over HTTP"
    (let [xhr (XhrIo.)]
      (c/fetch xhr nil))))

(run-tests)