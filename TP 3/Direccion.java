package Rover;

public abstract class Direccion {
    public abstract Direccion right();

    public abstract Direccion left();

    public boolean equals( Object anObject ) {
        return this.getClass().isInstance( anObject );
    }

    public abstract void forward(Posicion posicion);
    public abstract void backward(Posicion posicion);


}
