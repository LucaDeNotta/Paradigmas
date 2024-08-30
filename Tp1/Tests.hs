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





