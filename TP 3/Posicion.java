package Rover;

public class Posicion {
    //TODO cambiar a private cuando quitemos los ifs
    public int coordenada_X;
    public int coordenada_Y;

    public Posicion( int x, int y){
        this.coordenada_X = x;
        this.coordenada_Y = y;
    }

    public void forward(Norte Norte){ coordenada_Y++; }

    public void forward(Este Este){ coordenada_X++; }

    public void forward(Sur Sur){
        coordenada_Y--;
    }

    public void forward(Oeste Oeste){
        coordenada_X--;
    }

    public void backward(Norte Norte){ coordenada_Y--; }

    public void backward(Este este) { coordenada_X--; }

    public void backward(Sur Sur){ coordenada_Y++; }

    public void backward(Oeste Oeste){ coordenada_X++; }

    public boolean equals(Object anObject){
        return anObject instanceof Posicion another
                && another.coordenada_X == coordenada_X
                && another.coordenada_Y == coordenada_Y;
    }
}
