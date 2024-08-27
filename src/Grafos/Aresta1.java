package Grafos;

public class Aresta1<T> {
    private T info; 
    private double peso; 

    public Aresta1(T info, double peso) {
        this.info = info;
        this.peso = peso;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Aresta{" + "info=" + info + ", peso=" + peso + '}';
    }
}
