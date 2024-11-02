package Rover;

public class Avanzar extends Comando {
    {
        comando = 'f';
    }
    public void ejecutar(Rover rover) {
        rover.avanzar();
    }

}
