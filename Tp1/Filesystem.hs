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
                                                    | null (departamentosA anuncio)                            = error "Un anuncio no puede no tener departamentos asignados"
                                                    | not (chequeoDepF (departamentosA anuncio) departamentos) = error "Los departamentos asociados al anuncio no se encuentran en el FileSystem" 
                                                    | otherwise                                                = (FS departamentos (anuncio:anuncios))

sacarAnuncioF :: Anuncio -> FileSystem -> FileSystem              -- permite eliminar un anuncio
sacarAnuncioF anuncio (FS departamentos anuncios) | notElem anuncio anuncios = error "El anuncio ingresado no se encuentra en el FileSystem"
                                                  | otherwise                = (FS departamentos (filter (/=anuncio) anuncios))

agregarDepartamentoF :: Departamento -> FileSystem -> FileSystem  -- permite agregar un departamento 
agregarDepartamentoF departamento (FS departamentos anuncios) | null departamento               = error "Ingrese un departamento válido"
                                                              | elem departamento departamentos = error "El departamento ingresado ya se encuentra en el FileSystem"
                                                              | otherwise                       = (FS (departamento:departamentos) anuncios)

sacarDepartamentoF :: Departamento -> FileSystem -> FileSystem    -- permite eliminar un departamento
sacarDepartamentoF departamento (FS departamentos anuncios) | null departamento                     = error "Ingrese un departamento válido"
                                                            | notElem departamento departamentos    = error "El departamento ingresado no se encuentra en el FileSystem"
                                                            | departamentosAF anuncios departamento = error "El departamento ingresado está asociado a al menos un anuncio del FileSystem"
                                                            | otherwise                             = (FS (filter (/=departamento) departamentos) anuncios)

anunciosParaF :: [Departamento] -> FileSystem -> [Anuncio]        -- entrega los anuncios a emitir para un conjunto de departamentos
anunciosParaF departamentos (FS _ anuncios) | null departamentos = error "La lista de departamentos está vacía"
                                            | null anuncios      = error "La lista de anuncios está vacía"
                                            | otherwise          = [anuncio | anuncio <- anuncios, aplicaA departamentos anuncio]


chequeoDepF :: [Departamento] -> [Departamento] -> Bool          -- responde si todos departamentos de la primera lista estan en la segunda
chequeoDepF aDepartamentos departamentos | null departamentos       = False
                                         | null aDepartamentos      = True
                                         | otherwise                = elem departamento departamentos && chequeoDepF tailDepartamentos departamentos
                                            where departamento      = head aDepartamentos
                                                  tailDepartamentos = tail aDepartamentos

departamentosAF ::  [Anuncio] -> Departamento -> Bool           -- responde si un departamento esta asociado a alguno de los anuncios consultados
departamentosAF anuncios departamento | null anuncios   = False
                                      | otherwise       = elem departamento (departamentosA anuncioA) || departamentosAF anunciosA departamento 
                                        where anuncioA  = head anuncios
                                              anunciosA = tail anuncios
