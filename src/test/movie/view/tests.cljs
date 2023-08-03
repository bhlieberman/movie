(ns movie.view.tests
  (:require [cljs.test :refer [deftest is testing run-tests]]
            [goog.dom :as gdom]
            [goog.testing.events :refer [fireClickEvent]]
            [movie.view.core :as c]
            [movie.view.dom :as d]
            [movie.common.event :as e])
  (:import [goog.events EventType]))

(deftest views-are-instantiable
  (testing "that views can be created and can render to the DOM"
    (let [v (c/->View nil)
          html "<p>Hello Movie!</p>"]
      (is (= v (c/-render v html)))))
  (testing "that existing HTML can be rendered to the DOM"
    (let [sut (c/->View nil)
          div (d/create-dom)]
      (gdom/setTextContent div "testing existing HTML")
      (c/-render sut div))))

(deftest views-can-modify-their-element
  (testing "that the element field of a view is mutable and can be set correctly"
    (let [v (c/->View nil)]
      (set! (.-el v) (d/create-dom))
      (is "object" (goog/typeOf (.-el v))))))

(deftest can-add-event-listeners-to-views
  (testing "that the IEvent prtocol will add an event listener to a view"
    (let [v (c/->View (d/create-dom))
          html "<p>Hello Movie!</p>"]
      (c/-render v html)
      (gdom/setTextContent (.-el v) "testing event listeners")
      (e/on v EventType.CLICK)
      (is (fireClickEvent (.-el v))))))

(deftest removing-view-element
  (testing "that removing view element does not touch DOM"
    (let [v (c/->View (gdom/getElement "highlight-text"))]
      (c/remove-element v)
      (is (gdom/getElement "highlight-text")))))

(run-tests)