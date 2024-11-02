package Rover;

public abstract class Comando {
    protected char comando;

    public abstract void ejecutar(Rover rover);

    public boolean puedeEjecutar (char anotherComando) {
        return comando == anotherComando;
    }

}
