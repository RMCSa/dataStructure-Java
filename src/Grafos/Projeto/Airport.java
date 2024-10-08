package Grafos.Projeto;
/**
 * authors: Rafael Moreira Cavalcante de Souza - 23333
 * Vitor Henrique Girio Paes - 23340
 * Marcelo Henrique Morello Manzo - 23326
 * 
 */

import LinkedList.Desordenadas.ListaDuplamenteLigadaDesordenada.ListaDuplamenteLigadaDesordenada;

// Estruturado como um Vértice, não genérico, para atender às necessidades específicas do projeto
public class Airport {
    private String name;
    private String code;
    private ListaDuplamenteLigadaDesordenada<Flight> flights;

    public Airport(String name, String code) {
        this.name = name.toUpperCase();
        this.code = code.toUpperCase();
        this.flights = new ListaDuplamenteLigadaDesordenada<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ListaDuplamenteLigadaDesordenada<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) throws Exception {
        if (flight == null) {
            throw new Exception("Flight is null.");
        }
        flights.guardeNoFinal(flight);
    }

    public void removeFlight(Flight flight) throws Exception {
        if (flight == null) {
            throw new Exception("Flight is null.");
        }
        flights.remova(flight);
    }

    public void removeFlight(int number) throws Exception {
        Flight flight = findFlight(number);
        if (flight == null) {
            throw new Exception("Flight not found.");
        }
        flights.remova(flight);
    }

    private Flight findFlight(int number) throws Exception {
        for (int i = 0; i < flights.getTamanho(); i++) {
            Flight flight = flights.get(i);
            if (flight.getNumber() == number) {
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
            Flight flight = flights.get(i);
            System.out.println("Flight " + flight.getNumber() + " -> " + flight.getDestinationIndex());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Airport airport = (Airport) obj;
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
