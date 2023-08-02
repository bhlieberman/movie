(ns movie.model.tests
  (:require [cljs.test :refer [deftest is testing run-tests]]
            [movie.model.core :as m]))

(deftest map-model-is-valid
  (testing "that a PersistentArrayMap can be used as an IModel"
    (let [sut {:foo 1 :bar 2}]
      (is (implements? m/IModel sut))
      (is (= (m/-delete sut :foo) {:bar 2}))
      (is (= (m/-read sut :baz) "no such field in the model")))))

(deftest array-model-is-valid
  (testing "that a PersistentVector can be used as an IModel"
    (let [sut [:foo 1 :bar 2]]
      (is (implements? m/IModel sut))
      (is (= (m/-create sut) {:foo 1 :bar 2})))))

(run-tests)