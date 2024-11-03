package Rover;

public class Retroceder extends Comando {
    public Retroceder(){
        comando = 'b';
    }

    public void ejecutar(Rover rover) {
        rover.retroceder();
    }
}
