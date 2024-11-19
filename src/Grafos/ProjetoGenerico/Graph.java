package Grafos.ProjetoGenerico;

/**
 * authors: Rafael Moreira Cavalcante de Souza - 23333
 * Vitor Henrique Girio Paes - 23340
 * Marcelo Henrique Morello Manzo - 23326
 * 
 */

import Filas.ComListaEncadeada.Fila;
import LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada.ListaEncadeadaSimplesDesordenada;

// Estruturado como um Grafo genérico, utilizando Vertices e Arestas
public class Graph<X> {
    private ListaEncadeadaSimplesDesordenada<Vertice<X>> vertices;

    public Graph() {
        this.vertices = new ListaEncadeadaSimplesDesordenada<>();
    }

    // 1. Cadastramento de um novo vértice
    public void registerVertice(X name, X code) throws Exception {
        // Verifica se o vértice já está cadastrado
        for (int i = 0; i < vertices.getTamanho(); i++) {
            Vertice<X> vertice = vertices.get(i);
            if (vertice.getCode().equals(code) || vertice.getName().equals(name)) {
                throw new Exception("Vértice já cadastrado.");
            }
        }

        // Adiciona o vértice à lista de vértices
        Vertice<X> vertice = new Vertice<>(name, code);
        vertices.guardeNoFinal(vertice);
        System.out.println("Vértice " + name + " (" + code + ") cadastrado com sucesso.");
    }

    // 2. Cadastramento de uma aresta entre dois vértices
    public void registerAresta(X origin, X destination, X number) throws Exception {
        // Verifica se os vértices de origem e destino existem
        Vertice<X> verticeOrigem = findVertice(origin);
        int destinationIndex = findVerticeIndex(destination);
        if (verticeOrigem == null) {
            throw new Exception("Vértice de origem não encontrado.");
        }
        if (destinationIndex == -1) {
            throw new Exception("Vértice de destino não encontrado.");
        }

        // Verifica se a aresta já está cadastrada
        for (int i = 0; i < verticeOrigem.getFlights().getTamanho(); i++) {
            Aresta<X> aresta = verticeOrigem.getFlights().get(i);
            if (aresta.getNumber().equals(number)) {
                throw new Exception("Aresta já cadastrada.");
            }
        }

        // Cria uma nova aresta e a adiciona ao vértice de origem
        Aresta<X> novaAresta = new Aresta<>(destinationIndex, number);
        verticeOrigem.addFlight(novaAresta);
        System.out.println("Aresta Nº " + number + " (" + origin + " -> " + destination + ") cadastrada com sucesso.");
    }

    // 3. Remoção de uma aresta por número
    public void removeAresta(X origin, int number) throws Exception {
        Vertice<X> verticeOrigem = findVertice(origin);
        if (verticeOrigem == null) {
            throw new Exception("Vértice de origem não encontrado.");
        }
        verticeOrigem.removeFlight(number);
        System.out.println("Aresta Nº " + number + " removida com sucesso.");
    }

    // 4. Listagem de arestas de um vértice
    public void listArestas(X origin) throws Exception {
        Vertice<X> verticeOrigem = findVertice(origin);
        if (verticeOrigem == null) {
            throw new Exception("\nVértice de origem não encontrado.");
        }

        int arestaCount = verticeOrigem.getFlights().getTamanho();
        if (arestaCount == 0) {
            throw new Exception("Nenhuma aresta cadastrada para o vértice " + origin);
        }

        System.out.println("\nArestas saindo de " + verticeOrigem.getName() + ":");
        for (int i = 0; i < arestaCount; i++) {
            Aresta<X> aresta = verticeOrigem.getFlights().get(i);
            Vertice<X> destino = vertices.get((Integer) aresta.getDestinationIndex());
            System.out.println(origin + " -> " + destino.getCode() + " (Aresta " + aresta.getNumber() + ")");
        }
    }

    // 5. Listagem de possíveis trajetos entre dois vértices
    public void listRoutes(X origin, X destination) throws Exception {
        Vertice<X> verticeOrigem = findVertice(origin);
        Vertice<X> verticeDestino = findVertice(destination);
        if (verticeOrigem == null) {
            throw new Exception("Vértice de origem não encontrado.");
        }
        if (verticeDestino == null) {
            throw new Exception("Vértice de destino não encontrado.");
        }
        if (origin.equals(destination)) {
            System.out.println("Origem e destino são iguais.");
            return;
        }

        if (verticeOrigem.getFlights().getTamanho() == 0) {
            System.out.println("Nenhuma aresta cadastrada para o vértice " + origin);
            return;
        }

        Fila<ListaEncadeadaSimplesDesordenada<X>> fila = new Fila<>();
        ListaEncadeadaSimplesDesordenada<X> visitedRoutes = new ListaEncadeadaSimplesDesordenada<>();
        ListaEncadeadaSimplesDesordenada<X> initialRoute = new ListaEncadeadaSimplesDesordenada<>();

        initialRoute.guardeNoFinal(origin);
        visitedRoutes.guardeNoFinal(origin);
        fila.guardeUmItem(initialRoute);

        boolean foundRoute = false;
        while (!fila.isVazia()) {
            ListaEncadeadaSimplesDesordenada<X> currentRoute = fila.recupereUmItem();
            fila.removaUmItem();
            X lastVertice = currentRoute.get(currentRoute.getTamanho() - 1);
            Vertice<X> lastVerticeObject = findVertice(lastVertice);

            for (int i = 0; i < lastVerticeObject.getFlights().getTamanho(); i++) {
                Aresta<X> aresta = lastVerticeObject.getFlights().get(i);
                int destinationIndex = (Integer) aresta.getDestinationIndex();
                X nextVertice = vertices.get(destinationIndex).getCode();

                if (nextVertice.equals(destination)) {
                    currentRoute.guardeNoFinal(nextVertice);
                    System.out.println("Trajeto: " + currentRoute);
                    currentRoute.removaUltimo();
                    foundRoute = true;
                }

                if (!visitedRoutes.tem(nextVertice)) {
                    visitedRoutes.guardeNoFinal(nextVertice);
                    @SuppressWarnings("unchecked")
                    ListaEncadeadaSimplesDesordenada<X> newRoute = (ListaEncadeadaSimplesDesordenada<X>) currentRoute
                            .clone();
                    newRoute.guardeNoFinal(nextVertice);
                    fila.guardeUmItem(newRoute);
                }
            }
        }
        if (!foundRoute) {
            System.out.println("Nenhum caminho encontrado de " + origin + " para " + destination);
        }
    }

    // Funções auxiliares:

    // Buscar vértice pelo código
    private Vertice<X> findVertice(X code) throws Exception {
        for (int i = 0; i < vertices.getTamanho(); i++) {
            Vertice<X> vertice = vertices.get(i);
            if (vertice.getCode().equals(code)) {
                return vertice;
            }
        }
        return null;
    }

    // Encontrar o índice de um vértice
    private int findVerticeIndex(X code) throws Exception {
        for (int i = 0; i < vertices.getTamanho(); i++) {
            Vertice<X> vertice = vertices.get(i);
            if (vertice.getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    // Exibir menu de interação
    public void displayMenu() throws Exception {
        while (true) {
            System.out.println("\n1. Cadastrar Aeroporto");
            System.out.println("2. Cadastrar Voo");
            System.out.println("3. Remover Voo");
            System.out.println("4. Listar Voo de um Aeroporto");
            System.out.println("5. Listar Possíveis Trajetos");
            System.out.println("6. Sair");

            System.out.println("Escolha: ");

            int option = Teclado.getUmInt();
            try {
                switch (option) {
                    case 1:
                        System.out.print("Nome do vértice: ");
                        X name = (X) Teclado.getUmString();
                        System.out.print("Código do vértice: ");
                        X code = (X) Teclado.getUmString();
                        registerVertice(name, code);
                        break;
                    case 2:
                        listArestas((X) Teclado.getUmString());
                        System.out.print("Código do vértice de origem: ");
                        X origin = (X) Teclado.getUmString();
                        System.out.print("Código do vértice de destino: ");
                        X destination = (X) Teclado.getUmString();
                        System.out.print("Número da aresta: ");
                        X number = (X) Teclado.getUmString();
                        registerAresta(origin, destination, number);
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

    public static void main(String[] args) throws Exception {
        Graph<String> graph = new Graph<>();
        graph.registerVertice("Brasília", "BSB");
        graph.registerVertice("Belo Horizonte", "CNF");
        graph.registerVertice("Rio de Janeiro", "GIG");
        graph.registerVertice("São Paulo", "GRU");
        graph.registerVertice("Salvador", "SSA");

        graph.registerAresta("BSB", "SSA", "107");
        graph.registerAresta("CNF", "SSA", "214");
        graph.registerAresta("CNF", "GIG", "554");
        graph.registerAresta("CNF", "GRU", "101");
        graph.registerAresta("GIG", "CNF", "554");
        graph.registerAresta("GIG", "GRU", "90");
        graph.registerAresta("GRU", "BSB", "50");
        graph.registerAresta("GRU", "GIG", "89");
        graph.registerAresta("GRU", "CNF", "102");
        graph.registerAresta("SSA", "CNF", "215");

        graph.displayMenu();
    }
}
