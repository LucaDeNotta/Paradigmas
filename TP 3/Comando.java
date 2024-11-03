package Rover;

public class Comando {
    private char comando;
    private Runnable ejecutable;

    public Comando(char comando, Runnable ejecutable) {
        this.comando = comando;
        this.ejecutable = ejecutable;
    }

    public boolean puedeEjecutar (char anotherComando) {
        return comando == anotherComando;
    }

    public void ejecutar () {
        ejecutable.run();
    }
}
