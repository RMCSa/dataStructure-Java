package Grafos;

public class Aresta<T> {
    private Vertice<T> origem;
    private Vertice<T> destino;
    private double peso; 

    public Aresta(Vertice<T> origem, Vertice<T> destino) {
        this(origem, destino, 1.0); 
    }

    public Aresta(Vertice<T> origem, Vertice<T> destino, double peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice<T> getOrigem() {
        return origem;
    }

    public Vertice<T> getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return origem + " -> " + destino + " (peso: " + peso + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Aresta<T> aresta = (Aresta<T>) obj;

        if (Double.compare(aresta.peso, peso) != 0)
            return false;
        if (!origem.equals(aresta.origem))
            return false;
            
        return destino.equals(aresta.destino);
    }

    @Override
    public int hashCode() {
        int ret = 777;
        
        ret = 7 * ret + origem.hashCode();
        ret = 7 * ret + destino.hashCode();
        ret = 7 * ret + Double.hashCode(peso);

        if (ret < 0) ret = -ret;
        return ret;
    }
}
