package Grafos.Projeto;
/**
 * authors: Rafael Moreira Cavalcante de Souza - 23333
 * Vitor Henrique Girio Paes - 23340
 * Marcelo Henrique Morello Manzo - 23326
 * 
 */

// Estruturado como uma Aresta, não genérico, para atender às necessidades específicas do projeto
public class Flight {
    private int destinationIndex; 
    private int number;

    public Flight(int destinationIndex, int number) {
        this.destinationIndex = destinationIndex;
        this.number = number;
    }

    public int getDestinationIndex() {
        return destinationIndex;
    }

    public void setDestinationIndex(int destinationIndex) {
        this.destinationIndex = destinationIndex;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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

        Flight flight = (Flight) obj;
        return number == flight.number && destinationIndex == flight.destinationIndex;
    }

    @Override
    public int hashCode() {
        int ret = 777;
        ret = 7 * ret + Integer.valueOf(destinationIndex).hashCode();
        ret = 7 * ret + Integer.valueOf(number).hashCode();

        return ret >= 0 ? ret : -ret;
    }
}
