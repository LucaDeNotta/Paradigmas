module Prompter ( Prompter, nuevoP, archivosR, departamentosP, configurarP, anunciosP, showP, avanzarP, duracionP )
    where

import Tipos
import Anuncio
import FileSystem

data Prompter = Pro FileSystem [Departamento] Int deriving (Eq, Show)

nuevoP :: FileSystem -> Prompter                       -- permite obtener un nuevo Prompter en base a un FileSystem
nuevoP fileSystem = (Pro fileSystem [] 0)

archivosR :: Prompter -> FileSystem                    -- dado un prompter retorna su fileSystem
archivosR (Pro fileSystem _ _) = fileSystem

departamentosP :: Prompter -> [Departamento]           -- dado un prompter retorna sus departamentos
departamentosP (Pro _ departamentos _) = departamentos

configurarP :: Prompter -> [Departamento] -> Prompter  -- Prepara el prompter para emitir los anuncios en los departementos indicados
configurarP _ []                                                = error "Ingrese una lista de departamentos no vacía" 
configurarP (Pro fileSystem pDepartamentos index) departamentos = (Pro fileSystem departamentos index)

anunciosP :: Prompter ->  [Nombre]                      -- entrega la lista de nombres de anuncios configurados
anunciosP (Pro fileSystem departamentos _) = [(nombreA anuncio) | anuncio <- (anunciosParaF departamentos fileSystem)]

showP :: Prompter -> Anuncio                           -- muestra el anuncio actual
showP (Pro fileSystem departamentos index) | null anuncios =  error "No hay anuncios asignados a los departamentos del Prompter"
                                           | otherwise     = anuncios !! index
                                            where anuncios  = anunciosParaF departamentos fileSystem

avanzarP :: Prompter -> Prompter                       -- pasa al siguiente anuncio
avanzarP (Pro fileSystem departamentos index) = (Pro fileSystem departamentos (mod (index + 1) (length (anunciosParaF departamentos fileSystem))))

duracionP :: Prompter -> Duracion                      -- indica la duracion total de los anuncios configurados
duracionP (Pro fileSystem departamentos _) = sum [(duracionA anuncio) | anuncio <- (anunciosParaF departamentos fileSystem)]
