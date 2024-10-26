package Rover;

import static java.util.Arrays.stream;

public class Rover {
    public static final String noSePuedenAbrirLasDosEscotillas = "No pueden estar las dos escotillas abiertas al mismo tiempo";
    public static final String noSePuedenCerrarEscotillasCerradas = "No se pueden cerrar las escotillas si ninguna esta abierta";
    public static final String escotillaCerrada = "La escotilla se encuentra cerrada";
    public static final String noSePuedeEjecutarComando= "No se puede ejecutar comando %s, los siguientes comandos no serán ejecutados";
    private int puntoY;
    private int puntoX;
    private String direccion;
    private boolean escotillaSuperior = false;
    private boolean escotillaInferior = false;

    public Rover(int punto_x, int punto_y, String direccion) {
        this.puntoX = punto_x;
        this.puntoY = punto_y;
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
            throw new IllegalArgumentException(String.format(noSePuedeEjecutarComando,comando));
        }
        return this;
    }

    public Rover avanzar() {
        switch (direccion) {
            case "N":
                this.puntoY++;
                break;
            case "S":
                this.puntoY--;
                break;
            case "E":
                this.puntoX++;
                break;
            case "O":
                this.puntoX--;
                break;
        }
        return this;
    }

    public Rover retroceder() {
        switch (direccion) {
            case "N":
                this.puntoY--;
                break;
            case "S":
                this.puntoY++;
                break;
            case "E":
                this.puntoX--;
                break;
            case "O":
                this.puntoX++;
                break;
        }
        return this;
    }

    public Rover rotarIzq() {
        switch (direccion) {
            case "N":
                this.direccion = "O";
                break;
            case "S":
                this.direccion = "E";
                break;
            case "E":
                this.direccion = "N";
                break;
            case "O":
                this.direccion = "S";
        }
        return this;
    }

    public Rover rotarDer() {
        switch (direccion) {
            case "N":
                this.direccion = "E";
                break;
            case "S":
                this.direccion = "O";
                break;
            case "E":
                this.direccion = "S";
                break;
            case "O":
                this.direccion = "N";
        }
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

    public int getPuntoX() {
        return puntoX;
    }

    public int getPuntoY() {
        return puntoY;
    }

    public String getDireccion() {
        return direccion;
    }

    //OJO con logico booleana aca
    public boolean estaUbicacion(int puntoX, int puntoY) {
        return this.puntoX == puntoX && this.puntoY == puntoY;
    }

    public boolean apuntaDireccion(String direccion) {
        return this.direccion.equals(direccion);
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
                && aRover.estaUbicacion(puntoX, puntoY);
    }
}
