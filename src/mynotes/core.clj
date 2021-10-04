(ns mynotes.core
  (:require [clojure.java.jdbc :as sql])
  (:gen-class))
  (def mysql-db {
    :subprotocol "mysql"
    :subname "//127.0.0.1:3306/notes"
    :user "root"
    :password "1234"})
(defn addNotes []
;Add notes
  (println "Enter the note")
  (def mynote (read-line))
  (sql/insert! mysql-db
    :note {:text mynote})  
)
(defn viewNotes []
(println "Notes")
(println "------")
(def noteArray (sql/query mysql-db ["SELECT * FROM note"]))
(doseq [n noteArray]
(println (get n :text))  
)
)

(defn deleteNotes []
(println "Notes")
(println "------")
(def noteArray (sql/query mysql-db ["SELECT * FROM note"]))
(println noteArray)
(println "Select an Option")
(println "----------------")
(println "d1.Delete Single Note")
(println "d2.Delete Whole Notes")
(def dopt (read-line))
(cond
  (= dopt "d1")((fn [](println "Enter the ID")(def iden (read-line))(sql/delete! mysql-db :note ["id = ?" iden]))) 
  (= dopt "d2")((fn [](sql/execute! mysql-db ["DELETE FROM note"]))) 
  :else (println "Enter a valid option"))
)
(defn -main
  [& args]
  (println "Note Maker")
  (println "-----------")
  (println "a:Addnotes")
  (println "b:Viewnotes")
  (println "c:Delete Note/Notes")
  (def opt (read-line))
  (cond
    (= opt "a")(addNotes)
    (= opt "b")(viewNotes)
    (= opt "c")(deleteNotes)
    :else (println "Enter a valid option"))
  )
