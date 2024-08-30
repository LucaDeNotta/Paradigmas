module Anuncio ( Anuncio, nuevoA, nombreA, duracionA, departamentosA, agregarA, sacarA, aplicaA )
  where

import Tipos ( Departamento, Duracion, Nombre )

data Anuncio = Anu Nombre [ Departamento ] Duracion deriving (Eq, Show, Ord)

--PREGUNTAR EMILIO
nuevoA :: Nombre -> Duracion -> Anuncio         -- dado un nombre y una duracion en segundos retorna un nuevo Anuncio
nuevoA "" _                            = error "Ingrese un nombre válido" 
nuevoA nombre duracion | duracion <= 0 = error "Ingrese una duración válida"
                       | otherwise     = (Anu nombre [] duracion)

nombreA :: Anuncio -> Nombre                    -- dado un anuncio retorna su nombre
nombreA (Anu nombre _ _) = nombre

duracionA :: Anuncio -> Duracion                -- dado un anuncio retorna su duración
duracionA (Anu _ _ duracion) = duracion

departamentosA :: Anuncio -> [ Departamento ]   -- dado un anuncio retorna los departamentos que le fueron asociados
departamentosA (Anu _ departamentos _) = departamentos

agregarA :: Departamento -> Anuncio -> Anuncio -- permite asignar un departamento a un anuncio
agregarA "" _                                                              = error "Ingresar un departamento válido"
agregarA departamento anuncio | elem departamento (departamentosA anuncio) = error "El departamento ingresado ya está asignado al anuncio"
agregarA departamento (Anu nombre departamentos duracion)                  = (Anu nombre (departamento:departamentos) duracion)

sacarA :: Departamento -> Anuncio -> Anuncio    -- permite quitarle un departamento a un anuncio
sacarA departamento (Anu nombre departamentos duracion) | notElem departamento departamentos = error "El departamento ingresado no está asignado al anuncio"
                                                        | otherwise                          =  (Anu nombre (filter (/=departamento) departamentos) duracion)

aplicaA :: [ Departamento ] -> Anuncio -> Bool  -- responde si un anuncio debe emitirse para alguno de los departamentos consultados
aplicaA _ (Anu _ [] _) = error "La lista de departamentos del anuncio está vacía"
aplicaA [] _           = False
aplicaA (departamento:departamentos) anuncio = elem departamento (departamentosA anuncio) || aplicaA departamentos anuncio
