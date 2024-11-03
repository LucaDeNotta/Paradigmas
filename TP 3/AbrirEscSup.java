package Rover;

public class AbrirEscSup extends Comando {
    public AbrirEscSup(){
        comando = 'O';
    }

    public void ejecutar(Rover rover) {
        rover.abrirEscSup();
    }
}
