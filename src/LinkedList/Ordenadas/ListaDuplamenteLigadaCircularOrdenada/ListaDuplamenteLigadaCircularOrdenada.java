package LinkedList.Ordenadas.ListaDuplamenteLigadaCircularOrdenada;
import LinkedList.Clonador.Clonador;
/**
 * author: Rafael Moreira Cavalcante de Souza - 23333
 */

public class ListaDuplamenteLigadaCircularOrdenada<X extends Comparable<X>> {
    private class No implements Cloneable {
        private X info;
        private No prox;
        private No ant;

        public No(X i, No p, No a) {
            this.info = i;
            this.prox = p;
            this.ant = a;
        }

        public No(X i) {
            this.info = i;
            this.prox = null;
            this.ant = null;
        }

        public X getInfo() {
            return this.info;
        }

        public No getProx() {
            return this.prox;
        }

        public No getAnt() {
            return this.ant;
        }

        public void setInfo(X info) {
            this.info = info;
        }

        public void setProx(No prox) {
            this.prox = prox;
        }

        public void setAnt(No ant) {
            this.ant = ant;
        }

        @Override
        public String toString() {
            if (prox != null)
                return this.info.toString() + " <-> " + this.prox.toString();
            else
                return this.info.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || obj.getClass() != this.getClass())
                return false;

            No other = (No) obj;
            return this.info.equals(other.info);
        }

        @Override
        public int hashCode() {
            return this.info.hashCode();
        }

        public No(No model) {
            this.info = model.info;
            this.prox = null;
            this.ant = null;
        }

        public Object clone() {
            return new No(this);
        }
    }

    private No primeiro;
    private int tamanho;

    public ListaDuplamenteLigadaCircularOrdenada() {
        primeiro = null;
        tamanho = 0;
    }

    public void guarde(X i) throws Exception {
        if (i == null)
            throw new Exception("Informação Ausente");

        No novo = new No(i);
        if (this.primeiro == null) {
            this.primeiro = novo;
            novo.setProx(novo);
            novo.setAnt(novo);
        } else {
            No atual = this.primeiro;
            while (atual.getProx() != this.primeiro && atual.getInfo().compareTo(i) < 0) {
                atual = atual.getProx();
            }

            if (atual.getInfo().compareTo(i) < 0) { 
                novo.setProx(this.primeiro);
                novo.setAnt(atual);
                atual.setProx(novo);
                this.primeiro.setAnt(novo);
            } else { 
                atual.getAnt().setProx(novo);
                novo.setAnt(atual.getAnt());
                novo.setProx(atual);
                atual.setAnt(novo);
                if (atual == this.primeiro) {
                    this.primeiro = novo;
                }
            }
        }
        tamanho++;
    }

    public boolean tem(X i) throws Exception {
        if (i == null)
            throw new Exception("Informação Ausente");
        if (this.primeiro == null)
            return false;

        No atual = this.primeiro;
        do {
            if (atual.getInfo().equals(i))
                return true;
            atual = atual.getProx();
        } while (atual != this.primeiro);

        return false;
    }

    public X getPrimeiro() throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó Vazio");

        X info = new Clonador<X>().clone(this.primeiro.getInfo());
        return info;
    }

    public X getUltimo() throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó Vazio");

        X info = new Clonador<X>().clone(this.primeiro.getAnt().getInfo());
        return info;
    }

    public X get(int posicao) throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó Vazio");
        if (posicao < 0 || posicao >= tamanho)
            throw new Exception("Posição inválida");

        No atual = this.primeiro;
        int indice = 0;
        do {
            if (indice == posicao)
                return atual.getInfo();
            atual = atual.getProx();
            indice++;
        } while (atual != this.primeiro);

        throw new Exception("Posição inválida");
    }

    public void removaPrimeiro() throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó nulo, nada a retirar");

        if (this.primeiro.getProx() == this.primeiro) {
            this.primeiro = null;
        } else {
            this.primeiro.getAnt().setProx(this.primeiro.getProx());
            this.primeiro.getProx().setAnt(this.primeiro.getAnt());
            this.primeiro = this.primeiro.getProx();
        }

        tamanho--;
    }

    public void removaUltimo() throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó Nulo, nada a retirar");

        if (this.primeiro.getProx() == this.primeiro) {
            this.primeiro = null;
        } else {
            this.primeiro.getAnt().getAnt().setProx(this.primeiro);
            this.primeiro.setAnt(this.primeiro.getAnt().getAnt());
        }

        tamanho--;
    }

    public void remova(int posicao) throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó nulo, nada a retirar");
        if (posicao < 0 || posicao >= tamanho)
            throw new Exception("Posição inválida");

        if (posicao == 0) {
            removaPrimeiro();
            return;
        }

        No atual = this.primeiro;
        int indice = 0;
        do {
            if (indice == posicao) {
                atual.getAnt().setProx(atual.getProx());
                atual.getProx().setAnt(atual.getAnt());
                tamanho--;
                return;
            }
            atual = atual.getProx();
            indice++;
        } while (atual != this.primeiro);

        throw new Exception("Posição inválida");
    }

    public void reverter() throws Exception {
        if (this.primeiro == null || this.primeiro.getProx() == this.primeiro)
            return;

        No atual = this.primeiro;
        do {
            No temp = atual.getProx();
            atual.setProx(atual.getAnt());
            atual.setAnt(temp);
            atual = temp;
        } while (atual != this.primeiro);

        this.primeiro = this.primeiro.getProx();
    }

    @Override
    public String toString() {
        if (this.primeiro == null)
            return "[]";
        String ret = "[";
        No atual = this.primeiro;
        for (int i = 0; i < tamanho; i++) {
            ret += atual.getInfo();
            if (atual.getProx() != null)
                ret += " <-> ";
            atual = atual.getProx();
        }
        ret += this.primeiro.getInfo() + "]";
        return ret;

    }

    @Override
    public int hashCode() {
        int ret = 777;

        if (this.primeiro != null) {
            No atual = this.primeiro;
            do {
                ret = 13 * ret + atual.getInfo().hashCode();
                atual = atual.getProx();
            } while (atual != this.primeiro);
        }

        if (ret < 0)
            ret = -ret;
        return ret;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        ListaDuplamenteLigadaCircularOrdenada<X> other = (ListaDuplamenteLigadaCircularOrdenada<X>) obj;

        if (this.primeiro == null && other.primeiro == null)
            return true;

        if (this.primeiro == null || other.primeiro == null)
            return false;

        No atualThis = this.primeiro;
        No atualOther = other.primeiro;

        do {
            if (!atualThis.getInfo().equals(atualOther.getInfo()))
                return false;
            atualThis = atualThis.getProx();
            atualOther = atualOther.getProx();
        } while (atualThis != this.primeiro && atualOther != other.primeiro);

        return atualThis == this.primeiro && atualOther == other.primeiro;
    }

    public ListaDuplamenteLigadaCircularOrdenada(ListaDuplamenteLigadaCircularOrdenada<X> model)
            throws Exception {
        if (model == null)
            throw new Exception("Modelo Ausente");

        if (model.primeiro == null) {
            this.primeiro = null;
            return;
        }

        this.primeiro = new No(model.primeiro.getInfo());
        No atualDoThis = this.primeiro;
        No atualDoModel = model.primeiro.getProx();

        while (atualDoModel != model.primeiro) {
            atualDoThis.setProx(new No(atualDoModel.getInfo()));

            atualDoThis = atualDoThis.getProx();
            atualDoModel = atualDoModel.getProx();
        }

        atualDoThis.setProx(this.primeiro);
        this.primeiro.setAnt(atualDoThis);

        this.tamanho = model.tamanho;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object clone() {
        ListaDuplamenteLigadaCircularOrdenada ret = null;
        try {
            ret = new ListaDuplamenteLigadaCircularOrdenada(this);
        } catch (Exception error) {
        }
        return ret;
    }

}
