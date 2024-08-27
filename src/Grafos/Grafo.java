import LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada.ListaEncadeadaSimplesDesordenada;

public class Grafo<V> {
    private ListaEncadeadaSimplesDesordenada<Aresta<V>>[] adjacencia;
    private Vertice<V>[] vertices;
    private int numVertices;

    @SuppressWarnings("unchecked")
    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.vertices = new Vertice[numVertices];
        this.adjacencia = new ListaEncadeadaSimplesDesordenada[numVertices];
        for (int i = 0; i < numVertices; i++) {
            this.adjacencia[i] = new ListaEncadeadaSimplesDesordenada<>();
        }
    }

    public void adicionarVertice(int indice, V dado) {
        this.vertices[indice] = new Vertice<>(dado);
    }

    public void adicionarAresta(int v1, int v2, double peso) throws Exception {
        if (v1 < 0 || v2 < 0 || v1 >= numVertices || v2 >= numVertices) {
            throw new Exception("Vértices inválidos");
        }
        Vertice<V> verticeOrigem = this.vertices[v1];
        Vertice<V> verticeDestino = this.vertices[v2];
        Aresta<V> aresta = new Aresta<>(verticeOrigem, verticeDestino, peso);
        this.adjacencia[v1].guardeNoFinal(aresta);
    }

    public void mostrarGrafo() {
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Vértice " + vertices[i] + ": " + this.adjacencia[i].toString());
        }
    }

    public static void main(String[] args) {
        try {
            Grafo<String> grafo = new Grafo<>(5);

            grafo.adicionarVertice(0, "A");
            grafo.adicionarVertice(1, "B");
            grafo.adicionarVertice(2, "C");
            grafo.adicionarVertice(3, "D");
            grafo.adicionarVertice(4, "E");

            grafo.adicionarAresta(0, 1, 1.0);
            grafo.adicionarAresta(0, 2, 2.5);
            grafo.adicionarAresta(1, 2, 1.2);
            grafo.adicionarAresta(1, 3, 0.5);
            grafo.adicionarAresta(2, 4, 3.3);

            grafo.mostrarGrafo();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
