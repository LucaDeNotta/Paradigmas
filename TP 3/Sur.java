package Rover;

public class Sur extends Direccion {
    public Direccion right() { return new Oeste(); }

    public Direccion left() { return new Este(); }

    public Posicion forward(Posicion posicion){ return posicion.avanzarAlSur(); }

    public Posicion backward(Posicion posicion) { return posicion.avanzarAlNorte();
    }
}
