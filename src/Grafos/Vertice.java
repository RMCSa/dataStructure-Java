package Grafos;

public class Vertice<T> {
    private T info; 

    public Vertice(T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Vertice<T> vertice = (Vertice<T>) obj;

        return info.equals(vertice.info);
    }

    @Override
    public int hashCode() {
        int ret = 777;

        ret = 7 * ret + info.hashCode();

        if (ret < 0) ret = -ret;

        return ret;
    }

    @Override
    public String toString() {
        return "Vertice{" + "info=" + info + '}';
    }
}
