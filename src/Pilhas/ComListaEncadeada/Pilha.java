package Pilhas.ComListaEncadeada;

import LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada.ListaEncadeadaSimplesDesordenada;

public class Pilha<X> implements Cloneable {
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
        return lista.isVazia(); // Função q criei na ListaEncadeadaSimplesDesordenada q retorna um bool
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

    public Pilha(Pilha<X> modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Modelo ausente");
        this.lista = new ListaEncadeadaSimplesDesordenada<>(modelo.lista);
    }

    public Object clone() {
        Pilha<X> ret = null;
        try {
            ret = new Pilha<>(this);
        } catch (Exception e) {
        }
        return ret;
    }
}
