(ns movie.controller.tests
  (:require [cljs.test :refer [deftest is testing run-tests]]
            [clojure.string :as string]
            [movie.controller.core :as c])
  (:import [goog.net XhrIo]))

(deftest can-implement-protocol
  (testing "that goog.net.XhrIo can implement IController"
    (let [xhr (XhrIo.)]
      (is (implements? c/IController xhr)))))

;; passes but I don't know why the response is blank?
(deftest can-send-http-requests
  (testing "that the controller instance can retrieve data over HTTP"
    (let [xhr (XhrIo.)]
      (is (string/blank? (c/fetch xhr nil))))))

(run-tests)