package Filas.ComVetor;


public class Fila<X> implements Cloneable{

    private Object[] fila;
    private final int tamanhoInicial;
    private int inicio = 0;

    public Fila(int tamanhoInicial) {
        this.tamanhoInicial = tamanhoInicial;
        this.fila = new Object[tamanhoInicial];
    }
    
    public Fila() {
        this.tamanhoInicial = 10;
        this.fila = new Object[tamanhoInicial];
    }


    
}
