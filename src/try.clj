(ns try 
  (:require [libpython-clj2.python :as py]
            [libpython-clj2.require :refer [require-python]]))

(require-python '[builtins :as bt])
(py/initialize!)

(py/ '[builtins :as bt])
(py/from-import langchain.document_loaders OpenCityDataLoader)

(def loader (OpenCityDataLoader  :city_id "data.sfgov.org",
                                 :dataset_id "tmnf-yvry",
                                 :limit 2000))

(def docs (py/py. loader load))

(-> docs
    first
    (py/py.- page_content)
    (bt/eval  {})
    (py/->jvm))
