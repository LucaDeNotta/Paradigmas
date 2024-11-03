package Rover;

public class RotarIzq extends Comando{
    public RotarIzq(){
        comando = 'l';
    }

    public void ejecutar(Rover rover) {
        rover.rotarIzq();
    }
}
