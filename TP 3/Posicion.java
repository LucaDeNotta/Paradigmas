package Rover;

public class Posicion {
    //TODO cambiar a private cuando quitemos los ifs
    public int coordenada_X;
    public int coordenada_Y;

    public Posicion( int x, int y ){
        this.coordenada_X = x;
        this.coordenada_Y = y;
    }

    public Posicion forward( Direccion direccion ){ return direccion.forward( this ); }

    public Posicion backward( Direccion direccion ){ return direccion.backward( this ); }

    public Posicion avanzarAlNorte(){ return new Posicion( coordenada_X, coordenada_Y + 1 ); }

    public Posicion avanzarAlSur(){  return new Posicion( coordenada_X, coordenada_Y - 1 ); }

    public Posicion avanzarAlEste(){  return new Posicion( coordenada_X + 1, coordenada_Y ); }

    public Posicion avanzarAlOeste(){  return new Posicion( coordenada_X - 1, coordenada_Y ); }

    public boolean equals( Object anObject ){
        return anObject instanceof Posicion another
                && another.coordenada_X == coordenada_X
                && another.coordenada_Y == coordenada_Y;
    }
}
