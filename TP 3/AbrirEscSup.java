package Rover;

public class AbrirEscSup extends Comando {
    {
        comando = 'O';
    }
    public void ejecutar(Rover rover) {
        rover.abrirEscSup();
    }
}
