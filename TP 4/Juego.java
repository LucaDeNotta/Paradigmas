import java.util.List;
import java.util.Stack;

public class Juego {
    public static String cantidadDeJugadoresNoEsCorrecto = "Cantidad de jugadores no es correcta";

    int turnoActual = 0;
    List<Jugador> jugadores;
    Stack<Carta> mazo;
    Carta cartaActual;
    Jugador ganador;

    public Juego (List<Jugador> jugadores, Stack<Carta> mazo){
        Round rondaSeleccionada = Round.listaRounds().stream()
                .filter(ronda -> ronda.puedenJugar(jugadores.size()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(cantidadDeJugadoresNoEsCorrecto));

        this.jugadores = jugadores;
        rondaSeleccionada.repartir(this);

        this.mazo = mazo;
        cartaActual = mazo.pop();
    }

    public Juego avanzarTurno(){
        if( !mazo.isEmpty() ){
            turnoActual = ( turnoActual + 1 ) % jugadores.size();
            return this;
        }
        return terminarJuego();
    }

    public Juego terminarJuego() {
        //TODO problema cuando hay un empate
        ganador = jugadores.stream()
                .max((j1, j2) -> Integer.compare(j1.puntaje(), j2.puntaje())) // Compara puntajes
                .orElse(null);
        return this;
    }


    public Juego agarrarCarta(){
        getJugadorActual().agarrarCarta( cartaActual );
        cartaActual = mazo.pop();
        return this;
    }

    public Juego usarFicha(){
        getJugadorActual().usarFicha();
        cartaActual.usarFicha();
        avanzarTurno();

        return this;
    }

    public Jugador getJugadorActual() {
        return jugadores.get( turnoActual );
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void repartirFichas(int fichas) { jugadores.forEach(jugador -> jugador.asignarFichas(fichas)); }
}
