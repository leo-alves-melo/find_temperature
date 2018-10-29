; Dependencies 
(ns find-temperature.core
	(:gen-class))

(ns find-temperature.core
	(:require [clj-http.client :as client]))

(ns find-temperature.core
	(:require [clojure.data.json :as json]))

; Returns the temperature of the city in the completion function 
(defn get_temperature [city completion] 
	(let [api_url "http://api.openweathermap.org/data/2.5/weather?q="
		app_id "&APPID=173c44713662655df575fc4200bbb202"] 

		(client/get (str api_url city app_id)
			{:async? true :as :json}

			(fn [response] 
				(completion 
					(- (:temp
						(:main
							(json/read-str 
								(:body response) :key-fn keyword
							)
							
						)
					) 272) 
					
				)
			)
			; Raise callback
			(fn [exception] (println "exception message is: " (.getMessage exception)))
		)
	)
	
)

; Print the temperature
(defn print_temperature [temperature]
	(println "Temperature is: " temperature "ºC")
)

(defn -main
	[& args]
	(get_temperature (first args) print_temperature)
)
