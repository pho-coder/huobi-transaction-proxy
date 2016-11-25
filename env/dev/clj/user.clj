(ns user
  (:require [mount.core :as mount]
            rocks.pho.btc.huobi-transaction-proxy.core))

(defn start []
  (mount/start-without #'rocks.pho.btc.huobi-transaction-proxy.core/repl-server))

(defn stop []
  (mount/stop-except #'rocks.pho.btc.huobi-transaction-proxy.core/repl-server))

(defn restart []
  (stop)
  (start))


