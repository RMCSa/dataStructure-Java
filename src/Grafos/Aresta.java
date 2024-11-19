package Grafos;

public class Aresta<X> {
    private X destino; // Destino da aresta
    private int numero; // Identificador da aresta

    public Aresta(X destino, int numero) {
        this.destino = destino;
        this.numero = numero;
    }

    public X getDestino() {
        return destino;
    }

    public void setDestino(X destino) {
        this.destino = destino;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Aresta{" +
                "destino=" + destino +
                ", numero=" + numero +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Aresta<?> aresta = (Aresta<?>) obj;
        return numero == aresta.numero && destino.equals(aresta.destino);
    }

    @Override
    public int hashCode() {
        int ret = 777;
        ret = 7 * ret + destino.hashCode();
        ret = 7 * ret + Integer.valueOf(numero).hashCode();
        return ret >= 0 ? ret : -ret;
    }
}
