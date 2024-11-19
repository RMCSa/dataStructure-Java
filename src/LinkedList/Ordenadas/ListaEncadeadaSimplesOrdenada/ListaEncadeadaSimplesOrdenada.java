package LinkedList.Ordenadas.ListaEncadeadaSimplesOrdenada;

import LinkedList.Clonador.Clonador;
import LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada.ListaEncadeadaSimplesDesordenada;

/**
 * authors: Rafael Moreira Cavalcante de Souza - 23333
 * Vitor Henrique Girio Paes - 23340
 * Marcelo Henrique Morello Manzo - 23326
 */
public class ListaEncadeadaSimplesOrdenada<X> implements Cloneable {

    private class No implements Cloneable {
        private X info;
        private No prox;

        public No(X i, No p) {
            this.info = i;
            this.prox = p;
        }

        public No(X i) {
            this.info = i;
            this.prox = null;
        }

        public X getInfo() {
            return this.info;
        }

        public No getProx() {
            return this.prox;
        }

        public void setInfo(X info) {
            this.info = info;
        }

        public void setProx(No prox) {
            this.prox = prox;
        }

        @Override
        public String toString() {
            if (prox != null)
                return this.info.toString() + " -> " + this.prox.toString();
            else
                return this.info.toString();
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || obj.getClass() != this.getClass())
                return false;

            if (((No) obj).info.equals(this.getInfo()))
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int ret = 666;
            ret += 2 * ret + this.info.hashCode();

            if (ret < 0)
                ret = -ret;

            return ret;
        }

        public No(No model) throws Exception {
            if (model == null)
                throw new Exception("Modelo Ausente");
            if (model.info instanceof Cloneable) {
                this.info = new Clonador<X>().clone(model.info);
            } else
                this.info = model.info;
        }

        public Object clone() {
            No ret = null;
            try {
                ret = new No(this);
            } catch (Exception err) {
            }
            return ret;
        }
    }

    private No primeiro = null;

    public ListaEncadeadaSimplesOrdenada() {
        // Construtor vazio
    }

    public boolean isVazia() {
        return this.primeiro == null;
    }

    // Método para inserir um item na lista de forma ordenada
    public void guardeOrdenado(X i) throws Exception {
        if (i == null)
            throw new Exception("Informação Ausente");

        No novoNo = new No(i);

        // Se a lista estiver vazia, insere no início
        if (this.primeiro == null || compare(i, this.primeiro.getInfo()) <= 0) {
            novoNo.setProx(this.primeiro);
            this.primeiro = novoNo;
        } else {
            No atual = this.primeiro;
            while (atual.getProx() != null && compare(i, atual.getProx().getInfo()) > 0) {
                atual = atual.getProx();
            }
            novoNo.setProx(atual.getProx());
            atual.setProx(novoNo);
        }
    }

    // Função para comparar dois elementos de tipo X, substitua pela lógica de
    // comparação desejada
    private int compare(X a, X b) {
        // Aqui você deve definir como comparar os elementos.
        // Pode ser, por exemplo, verificando se a é maior ou menor que b de alguma
        // forma
        return ((Comparable<X>) a).compareTo(b);
    }

    public void guardeNoInicio(X i) throws Exception {
        if (i == null)
            throw new Exception("Informação Ausente");
        this.primeiro = new No(i, this.primeiro);
    }

    public void guardeNoFinal(X i) throws Exception {
        if (i == null)
            throw new Exception("Informação ausente");

        if (this.primeiro == null)
            this.primeiro = new No(i);
        else {
            No atual = this.primeiro;

            while (atual.getProx() != null)
                atual = atual.getProx();

            atual.setProx(new No(i));
        }
    }

    public boolean tem(X i) throws Exception {
        if (i == null)
            throw new Exception("Informação Ausente");
        No atual = this.primeiro;
        while (atual != null) {
            if (atual.getInfo().equals(i))
                return true;
            atual = atual.getProx();
        }
        return false;
    }

    public X getPrimeiro() throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó Vazio");

        return this.primeiro.getInfo();
    }

    public X getUltimo() throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó Vazio");
        if (this.primeiro.getProx() == null) {
            return this.primeiro.getInfo();
        }

        No atual = this.primeiro;
        while (atual.getProx() != null) {
            atual = atual.getProx();
        }

        return atual.getInfo();
    }

    public int getTamanho() {
        if (this.primeiro == null)
            return 0;
        if (this.primeiro.getProx() == null)
            return 1;
        No atual = this.primeiro;
        int elementos = 0;
        while (atual != null) {
            atual = atual.getProx();
            elementos++;
        }
        return elementos;
    }

    public X get(int posicao) throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó Vazio");

        if (posicao < 0 || posicao > getTamanho() - 1)
            throw new Exception("Posição inválida");

        if (posicao == 0) {
            return this.primeiro.getInfo();
        }

        No atual = this.primeiro;
        int indice = 0;

        while (atual != null && indice < posicao) {
            atual = atual.getProx();
            indice++;
        }
        if (indice != posicao)
            throw new Exception("Posição inválida");

        return atual.getInfo();
    }

    public void removaPrimeiro() throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó nulo, nada a retirar");

        if (this.primeiro.getProx() == null) {
            this.primeiro = null;
            return;
        }
        this.primeiro = this.primeiro.getProx();
    }

    public void removaUltimo() throws Exception {
        if (this.primeiro == null)
            throw new Exception("No Nulo, nada a retirar");

        if (this.primeiro.getProx() == null) {
            this.primeiro = null;
        } else {
            No anterior = this.primeiro;
            No atual = this.primeiro.getProx();

            while (atual.getProx() != null) {
                anterior = atual;
                atual = atual.getProx();
            }
            anterior.setProx(null);
        }
    }

    public void remova(X item) throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó nulo, nada a retirar");
        if (item == null)
            throw new Exception("Informação Ausente");

        if (this.primeiro.getInfo().equals(item)) {
            removaPrimeiro();
            return;
        }

        No anterior = this.primeiro;
        No atual = this.primeiro.getProx();

        while (atual != null && !atual.getInfo().equals(item)) {
            anterior = atual;
            atual = atual.getProx();
        }
        if (atual == null)
            throw new Exception("Item não encontrado");

        anterior.setProx(atual.getProx());
    }

    @Override
    public String toString() {
        if (this.primeiro == null)
            return "[]";
        String ret = "[";

        No atual = this.primeiro;
        while (atual != null) {
            ret += atual.getInfo();
            if (atual.getProx() != null)
                ret += " -> ";
            atual = atual.getProx();
        }
        ret += "]";
        return ret;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        No atualDoThis = this.primeiro;
        No atualDoObj = ((ListaEncadeadaSimplesOrdenada<X>) obj).primeiro;

        while (atualDoThis != null && atualDoObj != null) {
            if (!atualDoThis.getInfo().equals(atualDoObj.getInfo()))
                return false;
            atualDoThis = atualDoThis.getProx();
            atualDoObj = atualDoObj.getProx();
        }

        return (atualDoThis == null && atualDoObj == null);
    }

    public ListaEncadeadaSimplesOrdenada(ListaEncadeadaSimplesOrdenada<X> model) throws Exception {
        if (model == null)
            throw new Exception("Modelo Ausente");

        if (model.primeiro == null) {
            this.primeiro = null;
            return;
        }

        this.primeiro = new No(model.primeiro.getInfo());

        No atualDoThis = this.primeiro;
        No atualDoModel = model.primeiro.getProx();

        while (atualDoModel != null) {
            atualDoThis.setProx(new No(atualDoModel.getInfo()));

            atualDoThis = atualDoThis.getProx();
            atualDoModel = atualDoModel.getProx();
        }

    }

    @Override
    public Object clone() {
        ListaEncadeadaSimplesOrdenada ret = null;
        try {
            ret = new ListaEncadeadaSimplesOrdenada(this);
        } catch (Exception error) {
        }
        return ret;
    }
}