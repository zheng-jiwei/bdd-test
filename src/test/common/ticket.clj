(ns test.common.ticket
    (:require
    [clojure.common.ticket :as ct]
	  [clojure.test :refer :all]
    [eggplant.core :refer :all]
	  [clojure.string :as str]
      )
    )

; (deftest ^:user001 test-ticket-discount-65-years-old-weekday
;   ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
;   (testing
;     (= 0.5 (ct/get-ticket-discount-by-condition 65 1 false true))
;     (= 0.5 (ct/get-ticket-discount-by-condition 65 1 false false))
;        )
;   )

(defspec ^:user001 test-ticket-discount-65-years-old-weekday
  (specification
   {:given "a input of :age (65才) and :week (平日) and :isholiday  (祝日ではない) and :withCoupon (クーポンなし)"
    :when  "we use #clojure.common.ticket/get-ticket-discount-by-condition"
    :then  "we can expect a :result (0.5)"
    :data {:age 65 :week 1 :isholiday  false :withCoupon true :result 0.5}}))


; (deftest ^:user001 test-ticket-discount-65-years-old-holiday-with-coupon
;   ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
;   (is (= 0.1 (ct/get-ticket-discount-by-condition 65 1 true true)))
;   )

(defspec ^:user001 test-ticket-discount-65-years-old-holiday-with-coupon
  (specification
    {:given "a input of :age (65歳) and :week (平日) and :isholiday  (祝日) and :withCoupon (クーポンあり)"
    :when  "we use #clojure.common.ticket/get-ticket-discount-by-condition"
    :then  "we can expect a :result (0.1)"
    :data {:age 65 :week 1 :isholiday  true :withCoupon true :result 0.1}}))


; (deftest ^:user001 test-ticket-discount-65-years-old-holiday-without-coupon
;   ; get-ticket-discount-by-condition [^Integer age ^Integer weekday isHoliday hasCoupon]
;   (is (= 0 (ct/get-ticket-discount-by-condition 65 1 true false)))
;   )

(defspec ^:user001 test-ticket-discount-65-years-old-holiday-without-coupon
  (specification
    {:given "a input of :age (65歳) and :week (平日) and :isholiday  (祝日) and :withCoupon (クーポンなし)"
    :when  "we use #clojure.common.ticket/get-ticket-discount-by-condition"
    :then  "we can expect a :result (0)"
    :data {:age 65 :week 1 :isholiday  true :withCoupon false :result 0}})
         )


(defspec ^:user001 test-ticket-discount-65-years-old-weekend-with-coupon
  (specification
    {:given "a input of :age (65歳) and :week (土日) and :isholiday  (祝日ではない) and :withCoupon (クーポンあり)"
    :when  "we use #clojure.common.ticket/get-ticket-discount-by-condition"
    :then  "we can expect a :result (0.1)"
    :data {:age 65 :week 6 :isholiday  false :withCoupon true :result 0.1}})
  )

(defspec ^:user001 test-ticket-discount-65-years-old-weekend-without-coupon
  (specification
    {:given "a input of :age (65歳) and :week (土日) and :isholiday  (祝日ではない) and :withCoupon (クーポンなし)"
    :when  "we use #clojure.common.ticket/get-ticket-discount-by-condition"
    :then  "we can expect a :result (0)"
    :data {:age 65 :week 6 :isholiday  false :withCoupon false :result 0}})
  )

(defspec ^:user002 test-ticket-discount-14-years-old-weekend
  (specification
    {:given "a input of :age (14歳) and :week (土日) and :isholiday  (祝日ではない) and :withCoupon (クーポンあり)"
    :when  "we use #clojure.common.ticket/get-ticket-discount-by-condition"
    :then  "we can expect a :result (0.2)"
    :data {:age 14 :week 6 :isholiday  false :withCoupon true :result 0.2}})
  )

(defspec ^:user002 test-ticket-discount-14-years-old-weekend
  (specification
    {:given "a input of :age (14歳) and :week (平日) and :isholiday  (祝日) and :withCoupon (クーポンあり)"
    :when  "we use #clojure.common.ticket/get-ticket-discount-by-condition"
    :then  "we can expect a :result　（0.2）"
    :data {:age 14 :week 1 :isholiday  true :withCoupon true :result 0.2}})
  )

(defspec ^:user003 test-ticket-discount-15-years-old-weekday
  (specification
    {:given "a input of :age (15歳) and :week (平日) and :isholiday  (祝日) and :withCoupon (クーポンあり)"
    :when  "we use #clojure.common.ticket/get-ticket-discount-by-condition"
    :then  "we can expect a :result (0.3)"
    :data {:age 15 :week 1 :isholiday  true :withCoupon true :result 0.3}})
  )

(defspec ^:user003 test-ticket-discount-15-years-old-weekend-with-coupon
  (specification
    {:given "a input of :age (15歳) and :week (土日) and :isholiday  (祝日ではない) and :withCoupon (クーポンあり)"
    :when  "we use #clojure.common.ticket/get-ticket-discount-by-condition"
    :then  "we can expect a :result　（0.1）"
    :data {:age 15 :week 6 :isholiday  false :withCoupon true :result 0.1}})
  )
