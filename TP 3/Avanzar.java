package Rover;

public class Avanzar extends Comando {
    public Avanzar(){
        comando = 'f';
    }

    public void ejecutar(Rover rover) {
        rover.avanzar();
    }
}
