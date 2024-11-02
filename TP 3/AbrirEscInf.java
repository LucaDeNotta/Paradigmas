package Rover;

public class AbrirEscInf extends Comando {
    {
        comando = 'o';
    }
    public void ejecutar(Rover rover) {
        rover.abrirEscInf();
    }
}
