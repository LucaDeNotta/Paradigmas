package nogracias;

public class JugadorNoActivo extends Jugador{

    public static final String terminoElJuego = "Ya termino el juego";

    public JugadorNoActivo(String nombre, Integer fichas) {
        super(nombre, fichas);
    }

    public Jugador pagarFicha(String jugador, Carta carta) {
        throw new RuntimeException(terminoElJuego);
    }

    public Jugador tomarCarta(String jugador, Carta carta) {
        throw new RuntimeException(terminoElJuego);
    }

    public Jugador ganador(Juego juego) {
        return this;
    }
}
