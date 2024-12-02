package nogracias;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;

public abstract class Jugador {
    protected String nombre;
    protected Integer fichas;
    protected TreeSet<Carta> cartas = new TreeSet<>();

    public Jugador(String nombre, Integer fichas) {
        this.nombre = nombre;
        this.fichas = fichas;
    }

    public String nombre(){
        return nombre;
    }

    public Integer fichas(){
        return fichas;
    }

    public abstract Jugador pagarFicha(String jugador, Carta carta);

    public abstract Jugador tomarCarta(String jugador ,Carta carta);

    public Integer puntaje(){
        List<Integer> valores = cartas.stream()
                .map(Carta::value)
                .toList();

        return IntStream.range(0, valores.size())
                .filter(i -> i == 0 || valores.get(i) != valores.get(i - 1) + 1)
                .mapToObj(valores::get)
                .mapToInt(Integer::intValue)
                .sum() - fichas;
    }
    public abstract Jugador ganador(Juego juego);

}
