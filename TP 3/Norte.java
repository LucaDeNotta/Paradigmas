package Rover;

public class Norte extends Direccion {

    public Direccion right() {
        return new Este();
    }

    public Direccion left() {
        return new Oeste();
    }

    public void forward(Posicion posicion){
        posicion.forward(this);
    }

    public void backward(Posicion posicion) {
        posicion.backward(this);
    }
}
