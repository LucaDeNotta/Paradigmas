package nogracias;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public  class Juego {

    public static final String jugadorNoEncontrado = "Jugador no encontrado: ";
    public static String cantidadDeFichasInvalida = "Cantidad de fichas invalida";
    public static String cantidadDeJugadoresNoEsCorrecto = "Cantidad de jugadores no es correcta";

    private List<JugadorActivo> jugadores;
    private EstadoJugador jugadorActual;
    private Stack<Carta> mazo;

    public Juego (Stack<Carta> mazo, int fichasARepartir, List<String> jugadores){
        if (jugadores.size() < 3 || jugadores.size() > 7){
            throw new RuntimeException(cantidadDeJugadoresNoEsCorrecto);
        }
        if (fichasARepartir <= 0){
            throw new RuntimeException(cantidadDeFichasInvalida);
        }

        this.jugadores = jugadores.stream()
                .map(jugador -> new JugadorActivo(jugador, fichasARepartir) )
                .collect(Collectors.toList());

        IntStream.range(0, jugadores.size())
                .forEach(i -> {
                    JugadorActivo actual = this.jugadores.get(i);
                    JugadorActivo siguiente = this.jugadores.get((i + 1) % this.jugadores.size());
                    actual.asignarJugadorSiguiente(siguiente);
                });

        this.jugadorActual = this.jugadores.getFirst();
        this.mazo = mazo;
    }

    public Juego tomarCarta(String jugador){
        jugadorActual = jugadorActual.tomarCarta( jugador, mazo);
        return this;
    }

    public Juego pagarFicha(String jugador){
        jugadorActual = jugadorActual.pagarFicha(jugador, mazo);
        return this;
    }

    public Integer puntaje (String unJugador) {
        return jugadores.stream()
                .filter(jugador -> jugador.esJugador(unJugador))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(jugadorNoEncontrado + unJugador)).puntaje();
    }
}
