package Rover;

public class Retroceder extends Comando {
    {
        comando = 'b';
    }
    public void ejecutar(Rover rover) {
        rover.retroceder();
    }
}
