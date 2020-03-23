(ns test.storefront
    (:require
      [clojure.common.other :as co]
      [clojure.storefront :as cs]
	  [clojure.string :as str]
	  [clojure.test :refer :all]
    [eggplant.core :refer :all]
  )
      (:import
        (java.util Date TimeZone Locale Calendar)
      )
    )

(def store_setting (ref {
	"shop_name" "store-354",
	"shop_cache" "7xuqbyzkli",
	"access_token" "7szy414j7vqgcloog7qq15idhfc7ut9",
	"client_id"  "9woozsildiyjkk9x3inyrc4r0nki3cu",
	"client_secret" "968f49b8a492a50104e19ecd373a2952e7e1ab86f1d905158b3da1fb589b796c"
}))

(defspec ^:user001 test-id-to-base64
  (specification
   {:given ":type ('bigcommerce')と:id (1)は決められた"
    :when  "we use #clojure.storefront/id-to-base64 to test it"
    :then  "we can expect a :result ('Z2lkOi8vYmlnY29tbWVyY2UvYmlnY29tbWVyY2UvMQ==')"
    :data {:type "bigcommerce" :id 1 :result "Z2lkOi8vYmlnY29tbWVyY2UvYmlnY29tbWVyY2UvMQ=="}})
         )

(defspec ^:user002 test-verify-token-over-expires
  (binding [cs/store_setting store_setting]
     (let [token (cs/create-jwt {:a 1 :b 2} (Date. (- (.getTime (Date.)) (* 24 3600 1000))))]
       (specification
        {:given (str ":token (" token ") expires= -1 month は決められた")
         :when  "we use #clojure.storefront/verify-jwt to test it"
         :then  "we can expect a :result ({:error 'error'})"
         :data {:token token :result {:error "error"}}})
            )
       )
      )

(defspec ^:user003 test-verify-token-by-value
  (binding [cs/store_setting store_setting]
     (let [time (+ (.getTime (Date.)) (* 24 3600 1000))
           token (cs/create-jwt {:a 1 :b 2} (Date. time))
           time (int (/ time 1000))]
       (specification
        {:given (str ":token (" token ") expires= +1 month は決められた")
         :when  "we use #clojure.storefront/verify-jwt to test it"
         :then  (str "we can expect a :result ({':a' 1, 'iss' 'bigcommerce', ':b' 2, 'exp' " time "})")
         :data {:token token :result {":a" 1, "iss" "bigcommerce", ":b" 2, "exp" time}}})
            )
       )
      )
