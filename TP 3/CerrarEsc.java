package Rover;

public class CerrarEsc extends Comando{
    public CerrarEsc(){
        comando = 'c';
    }

    public void ejecutar(Rover rover) {
        rover.cerrarEsc();
    }
}
