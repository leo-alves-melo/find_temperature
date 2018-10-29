(ns find-temperature.core
  (:gen-class))

(ns find-temperature.core
  (:require [clj-http.client :as client]))

(ns find-temperature.core
  (:require [clojure.data.json :as json]))

(require '[clj-http.client :as http])

(defn get_temperature [city] 
	(let [api_url "http://api.openweathermap.org/data/2.5/weather?q="
		app_id "&APPID=173c44713662655df575fc4200bbb202"] 

		(client/get (str api_url city app_id)
            {:async? true :as :json}
            ;; respond callback
            (fn [response] 
            	(println "Temperature is:" 
            		(:temp
	            		(:main
	            			(json/read-str 
	            				(:body response) :key-fn keyword
	            			)
	            			
	            		)
            		)

            		"ºF"

            	)
            )
            ;; raise callback
            (fn [exception] (println "exception message is: " (.getMessage exception) city))
    	)
	)
	
)

(defn -main
  [& args]
  (get_temperature (first args))
 )
