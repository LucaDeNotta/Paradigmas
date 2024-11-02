package Rover;

public class CerrarEsc extends Comando{
    {
        comando = 'c';
    }
    public void ejecutar(Rover rover) {
        rover.cerrarEsc();
    }
}
