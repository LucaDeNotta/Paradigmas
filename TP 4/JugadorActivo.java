package nogracias;

import java.util.List;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class JugadorActivo extends EstadoJugador {

    public static final String noEsTuTurno = "No es tu turno";
    public static final String noHayFichas = "No hay fichas";

    protected String nombre;
    protected Integer fichas;
    protected TreeSet<Carta> cartas = new TreeSet<>();
    protected EstadoJugador jugadorSiguiente;

    public JugadorActivo(String nombre, Integer fichas) {
        this.nombre = nombre;
        this.fichas = fichas;
    }

    public EstadoJugador asignarJugadorSiguiente(EstadoJugador jugadorSiguiente ) {
        this.jugadorSiguiente = jugadorSiguiente;
        return this;
    }

    public EstadoJugador pagarFicha(String jugador, Stack<Carta> mazo) {
        esTurno(jugador);
        if (fichas == 0){
            throw new RuntimeException(noHayFichas);
        }
        fichas--;
        mazo.peek().pagarFicha();
        return jugadorSiguiente;
    }

    public EstadoJugador tomarCarta(String jugador, Stack<Carta> mazo) {
        esTurno(jugador);
        Carta carta = mazo.pop();
        cartas.add(carta);
        fichas += carta.fichas();
        if (mazo.isEmpty()){
            return new JugadorNoActivo();
        }
        return this;
    }


    private void esTurno(String jugador) {
        if (!esJugador(jugador)) {
            throw new RuntimeException(noEsTuTurno);
        }
    }

    public Integer puntaje(){
        List<Integer> valores = cartas.stream()
                .map(Carta::value)
                .toList();

        return IntStream.range(0, valores.size())
                .filter(i -> i == 0 || valores.get(i) != valores.get(i - 1) + 1)
                .mapToObj(valores::get)
                .mapToInt(Integer::intValue)
                .sum() * -1 + fichas;
    }

    public boolean esJugador(String jugador) {
        return jugador.equals(nombre);
    }

}
