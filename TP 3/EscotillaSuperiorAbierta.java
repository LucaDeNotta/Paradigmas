package Rover;

public class EscotillaSuperiorAbierta extends Escotillas {
    public boolean escotillaInferiorAbierta() { return false; }

    public boolean escotillaSuperiorAbierta() { return true; }

    public Escotillas abrirEscotillaInferior() { throw new RuntimeException( noSePuedenAbrirLasDosEscotillas ); }

    public Escotillas abrirEscotillaSuperior() { return this; }

    public Escotillas cerrarEscotillas() { return new EscotillasCerradas(); }

    public void aspirar() {}

    public void recogerMuestra() { throw new RuntimeException( escotillaCerrada ); }
}
