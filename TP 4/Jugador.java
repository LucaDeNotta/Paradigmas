import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private int fichas = 0;
    private List<Carta> cartas =  new ArrayList<Carta>();;

    public Jugador(String nombre) { this.nombre = nombre; }

    public Jugador asignarFichas(int fichas) {
        this.fichas = fichas;
        return this;
    }

    public Jugador agarrarCarta(Carta carta) {
        cartas.add(carta);
        fichas += carta.fichas();
        return this;
    }

    public Jugador usarFicha(){
        if( fichas == 0 ){
            throw new RuntimeException("No hay fichas");
        }
        fichas--;
        return this;
    }

    public int puntaje(){
        List<Integer> valores = cartas.stream()
                .map(Carta::value)
                .toList();

        return valores.stream()
                .filter(each -> !valores.contains(each - 1))
                .reduce(0, Integer::sum) - fichas;
    }

    public int fichas() { return fichas; }

}
