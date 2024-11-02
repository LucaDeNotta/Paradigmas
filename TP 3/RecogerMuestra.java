package Rover;

public class RecogerMuestra extends Comando {
    {
        comando = 'i';
    }
    public void ejecutar(Rover rover) {
        rover.recogerMuestra();
    }
}
