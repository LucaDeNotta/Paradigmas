package Rover;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static Rover.Rover.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverTest {
    // TODO cambiar somewhere
    @Test public void newRoverPointsSomewhere(){
        assertTrue(new Rover(1, 1, "N").apuntaDireccion("N"));
    }

    @Test public void RoverMovesForward(){
        assertTrue(new Rover(1, 1, "N").ejecutarComando('f').estaUbicacion(1,2));
        assertTrue(new Rover(1, 1, "E").ejecutarComando('f').estaUbicacion(2,1));
        assertTrue(new Rover(1, 1, "S").ejecutarComando('f').estaUbicacion(1,0));
        assertTrue(new Rover(1, 1, "O").ejecutarComando('f').estaUbicacion(0,1));
    }

    @Test public void RoverMovesBackward(){
        assertTrue(new Rover(1, 1, "N").ejecutarComando('b').estaUbicacion(1,0));
        assertTrue(new Rover(1, 1, "E").ejecutarComando('b').estaUbicacion(0,1));
        assertTrue(new Rover(1, 1, "S").ejecutarComando('b').estaUbicacion(1,2));
        assertTrue(new Rover(1, 1, "O").ejecutarComando('b').estaUbicacion(2,1));
    }

    @Test public void RoverRotatesLeft(){
        Rover rover = new Rover(1, 1, "N");
        assertTrue(rover.recibirComandos("llll").apuntaDireccion("N"));
        assertTrue(rover.ejecutarComando('l').apuntaDireccion("O"));
        assertTrue(rover.ejecutarComando('l').apuntaDireccion("S"));
        assertTrue(rover.ejecutarComando('l').apuntaDireccion("E"));
        assertTrue(rover.ejecutarComando('l').apuntaDireccion("N"));
    }

    @Test public void RoverRotatesRight(){
        Rover rover = new Rover(1, 1, "N");
        assertTrue(rover.recibirComandos("rrrr").apuntaDireccion("N"));
        assertTrue(rover.ejecutarComando('r').apuntaDireccion("E"));
        assertTrue(rover.ejecutarComando('r').apuntaDireccion("S"));
        assertTrue(rover.ejecutarComando('r').apuntaDireccion("O"));
        assertTrue(rover.ejecutarComando('r').apuntaDireccion("N"));
    }

    @Test public void canOpenEscotillaSuperior(){
        assertTrue(new Rover(1, 1, "N").ejecutarComando('O').isEscotillaSuperiorOpen());
    }

    @Test public void canOpenEscotillaInferior(){
        assertTrue(new Rover(1, 1, "N").ejecutarComando('o').isEscotillaInferiorOpen());
    }

    @Test public void canAspirar(){
        Rover rover = new Rover(1, 1, "N").ejecutarComando('O');
        assertEquals(rover, rover.ejecutarComando('a'));
    }

    @Test public void canCavar(){
        Rover rover = new Rover(1, 1, "N").ejecutarComando('o');
        assertEquals(rover, rover.ejecutarComando('i'));
    }

    @Test public void cantOpenBothEscotillas(){
        assertThrowsLike(noSePuedenAbrirLasDosEscotillas,
                () -> new Rover(1, 1, "N").ejecutarComando('o').ejecutarComando('O'));
    }

    @Test public void cantCloseClosedEscotillas(){
        assertThrowsLike(noSePuedenCerrarEscotillasCerradas,
                () -> new Rover(1, 1, "N").ejecutarComando('c'));
    }

    @Test public void cantAspirarIfEscotillaClosed(){
        assertThrowsLike(escotillaCerrada,
                () -> new Rover(1, 1, "N").ejecutarComando('a'));
    }

    @Test public void cantCavarIfEscotillaClosed(){
        assertThrowsLike(escotillaCerrada,
                () -> new Rover(1, 1, "N").ejecutarComando('i'));
    }

    @Test public void forwardAndTurnRightComands(){
        Rover rover = new Rover(1, 1, "N").recibirComandos("ffrfffrrffff");
        assertTrue(rover.apuntaDireccion("O"));
        assertTrue(rover.estaUbicacion(0,3));
    }

    @Test public void backwardAndTurnLeftComands(){
        Rover rover = new Rover(1, 1, "N").recibirComandos("blbbllbbbb");
        assertTrue(rover.apuntaDireccion("E"));
        assertTrue(rover.estaUbicacion(-1,0));
    }

    @Test public void forwardBackwardLeftRightComands(){
        Rover rover = new Rover(1, 1, "N").recibirComandos("fffrbrblfffrb");
        assertTrue(rover.apuntaDireccion("S"));
        assertTrue(rover.estaUbicacion(3,6));
    }

    @Test public void aspirarCavarAbrirCerrarEscotillasComands(){
        Rover rover = new Rover(1, 1, "N");
        assertEquals(rover, rover.recibirComandos("Oacoic"));
    }

    @Test public void notProcessFollowingComands(){
        Rover rover = new Rover(1, 1, "N");
        assertThrowsLike(String.format(noSePuedeEjecutarComando, 'z'),
                () -> rover.recibirComandos("frfllbzbbf"));
        assertTrue(rover.apuntaDireccion("O"));
        assertTrue(rover.estaUbicacion(3,2));
    }

    private static void assertThrowsLike( String expectedMsg, Executable expression ){
        assertEquals( expectedMsg, assertThrows( RuntimeException.class, expression ).getMessage() );
    }
}