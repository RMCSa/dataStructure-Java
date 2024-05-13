package BinaryTree;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.apple.laf.resources.aqua;

public class ArvoreBinariaDeBusca<X extends Comparable<X>> implements Cloneable {
    private class No {
        private No esq;
        private X info;
        private No dir;

        public No(No esq, X info, No dir) {
            this.esq = esq;
            this.info = info;
            this.dir = dir;
        }

        public No(X info) {
            this(null, info, null);
        }

        public No(No esq, X info) {
            this(esq, info, null);
        }

        public No(X info, No dir) {
            this(null, info, dir);
        }

        public No getEsq() {
            return this.esq;
        }

        public X getInfo() {
            return this.info;
        }

        public No getDir() {
            return this.dir;
        }

        public void setEsq(No esq) {
            this.esq = esq;
        }

        public void setInfo(X info) {
            this.info = info;
        }

        public void setDir(No dir) {
            this.dir = dir;
        }

        // métodos obrigatórios
    }

    private No raiz;

    private X meuCloneDeX(X x) {
        X ret = null;

        try {
            Class<?> classe = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo = classe.getMethod("clone", tipoDosParms);
            Object[] parms = null;
            ret = (X) metodo.invoke(x, parms);
        } catch (NoSuchMethodException erro) {
        } catch (IllegalAccessException erro) {
        } catch (InvocationTargetException erro) {
        }

        return ret;
    }

    public void inclua(X inf) throws Exception {
        if (inf == null)
            throw new Exception("informacao ausente");

        No info;
        if (inf instanceof Cloneable)
            info = (X) meuCloneDeX(inf);
        else
            info = inf;

        if (this.raiz == null) {
            this.raiz = new No(info);
            return;
        }

        No atual = this.raiz;

        for (;;) // forever
        {
            int comparacao = info.compareTo(atual.getInfo());
            if (comparacao == 0)
                throw new Exception("informacao repetida");

            if (comparacao < 0) // deve-se inserir info para o lado esquerdo
                if (atual.getEsq() != null)
                    atual = atual.getEsq();
                else // achei onde inserir; eh para a esquerda do atual
                {
                    atual.setEsq(new No(info));
                    return;
                }
            else // deve-se inserir info para o lado direito
            if (atual.getDir() != null)
                atual = atual.getDir();
            else // achei onde inserir; eh para a direito do atual
            {
                atual.setDir(new No(info));
                return;
            }
        }
    }

    public boolean tem(X info) throws Exception {
        if (info == null)
            throw new Exception("informacao ausente");

        No atual = this.raiz;

        for (;;){
            if (atual == null) {
                throw new Exception();
            }
            int comparacao = info.compareTo(atual.info);

            if (comparacao == 0) {
                return true;
            }

            if (comparacao < 0) {
                atual = atual.getEsq();
            }
            else{
                atual = atual.getDir();
            }
        }
        // complete a implementacao
    }

    public X getMenor() throws Exception{
        No atual = this.raiz;
        if (atual == null) {
            throw new Exception("Nó nulo");
        }

        for(;;){
            if (atual.getEsq() == null){
                return atual.info;
            }
            atual = atual.getEsq();
        }

    }

    public X getMaior() throws Exception {
        No atual = this.raiz;
        if (atual == null) {
            throw new Exception("Nó nulo");
        }

        for (;;) {
            if (atual.getDir() == null) {
                return atual.info;
            }
            atual = atual.getDir();
        }
    }

    @Override 
    public String toString() {
        String ret = "";
    }
}
