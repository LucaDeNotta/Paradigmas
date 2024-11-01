package Rover;

public class Norte extends Direccion {

    public Direccion right() {
        return new Este();
    }

    public Direccion left() {
        return new Oeste();
    }

    public Posicion forward(Posicion posicion){
        return posicion.avanzarAlNorte();
    }

    public Posicion backward(Posicion posicion) { return posicion.avanzarAlSur(); }
}
