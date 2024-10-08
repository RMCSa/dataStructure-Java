package Grafos.Projeto;
/**
 * authors: Rafael Moreira Cavalcante de Souza - 23333
 * Vitor Henrique Girio Paes - 23340
 * Marcelo Henrique Morello Manzo - 23326
 * 
 */

import Filas.ComListaEncadeada.Fila;
import LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada.ListaEncadeadaSimplesDesordenada;

// Estruturado como um Grafo, não genérico, para atender às necessidades específicas do projeto
public class Graph {
    private ListaEncadeadaSimplesDesordenada<Airport> airports;

    public Graph() {
        this.airports = new ListaEncadeadaSimplesDesordenada<>();
    }

    // 1. Cadastrar um novo aeroporto
    public void registerAirport(String name, String code) throws Exception {
        // Converte o nome e o código para maiúsculas por fim de padronização
        String nameUpper = name.toUpperCase();
        String codeUpper = code.toUpperCase();

        // Verifica se o aeroporto já está cadastrado
        for (int i = 0; i < airports.getTamanho(); i++) {
            Airport airport = airports.get(i);
            if (airport.getCode().equals(codeUpper) || airport.getName().equals(nameUpper)) {
                throw new Exception("Aeroporto já cadastrado.");
            }
        }

        // Adiciona o aeroporto à lista de aeroportos
        Airport airport = new Airport(nameUpper, codeUpper);
        airports.guardeNoFinal(airport);
        System.out.println("Aeroporto " + nameUpper + " (" + codeUpper + ") cadastrado com sucesso.");
    }

    // 2. Cadastramento de um voo com um determinado número entre dois aeroportos
    public void registerFlight(String origin, String destination, int number) throws Exception {
        // Converte os códigos dos aeroportos para maiúsculas por fim de padronização
        String originUpper = origin.toUpperCase();
        String destinationUpper = destination.toUpperCase();

        // Verifica se os aeroportos de origem e destino existem
        Airport originAirport = findAirport(originUpper);
        // Encontra o índice do aeroporto de destino
        int destinationIndex = findAirportIndex(destinationUpper); 
        if (originAirport == null) {
            throw new Exception("Aeroporto de origem não encontrado.");
        }
        // Verifica se o destino existe
        if (destinationIndex == -1) { 
            throw new Exception("Aeroporto de destino não encontrado.");
        }

        // Cria um novo voo e o adiciona ao aeroporto de origem
        Flight newFlight = new Flight(destinationIndex, number); 
        originAirport.addFlight(newFlight);
        System.out.println(
                "Voo Nº " + number + " (" + originUpper + " -> " + destinationUpper + ") cadastrado com sucesso.");
    }

    // 3. Remoção de um voo indicado pelo número
    public void removeFlight(String origin, int number) throws Exception {
        // Converte o código para maiúsculas por fim de padronização
        String originUpper = origin.toUpperCase();

        // Verifica se o aeroporto de origem existe
        Airport originAirport = findAirport(originUpper);
        if (originAirport == null) {
            throw new Exception("Aeroporto de origem não encontrado.");
        }

        // Remove o voo do aeroporto de origem a partir do seu número
        originAirport.removeFlight(number);
        System.out.println("Voo Nº " + number + " removido com sucesso.");
    }

    // 4. Listagem de voos de um aeroporto
    public void listFlights(String origin) throws Exception {
        String originUpper = origin.toUpperCase();

        // Verifica se o aeroporto de origem existe
        Airport originAirport = findAirport(originUpper);
        if (originAirport == null) {
            throw new Exception("\nAeroporto de origem não encontrado.");
        }

        int flightCount = originAirport.getFlights().getTamanho();
        if (flightCount == 0) {
            System.out.println("\nNenhum voo cadastrado para o aeroporto " + originUpper);
            return;
        }

        System.out.println("\nVoos saindo de " + originAirport.getName() + ":");

        // Exibe os voos a partir do aeroporto de origem
        for (int i = 0; i < flightCount; i++) {
            Flight flight = originAirport.getFlights().get(i);
            Airport destinationAirport = airports.get(flight.getDestinationIndex());
            System.out
                    .println(originUpper + " -> " + destinationAirport.getCode() + " (Voo " + flight.getNumber() + ")");
        }
    }

    // 5. Listagem de possíveis trajetos entre dois aeroportos
    public void listRoutes(String origin, String destination) throws Exception {
        // Converte os códigos dos aeroportos para maiúsculas por fim de padronização
        String originUpper = origin.toUpperCase();
        String destinationUpper = destination.toUpperCase();

        // Verifica se os aeroportos de origem e destino existem
        Airport originAirportObject = findAirport(originUpper);
        Airport destinationAirportObject = findAirport(destinationUpper);
        if (originAirportObject == null) {
            throw new Exception("Aeroporto de origem não encontrado.");
        }
        if (destinationAirportObject == null) {
            throw new Exception("Aeroporto de destino não encontrado.");
        }
        if (originUpper.equals(destinationUpper)) {
            System.out.println("Origem e destino são iguais.");
            return;
        }

        // Verifica se o aeroporto de origem possui voos cadastrados
        if (originAirportObject.getFlights().getTamanho() == 0) {
            System.out.println("Nenhum voo cadastrado para o aeroporto " + originUpper);
            return;
        }

        // Criação da fila para armazenar os trajetos
        Fila<ListaEncadeadaSimplesDesordenada<String>> queue = new Fila<>();
        ListaEncadeadaSimplesDesordenada<String> visitedRoutes = new ListaEncadeadaSimplesDesordenada<>();
        ListaEncadeadaSimplesDesordenada<String> initialRoute = new ListaEncadeadaSimplesDesordenada<>();

        // Adiciona o codigo do aeroporto de origem ao trajeto inicial e aos visitados
        initialRoute.guardeNoFinal(originUpper);
        visitedRoutes.guardeNoFinal(originUpper);
        
        // Adiciona o trajeto inicial à fila
        // Obs: o initialRoute é utilizado aqui para evitar erros ao manipular a visitedRoutes
        queue.guardeUmItem(initialRoute);

        boolean foundRoute = false;

        // Processa os trajetos na fila
        while (!queue.isVazia()) {
            // Recupera o trajeto atual da fila dando acesso ao código último aeroporto visitado
            ListaEncadeadaSimplesDesordenada<String> currentRoute = queue.recupereUmItem();
            // Remove o trajeto atual da fila para que não seja processado novamente
            queue.removaUmItem();
            // Recupera o último aeroporto do trajeto atual
            String lastAirportInRoute = currentRoute.get(currentRoute.getTamanho() - 1);
            // Encontra o aeroporto pelo código
            Airport lastAirportObject = findAirport(lastAirportInRoute);

            // Percorre cada voo do aeroporto atual
            for (int i = 0; i < lastAirportObject.getFlights().getTamanho(); i++) {
                // Recupera o voo atual, encontra o índice do aeroporto de destino e, com ele, o código do próximo aeroporto
                Flight flightObject = lastAirportObject.getFlights().get(i);
                int destinationIndex = flightObject.getDestinationIndex();
                String nextAirportCode = airports.get(destinationIndex).getCode();

                // Se o próximo aeroporto for o destino, exibe o trajeto completo
                if (nextAirportCode.equals(destinationUpper)) {
                    currentRoute.guardeNoFinal(nextAirportCode);
                    System.out.println("Trajeto: " + currentRoute);
                    // Remove o último aeroporto após exibir o trajeto para explorar outras rotas
                    currentRoute.removaUltimo();
                    foundRoute = true;
                }

                // Se o próximo aeroporto ainda não foi visitado, continua o processamento
                if (!visitedRoutes.tem(nextAirportCode)) {
                    // Adiciona o próximo aeroporto ao trajeto atual e aos visitados
                    visitedRoutes.guardeNoFinal(nextAirportCode);
                    // Clona o trajeto atual para criar um novo trajeto
                    @SuppressWarnings("unchecked")
                    ListaEncadeadaSimplesDesordenada<String> newRoute = (ListaEncadeadaSimplesDesordenada<String>) currentRoute
                            .clone();
                    // Adiciona o próximo aeroporto ao novo trajeto
                    newRoute.guardeNoFinal(nextAirportCode);
                    // Adiciona o novo trajeto à fila para processamento
                    queue.guardeUmItem(newRoute);
                }
            }
        }
        // Se não encontrou nenhum trajeto
        if (!foundRoute) {
            System.out.println("Nenhum caminho encontrado de " + originUpper + " para " + destinationUpper);
        }
    }

    // Funções auxiliares:

    // Listagem de aeroportos cadastrados
    public void listAirports() throws Exception {
        System.out.println("\nAeroportos cadastrados:");

        // Exibe os aeroportos cadastrados percorrendo a lista de aeroportos
        for (int i = 0; i < airports.getTamanho(); i++) {
            Airport airport = airports.get(i);
            System.out.println(airport.getName() + " (" + airport.getCode() + ")");
        }
    }

    // buscar aeroporto pelo código
    private Airport findAirport(String code) throws Exception {
        // Converte o código para maiúsculas por fim de padronização
        String codeUpper = code.toUpperCase();

        // Itera sobre a lista de aeroportos para encontrar o aeroporto com o código especificado
        for (int i = 0; i < airports.getTamanho(); i++) {
            Airport airport = airports.get(i);
            if (airport.getCode().equals(codeUpper)) {
                return airport;
            }
        }

        return null;
    }

    // Encontra o índice de um aeroporto pelo código
    private int findAirportIndex(String code) throws Exception {
        String codeUpper = code.toUpperCase();

        for (int i = 0; i < airports.getTamanho(); i++) {
            Airport airport = airports.get(i);
            if (airport.getCode().equals(codeUpper)) {
                return i; // Retorna o índice do aeroporto encontrado
            }
        }

        return -1; // Retorna -1 se não encontrar o aeroporto
    }

    // Menu de interação com o usuário
    public void displayMenu() throws Exception {
        while (true) {
            System.out.println("\n1. Cadastrar Aeroporto");
            System.out.println("2. Cadastrar Voo");
            System.out.println("3. Remover Voo");
            System.out.println("4. Listar Voos de um Aeroporto");
            System.out.println("5. Listar Possíveis Trajetos");
            System.out.println("6. Sair");

            System.out.print("Escolha uma opção: ");
            
            try {
                int option = Teclado.getUmInt();
                switch (option) {
                    case 1:
                        System.out.print("Nome do aeroporto: ");
                        String name = Teclado.getUmString();
                        System.out.print("Código do aeroporto: ");
                        String code = Teclado.getUmString();
                        registerAirport(name, code);
                        break;

                    case 2:
                        listAirports();
                        System.out.print("Código do aeroporto de origem: ");
                        String origin = Teclado.getUmString();
                        System.out.print("Código do aeroporto de destino: ");
                        String destination = Teclado.getUmString();
                        System.out.print("Número do voo: ");
                        int number = Teclado.getUmInt();
                        registerFlight(origin, destination, number);
                        break;

                    case 3:
                        listAirports();
                        System.out.print("Código do aeroporto de origem: ");
                        String originRemove = Teclado.getUmString();
                        listFlights(originRemove);
                        System.out.print("\nNúmero do voo a ser removido: ");
                        int numberRemove = Teclado.getUmInt();
                        System.out.println("Removendo voo...");
                        removeFlight(originRemove, numberRemove);
                        break;

                    case 4:
                        listAirports();
                        System.out.print("Código do aeroporto de origem: ");
                        String originFlights = Teclado.getUmString();
                        listFlights(originFlights);
                        break;

                    case 5:
                        listAirports();
                        System.out.print("Código do aeroporto de origem: ");
                        String originRoutes = Teclado.getUmString();
                        System.out.print("Código do aeroporto de destino: ");
                        String destinationRoutes = Teclado.getUmString();
                        listRoutes(originRoutes, destinationRoutes);
                        break;

                    case 6:
                        System.out.println("Saindo...");
                        return;

                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "Graph{" + "airports=" + airports + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Graph graph = (Graph) obj;
        return airports.equals(graph.airports);
    }

    @Override
    public int hashCode() {
        int ret = 777;
        ret = 7 * ret + airports.hashCode();

        return ret >= 0 ? ret : -ret;
    }

    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();
        graph.registerAirport("Brasília", "BSB");
        graph.registerAirport("Belo Horizonte", "CNF");
        graph.registerAirport("Rio de Janeiro", "GIG");
        graph.registerAirport("São Paulo", "GRU");
        graph.registerAirport("Salvador", "SSA");
        
        graph.registerFlight("BSB", "SSA", 107);
        graph.registerFlight("CNF", "SSA", 214);
        graph.registerFlight("CNF", "GIG", 554);
        graph.registerFlight("CNF", "GRU", 101);
        graph.registerFlight("GIG", "CNF", 554);
        graph.registerFlight("GIG", "GRU", 90);
        graph.registerFlight("GRU", "BSB", 50);
        graph.registerFlight("GRU", "GIG", 89);
        graph.registerFlight("GRU", "CNF", 102);
        graph.registerFlight("SSA", "CNF", 215);

        graph.registerAirport("TESTE", "TST");
        graph.registerAirport("TESTE2", "TST2");
        graph.registerFlight("TST", "TST2", 500);
        
        graph.displayMenu();
    }
}
