package Grafos.ProjetoGenerico;
/**
 * authors: Rafael Moreira Cavalcante de Souza - 23333
 * Vitor Henrique Girio Paes - 23340
 * Marcelo Henrique Morello Manzo - 23326
 * 
 */

// Estruturado como uma Aresta, não genérico, para atender às necessidades específicas do projeto
public class Aresta<X> {
    private int destinationIndex; 
    private X number;


    public Aresta(int destinationIndex, X number) {
        this.destinationIndex = destinationIndex;
        this.number = number;
    }

    public int getDestinationIndex() {
        return destinationIndex;
    }

    public void setDestinationIndex(int destinationIndex) {
        this.destinationIndex = destinationIndex;
    }

    public X getNumber() {
        return number;
    }

    public void setNumber(X number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "destinationIndex=" + destinationIndex +
                ", number=" + number +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Aresta<X> aresta = (Aresta<X>) obj;
        return number.equals(aresta.number) && destinationIndex == aresta.destinationIndex;
    }

    
    @Override
    public int hashCode() {
        int ret = 777;
        ret = 7 * ret + Integer.valueOf(destinationIndex).hashCode();
        ret = 7 * ret + number.hashCode();

        return ret >= 0 ? ret : -ret;
    }
}
