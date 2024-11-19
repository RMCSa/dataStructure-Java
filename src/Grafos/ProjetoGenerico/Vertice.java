package Grafos.ProjetoGenerico;
/**
 * authors: Rafael Moreira Cavalcante de Souza - 23333
 * Vitor Henrique Girio Paes - 23340
 * Marcelo Henrique Morello Manzo - 23326
 */

import LinkedList.Desordenadas.ListaDuplamenteLigadaDesordenada.ListaDuplamenteLigadaDesordenada;

// Estruturado como um Vértice, não genérico, para atender às necessidades específicas do projeto
public class Vertice<X> {
    private X name;
    private X code;
    private ListaDuplamenteLigadaDesordenada<Aresta<X>> flights;

    public Vertice(X name, X code) {
        this.name = name;
        this.code = code;
        this.flights = new ListaDuplamenteLigadaDesordenada<>();
    }

    public X getName() {
        return name;
    }

    public void setName(X name) {
        this.name = name;
    }

    public X getCode() {
        return code;
    }

    public void setCode(X code) {
        this.code = code;
    }

    public ListaDuplamenteLigadaDesordenada<Aresta<X>> getFlights() {
        return flights;
    }

    public void addFlight(Aresta<X> flight) throws Exception {
        if (flight == null) {
            throw new Exception("Flight is null.");
        }
        flights.guardeNoFinal(flight);
    }

    public void removeFlight(Aresta<X> flight) throws Exception {
        if (flight == null) {
            throw new Exception("Flight is null.");
        }
        flights.remova(flight);
    }

    public void removeFlight(int number) throws Exception {
        Aresta<X> flight = findFlight(number);
        if (flight == null) {
            throw new Exception("Flight not found.");
        }
        flights.remova(flight);
    }

    private Aresta<X> findFlight(int number) throws Exception {
        for (int i = 0; i < flights.getTamanho(); i++) {
            Aresta<X> flight = flights.get(i);
            if (flight.getNumber().equals(number)) {
                return flight;
            }
        }

        return null;
    }

    public void listFlights() throws Exception {
        if (flights.getTamanho() == 0) {
            System.out.println("No flights registered.");
            return;
        }

        for (int i = 0; i < flights.getTamanho(); i++) {
            Aresta<X> flight = flights.get(i);
            System.out.println("Flight " + flight.getNumber() + " -> " + flight.getDestinationIndex());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Vertice airport = (Vertice) obj;
        return code.equals(airport.code) && name.equals(airport.name);
    }

    @Override
    public int hashCode() {
        int ret = 777;
        ret = 7 * ret + String.valueOf(code).hashCode();
        ret = 7 * ret + String.valueOf(name).hashCode();

        return ret >= 0 ? ret : -ret;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", flights=" + flights.toString() + '}';
    }
}
