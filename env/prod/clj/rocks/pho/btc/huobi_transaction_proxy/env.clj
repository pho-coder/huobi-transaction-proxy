(ns rocks.pho.btc.huobi-transaction-proxy.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[rocks.pho.btc.huobi-transaction-proxy started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[rocks.pho.btc.huobi-transaction-proxy has shut down successfully]=-"))
   :middleware identity})
