package Rover;

public class AbrirEscInf extends Comando {
    public AbrirEscInf(){
        comando = 'o';
    }

    public void ejecutar(Rover rover) {
        rover.abrirEscInf();
    }
}
