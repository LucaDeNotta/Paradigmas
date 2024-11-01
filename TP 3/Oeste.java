package Rover;

public class Oeste extends Direccion {
    public Direccion right() {
        return new Norte();
    }

    public Direccion left() {
        return new Sur();
    }

    public Posicion forward(Posicion posicion){
        return posicion.avanzarAlOeste();
    }

    public Posicion backward(Posicion posicion) { return posicion.avanzarAlEste(); }
}

