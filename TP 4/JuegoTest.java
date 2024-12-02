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
        assertThrowsLike(Juego.cantidadDeJugadoresNoEsCorrecto, () -> new Juego( List.of(jugador1()), mazo5Cartas() ) );
    }

    @Test public void juegoConMasDe7Jugadores() {
        List<Jugador> jugadores = sieteJugadores();
        jugadores.add(new Jugador("jugador8"));
        assertThrowsLike(Juego.cantidadDeJugadoresNoEsCorrecto, () -> new Juego( jugadores, mazo5Cartas() ) );
    }

    @Test public void cantidadFichasIniciales3Jugadores(){
        assertEquals(11, juego3Jugadores().getJugadorActual().fichas());
    }

    @Test public void cantidadFichasIniciales6Jugadores(){
        assertEquals(9, juego6Jugadores().getJugadorActual().fichas());
    }

    @Test public void cantidadFichasIniciales7Jugadores(){
        assertEquals(7, juego7Jugadores().getJugadorActual().fichas());
    }

    @Test public void agarrarCartaCon2Fichas(){
        assertEquals( 13, juego3Jugadores().usarFicha().usarFicha().agarrarCarta().getJugadorActual().fichas() );
    }

    @Test public void agarrarCartaCon3Fichas(){ //Da una vuelta
        assertEquals( 13,
                juego3Jugadores().usarFicha().usarFicha().usarFicha().agarrarCarta().getJugadorActual().fichas() );
    }


    @Test public void noSePuedeUsarFichaSinFichas(){
        assertThrowsLike( "No hay fichas",
                () -> new Jugador("jugador").asignarFichas(0).usarFicha() );
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
        return new Juego(tresJugadores(), mazo33Cartas());
    }


    private Juego juego6Jugadores(){
        return new Juego(seisJugadores(), mazo33Cartas());
    }


    private Juego juego7Jugadores() {
        return new Juego(sieteJugadores(), mazo33Cartas());
    }

    private static Jugador jugador1() {
        return new Jugador("Jugador1");
    }

    private static Jugador jugador2() {
        return new Jugador("Jugador2");
    }

    private static Jugador jugador3() {
        return new Jugador("Jugador3");
    }

    private static Jugador jugador4() {
        return new Jugador("Jugador4");
    }

    private static Jugador jugador5() {
        return new Jugador("Jugador5");
    }

    private static Jugador jugador6() {
        return new Jugador("Jugador6");
    }

    private static Jugador jugador7() {
        return new Jugador("Jugador7");
    }

    private static List<Jugador> tresJugadores(){
        return new ArrayList<>(Arrays.asList(jugador1(), jugador2(), jugador3()));
    }

    private static List<Jugador> seisJugadores(){
        List<Jugador> jugadores = tresJugadores();
        jugadores.addAll(Arrays.asList(jugador4(), jugador5(), jugador6()));
        return jugadores;
    }

    private static List<Jugador> sieteJugadores(){
        List<Jugador> jugadores = seisJugadores();
        jugadores.add(jugador7());
        return jugadores;
    }

}

