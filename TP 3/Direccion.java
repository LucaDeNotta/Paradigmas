package Rover;

public abstract class Direccion {
    public abstract Direccion right();

    public abstract Direccion left();

    public abstract Posicion forward(Posicion posicion);

    public abstract Posicion backward(Posicion posicion);

    public boolean equals( Object anObject ) {
        return this.getClass().isInstance( anObject );
    }
}

