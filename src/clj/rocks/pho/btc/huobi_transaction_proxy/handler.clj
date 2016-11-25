(ns rocks.pho.btc.huobi-transaction-proxy.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [rocks.pho.btc.huobi-transaction-proxy.layout :refer [error-page]]
            [rocks.pho.btc.huobi-transaction-proxy.routes.home :refer [home-routes]]
            [rocks.pho.btc.huobi-transaction-proxy.routes.services :refer [service-routes]]
            [compojure.route :as route]
            [rocks.pho.btc.huobi-transaction-proxy.env :refer [defaults]]
            [mount.core :as mount]
            [rocks.pho.btc.huobi-transaction-proxy.middleware :as middleware]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(def app-routes
  (routes
    (-> #'home-routes
        (wrap-routes middleware/wrap-csrf)
        (wrap-routes middleware/wrap-formats))
    #'service-routes
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))


(defn app [] (middleware/wrap-base #'app-routes))
