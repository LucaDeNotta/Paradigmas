package Rover;

public class Este extends Direccion {
    public Direccion right() {
        return new Sur();
    }

    public Direccion left() {
        return new Norte();
    }

    public Posicion forward(Posicion posicion){
        return posicion.avanzarAlEste();
    }

    public Posicion backward(Posicion posicion) { return posicion.avanzarAlOeste(); }
}

