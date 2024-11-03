package Rover;

public class AbrirEscotillacInferior extends Comando {
    public AbrirEscotillaInferior(){
        comando = 'o';
    }

    public void ejecutar(Rover rover) {
        rover.abrirEscotillaInferior();
    }
}
