package nogracias;

import java.util.Stack;

public class JugadorNoActivo extends EstadoJugador {

    public static final String terminoElJuego = "Ya termino el juego";

    public JugadorNoActivo() {}

    public EstadoJugador pagarFicha(String jugador, Stack<Carta> cartas) {
        throw new RuntimeException(terminoElJuego);
    }

    public EstadoJugador tomarCarta(String jugador, Stack<Carta> cartas) {
        throw new RuntimeException(terminoElJuego);
    }
}
