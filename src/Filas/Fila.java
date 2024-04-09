package Filas;

import LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada.ListaEncadeadaSimplesDesordenada;

public class Fila<X> {
    private ListaEncadeadaSimplesDesordenada<X> lista;
    private int capacidade;

    public Fila(int capacidade) {
        this.lista = new ListaEncadeadaSimplesDesordenada<>();
        this.capacidade = capacidade;
    }

    public void guardeUmItem(X i) throws Exception {
        if (isCheia())
            throw new Exception("Fila cheia");
        lista.guardeNoFinal(i);
    }

    public X recupereUmItem() throws Exception {
        if (isVazia())
            throw new Exception("Fila vazia");
        return lista.getPrimeiro();
    }

    public void removaUmItem() throws Exception {
        if (isVazia())
            throw new Exception("Fila vazia");
        lista.removaPrimeiro();
    }

    public boolean isCheia() {
        return false;
    }

    public boolean isVazia() {
        return lista.isVazia();
    }

    public int tamanho() {
        return lista.getTamanho();
    }

    @Override
    public String toString() {
        return this.lista.toString();
    }   
}
