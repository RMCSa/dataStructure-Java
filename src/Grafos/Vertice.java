package Grafos;

import LinkedList.Desordenadas.ListaDuplamenteLigadaDesordenada.ListaDuplamenteLigadaDesordenada;

public class Vertice<X> {
    private X valor; // O valor armazenado no vértice
    private ListaDuplamenteLigadaDesordenada<Aresta<X>> arestas; // Lista de arestas

    public Vertice(X valor) {
        this.valor = valor;
        this.arestas = new ListaDuplamenteLigadaDesordenada<>();
    }

    public X getValor() {
        return valor;
    }

    public void setValor(X valor) {
        this.valor = valor;
    }

    public ListaDuplamenteLigadaDesordenada<Aresta<X>> getArestas() {
        return arestas;
    }

    public void adicionarAresta(Aresta<X> aresta) throws Exception {
        if (aresta == null) {
            throw new Exception("Aresta nula.");
        }
        arestas.guardeNoFinal(aresta);
    }

    public void removerAresta(Aresta<X> aresta) throws Exception {
        if (aresta == null) {
            throw new Exception("Aresta nula.");
        }
        arestas.remova(aresta);
    }

    public void removerAresta(int numero) throws Exception {
        Aresta<X> aresta = encontrarAresta(numero);
        if (aresta == null) {
            throw new Exception("Aresta não encontrada.");
        }
        arestas.remova(aresta);
    }

    private Aresta<X> encontrarAresta(int numero) throws Exception {
        for (int i = 0; i < arestas.getTamanho(); i++) {
            Aresta<X> aresta = arestas.get(i);
            if (aresta.getNumero() == numero) {
                return aresta;
            }
        }
        return null;
    }

    public void listarArestas() throws Exception {
        if (arestas.getTamanho() == 0) {
            System.out.println("Nenhuma aresta registrada.");
            return;
        }

        for (int i = 0; i < arestas.getTamanho(); i++) {
            Aresta<X> aresta = arestas.get(i);
            System.out.println("Aresta " + aresta.getNumero() + " -> " + aresta.getDestino());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Vertice<?> vertice = (Vertice<?>) obj;
        return valor.equals(vertice.valor);
    }

    @Override
    public int hashCode() {
        int ret = 777;
        ret = 7 * ret + valor.hashCode();
        return ret >= 0 ? ret : -ret;
    }

    @Override
    public String toString() {
        return "Vertice{" +
                "valor=" + valor +
                ", arestas=" + arestas.toString() + '}';
    }
}
