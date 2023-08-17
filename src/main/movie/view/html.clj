(ns movie.view.html
  {:doc "stub ns for working on static views"}
  (:require [clojure.java.io :as io]
            [clojure.java.shell :refer [sh]])
  (:import [com.google.template.soy SoyFileSet SoyToJsSrcCompiler]
           [com.google.template.soy.tofu SoyTofu]))

(set! *warn-on-reflection* true)

(defmacro compile-template [f ^String template]
  (let [^SoyFileSet sfs (.. (SoyFileSet/builder) (add (io/resource f)) build)
        ^SoyTofu tofu (.compileToTofu sfs)] 
    (.. tofu (newRenderer template) render)))

(defmacro compile-soy-to-js [template-name]
  (if-not (= "11" (System/getProperty "java.vm.specification.version"))
    (throw (ex-info "This feature only works on Java 11, sorry!" {}))
    (sh "java" "-jar" "resources/SoyToJsSrcCompiler.jar" "--outputPathFormat" 
        (str "./src/js/movie/view/" template-name ".js") "--srcs" "resources/simple.soy")))

(comment (compile-template "simple.soy" "examples.simple.helloWorld")
         (compile-soy-to-js "simple"))

;; this works but System/exit is called by main and kills the REPL (sigh)
(comment 
  (let [args (make-array String 4)]
    (aset args 0 "--outputPathFormat")
    (aset args 1 "simple.js")
    (aset args 2 "--srcs")
    (aset args 3 "/home/bhlieberman/dev/clj/cljs/movie/resources/simple.soy")
    (SoyToJsSrcCompiler/main args))
  )

(comment 
  (System/getProperty "java.class.path"))