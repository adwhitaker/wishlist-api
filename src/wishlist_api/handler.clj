(ns wishlist-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clojure.data.json :as json]))

(defn uuid [] (str (java.util.UUID/randomUUID)))
(defn get-time [] (.getTime (java.util.Date.)))


(defn get-items []
  [{ :id (uuid)
     :name "Legos"
     :created (get-time)
     :lastUpdated (get-time)
     :purchased nil},
   { :id (uuid)
     :name "Coffee"
     :created (get-time)
     :lastUpdated (get-time)
     :purchase { :userId (uuid)
                   :timestamp (get-time)
                   :name "Alex"}}])

(defn json-success [body]
  {:status 200
   :headers { "Content-Type" "application/json"}
   :body (json/write-str { :data body })})


(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/:id" [id] (json-success (get-items)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
