module FileSystem ( FileSystem, nuevoF, departamentosF, anunciosF, agregarAnuncioF, sacarAnuncioF, agregarDepartamentoF, sacarDepartamentoF, anunciosParaF )
    where

import Tipos
import Anuncio

data FileSystem = FS [Departamento] [Anuncio] deriving (Eq, Show)

nuevoF :: FileSystem                                              -- permite obtener un nuevo FileSystem
nuevoF = (FS [] [])

departamentosF :: FileSystem -> [ Departamento ]                  -- dado un FileSystem retorna los departamentos que incluye
departamentosF (FS departamentos _) = departamentos

anunciosF :: FileSystem -> [ Anuncio ]                            -- dado un FileSystem retorna los anuncios que incluye
anunciosF (FS _ anuncios) = anuncios

agregarAnuncioF :: Anuncio -> FileSystem -> FileSystem            -- permite agregar un anuncio
agregarAnuncioF anuncio (FS departamentos anuncios) | elem anuncio anuncios                                    = error "El anuncio ya se encuentra en el FileSystem"
                                                    | null (departamentosA anuncio)                            = error "Un anuncio no puede no tener departamentos"
                                                    | not (chequeoDepF (departamentosA anuncio) departamentos) = error "Los departamentos asociados al anuncio no se encuentran en el FileSystem" 
                                                    | otherwise                                                = (FS departamentos (anuncio:anuncios))

sacarAnuncioF :: Anuncio -> FileSystem -> FileSystem              -- permite eliminar un anuncio
sacarAnuncioF anuncio (FS departamentos anuncios) | notElem anuncio anuncios = error "El anuncio ingresado no se encuentra en el FileSystem"
                                                  | otherwise                = (FS departamentos (filter (/=anuncio) anuncios))

--CORREGIR LUCA. PREGUNTAR EMILIO
agregarDepartamentoF :: Departamento -> FileSystem -> FileSystem  -- permite agregar un departamento 
agregarDepartamentoF "" _                                                                       = error "Ingrese un departamento valido"
agregarDepartamentoF departamento (FS departamentos anuncios) | elem departamento departamentos = error "El departamento ingresado ya se encuentra en el FileSystem"
                                                              | otherwise                       = (FS (departamento:departamentos) anuncios)

sacarDepartamentoF :: Departamento -> FileSystem -> FileSystem    -- permite eliminar un departamento
sacarDepartamentoF departamento (FS departamentos anuncios) | notElem departamento departamentos = error "El departamento ingresado no se encuentra en el FilseSystem"
                                                            | otherwise                = (FS (filter (/=departamento) departamentos) anuncios)

anunciosParaF :: [Departamento] -> FileSystem -> [Anuncio]        -- entrega los anuncios a emitir para un conjunto de departamentos
anunciosParaF [] _ = error "La lista de departamentos esta vacia"
anunciosParaF departamentos (FS [] _) = error "La lista de anuncios esta vacia"
anunciosParaF departamentos (FS _ anuncios) = [anuncio | anuncio <- anuncios, aplicaA departamentos anuncio]


chequeoDepF :: [Departamento] -> [Departamento] -> Bool
chequeoDepF _ []                                         = False
chequeoDepF [] _                                         = True
chequeoDepF (aDepartamento:aDepartamentos) departamentos = elem aDepartamento departamentos && chequeoDepF aDepartamentos departamentos
