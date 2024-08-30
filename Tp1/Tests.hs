import Tipos
import Anuncio
import FileSystem
import Prompter
import Control.Exception
import System.IO.Unsafe


testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()


departamento1 = "Electrónica"
departamento2 = "Juguetes"
departamento3 = "Bazar"
departamento4 = "Librería"
listaVacia = []
listaDepartamentos = [departamento1, departamento2, departamento3, departamento4]
listaIncompleta = [departamento1, departamento3]
nombreAnuncio1 = "Samsung"
nombreAnuncio2 = "Barbie"
nombreAnuncio3 = "Tramontina"
nombreAnuncio4 = "FW"
anuncio1 = nuevoA nombreAnuncio1 5
anuncio2 = nuevoA nombreAnuncio2 10
anuncio3 = nuevoA nombreAnuncio3 15
anuncio4 = nuevoA nombreAnuncio4 20
--anuncio1_5 tiene todos los departamentos
anuncio1_2 = agregarA departamento1 anuncio1
anuncio1_3 = agregarA departamento2 anuncio1_2
anuncio1_4 = agregarA departamento3 anuncio1_3
anuncio1Final = agregarA departamento4 anuncio1_4
--anuncio2_3 tiene los departamentos 1 y 3
anuncio2_2 = agregarA departamento1 anuncio2
anuncio2Final = agregarA departamento3 anuncio2_2
--anuncio3_2 tiene el departamento 4
anuncio3Final = agregarA departamento4 anuncio3
--anuncio4 no tiene departamentos

filesystemVacio = nuevoF
--filesystem1 tiene todos los departamentos y los anuncios 1 y 2
filesystem1 = agregarDepartamentoF departamento1 filesystemVacio
filesystem1_2 = agregarDepartamentoF departamento2 filesystem1
filesystem1_3 = agregarDepartamentoF departamento3 filesystem1_2
filesystem1_4 = agregarDepartamentoF departamento4 filesystem1_3
filesystem1_5 = agregarAnuncioF anuncio1Final filesystem1_4
filesystem1Final = agregarAnuncioF anuncio2Final filesystem1_5
--filesystem2 tiene todos los departamentos 1 y 3 y el anuncio 2
filesystem2 = agregarDepartamentoF departamento3 filesystem1
filesystem2Final = agregarAnuncioF anuncio2Final filesystem2

--prompter1_2 tiene todos los departamentos
prompter1 = nuevoP filesystem1Final
prompter1_2 = configurarP prompter1 listaDepartamentos
--prompter2_2 tiene los departamentos 1 y 3
prompter2 = nuevoP filesystem2Final
prompter2_2 = configurarP prompter2 listaIncompleta

t = [ testF(nuevoA "" 2), -- nuevoA
      testF(nuevoA "Patata" (-1)),
      nombreA anuncio1Final == "Samsung", -- nombreA
      duracionA anuncio1Final == 5, -- duracionA
      departamentosA anuncio1Final == (reverse listaDepartamentos), -- departamentosA
      departamentosA (agregarA departamento2 anuncio2Final) == [departamento2, departamento3, departamento1], -- agregarA
      testF(agregarA "" anuncio1Final),
      testF(agregarA departamento3 anuncio1Final),
      sacarA departamento4 anuncio1Final == anuncio1_4, -- sacarA
      departamentosA (sacarA departamento2 anuncio1Final) == [departamento4, departamento3, departamento1],
      testF(sacarA departamento2 anuncio2Final),
      aplicaA listaIncompleta anuncio2Final, -- aplicaA
      not (aplicaA [departamento2, departamento4] anuncio2Final),
      testF(aplicaA listaIncompleta anuncio4),

      departamentosF filesystem1Final == (reverse listaDepartamentos), -- departamentosF
      anunciosF filesystem1Final == [anuncio2Final, anuncio1Final],
      testF(agregarAnuncioF anuncio1Final filesystem1Final), -- agregarAnuncioF
      testF(agregarAnuncioF anuncio4 filesystem1Final),
      testF(agregarAnuncioF anuncio1Final filesystem2Final),
      anunciosF (sacarAnuncioF anuncio1Final filesystem1Final) == [anuncio2Final], -- sacarAnuncioF
      testF(sacarAnuncioF anuncio3Final filesystem1Final),
      departamentosF(agregarDepartamentoF departamento2 filesystem2Final) == [departamento2, departamento3, departamento1], -- agregarDepartamentoF
      testF(agregarDepartamentoF "" filesystem1Final),
      testF(agregarDepartamentoF departamento1 filesystem1Final)
      ]
