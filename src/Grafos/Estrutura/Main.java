package Grafos.Estrutura;

public class Main {
    public static void main(String[] args) {
        try {
            // Criando um grafo de exemplo
            Grafo<String> grafo = new Grafo<>();

            // Adicionando vértices
            grafo.adicionarVertice("A");
            grafo.adicionarVertice("B");
            grafo.adicionarVertice("C");
            grafo.adicionarVertice("D");
            grafo.adicionarVertice("E");

            // Adicionando arestas
            grafo.adicionarAresta(1.0, "A", "B");
            grafo.adicionarAresta(1.0, "A", "C");
            grafo.adicionarAresta(1.0, "B", "D");
            grafo.adicionarAresta(1.0, "C", "D");
            grafo.adicionarAresta(1.0, "D", "E");

            // Realizando busca em largura
            System.out.println("Busca em largura:");
            grafo.buscaEmLargura();

        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}
