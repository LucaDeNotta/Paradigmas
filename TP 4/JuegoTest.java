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

    @Test public void juegoConMenosDe3Jugadores() {
        assertThrowsLike(Juego.cantidadDeJugadoresNoEsCorrecto, () -> new Juego(
                mazo5Cartas(),
                2,
                List.of(jugador1())) );
    }

    @Test public void juegoConMasDe7Jugadores() {
        List<String> jugadores = sieteJugadores();
        jugadores.add("jugador8");
        assertThrowsLike(Juego.cantidadDeJugadoresNoEsCorrecto,
                () -> new Juego( 
                                mazo5Cartas(),
                            2 , 
                            jugadores) );
    }

    @Test public void cantidadFichasIniciales3Jugadores(){
        Assertions.assertEquals(11, juego3Jugadores().getJugadorActual().fichas());
    }

    @Test public void cantidadFichasIniciales6Jugadores(){
        Assertions.assertEquals(9, juego6Jugadores().getJugadorActual().fichas());
    }

    @Test public void cantidadFichasIniciales7Jugadores(){
        Assertions.assertEquals(7, juego7Jugadores().getJugadorActual().fichas());
    }

    @Test public void agarrarCartaCon2Fichas(){
        Assertions.assertEquals( 13, juego3Jugadores().pagarFicha(jugador1()).pagarFicha(jugador2()).tomarCarta(jugador3()).getJugadorActual().fichas() );
    }

    @Test public void agarrarCartaCon3Fichas(){ //Da una vuelta
        Assertions.assertEquals( 13,
                juego3Jugadores().pagarFicha(jugador1()).pagarFicha(jugador2()).pagarFicha(jugador3()).tomarCarta(jugador4()).getJugadorActual().fichas() );
    }


    @Test public void noSePuedepagarFichaSinFichas(){
        assertThrowsLike( "No hay fichas",
                () -> new Juego( mazo5Cartas(), 0, tresJugadores()).pagarFicha(jugador1()));
    }

    private static void assertThrowsLike( String expectedMsg, Executable expression ){
        assertEquals( expectedMsg, assertThrows( RuntimeException.class, expression ).getMessage() );
    }

    //TODO implemento sacar 9 cartas? No se por que hice un mazo tan grande, no hace falta
    private Stack<Carta> mazo33Cartas(){
        Stack<Carta> mazo = new Stack<>();
        mazo.add(new Carta(3));
        mazo.add(new Carta(4));
        mazo.add(new Carta(5));
        mazo.add(new Carta(6));
        mazo.add(new Carta(7));
        mazo.add(new Carta(8));
        mazo.add(new Carta(9));
        mazo.add(new Carta(10));
        mazo.add(new Carta(11));
        mazo.add(new Carta(12));
        mazo.add(new Carta(13));
        mazo.add(new Carta(14));
        mazo.add(new Carta(15));
        mazo.add(new Carta(16));
        mazo.add(new Carta(17));
        mazo.add(new Carta(18));
        mazo.add(new Carta(19));
        mazo.add(new Carta(20));
        mazo.add(new Carta(21));
        mazo.add(new Carta(22));
        mazo.add(new Carta(23));
        mazo.add(new Carta(24));
        mazo.add(new Carta(25));
        mazo.add(new Carta(26));
        mazo.add(new Carta(27));
        mazo.add(new Carta(28));
        mazo.add(new Carta(29));
        mazo.add(new Carta(30));
        mazo.add(new Carta(31));
        mazo.add(new Carta(32));
        mazo.add(new Carta(33));
        mazo.add(new Carta(34));
        mazo.add(new Carta(35));
        return mazo;
    }

    private Stack<Carta> mazo5Cartas(){
        Stack<Carta> mazo = new Stack<>();
        mazo.add(new Carta(5));
        mazo.add(new Carta(6));
        mazo.add(new Carta(7));
        mazo.add(new Carta(10));
        mazo.add(new Carta(12));
        return mazo;
    }

    private Juego juego3Jugadores(){
        return new Juego( mazo33Cartas(), 2, tresJugadores() );
    }


    private Juego juego6Jugadores(){
        return new Juego( mazo33Cartas(), 2 , seisJugadores());
    }


    private Juego juego7Jugadores() {
        return new Juego( mazo33Cartas(), 2, sieteJugadores());
    }

    private static String jugador1() {
        return "Jugador1";
    }

    private static String jugador2() {
        return "Jugador2";
    }

    private static String jugador3() {
        return "Jugador3";
    }

    private static String jugador4() {
        return "Jugador4";
    }

    private static String jugador5() {
        return "Jugador5";
    }

    private static String jugador6() {
        return "Jugador6";
    }

    private static String jugador7() {
        return "Jugador7";
    }

    private static List<String> tresJugadores(){
        return new ArrayList<>(Arrays.asList(jugador1(), jugador2(), jugador3()));
    }

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
}
