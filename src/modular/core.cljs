(ns modular.core
  (:require [clojure.browser.repl :as repl]
            [hum.core :as hum]
            [dommy.core :as dommy :refer-macros [sel sel1]]))

(def ctx (hum/create-context))
(def vco (hum/create-osc ctx :sawtooth))
(def vcf (hum/create-biquad-filter ctx))
(def output (hum/create-gain ctx))

(hum/connect vco vcf output)

(hum/start-osc vco)

(hum/connect-output output)

(defn drag-handler [event]
  (hum/note-on output vco (dommy/value (.-currentTarget event))))

(dommy/listen! (sel1 :#freq) :change drag-handler)

;(hum/note-on output vco 440)
