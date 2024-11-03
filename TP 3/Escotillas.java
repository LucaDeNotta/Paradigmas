package Rover;

public abstract class Escotillas {
    protected static final String noSePuedenAbrirLasDosEscotillas = "No pueden estar las dos escotillas abiertas al mismo tiempo";
    protected static final String noSePuedenCerrarEscotillasCerradas = "No se pueden cerrar las escotillas si ninguna esta abierta";
    protected static final String escotillaCerrada = "La escotilla se encuentra cerrada";

    public abstract boolean escotillaInferiorAbierta();

    public abstract boolean escotillaSuperiorAbierta();

    public abstract Escotillas abrirEscotillaInferior();

    public abstract Escotillas abrirEscotillaSuperior();

    public abstract Escotillas cerrarEscotillas();

    public abstract void aspirar();

    public abstract void recogerMuestra();
}
