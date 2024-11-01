package Rover;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static Rover.Rover.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverTest {
    // TODO cambiar somewhere
    @Test public void newRoverPointsSomewhere(){
        assertTrue(new Rover(posicionInicial(), norte()).apuntaDireccion(norte()));
    }

    // TODO organizar la creacion de los Rover y cuando sea necesario, organizar el codigo
    @Test public void RoverMovesForward(){
        assertTrue(new Rover(posicionInicial(), norte()).ejecutarComando('f').estaUbicacion(1,2));
        assertTrue(new Rover(posicionInicial(), este()).ejecutarComando('f').estaUbicacion(2,1));
        assertTrue(new Rover(posicionInicial(), sur()).ejecutarComando('f').estaUbicacion(1,0));
        assertTrue(new Rover(posicionInicial(), oeste()).ejecutarComando('f').estaUbicacion(0,1));
    }

    @Test public void RoverMovesBackward(){
        assertTrue(new Rover(posicionInicial(), norte()).ejecutarComando('b').estaUbicacion(1,0));
        assertTrue(new Rover(posicionInicial(), este()).ejecutarComando('b').estaUbicacion(0,1));
        assertTrue(new Rover(posicionInicial(), sur()).ejecutarComando('b').estaUbicacion(1,2));
        assertTrue(new Rover(posicionInicial(), oeste()).ejecutarComando('b').estaUbicacion(2,1));
    }

    @Test public void RoverRotatesLeft(){
        Rover rover = new Rover(posicionInicial(), norte());
        assertTrue(rover.recibirComandos("llll").apuntaDireccion(norte()));
        assertTrue(rover.ejecutarComando('l').apuntaDireccion(oeste()));
        assertTrue(rover.ejecutarComando('l').apuntaDireccion(sur()));
        assertTrue(rover.ejecutarComando('l').apuntaDireccion(este()));
        assertTrue(rover.ejecutarComando('l').apuntaDireccion(norte()));
    }

    @Test public void RoverRotatesRight(){
        Rover rover = new Rover(posicionInicial(), norte());
        assertTrue(rover.recibirComandos("rrrr").apuntaDireccion(norte()));
        assertTrue(rover.ejecutarComando('r').apuntaDireccion(este()));
        assertTrue(rover.ejecutarComando('r').apuntaDireccion(sur()));
        assertTrue(rover.ejecutarComando('r').apuntaDireccion(oeste()));
        assertTrue(rover.ejecutarComando('r').apuntaDireccion(norte()));
    }

    @Test public void canOpenEscotillaSuperior(){
        assertTrue(new Rover(posicionInicial(), norte()).ejecutarComando('O').isEscotillaSuperiorOpen());
    }

    @Test public void canOpenEscotillaInferior(){
        assertTrue(new Rover(posicionInicial(), norte()).ejecutarComando('o').isEscotillaInferiorOpen());
    }

    @Test public void canAspirar(){
        Rover rover = new Rover(posicionInicial(), norte()).ejecutarComando('O');
        assertEquals(rover, rover.ejecutarComando('a'));
    }

    @Test public void canCavar(){
        Rover rover = new Rover( posicionInicial(), norte() ).ejecutarComando('o');
        assertEquals( rover, rover.ejecutarComando('i') );
    }

    @Test public void cantOpenBothEscotillas(){
        assertThrowsLike(noSePuedenAbrirLasDosEscotillas,
                () -> new Rover(posicionInicial(), norte()).ejecutarComando('o').ejecutarComando('O'));
    }

    @Test public void cantCloseClosedEscotillas(){
        assertThrowsLike( noSePuedenCerrarEscotillasCerradas,
                () -> new Rover(posicionInicial(), norte()).ejecutarComando('c') );
    }

    @Test public void cantAspirarIfEscotillaClosed(){
        assertThrowsLike( escotillaCerrada,
                () -> new Rover( posicionInicial(), norte()).ejecutarComando('a') );
    }

    @Test public void cantCavarIfEscotillaClosed(){
        assertThrowsLike(escotillaCerrada,
                () -> new Rover( posicionInicial(), norte() ).ejecutarComando('i'));
    }

    @Test public void forwardAndTurnRightComands(){
        Rover rover = new Rover( posicionInicial(), norte() ).recibirComandos("ffrfffrrffff");
        assertTrue( rover.apuntaDireccion(oeste()) );
        assertTrue( rover.estaUbicacion(0,3) );
    }

    @Test public void backwardAndTurnLeftComands(){
        Rover rover = new Rover( posicionInicial(), norte() ).recibirComandos("blbbllbbbb");
        assertTrue( rover.apuntaDireccion( este() ) );
        assertTrue( rover.estaUbicacion(-1,0) );
    }

    @Test public void forwardBackwardLeftRightComands(){
        Rover rover = new Rover( posicionInicial(), norte() ).recibirComandos("fffrbrblfffrb");
        assertTrue( rover.apuntaDireccion(sur()) );
        assertTrue( rover.estaUbicacion(3,6) );
    }

    @Test public void aspirarCavarAbrirCerrarEscotillasComands(){
        Rover rover = new Rover( posicionInicial(), norte() );
        assertEquals( rover, rover.recibirComandos("Oacoic") );
        assertTrue( rover.estaUbicacion(1,1) );
    }

    @Test public void notProcessFollowingComands(){
        Rover rover = new Rover( posicionInicial(), norte() );
        assertThrowsLike( String.format(noSePuedeEjecutarComando, 'z'),
                () -> rover.recibirComandos("frfllbzbbf") );
        assertTrue(rover.apuntaDireccion( oeste()) );
        assertTrue( rover.estaUbicacion(3,2) );
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
}
