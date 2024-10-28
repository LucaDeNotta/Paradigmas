package Rover;

public class Sur extends Direccion {
    public Direccion right() {
        return new Oeste();
    }

    public Direccion left() {
        return new Este();
    }

    public void forward(Posicion posicion){
        posicion.forward(this);
    }

    public void backward(Posicion posicion) { posicion.backward(this);
    }
}
