package Grafos.Estrutura;

import LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada.ListaEncadeadaSimplesDesordenada;

public class Vertice<TIPO> {
    private TIPO dado;
    private ListaEncadeadaSimplesDesordenada<Aresta<TIPO>> arestasEntrada;
    private ListaEncadeadaSimplesDesordenada<Aresta<TIPO>> arestasSaida;

    public Vertice(TIPO valor) {
        this.dado = valor;
        this.arestasEntrada = new ListaEncadeadaSimplesDesordenada<>();
        this.arestasSaida = new ListaEncadeadaSimplesDesordenada<>();
    }

    public TIPO getDado() {
        return dado;
    }

    public void setDado(TIPO dado) {
        this.dado = dado;
    }

    public void adicionarArestaEntrada(Aresta<TIPO> aresta) throws Exception {
        this.arestasEntrada.guardeNoFinal(aresta);
    }

    public void adicionarArestaSaida(Aresta<TIPO> aresta) throws Exception {
        this.arestasSaida.guardeNoFinal(aresta);
    }

    public ListaEncadeadaSimplesDesordenada<Aresta<TIPO>> getArestasEntrada() {
        return arestasEntrada;
    }

    public ListaEncadeadaSimplesDesordenada<Aresta<TIPO>> getArestasSaida() {
        return arestasSaida;
    }
}
