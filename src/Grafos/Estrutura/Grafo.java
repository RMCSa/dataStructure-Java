package Grafos.Estrutura;

import LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada.ListaEncadeadaSimplesDesordenada;

public class Grafo<TIPO> {
    private ListaEncadeadaSimplesDesordenada<Vertice<TIPO>> vertices;
    private ListaEncadeadaSimplesDesordenada<Aresta<TIPO>> arestas;

    public Grafo() {
        this.vertices = new ListaEncadeadaSimplesDesordenada<>();
        this.arestas = new ListaEncadeadaSimplesDesordenada<>();
    }

    public void adicionarVertice(TIPO dado) throws Exception {
        Vertice<TIPO> novoVertice = new Vertice<>(dado);
        this.vertices.guardeNoFinal(novoVertice);
    }

    public void adicionarAresta(Double peso, TIPO dadoInicio, TIPO dadoFim) throws Exception {
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);
        Aresta<TIPO> aresta = new Aresta<>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.guardeNoFinal(aresta);
    }

    public Vertice<TIPO> getVertice(TIPO dado) throws Exception {
        for (int i = 0; i < this.vertices.getTamanho(); i++) {
            Vertice<TIPO> vertice = this.vertices.get(i);
            if (vertice.getDado().equals(dado)) {
                return vertice;
            }
        }
        throw new Exception("Vértice não encontrado.");
    }

    public void buscaEmLargura() throws Exception {
        ListaEncadeadaSimplesDesordenada<Vertice<TIPO>> marcados = new ListaEncadeadaSimplesDesordenada<>();
        ListaEncadeadaSimplesDesordenada<Vertice<TIPO>> fila = new ListaEncadeadaSimplesDesordenada<>();
        Vertice<TIPO> atual = this.vertices.get(0);
        marcados.guardeNoFinal(atual);
        System.out.println(atual.getDado());
        fila.guardeNoFinal(atual);

        while (fila.getTamanho() > 0) {
            Vertice<TIPO> visitado = fila.get(0);
            fila.removaPrimeiro();

            for (int i = 0; i < visitado.getArestasSaida().getTamanho(); i++) {
                Vertice<TIPO> proximo = visitado.getArestasSaida().get(i).getFim();
                if (!marcados.tem(proximo)) {
                    marcados.guardeNoFinal(proximo);
                    System.out.println(proximo.getDado());
                    fila.guardeNoFinal(proximo);
                }
            }
        }
    }
}
