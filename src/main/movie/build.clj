(ns movie.build
  (:require [clojure.java.shell :as sh]))

(defn compile-templates 
  {:shadow.build/stage :flush}
  [build-state]
  (let [res (sh/sh "java" "-jar" "SoyToJsSrcCompiler.jar" "--outputPathFormat" "../public/simple.js" "--srcs" "simple.soy")]
    (if (zero? (:exit res)) (println "successfully compiled template") (println "something went wrong"))
    build-state))