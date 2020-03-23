(ns test.common.other
    (:require
	  [clojure.string :as str]
	  [clojure.test :refer :all]
    [clojure.common.other :as co]
    [eggplant.core :refer :all]
  )
      (:import
        (java.util Date TimeZone Locale Calendar)
        (java.text DateFormat)
      )
    )

(defspec ^:user003 test-gtm-string
  (let [df (DateFormat/getDateInstance DateFormat/LONG, Locale/US)]
    (specification
     {:given ":date ('March 18, 2018')と:format（'YYYY-MM-dd-HH'）が決められた"
      :when  "#clojure.common.other/get-gmt-string ファンクションを利用する"
      :then  ":result（'2018-03-17-15'）が期待される"
      :data {:date (.parse df "March 18, 2018") :format "YYYY-MM-dd-HH" :result "2018-03-17-15"}}
    )
  )
)
