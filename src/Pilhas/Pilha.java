package Pilhas;

import LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada.ListaEncadeadaSimplesDesordenada;

public class Pilha<X> {
    private ListaEncadeadaSimplesDesordenada<X> lista;

    public Pilha() {
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
        return false; // Listas encadeadas nãop possuem limite
    }

    public boolean isVazia() {
        return lista.isVazia();
    }

    @Override
    public String toString() {
        return this.lista.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (this.getClass() != obj.getClass() || obj == null)
            return false;
            
        Pilha<X> pilha = (Pilha<X>) obj;
        if (this.lista.equals(pilha.lista))
            return true;
            
        return false;
    }

    @Override 
    public int hashCode() {
        int ret = 777;
        ret = ret * 7 + this.lista.hashCode();
        return ret;
    }
}
