(ns wishlist-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn uuid [] (str (java.util.UUID/randomUUID)))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/:id" [id] (uuid))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
