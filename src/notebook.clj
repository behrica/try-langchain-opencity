(ns notebook
  (:require [nextjournal.clerk :as clerk]
            [libpython-clj2.python :as py]
            [libpython-clj2.require :refer [require-python]]))

(comment
  (clerk/serve! {:browse? false})
  (clerk/show! (find-ns 'notebook))
  (nextjournal.clerk/show! 'nextjournal.clerk.tap))


(py/initialize!)

(require-python '[builtins :as bt])
(py/from-import langchain.document_loaders OpenCityDataLoader)

(def loader (OpenCityDataLoader  :city_id "data.sfgov.org",
                                 :dataset_id "tmnf-yvry",
                                 :limit 2000))



(->
 (py/py. loader load)

 first
 (py/py.- page_content)
 (bt/eval  {})
 (py/->jvm))
