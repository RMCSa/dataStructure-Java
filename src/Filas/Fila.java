package Filas;

import LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada.ListaEncadeadaSimplesDesordenada;
public class Fila<X> {
    private ListaEncadeadaSimplesDesordenada<X> lista;

    public Fila() {
        this.lista = new ListaEncadeadaSimplesDesordenada<>();
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
        return false; // Listas encadeadas não possuem limite
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

        Fila<X> fila = (Fila<X>) obj;
        if(this.lista.equals(fila.lista))
            return true;
            
        return false
    }

    @Override
    public int hashCode() {
        int ret = 777;
        ret = ret * 7 + this.lista.hashCode();
        return ret;
    }
}
