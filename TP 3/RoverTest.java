package Rover;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static Rover.Rover.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverTest {
    
    @Test public void newRoverPointsSomewhere(){
        assertDireccionYUbicacion(roverApuntaNorte(), norte(), posicionInicial());
    }

    @Test public void RoverPointsNorthMovesForward(){
        assertDireccionYUbicacion(roverApuntaNorte().ejecutarComando('f'), norte(), new Posicion(1,2));
    }

    @Test public void RoverPointsEastMovesForward(){
        assertDireccionYUbicacion(roverApuntaEste().ejecutarComando('f'), este(), new Posicion(2,1));
    }

    @Test public void RoverPointsSouthMovesForward(){
        assertDireccionYUbicacion(roverApuntaSur().ejecutarComando('f'), sur(), new Posicion(1,0));
    }

    @Test public void RoverPointsWestMovesForward(){
        assertDireccionYUbicacion(roverApuntaOeste().ejecutarComando('f'), oeste(), new Posicion(0,1));
    }

    @Test public void RoverPointsNorthMovesBackward(){
        assertDireccionYUbicacion(roverApuntaNorte().ejecutarComando('b'), norte(), new Posicion(1,0));
    }

    @Test public void RoverPointsEastMovesBackward(){
        assertDireccionYUbicacion(roverApuntaEste().ejecutarComando('b'), este(), new Posicion(0,1));
    }

    @Test public void RoverPointsSouthMovesBackward(){
        assertDireccionYUbicacion(roverApuntaSur().ejecutarComando('b'), sur(), new Posicion(1,2));
    }

    @Test public void RoverPointsWestMovesBackward(){
        assertDireccionYUbicacion(roverApuntaOeste().ejecutarComando('b'), oeste(), new Posicion(2,1));
    }

    @Test public void RoverPointsNorthRotatesLeft(){
        assertDireccionYUbicacion(roverApuntaNorte().ejecutarComando('l'), oeste(), posicionInicial());
    }

    @Test public void RoverPointsWestRotatesLeft(){
        assertDireccionYUbicacion(roverApuntaOeste().ejecutarComando('l'), sur(), posicionInicial());
    }

    @Test public void RoverPointsSouthRotatesLeft(){
        assertDireccionYUbicacion(roverApuntaSur().ejecutarComando('l'), este(), posicionInicial());
    }

    @Test public void RoverPointsEastRotatesLeft(){
        assertDireccionYUbicacion(roverApuntaEste().ejecutarComando('l'), norte(), posicionInicial());
    }

    @Test public void RoverFullRotatesLeft(){;
        assertDireccionYUbicacion(roverApuntaNorte().recibirComandos("llll"), norte(), posicionInicial());
    }

    @Test public void RoverPointsNorthRotatesRight(){
        assertDireccionYUbicacion(roverApuntaNorte().ejecutarComando('r'), este(), posicionInicial());
    }

    @Test public void RoverPointsEastRotatesRight(){
        assertDireccionYUbicacion(roverApuntaEste().ejecutarComando('r'), sur(), posicionInicial());
    }

    @Test public void RoverPointsSouthRotatesRight(){
        assertDireccionYUbicacion(roverApuntaSur().ejecutarComando('r'), oeste(), posicionInicial());
    }

    @Test public void RoverPointsWestRotatesRight(){
        assertDireccionYUbicacion(roverApuntaOeste().ejecutarComando('r'), norte(), posicionInicial());
    }

    @Test public void RoverFullRotatesRight(){
        assertDireccionYUbicacion(roverApuntaNorte().recibirComandos("rrrr"), norte(), posicionInicial());
    }

    @Test public void canOpenEscotillaSuperior(){
        assertTrue(roverApuntaNorte().ejecutarComando('O').isEscotillaSuperiorOpen());
    }

    @Test public void canOpenEscotillaInferior(){
        assertTrue(roverApuntaNorte().ejecutarComando('o').isEscotillaInferiorOpen());
    }

    @Test public void canAspirar(){
        assertDireccionYUbicacion(roverApuntaNorte().ejecutarComando('O').ejecutarComando('a'), norte(), posicionInicial());
    }

    @Test public void canCavar(){
        assertDireccionYUbicacion(roverApuntaNorte().ejecutarComando('o').ejecutarComando('i'), norte(), posicionInicial());
    }

    @Test public void cantOpenBothEscotillas(){
        assertThrowsLike(Escotillas.noSePuedenAbrirLasDosEscotillas,
                () -> roverApuntaNorte().ejecutarComando('o').ejecutarComando('O'));
    }

    @Test public void cantCloseClosedEscotillas(){
        assertThrowsLike( Escotillas.noSePuedenCerrarEscotillasCerradas,
                () -> roverApuntaNorte().ejecutarComando('c') );
    }

    @Test public void cantAspirarIfEscotillaClosed(){
        assertThrowsLike( Escotillas.escotillaCerrada,
                () -> new Rover( posicionInicial(), norte()).ejecutarComando('a') );
    }

    @Test public void cantCavarIfEscotillaClosed(){
        assertThrowsLike(Escotillas.escotillaCerrada,
                () -> new Rover( posicionInicial(), norte() ).ejecutarComando('i'));
    }

    @Test public void forwardAndTurnRightComands(){
        assertDireccionYUbicacion(roverApuntaNorte().recibirComandos("ffrfffrrffff"), oeste(), new Posicion(0,3));
    }

    @Test public void backwardAndTurnLeftComands(){
        assertDireccionYUbicacion(roverApuntaNorte().recibirComandos("blbbllbbbb"), este(), new Posicion(-1,0));
    }

    @Test public void forwardBackwardLeftRightComands(){
        assertDireccionYUbicacion(roverApuntaNorte().recibirComandos("fffrbrblfffrb"), sur(), new Posicion(3,6));
    }

    @Test public void aspirarCavarAbrirCerrarEscotillasComands(){
        assertDireccionYUbicacion(roverApuntaNorte().recibirComandos("Oacoic"), norte(), new Posicion(1,1));
    }

    @Test public void notProcessFollowingComands(){
        Rover rover = roverApuntaNorte();
        assertThrowsLike( String.format(noSePuedeEjecutarComando, 'z'),
                () -> rover.recibirComandos("frfllbzbbf") );
        assertDireccionYUbicacion(rover, oeste(), new Posicion(3,2));
    }

    public void assertDireccionYUbicacion(Rover rover, Direccion direccion, Posicion posicion){
        assertTrue(rover.apuntaDireccion(direccion));
        assertTrue(rover.estaUbicacion(posicion));
    }

    private static void assertThrowsLike( String expectedMsg, Executable expression ){
        assertEquals( expectedMsg, assertThrows( RuntimeException.class, expression ).getMessage() );
    }

    public Direccion norte(){
        return new Norte();
    }

    public Direccion sur(){
        return new Sur();
    }

    public Direccion este(){
        return new Este();
    }

    public Direccion oeste(){
        return new Oeste();
    }

    public Posicion posicionInicial(){
        return new Posicion(1, 1);
    }

    private Rover roverApuntaNorte() {
        return new Rover(posicionInicial(), norte());
    }

    private Rover roverApuntaEste() {
        return new Rover(posicionInicial(), este());
    }

    private Rover roverApuntaSur() {
        return new Rover(posicionInicial(), sur());
    }

    private Rover roverApuntaOeste() {
        return new Rover(posicionInicial(), oeste());
    }
}
