(ns rocks.pho.btc.huobi-transaction-proxy.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [rocks.pho.btc.huobi-transaction-proxy.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[rocks.pho.btc.huobi-transaction-proxy started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[rocks.pho.btc.huobi-transaction-proxy has shut down successfully]=-"))
   :middleware wrap-dev})
