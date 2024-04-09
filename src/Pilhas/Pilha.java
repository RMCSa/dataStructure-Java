package Pilhas;

import LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada.ListaEncadeadaSimplesDesordenada;

public class Pilha<X> {
    private ListaEncadeadaSimplesDesordenada<X> lista;

    public Pilha(int capacidade) {
        this.lista = new ListaEncadeadaSimplesDesordenada<>();
    }

    public void guardeUmItem(X i) throws Exception {
        if (isCheia())
            throw new Exception("Pilha cheia");
        lista.guardeNoFinal(i);
    }

    public X recupereUmItem() throws Exception {
        if (isVazia())
            throw new Exception("Pilha vazia");
        return lista.getUltimo();
    }

    public void removaUmItem() throws Exception {
        if (isVazia())
            throw new Exception("Pilha vazia");
        lista.removaUltimo();
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
