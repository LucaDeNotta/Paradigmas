public class Carta {
    private int numero;
    private int fichasEnCarta = 0;

    public Carta(int numero){
        if (numero < 3 || numero > 35){
            throw new RuntimeException("Numero invalido");
        }
        this.numero = numero;
    }

    public Carta usarFicha(){
        this.fichasEnCarta++;
        return this;
    }

    public int value(){
        return this.numero;
    }

    public int fichas(){
        return this.fichasEnCarta;
    }
}
