package Rover;

public class RotarDerecha extends Comando {
    public RotarDerecha() {
        comando = 'r';
    }

    public void ejecutar(Rover rover) {
        rover.rotarDerecha();
    }
}
