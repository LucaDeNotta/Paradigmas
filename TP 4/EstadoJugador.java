package nogracias;

import java.util.List;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.IntStream;

public abstract class EstadoJugador {

    public abstract EstadoJugador pagarFicha(String jugador, Stack<Carta> mazo);

    public abstract EstadoJugador tomarCarta(String jugador , Stack<Carta> mazo);
}