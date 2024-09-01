module Anuncio ( Anuncio, nuevoA, nombreA, duracionA, departamentosA, agregarA, sacarA, aplicaA )
  where

import Tipos 

data Anuncio = Anu Nombre [ Departamento ] Duracion deriving (Eq, Show, Ord)

--PREGUNTAR EMILIO
nuevoA :: Nombre -> Duracion -> Anuncio         -- dado un nombre y una duracion en segundos retorna un nuevo Anuncio 
nuevoA nombre duracion | null nombre   = error "Ingrese un nombre válido"
                       | duracion <= 0 = error "Ingrese una duración válida"
                       | otherwise     = (Anu nombre [] duracion)

nombreA :: Anuncio -> Nombre                    -- dado un anuncio retorna su nombre
nombreA (Anu nombre _ _) = nombre

duracionA :: Anuncio -> Duracion                -- dado un anuncio retorna su duración
duracionA (Anu _ _ duracion) = duracion

departamentosA :: Anuncio -> [ Departamento ]   -- dado un anuncio retorna los departamentos que le fueron asociados
departamentosA (Anu _ departamentos _) = departamentos

agregarA :: Departamento -> Anuncio -> Anuncio -- permite asignar un departamento a un anuncio
agregarA departamento (Anu nombre departamentos duracion) | null departamento               = error "Ingresar un departamento válido"
                                                          | elem departamento departamentos = error "El departamento ingresado ya está asignado al anuncio"
                                                          | otherwise                       = (Anu nombre (departamento:departamentos) duracion)

sacarA :: Departamento -> Anuncio -> Anuncio    -- permite quitarle un departamento a un anuncio
sacarA departamento (Anu nombre departamentos duracion) | notElem departamento departamentos = error "El departamento ingresado no está asignado al anuncio"
                                                        | otherwise                          =  (Anu nombre (filter (/=departamento) departamentos) duracion)

aplicaA :: [Departamento] -> Anuncio -> Bool    -- responde si un anuncio debe emitirse para alguno de los departamentos consultados
aplicaA departamentos (Anu nombre departamentosA duracion) | null departamentosA     = error "La lista de departamentos del anuncio está vacía"
                                                           | null departamentos      = False
                                                           | otherwise               = elem departamento departamentosA || aplicaA tailDepartamentos anuncio
                                                             where anuncio           = (Anu nombre departamentosA duracion)
                                                                   departamento      = head departamentos
                                                                   tailDepartamentos = tail departamentos
