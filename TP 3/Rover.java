package Rover;

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

    public Rover(Posicion posicion, Direccion direccion) {
        this.posicion = posicion;
        this.direccion = direccion;
    }

    public Rover recibirComandos(String comandos){
        comandos.chars().mapToObj(c -> (char) c).forEach(comando -> ejecutarComando(comando));
        return this;
    }

    public Rover ejecutarComando(char comando){
        if (comando == 'f') {
            avanzar();
        }
        else if (comando == 'b') {
            retroceder();
        }
        else if (comando == 'l'){
            rotarIzq();
        }
        else if (comando == 'r'){
            rotarDer();
        }
        else if (comando == 'O'){
            abrirEscSup();
        }
        else if (comando == 'o'){
            abrirEscInf();
        }
        else if (comando == 'c'){
            cerrarEsc();
        }
        else if (comando == 'a'){
            aspirar();
        }
        else if (comando == 'i'){
            recogerMuestra();
        }
        else{
            throw new IllegalArgumentException( String.format( noSePuedeEjecutarComando,comando ) );
        }
        return this;
    }

    public Rover avanzar() {
//        switch (direccion.getClass()) {
//            case "N":
//                this.puntoY++;
//                break;
//            case "S":
//                this.puntoY--;
//                break;
//            case "E":
//                this.puntoX++;
//                break;
//            case "O":
//                this.puntoX--;
//                break;
//        }
        posicion = posicion.forward(direccion);
        return this;
    }

    public Rover retroceder() {
//        switch (direccion) {
//            case "N":
//                this.puntoY--;
//                break;
//            case "S":
//                this.puntoY++;
//                break;
//            case "E":
//                this.puntoX--;
//                break;
//            case "O":
//                this.puntoX++;
//                break;
//        }
        posicion = posicion.backward(direccion);
        return this;
    }

    public Rover rotarIzq() {
//        switch (direccion) {
//            case "N":
//                this.direccion = "O";
//                break;
//            case "S":
//                this.direccion = "E";
//                break;
//            case "E":
//                this.direccion = "N";
//                break;
//            case "O":
//                this.direccion = "S";
//        }
        direccion = direccion.left();
        return this;
    }

    public Rover rotarDer() {
//        switch (direccion) {
//            case "N":
//                this.direccion = "E";
//                break;
//            case "S":
//                this.direccion = "O";
//                break;
//            case "E":
//                this.direccion = "S";
//                break;
//            case "O":
//                this.direccion = "N";
//        }
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
