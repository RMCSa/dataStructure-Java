package Filas.ComVetor;

import java.lang.reflect.*;

public class Fila<X> implements Cloneable {
    private Object[] elemento;
    private final int tamanhoMaximo;
    private int inicio;
    private int fim;

    public Fila(int tamanho) throws Exception {
        if (tamanho <= 0)
            throw new Exception("Tamanho inválido");

        this.elemento = new Object[tamanho];
        this.tamanhoMaximo = tamanho;
        this.inicio = 0;
        this.fim = -1;
    }

    public Fila() {
        this.elemento = new Object[10]; 
        this.tamanhoMaximo = 10;
        this.inicio = 0;
        this.fim = -1;
    }

    private void redimensioneSe(float fator) {
        Object[] novo = new Object[(int) Math.ceil(this.elemento.length * fator)];

        for (int i = 0; i < this.tamanho(); i++)
            novo[i] = this.elemento[(inicio + i) % this.elemento.length];

        this.elemento = novo;
        this.inicio = 0;
        this.fim = this.tamanho() - 1;
    }

    public void enfileire(X x) throws Exception {
        if (x == null)
            throw new Exception("Falta o que enfileirar");

        if (this.tamanho() == this.tamanhoMaximo)
            this.redimensioneSe(2.0F);

        this.fim = (this.fim + 1) % this.elemento.length;
        this.elemento[this.fim] = x;
    }

    public X recupereUmItem() throws Exception {
        if (this.isVazia())
            throw new Exception("Fila vazia");

        return (X) this.elemento[inicio];
    }

    public void desenfileire() throws Exception {
        if (this.isVazia())
            throw new Exception("Fila vazia");

        this.elemento[inicio] = null;
        this.inicio = (this.inicio + 1) % this.elemento.length;

        if (this.tamanho() > 0 && this.tamanho() <= Math.round(this.elemento.length * 0.25F))
            this.redimensioneSe(0.5F);
    }

    public boolean isCheia() {
        return this.tamanho() == this.tamanhoMaximo;
    }

    public boolean isVazia() {
        return this.inicio == (this.fim + 1) % this.elemento.length;
    }

    public int tamanho() {
        if (this.inicio <= this.fim)
            return this.fim - this.inicio + 1;
        else
            return this.elemento.length - this.inicio + this.fim + 1;
    }

    public String toString() {
        return this.tamanho() + " elemento(s)";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Fila<X> fila = (Fila<X>) obj;

        if (this.tamanho() != fila.tamanho() || this.tamanhoMaximo != fila.tamanhoMaximo)
            return false;

        for (int i = 0; i < this.tamanho(); i++)
            if (!this.elemento[(inicio + i) % this.elemento.length]
                    .equals(fila.elemento[(fila.inicio + i) % fila.elemento.length]))
                return false;

        return true;
    }

    public int hashCode() {
        int ret = 666;

        ret = ret * 7 + new Integer(this.tamanho()).hashCode();
        ret = ret * 7 + new Integer(this.tamanhoMaximo).hashCode();

        for (int i = 0; i < this.tamanho(); i++)
            ret = ret * 7 + this.elemento[(inicio + i) % this.elemento.length].hashCode();

        if (ret < 0)
            ret = -ret;

        return ret;
    }

    public Fila(Fila<X> modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Modelo ausente");

        this.tamanhoMaximo = modelo.tamanhoMaximo;

        this.elemento = new Object[modelo.elemento.length];

        for (int i = 0; i < modelo.elemento.length; i++)
            this.elemento[i] = modelo.elemento[i];

        this.inicio = modelo.inicio;
        this.fim = modelo.fim;
    }

    public Object clone() {
        Fila<X> ret = null;

        try {
            ret = new Fila<X>(this);
        } catch (Exception erro) {
        }

        return ret;
    }
}
