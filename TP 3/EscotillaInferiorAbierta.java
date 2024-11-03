package Rover;

public class EscotillaInferiorAbierta extends Escotillas {
    public boolean escotillaInferiorAbierta() { return true; }

    public boolean escotillaSuperiorAbierta() { return false; }

    //REFLEJAR COMPORTAMIENTO EN TESTS
    public Escotillas abrirEscotillaInferior() { return this; }

    public Escotillas abrirEscotillaSuperior() { throw new RuntimeException(noSePuedenAbrirLasDosEscotillas); }

    public Escotillas cerrarEscotillas() { return new EscotillasCerradas(); }

    public void aspirar() { throw new RuntimeException(escotillaCerrada); }

    public void recogerMuestra() {}
}
