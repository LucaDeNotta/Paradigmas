package Rover;

public class Oeste extends Direccion {
    public Direccion right() {
        return new Norte();
    }

    public Direccion left() {
        return new Sur();
    }

    public void forward(Posicion posicion){
        posicion.forward(this);
    }

    public void backward(Posicion posicion) {
        posicion.backward(this);
    }
}
