package Rover;

public class Aspirar extends Comando {
    {
        comando = 'a';
    }
    public void ejecutar(Rover rover) {
        rover.aspirar();
    }
}
