(ns rocks.pho.btc.huobi-transaction-proxy.routes.services
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [clojure.tools.logging :as log]

            [rocks.pho.btc.huobi-transaction-proxy.huobi-api :as ha]
            [rocks.pho.btc.huobi-transaction-proxy.config :refer [env]]))

(s/defschema Info {:success? Boolean
                   :info String})

(defapi service-routes
  {:swagger {:ui "/swagger-ui"
             :spec "/swagger.json"
             :data {:info {:version "1.0.0"
                           :title "Sample API"
                           :description "Sample Services"}}}}
  
  (context "/api" []
    :tags ["thingie"]

    (GET "/plus" []
      :return       Long
      :query-params [x :- Long, {y :- Long 1}]
      :summary      "x+y with query-parameters. y defaults to 1."
      (ok (+ x y)))

    (POST "/minus" []
      :return      Long
      :body-params [x :- Long, y :- Long]
      :summary     "x-y with body-parameters."
      (ok (- x y)))

    (GET "/times/:x/:y" []
      :return      Long
      :path-params [x :- Long, y :- Long]
      :summary     "x*y with path-parameters"
      (ok (* x y)))

    (POST "/divide" []
      :return      Double
      :form-params [x :- Long, y :- Long]
      :summary     "x/y with form-parameters"
      (ok (/ x y)))

    (GET "/power" []
      :return      Long
      :header-params [x :- Long, y :- Long]
      :summary     "x^y with header-parameters"
      (ok (long (Math/pow x y))))

    (POST "/buy-market" []
          :return Info
          :body-params [code :- Long, amount :- Double]
          :summary "buy market"
          (ok (try
                (log/info "buy market: code:" code "amount:" amount)
                (if (not= code 1074)
                  {:success? false
                   :info (str "code: " code " ERROR!")}
                  (let [re (ha/buy-market (:huobi-access-key env)
                                          (:huobi-secret-key env)
                                          amount)]
                    (if (= "success" (:result re))
                      {:success? true
                       :info (str "id: " (:id re))}
                      {:success? false
                       :info (str re)})))
                (catch Exception e
                  (log/error "buy market ERROR!")
                  (log/error e)
                  {:success? false
                   :info (.toString e)}))))

    (POST "/sell-market" []
          :return Info
          :body-params [code :- Long, amount :- Double]
          :summary "sell market"
          (ok (try
                (log/info "sell market: code:" code "amount:" amount)
                (if (not= code 1074)
                  {:success? false
                   :info (str "code: " code " ERROR!")}
                  (let [re (ha/sell-market (:huobi-access-key env)
                                           (:huobi-secret-key env)
                                           amount)]
                    (if (= "success" (:result re))
                      {:success? true
                       :info (str "id: " (:id re))}
                      {:success? false
                       :info (str re)})))
                (catch Exception e
                  (log/error "sell market ERROR!")
                  (log/error e)
                  {:success? false
                   :info (.toString e)}))))))
