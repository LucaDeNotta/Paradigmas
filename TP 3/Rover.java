package Rover;

import java.util.Arrays;
import java.util.List;

public class Rover {
    public static final String noSePuedeEjecutarComando= "No existe el comando %s, los comandos posteriores no serán ejecutados";

    private Posicion posicion;
    private Direccion direccion;
    private Escotillas escotillas = new EscotillasCerradas();
    private List<Comando> comandos = Arrays.asList(
            new Comando( 'f', this::avanzar ),
            new Comando( 'b', this::retroceder ),
            new Comando( 'l', this::rotarIzquierda ),
            new Comando( 'r', this::rotarDerecha ),
            new Comando( 'O', this::abrirEscotillaSuperior ),
            new Comando( 'o', this::abrirEscotillaInferior ),
            new Comando( 'c', this::cerrarEscotillas ),
            new Comando( 'a', this::aspirar ),
            new Comando( 'i', this::recogerMuestra )
    );

    public Rover( Posicion posicion, Direccion direccion ) {
        this.posicion = posicion;
        this.direccion = direccion;
    }

    public Rover recibirComandos ( String comandosRecibidos ){
        comandosRecibidos.chars().mapToObj( c -> (char) c ).forEach( comandoRecibido -> ejecutarComando( comandoRecibido ) );
        return this;
    }

    public Rover ejecutarComando( char comando ){
        comandos.stream()
                .filter(each -> each.puedeEjecutar( comando ) )
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException( String.format( noSePuedeEjecutarComando,comando ) ) )
                .ejecutar();
        return this;
    }

    public Rover avanzar() {
        posicion = posicion.forward( direccion );
        return this;
    }

    public Rover retroceder() {
        posicion = posicion.backward( direccion );
        return this;
    }

    public Rover rotarIzquierda() {
        direccion = direccion.left();
        return this;
    }

    public Rover rotarDerecha() {
        direccion = direccion.right();
        return this;
    }

    public Rover abrirEscotillaSuperior() {
        escotillas = escotillas.abrirEscotillaSuperior();
        return this;
    }

    public Rover abrirEscotillaInferior() {
        escotillas = escotillas.abrirEscotillaInferior();
        return this;
    }

    public Rover cerrarEscotillas() {
        escotillas = escotillas.cerrarEscotillas();
        return this;
    }

    public Rover aspirar() {
        escotillas.aspirar();
        return this;
    }

    public Rover recogerMuestra() {
        escotillas.recogerMuestra();
        return this;
    }
    
    public boolean estaUbicacion( Posicion coordenadas ) {
        return posicion.equals( coordenadas );
    }

    public boolean apuntaDireccion( Direccion direccion ) {
        return this.direccion.equals( direccion );
    }

    public boolean isEscotillaSuperiorOpen() {
        return escotillas.escotillaSuperiorAbierta();
    }

    public boolean isEscotillaInferiorOpen() {
        return escotillas.escotillaInferiorAbierta();
    }

    public boolean equals( Object anObject ) {
        return anObject instanceof Rover aRover
                && aRover.apuntaDireccion( direccion )
                && escotillas.getClass().isInstance( aRover.escotillas )
                && aRover.estaUbicacion( posicion );
    }

    public Posicion getPosicion(){
        return posicion;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
