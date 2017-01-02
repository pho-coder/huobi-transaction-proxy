(ns rocks.pho.btc.huobi-transaction-proxy.huobi-api
  (:require [clj-http.client :as http-client]
            [clojure.data.json :as json]
            [digest :as digest]))

(defn get-account-info
  "get account info"
  [access_key secret_key]
  (let [unix-time (int (/ (System/currentTimeMillis) 1000))
        sign-str (str "access_key="
                      access_key
                      "&created="
                      unix-time
                      "&method=get_account_info&secret_key="
                      secret_key)
        sign (digest/md5 sign-str)]
    (json/read-str (:body (http-client/post "https://api.huobi.com/apiv3"
                                            {:headers {"Content-Type" "application/x-www-form-urlencoded"}
                                             :form-params {:method "get_account_info"
                                                           :access_key access_key
                                                           :created unix-time
                                                           :sign sign}}))
                   :key-fn keyword)))

(defn buy
  "buy by amount & price"
  [access-key secret-key amount price]
  (let [unix-time (int (/ (System/currentTimeMillis) 1000))
        sign-str (str "access_key=" access-key
                      "&amount=" amount
                      "&coin_type=1"
                      "&created=" unix-time
                      "&method=buy"
                      "&price=" price
                      "&secret_key=" secret-key)
        sign (digest/md5 sign-str)]
    (json/read-str (:body (http-client/post "https://api.huobi.com/apiv3"
                                            {:headers {"Content-Type" "application/x-www-form-urlencoded"}
                                             :form-params {:method "buy"
                                                           :access_key access-key
                                                           :coin_type 1
                                                           :price price
                                                           :amount amount
                                                           :created unix-time
                                                           :sign sign}}))
                   :key-fn keyword)))

(defn sell
  "sell by amount & price"
  [access-key secret-key amount price]
  (let [unix-time (int (/ (System/currentTimeMillis) 1000))
        sign-str (str "access_key=" access-key
                      "&amount=" amount
                      "&coin_type=1"
                      "&created=" unix-time
                      "&method=sell"
                      "&price=" price
                      "&secret_key=" secret-key)
        sign (digest/md5 sign-str)]
    (json/read-str (:body (http-client/post "https://api.huobi.com/apiv3"
                                            {:headers {"Content-Type" "application/x-www-form-urlencoded"}
                                             :form-params {:method "sell"
                                                           :access_key access-key
                                                           :coin_type 1
                                                           :price price
                                                           :amount amount
                                                           :created unix-time
                                                           :sign sign}}))
                   :key-fn keyword)))

(defn buy-market
  "buy now"
  [access-key secret-key amount]
  (let [unix-time (int (/ (System/currentTimeMillis) 1000))
        sign-str (str "access_key=" access-key
                      "&amount=" amount
                      "&coin_type=1"
                      "&created=" unix-time
                      "&method=buy_market"
                      "&secret_key=" secret-key)
        sign (digest/md5 sign-str)]
    (json/read-str (:body (http-client/post "https://api.huobi.com/apiv3"
                                            {:headers {"Content-Type" "application/x-www-form-urlencoded"}
                                             :form-params {:method "buy_market"
                                                           :access_key access-key
                                                           :coin_type 1
                                                           :amount amount
                                                           :created unix-time
                                                           :sign sign}}))
                   :key-fn keyword)))

(defn sell-market
  "sell now"
  [access-key secret-key amount]
  (let [unix-time (int (/ (System/currentTimeMillis) 1000))
        sign-str (str "access_key=" access-key
                      "&amount=" amount
                      "&coin_type=1"
                      "&created=" unix-time
                      "&method=sell_market"
                      "&secret_key=" secret-key)
        sign (digest/md5 sign-str)]
    (json/read-str (:body (http-client/post "https://api.huobi.com/apiv3"
                                            {:headers {"Content-Type" "application/x-www-form-urlencoded"}
                                             :form-params {:method "sell_market"
                                                           :access_key access-key
                                                           :coin_type 1
                                                           :amount amount
                                                           :created unix-time
                                                           :sign sign}}))
                   :key-fn keyword)))

(defn loan-btc
  "loan now"
  [access-key secret-key amount]
  (let [unix-time (int (/ (System/currentTimeMillis) 1000))
        sign-str (str "access_key=" access-key
                      "&amount=" amount
                      "&created=" unix-time
                      "&loan_type=2"
                      "&method=loan"
                      "&secret_key=" secret-key)
        sign (digest/md5 sign-str)]
    (json/read-str (:body (http-client/post "https://api.huobi.com/apiv3"
                                            {:headers {"Content-Type" "application/x-www-form-urlencoded"}
                                             :form-params {:method "loan"
                                                           :access_key access-key
                                                           :amount amount
                                                           :loan_type 2
                                                           :created unix-time
                                                           :sign sign}}))
                   :key-fn keyword)))

(defn repay-btc
  "repay btc"
  [access-key secret-key loan-id amount]
  (let [unix-time (int (/ (System/currentTimeMillis) 1000))
        sign-str (str "access_key=" access-key
                      "&amount=" amount
                      "&created=" unix-time
                      "&loan_id=" loan-id
                      "&method=repayment"
                      "&secret_key=" secret-key)
        sign (digest/md5 sign-str)]
    (json/read-str (:body (http-client/post "https://api.huobi.com/apiv3"
                                            {:headers {"Content-Type" "application/x-www-form-urlencoded"}
                                             :form-params {:method "repayment"
                                                           :access_key access-key
                                                           :loan_id loan-id
                                                           :amount amount
                                                           :created unix-time
                                                           :sign sign}}))
                   :key-fn keyword)))
