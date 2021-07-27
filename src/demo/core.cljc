(ns demo.core
  (:require [taoensso.sente :as sente]
            
            #?@(:clj
                [[aleph.http :as http]
                 [ring.middleware.defaults :as ring-dflt]
                 [ring.middleware.anti-forgery :as anti-forgery]
                 [reitit.core :as r]
                 [reitit.ring :as rring]
                 [taoensso.sente.server-adapters.aleph :as sente-adapter]
                 [cljs.main :as cljs]
                 [clojure.java.browse :refer [browse-url]]
                 ;; not necesary to require for error
                 ;;[tech.v3.dataset :as ds]
                 ])


            ))

#?(:cljs
   (-> (js/fetch "/token")
       (.then (fn [response]
                (.then (.text response)
                  (fn [csrf]
                    (let [sente (sente/make-channel-socket-client! "/chsk" csrf {})]
                      
                      ))))))

   :clj
   [(defn get-token [req]
      {:status       200
       :content-type "text/plain"
       :body 
       (or (get-in req [:session :ring.middleware.anti-forgery/anti-forgery-token])
           (assert false "No anti-forgery token found"))})


    (def server (atom nil))
    
    (defn start [& args]
      (println "compiling cljs...")
      (cljs/-main "--optimizations" "advanced" "--compile" "demo.core")
      (println "starting server...")
      (let [sente (sente/make-channel-socket-server! (sente-adapter/get-sch-adapter))
            sente-routes ["/chsk" {:get  (:ajax-get-or-ws-handshake-fn sente) 
                                   :post (:ajax-post-fn sente)}]
            router  (rring/router [["/token" {:get get-token}]
                                   sente-routes])
            handler (rring/ring-handler router
                      (rring/routes
                        (rring/create-file-handler {:root "public" :path "/"})
                        (rring/create-file-handler {:root "out" :path "/js"})
                        (rring/create-default-handler))
                      {:middleware [[(fn [routes]
                                       (ring-dflt/wrap-defaults routes
                                         (dissoc ring-dflt/site-defaults :static)))]]})
            srv (http/start-server handler {:port 8888})]
        
        (reset! server srv)
        (println "opening url...")
        (browse-url "http://localhost:8888")
        ))

    (defn stop []
      (.close @server))])
