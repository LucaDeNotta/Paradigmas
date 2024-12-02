package nogracias;

import java.util.List;
import java.util.Stack;

public  class Juego {

    public static String cantidadDeFichasInvalida = "Cantidad de fichas invalida";
    public static String cantidadDeJugadoresNoEsCorrecto = "Cantidad de jugadores no es correcta";

    Jugador jugadorActual;
    Stack<Carta> mazo;

    public Juego (Stack<Carta> mazo, int fichasARepartir, List<String> jugadores){
        if (jugadores.size() < 3 || jugadores.size() > 7){
            throw new RuntimeException(cantidadDeJugadoresNoEsCorrecto);
        }
        if (fichasARepartir < 0){
            throw new RuntimeException(cantidadDeFichasInvalida);
        }

//        jugadorActual = jugadores.stream()
//                .map(jugador -> new JugadorActivo(jugador, fichasARepartir, ) {
//                });
        this.jugadorActual = new JugadorActivo(jugadores.getFirst(), fichasARepartir);
        this.mazo = mazo;
    }

    public Juego tomarCarta(String jugador){
        jugadorActual.tomarCarta( jugador, mazo.pop() );
        return this;
    }

    public Juego pagarFicha(String jugador){
        jugadorActual.pagarFicha(jugador, mazo.peek());

        return this;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }
}
