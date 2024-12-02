package nogracias;

public class Carta implements Comparable<Carta>{
    private Integer valor;
    private Integer fichas = 0;

    public Carta(Integer valor) {
        if (valor < 3 || valor > 35){
            throw new RuntimeException("Numero invalido");

        }
        this.valor = valor;
    }
    public void pagarFicha() {
        fichas++;
    }

    public int value(){
        return valor;
    }

    public int fichas(){
        return fichas;
    }

    public int compareTo(Carta other) {
        return Integer.compare(valor, other.valor);
    }
}
