package Rover;

public class Aspirar extends Comando {
    public Aspirar(){
        comando = 'a';
    }

    public void ejecutar(Rover rover) {
        rover.aspirar();
    }
}
