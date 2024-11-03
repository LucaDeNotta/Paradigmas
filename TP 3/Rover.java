package Rover;

import java.util.Arrays;
import java.util.List;

public class Rover {
    public static final String noSePuedeEjecutarComando= "No existe el comando %s, los comandos posteriores no serán ejecutados";

    private Posicion posicion;
    private Direccion direccion;
    //TODO cambiar estar variables para luego quitar ifs
    private Escotillas escotillas = new EscotillasCerradas();

    public List<Comando> comandos = Arrays.asList( new Avanzar(), new Retroceder(), new RotarIzquierda(), new RotarDerecha(), new AbrirEscotillaSuperior(),
                                                   new AbrirEscotillaInferior(), new CerrarEscotilla(), new Aspirar(), new RecogerMuestra());

    public Rover(Posicion posicion, Direccion direccion) {
        this.posicion = posicion;
        this.direccion = direccion;
    }

    public Rover recibirComandos (String comandosRecibidos){
        comandosRecibidos.chars().mapToObj(c -> (char) c).forEach(comandoRecibido -> ejecutarComando(comandoRecibido));
        return this;
    }

    public Rover ejecutarComando(char comando){
        comandos.stream()
                .filter(each -> each.puedeEjecutar(comando))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException( String.format( noSePuedeEjecutarComando,comando ) ) )
                .ejecutar(this);
        return this;
    }

    public Rover avanzar() {
        posicion = posicion.forward(direccion);
        return this;
    }

    public Rover retroceder() {
        posicion = posicion.backward(direccion);
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

    //OJO con logico booleana aca
    // TODO cambiar que se pasen los puntos y que se pase la posicion directamente
    public boolean estaUbicacion(Posicion coordenadas) {
        return posicion.equals( coordenadas );
    }

    public boolean apuntaDireccion(Direccion direccion) {
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
                && escotillas.getClass().isInstance(aRover.escotillas)
                && aRover.estaUbicacion( posicion );
    }

    public Posicion getPosicion(){
        return posicion;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
