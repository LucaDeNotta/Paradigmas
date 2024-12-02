package nogracias;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoTest {
    @Test public void juegoConMenosDe3Jugadores() {assertThrowsLike(Juego.cantidadDeJugadoresNoEsCorrecto, () -> new Juego( mazo2Cartas(),
                2,
                List.of(jugador1())) ); }

    @Test public void juegoConMasDe7Jugadores() {
        List<String> jugadores = sieteJugadores();
        jugadores.add("jugador8");
        assertThrowsLike(Juego.cantidadDeJugadoresNoEsCorrecto,
                () -> new Juego( mazo2Cartas(), 2, jugadores) );
    }

    @Test public void cantidadFichasIniciales(){
        Assertions.assertEquals( 2, juego3Jugadores().puntaje(jugador1()));
    }

    @Test public void agarrarCartaCon2Fichas(){
        assertEquals( -8, juego3Jugadores().pagarFicha(jugador1()).pagarFicha(jugador2()).tomarCarta(jugador3()).puntaje(jugador3()) );
    }

    @Test public void darUnaVueltaAgarrarCarta(){
        assertEquals( -8,
                juego3Jugadores().pagarFicha(jugador1()).pagarFicha(jugador2()).pagarFicha(jugador3()).tomarCarta(jugador1()).puntaje(jugador1()) );
    }

    @Test public void noPuedePagarFichaSiNoEsSuTurno(){
        assertThrowsLike(JugadorActivo.noEsTuTurno, () -> juego3Jugadores().pagarFicha(jugador1()).pagarFicha(jugador3()));
    }

    @Test public void noPuedeTomarCartaSiNoEsSuTurno(){
        assertThrowsLike(JugadorActivo.noEsTuTurno, () -> juego3Jugadores().pagarFicha(jugador1()).tomarCarta(jugador3()));
    }

    @Test public void noSePuedePagarFichaSinFichas(){
        assertThrowsLike( JugadorActivo.noHayFichas,
                () -> juego3Jugadores1Ronda()
                        .pagarFicha(jugador1())
                        .pagarFicha(jugador2())
                        .pagarFicha(jugador3())
                        .pagarFicha(jugador1()));
    }

    @Test public void noSePuedeEmpezarConFichasNegativas(){
        assertThrowsLike( Juego.cantidadDeFichasInvalida, () -> new Juego( mazo2Cartas(), -1, tresJugadores() ));
    }

    @Test public void seConsultaPuntajeExitosamenteEnTurno(){
        assertEquals( -8, juego3Jugadores1Ronda().tomarCarta(jugador1()).puntaje(jugador1()));
    }

    @Test public void seConsultaPuntajeExitosamenteFueraTurno(){
        assertEquals( -9, juego3Jugadores1Ronda().tomarCarta(jugador1()).pagarFicha(jugador1()).puntaje(jugador1()));
    }

    @Test public void puntajeConCartasConsecutivas(){
        assertEquals( -3 , juego3JugadoresMazoConsecutivo().tomarCarta(jugador1()).tomarCarta(jugador1()).puntaje(jugador1()) );
    }

    @Test public void noSePuedeConsultarPuntajeDeNoJugador(){
        assertThrowsLike(Juego.jugadorNoEncontrado + jugador4(), () -> juego3Jugadores().puntaje(jugador4()));
    }

    @Test public void noSePuedePagarFichasCuandoTerminaElJuego(){
        assertThrowsLike( JugadorNoActivo.terminoElJuego, () -> juego3Jugadores1Ronda()
                .tomarCarta(jugador1())
                .tomarCarta(jugador1())
                .pagarFicha(jugador1()) );
    }

    @Test public void noSePuedeTomarMasCartasCuandoTerminaElJuego(){
        assertThrowsLike( JugadorNoActivo.terminoElJuego, () -> juego3Jugadores1Ronda()
                .tomarCarta(jugador1())
                .tomarCarta(jugador1())
                .tomarCarta(jugador1()) );
    }

    private static void assertThrowsLike( String expectedMsg, Executable expression ){
        assertEquals( expectedMsg, assertThrows( RuntimeException.class, expression ).getMessage() );
    }

    private Stack<Carta> mazo2Cartas(){
        Stack<Carta> mazo = new Stack<>();
        mazo.add(new Carta(5));
        mazo.add(new Carta(12));
        return mazo;
    }

    private Stack<Carta> mazo2CartasConsecutivas(){
        Stack<Carta> mazo = new Stack<>();
        mazo.add(new Carta(5));
        mazo.add(new Carta(6));
        return mazo;
    }

    private Juego juego3Jugadores(){ return new Juego( mazo2Cartas(), 2, tresJugadores() ); }

    private Juego juego3JugadoresMazoConsecutivo(){ return new Juego( mazo2CartasConsecutivas(), 2, tresJugadores() ); }

    private static String jugador1() { return "Jugador1"; }

    private static String jugador2() { return "Jugador2"; }

    private static String jugador3() { return "Jugador3"; }

    private static String jugador4() { return "Jugador4"; }

    private static String jugador5() { return "Jugador5"; }

    private static String jugador6() { return "Jugador6"; }

    private static String jugador7() { return "Jugador7"; }

    private static List<String> tresJugadores(){ return new ArrayList<>(Arrays.asList(jugador1(), jugador2(), jugador3())); }

    private static List<String> seisJugadores(){
        List<String> jugadores = tresJugadores();
        jugadores.addAll(Arrays.asList(jugador4(), jugador5(), jugador6()));
        return jugadores;
    }

    private static List<String> sieteJugadores(){
        List<String> jugadores = seisJugadores();
        jugadores.add(jugador7());
        return jugadores;
    }

    private Juego juego3Jugadores1Ronda() {
        return juego3Jugadores().pagarFicha(jugador1()).pagarFicha(jugador2()).pagarFicha(jugador3());
    }
}
