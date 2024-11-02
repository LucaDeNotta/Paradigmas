package Rover;

public class RotarIzq extends Comando{
    {
        comando = 'l';
    }
    public void ejecutar(Rover rover) {
        rover.rotarIzq();
    }
}
