package nogracias;

public class JugadorActivo extends Jugador{

    public static final String noEsTuTurno = "No es tu turno";

    public JugadorActivo(String nombre, Integer fichas) {
        super(nombre, fichas);
    }

    public Jugador pagarFicha(String jugador, Carta carta) {
        esTurno(jugador);
        if (fichas == 0){
            throw new RuntimeException("No hay fichas");
        }
        fichas--;
        carta.pagarFicha();
        return this;
    }

    public Jugador tomarCarta(String jugador, Carta carta) {
        esTurno(jugador);
        cartas.add(carta);
        fichas += carta.fichas();
        return this;
    }

    public Jugador ganador(Juego juego) {
        throw new RuntimeException("El juego Continua");
    }

    private void esTurno(String jugador) {
        if (nombre != jugador){
            throw new RuntimeException(noEsTuTurno);
        }
    }

}
