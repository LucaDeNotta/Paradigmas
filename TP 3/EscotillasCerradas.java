package Rover;

public class EscotillasCerradas extends Escotillas {
    public boolean escotillaInferiorAbierta() { return false; }

    public boolean escotillaSuperiorAbierta() { return false; }

    public Escotillas abrirEscotillaInferior() { return new EscotillaInferiorAbierta(); }

    public Escotillas abrirEscotillaSuperior() { return new EscotillaSuperiorAbierta(); }

    public Escotillas cerrarEscotillas() { throw new RuntimeException(noSePuedenCerrarEscotillasCerradas); }

    public void aspirar() { throw new RuntimeException(escotillaCerrada); }

    public void recogerMuestra() { throw new RuntimeException(escotillaCerrada); }
}
