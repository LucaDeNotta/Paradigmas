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
agregarAnuncioF anuncio (FS departamentos anuncios) | elem anuncio anuncios = error "El anuncio ya se encuentra en el FileSystem"
                                                    | otherwise             = (FS departamentos (anuncio:anuncios))

sacarAnuncioF :: Anuncio -> FileSystem -> FileSystem              -- permite eliminar un anuncio
sacarAnuncioF anuncio (FS departamentos anuncios) | notElem anuncio anuncios = error "El anuncio ingresado no se encuentra en el Filesystem"
                                                  | otherwise                = (FS departamentos (filter (/=anuncio) anuncios))

--CORREGIR LUCA. PREGUNTAR EMILIO
agregarDepartamentoF :: Departamento -> FileSystem -> FileSystem  -- permite agregar un departamento 
agregarDepartamentoF departamento (FS departamentos anuncios) | elem departamento departamentos = error "El departamento ingresado ya se encuentra en el Filesystem"
                                                              | otherwise                       = (FS (departamento:departamentos) anuncios)
