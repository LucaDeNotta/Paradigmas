package Rover;

public class Este extends Direccion {
    public Direccion right() {
        return new Sur();
    }

    public Direccion left() {
        return new Norte();
    }

    public void forward(Posicion posicion){
        posicion.forward(this);
    }

    public void backward(Posicion posicion) {
        posicion.backward(this);
    }
}
