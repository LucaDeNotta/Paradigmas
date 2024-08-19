module Anuncio ( Anuncio, nuevoA, nombreA, duracionA, departamentosA, agregarA, sacarA, aplicaA )
  where

import Tipos

data Anuncio = Anu Nombre [ Departamento ] Duracion deriving (Eq, Show, Ord)

--PREGUNTAR EMILIO
nuevoA :: Nombre -> Duracion -> Anuncio         -- dado un nombre y una duracion en segundos retorna un nuevo Anuncio
nuevoA nombre duracion | null nombre || duracion <= 0 = error "Ingrese datos válidos"
                       | otherwise                    = (Anu nombre [] duracion)

nombreA :: Anuncio -> Nombre                    -- dado un anuncio retorna su nombre
nombreA (Anu nombre _ _) = nombre

duracionA :: Anuncio -> Duracion                -- dado un anuncio retorna su duración
duracionA (Anu _ _ duracion) = duracion

departamentosA :: Anuncio -> [ Departamento ]   -- dado un anuncio retorna los departamentos que le fueron asociados
departamentosA (Anu _ departamentos _) = departamentos

agregarA :: Departamento -> Anuncio -> Anuncio -- permite asignar un departamento a un anuncio
agregarA "" anuncio                                                        = error "Ingresar un departamento válido"
agregarA departamento anuncio | elem departamento (departamentosA anuncio) = error "El departamento ingresado ya está asignado al anuncio"
agregarA departamento (Anu nombre departamentos duracion)                  = (Anu nombre (departamento:departamentos) duracion)

--PENSAR MENSAJE ERROR
sacarA :: Departamento -> Anuncio -> Anuncio    -- permite quitarle un departamento a un anuncio
sacarA departamento anuncio | notElem departamento (departamentosA anuncio) = error "El departamento ingresado no está asignado al anuncio"
sacarA departamento (Anu nombre departamentos duracion) = (Anu nombre (filter (/=departamento) departamentos) duracion)

aplicaA :: [ Departamento ] -> Anuncio -> Bool  -- responde si un anuncion debe emitirse para alguno de los departamentos consultados
aplicaA _ (Anu _ [] _) = False
aplicaA [] _           = False
aplicaA (x:xs) anuncio = elem x (departamentosA anuncio) || aplicaA xs anuncio
