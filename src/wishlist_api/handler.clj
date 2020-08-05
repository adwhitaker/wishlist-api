(ns wishlist-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clojure.data.json :as json]))

(defn uuid [] (str (java.util.UUID/randomUUID)))
(defn get-time [] (.getTime (java.util.Date.)))
(def user-1 (uuid))
(def user-2 (uuid))

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
     :purchase {:userId      user-1
                :timestamp   (get-time)
                :creatorName "Alex"}}])

(defn get-lists []
  [{:id      (uuid)
    :name    "Alex's List"
    :created {:creatorId  user-1
              :timestamp (get-time)}
    :items   (get-items)},
   {:id      (uuid)
    :name    "Mitchell's List"
    :created {:creatorId   user-2
              :timestamp   (get-time)
              :creatorName "Mitchell"}
    :items   (get-items)}])

(defn get-groups []
  [{:id    (uuid)
    :name  "Christmas 2020"
    :lists (get-lists)},
   {:id    (uuid)
    :name  "Christmas 2019"
    :lists (get-lists)},
   {:id    (uuid)
    :name  "Alex's Birthday"
    :lists (get-lists)}])


(defn json-success [body]
  {:status 200
   :headers { "Content-Type" "application/json"}
   :body (json/write-str { :data body })})


(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/:id" [id] (json-success (get-groups)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
