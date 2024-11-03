package Rover;

public class RotarIzquierda extends Comando{
    public RotarIzquierda(){
        comando = 'l';
    }

    public void ejecutar(Rover rover) {
        rover.rotarIzquierda();
    }
}
