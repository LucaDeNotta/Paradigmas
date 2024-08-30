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
anuncio1_5 = agregarA departamento4 anuncio1_4
--anuncio2_3 tiene los departamentos 1 y 3
anuncio2_2 = agregarA departamento1 anuncio2
anuncio2_3 = agregarA departamento3 anuncio2_2
--anuncio3_2 tiene el departamento 4
anuncio3_2 = agregarA departamento4 anuncio3
--anuncio4 no tiene departamentos
filesystemVacio = nuevoF
filesystem1 = agregarDepartamentoF departamento1 filesystemVacio
filesystem1_2 = agregarDepartamentoF departamento2 filesystem1
filesystem1_3 = agregarDepartamentoF departamento3 filesystem1_2
filesystem1_4 = agregarDepartamentoF departamento4 filesystem1_3
filesystem1_5 = agregarAnuncioF anuncio1_5 filesystem1_4
filesystem1_6 = agregarAnuncioF anuncio2_3 filesystem1_5
filesystem2 = agregarDepartamentoF departamento3 filesystem1
filesystem2_2 = agregarAnuncioF anuncio2_3 filesystem2

prompter1 = nuevoP filesystem1_6
