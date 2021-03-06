(defproject bdd-test-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [org.clojure/data.json "0.2.6"]
                 [eggplant "0.2.0"]
			   [clj-http "3.10.0"]
			   [com.auth0/java-jwt "3.8.2"]
				[cheshire "5.7.0"]
                 ]
  :plugins [[lein-ring "0.12.5"]
            ]
  :ring {:handler clojure.handler/app :port 8080}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}}
  :test-paths ["src/test"]
  :test-selectors {:default (complement :all)
				   :user001 :user001
				   :user002 :user002
           :user003 :user003
					:all (fn[_] true)}
  )
