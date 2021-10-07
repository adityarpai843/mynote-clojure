(ns mynotes.macros)

;Menu Prompts
(defmacro menuPrompts[ & optionBanners ]
`((println "Note Maker:- Select an Option")(println "----------------")(~optionBanners))
)

(defmacro safe
  [expression]
  `(try ~expression
     (catch Exception e#
       (str "caught exception: " (.getMessage e#)))))  

