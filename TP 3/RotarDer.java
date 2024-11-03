package Rover;

public class RotarDer extends Comando {
    public RotarDer() {
        comando = 'r';
    }

    public void ejecutar(Rover rover) {
        rover.rotarDer();
    }
}
