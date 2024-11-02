package Rover;

import java.util.Arrays;
import java.util.List;

public class Rover {
    public static final String noSePuedenAbrirLasDosEscotillas = "No pueden estar las dos escotillas abiertas al mismo tiempo";
    public static final String noSePuedenCerrarEscotillasCerradas = "No se pueden cerrar las escotillas si ninguna esta abierta";
    public static final String escotillaCerrada = "La escotilla se encuentra cerrada";
    public static final String noSePuedeEjecutarComando= "No existe el comando %s, los comandos posteriores no serán ejecutados";

    private Posicion posicion;
    private Direccion direccion;
    //TODO cambiar estar variables para luego quitar ifs
    private boolean escotillaSuperior = false;
    private boolean escotillaInferior = false;

    public List<Comando> comandos = Arrays.asList( new Avanzar(), new Retroceder(), new RotarIzq(), new RotarDer(), new AbrirEscSup(),
                                                   new AbrirEscInf(), new CerrarEsc(), new Aspirar(), new RecogerMuestra());

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

    public Rover rotarIzq() {
        direccion = direccion.left();
        return this;
    }

    public Rover rotarDer() {
        direccion = direccion.right();
        return this;
    }

    public Rover abrirEscSup() {
        if (escotillaInferior){
            throw new RuntimeException(noSePuedenAbrirLasDosEscotillas);
        }
        escotillaSuperior = true;
        return this;
    }

    public Rover abrirEscInf() {
        if (escotillaSuperior){
            throw new RuntimeException(noSePuedenAbrirLasDosEscotillas);
        }
        escotillaInferior = true;
        return this;
    }

    public Rover cerrarEsc() {
        if (!escotillaSuperior && !escotillaInferior){
            throw new RuntimeException(noSePuedenCerrarEscotillasCerradas);
        }
        escotillaSuperior = false;
        escotillaInferior = false;
        return this;
    }

    public Rover aspirar() {
        if (!escotillaSuperior){
            throw new RuntimeException(escotillaCerrada);
        }
        return this;
    }

    public Rover recogerMuestra() {
        if (!escotillaInferior){
            throw new RuntimeException(escotillaCerrada);
        }
        return this;
    }

    //OJO con logico booleana aca
    // TODO cambiar que se pasen los puntos y que se pase la posicion directamente
    public boolean estaUbicacion(int puntoX, int puntoY) {
        return posicion.equals( new Posicion( puntoX, puntoY ) );
    }

    public boolean apuntaDireccion(Direccion direccion) {
        return this.direccion.equals( direccion );
    }

    public boolean isEscotillaInferiorOpen() {
        return escotillaInferior;
    }

    public boolean isEscotillaSuperiorOpen() {
        return escotillaSuperior;
    }

    public boolean equals( Object anObject ) {
        return anObject instanceof Rover aRover
                && aRover.apuntaDireccion(direccion)
                && aRover.escotillaInferior == escotillaInferior
                && aRover.escotillaSuperior == escotillaSuperior
                && aRover.estaUbicacion(posicion.coordenada_X, posicion.coordenada_Y);
    }

    public Posicion getPosicion(){
        return posicion;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
