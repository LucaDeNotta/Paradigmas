import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public  class Round {
    private Predicate<Integer> cantidadJugadores;
    private Consumer<Juego> accion;

    public static List<Round> listaRounds(){
        return Arrays.asList( new Round( jugadores -> jugadores >= 3 && jugadores <= 5,
                juego -> { juego.repartirFichas(11); }),
            new Round( jugadores -> jugadores == 6,
                    juego -> { juego.repartirFichas(9); }),
            new Round( jugadores -> jugadores == 7,
                    juego -> { juego.repartirFichas(7); })
        );
    }

    public Round(Predicate<Integer> cantidadJugadores, Consumer<Juego> expression) {
        this.cantidadJugadores = cantidadJugadores;
        this.accion = expression;
    }

    public boolean puedenJugar(int numJugadores) {
        return cantidadJugadores.test(numJugadores);
    }

    public void repartir(Juego juego) {
        accion.accept(juego);
    }
}
