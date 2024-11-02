package Rover;

public class RotarDer extends Comando {
    {
        comando = 'r';
    }
    public void ejecutar(Rover rover) {
        rover.rotarDer();
    }
}
