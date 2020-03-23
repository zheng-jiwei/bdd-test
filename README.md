
### テストコードの説明

- テスト実施できる粒度下記のように分けられています
  - lein test
    - parameterなしでdefaultを実施したら、:test-pathsで指定したパス配下にすべてcljファイルにある defspec ファンクションをテストします。
  - lein test test.common.ticket
    - テストファイルのnamespaceを指定したら、該当namespaceに定義した defspecをすべてテストします。
  - lein test :user001
    - keywordを指定したら、defspec ^:user001 のような該当keywordを明確標記したファンクションをテストします。
  - lein test :only test.common.ticket/function-name 　
    - namespaceとfunction-nameを指定したら、指定したnamespaceにある function-nameのみテストします。
  - 組み合わせテストも可能です
```
   lein test :user001 :user002
   //:user001 と :user002 を標記した defspec を実施する

   lein test :user003 test.common.ticket
   //:user003を標記したdeftestとnamespace test.common.ticketにあるすべてdefspecを実施する

   lein test test.common.ticket test.common.other
   //namespace test.common.ticketとtest.common.otherにあるすべてdefspecを実施する
```

- テスト実施を分けるため、project.cljに下記のようなコードを追加します
```
  :test-paths
  :test-selectors {:default (complement :all)
  　　　　　    　 :user001 :user001
  　　　　　    　 :user002 :user002
  　　　　　    　 :user003 :user003
　　　　　　　　:all (fn[_] true)}
```

- テストファンクションの構成

  ```
  　(defspec ^keyword function-name)
   例：
	(defspec ^:user001 test-id-to-base64
	  (specification
	   {:given ":type ('bigcommerce')と:id (1)は決められた"
	    :when  "we use #clojure.storefront/id-to-base64 to test it"
	    :then  "we can expect a :result ('Z2lkOi8vYmlnY29tbWVyY2UvYmlnY29tbWVyY2UvMQ==')"
	    :data {:type "bigcommerce" :id 1 :result "Z2lkOi8vYmlnY29tbWVyY2UvYmlnY29tbWVyY2UvMQ=="}})
	         )

  ```
  - keyword は test-selectorsに定義したkeywordの一つを選べる
　- function-nameは任意
