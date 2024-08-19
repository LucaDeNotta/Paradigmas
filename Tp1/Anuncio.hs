module Anuncio ( Anuncio, nuevoA, nombreA, duracionA, departamentosA, agreagarA, sacarA, aplicaA )
  where

import Tipos

data Anuncio = Anu Nombre [ Departamento ] Duracion deriving (Eq, Show, Ord)

nuevoA :: Nombre -> Duracion -> Anuncio         -- dado un nombre y una duracion en segundos retorna un nuevo Anuncio
nuevoA name duration = (Anu name [] duration)
nombreA :: Anuncio -> Nombre                    -- dado un anuncio retorna su nombre
nombreA (Anu name _ _) = name
duracionA :: Anuncio -> Duracion                -- dado un anuncio retorna su duración
duracionA (Anu _ _ duration) = duration
departamentosA :: Anuncio -> [ Departamento ]   -- dado un anuncio retorna los departamentos que le fueron asociados
departamentosA (Anu _ departamentos _) = departamentos
agreagarA :: Departamento -> Anuncio -> Anuncio -- permite asignar un departamento a un anuncio
agreagarA "" anuncio = anuncio
sacarA :: Departamento -> Anuncio -> Anuncio    -- permite quitarle un departamento a un anuncio
sacarA depa (Anu nombre [] duracion) = (Anu nombre [] duracion)
aplicaA :: [ Departamento ] -> Anuncio -> Bool  -- responde si un anuncion debe emitirse para alguno de los departamentos consultados
aplicaA _ (Anu _ [] _) = False
aplicaA [] _ = False
aplicaA (x:xs) anuncio = elem x (departamentosA anuncio) || aplicaA xs anuncio