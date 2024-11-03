package Rover;

public class AbrirEscotillaSuperior extends Comando {
    public AbrirEscotillaSuperior(){
        comando = 'O';
    }

    public void ejecutar(Rover rover) {
        rover.abrirEscotillaSuperior();
    }
}
