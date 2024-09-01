module Prompter ( Prompter, nuevoP, archivosR, departamentosP, configurarP, anunciosP, showP, avanzarP, duracionP )
    where

import Tipos
import Anuncio
import FileSystem

data Prompter = Pro FileSystem [Departamento] Int deriving (Eq, Show)

nuevoP :: FileSystem -> Prompter                       -- permite obtener un nuevo Prompter en base a un FileSystem
nuevoP fileSystem | null (anunciosF fileSystem)      = error "El FileSystem no tiene anuncios"
                  | null (departamentosF fileSystem) = error "El FileSystem no tiene departamentos"
                  | otherwise                        = (Pro fileSystem [] 0)

archivosR :: Prompter -> FileSystem                    -- dado un prompter retorna su fileSystem
archivosR (Pro fileSystem _ _) = fileSystem

departamentosP :: Prompter -> [Departamento]           -- dado un prompter retorna sus departamentos
departamentosP (Pro _ departamentos _) = departamentos

configurarP :: Prompter -> [Departamento] -> Prompter  -- Prepara el prompter para emitir los anuncios en los departementos indicados
configurarP (Pro fileSystem pDepartamentos index) departamentos | null departamentos = error "Ingrese una lista de departamentos no vacía" 
                                                                | otherwise          = (Pro fileSystem departamentos index)

anunciosP :: Prompter ->  [Nombre]                      -- entrega la lista de nombres de anuncios configurados
anunciosP (Pro fileSystem departamentos _) = [(nombreA anuncio) | anuncio <- (anunciosConfP departamentos fileSystem)]

showP :: Prompter -> Anuncio                           -- muestra el anuncio actual
showP (Pro fileSystem departamentos index) = (anunciosConfP departamentos fileSystem) !! index

avanzarP :: Prompter -> Prompter                       -- pasa al siguiente anuncio
avanzarP (Pro fileSystem departamentos index) = (Pro fileSystem departamentos sigIndex)
                         where lengthAnuncios = length (anunciosConfP departamentos fileSystem)
                               sigIndex       = mod (index + 1) lengthAnuncios

duracionP :: Prompter -> Duracion                      -- indica la duracion total de los anuncios configurados
duracionP (Pro fileSystem departamentos _) = sum [(duracionA anuncio) | anuncio <- (anunciosConfP departamentos fileSystem)]

anunciosConfP :: [Departamento] -> FileSystem -> [Anuncio]                           -- chequea que el prompter tenga al menos un departamento y un anuncio
anunciosConfP departamentos fileSystem | null departamentos = error "No hay departamentos configurados para asignar anuncios"
                                       | null anuncios      = error "No hay anuncios asignados a los departamentos del Prompter"
                                       | otherwise          = anuncios
                                         where anuncios     = anunciosParaF departamentos fileSystem
