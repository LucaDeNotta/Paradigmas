package Rover;

public class RecogerMuestra extends Comando {
    public RecogerMuestra() {
        comando = 'i';
    }

    public void ejecutar(Rover rover) {
        rover.recogerMuestra();
    }
}
