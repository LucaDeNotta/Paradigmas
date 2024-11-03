package Rover;

public class CerrarEscotilla extends Comando{
    public CerrarEscotilla(){
        comando = 'c';
    }

    public void ejecutar(Rover rover) {
        rover.cerrarEscotilla();
    }
}
