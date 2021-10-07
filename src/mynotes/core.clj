(ns mynotes.core
  (:require [clojure.java.jdbc :as sql])
  (:require [mynotes.macros :as ms])
  (:gen-class))
  (def mysql-db {
    :subprotocol "mysql"
    :subname "//127.0.0.1:3306/notes"
    :user "root"
    :password "1234"})
(defn addNotes []
;Add notes
  (println "Enter the note")
  (let [mynote (read-line)]
    (sql/insert! mysql-db
      :note {:text mynote})   
  ) 
)

(defn viewNotes []
(println "Notes")
(println "------")
(let [noteArray  (ms/safe (sql/query mysql-db ["SELECT * FROM note"]))]
(doseq [n noteArray] (println (get n :text))))
)

(defn deleteNotes []
(println "Notes")
(println "------")
(let [noteArray  (sql/query mysql-db ["SELECT * FROM note"])] 
(doseq [n noteArray](println (format "%d. %s" (get n :id) (get n :text)))))
(println "Select an Option")
(println "-----DELETE------")
(println "d1) Single Note")
(println "d2) Whole Notes")
(let [dopt (read-line)]
  (cond
    (= dopt "d1")((fn [](println "Enter the ID")(let [iden (read-line)](sql/delete! mysql-db :note ["id = ?" iden])))) 
    (= dopt "d2")((fn [](sql/execute! mysql-db ["DELETE FROM note"]))) 
    :else (println "Enter a valid option"))  
))
(defn -main
  [& args]
  (println "Note Maker:- Select An Option")
  (println "-----------------------------")
  (println "a) Add notes")
  (println "b) View notes")
  (println "c) Delete Note/Notes")
  (let [opt (read-line)] 
    (cond
      (= opt "a")(addNotes)
      (= opt "b")(viewNotes)
      (= opt "c")(deleteNotes)
      :else (println "Enter a valid option")))
)
