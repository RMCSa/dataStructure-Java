package LinkedList.Desordenadas.ListaDuplamenteLigadaDesordenada;
import LinkedList.Clonador.Clonador;
/**
 * author: Rafael Moreira Cavalcante de Souza - 23333
 */
public class ListaDuplamenteLigadaDesordenada<X> implements Cloneable {
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

    private No primeiro;

    public ListaDuplamenteLigadaDesordenada() {
        primeiro = null;
    }

    public void guardeNoInicio(X i) throws Exception {
        if (i == null)
            throw new Exception("Informação Ausente");
        if (this.primeiro == null)
            this.primeiro = new No(i);
        else {
            No novo = new No(i, this.primeiro, null);
            this.primeiro.setAnt(novo);
            this.primeiro = novo;
        }
    }

    public void guardeNoFinal(X i) throws Exception {
        if (i == null)
            throw new Exception("Informação Ausente");
        if (this.primeiro == null)
            this.primeiro = new No(i);
        else {
            No atual = this.primeiro;
            while (atual.getProx() != null)
                atual = atual.getProx();
            No novo = new No(i, null, atual);
            atual.setProx(novo);
        }
    }

    public void guardeEm(int posicao, X info) throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó Vazio");
        if (info == null)
            throw new Exception("Informação Ausente");
        if (posicao < 0)
            throw new Exception("Posição inválida");
        if (posicao == 0) {
            guardeNoInicio(info);
            return;
        }

        No anterior = this.primeiro;
        No atual = this.primeiro.getProx();
        int indice = 1;
        while (atual.getProx() != null && indice < posicao) {
            anterior = atual;
            atual = atual.getProx();
            indice++;
        }
        if (indice < posicao)
            throw new Exception("Posição inválida");

        No novo = new No(info, atual, anterior);
        anterior.setProx(novo);
        if (atual != null)
            atual.setAnt(novo);
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

        X info = new Clonador<X>().clone(this.primeiro.getInfo());
        return info;
    }

    public X getUltimo() throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó Vazio");

        No atual = this.primeiro;
        while (atual.getProx() != null)
            atual = atual.getProx();
        X info = new Clonador<X>().clone(atual.getInfo());
        return info;
    }

    public X get(int posicao) throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó Vazio");
        if (posicao == 0)
            return this.primeiro.getInfo();

        No atual = this.primeiro;
        int indice = 0;
        while (atual != null && indice < posicao) {
            atual = atual.getProx();
            indice++;
        }
        if (indice != posicao || atual == null)
            throw new Exception("Posição inválida");

        return atual.getInfo();
    }

    public void removaPrimeiro() throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó nulo, nada a retirar");

        if (this.primeiro.getProx() != null)
            this.primeiro.getProx().setAnt(null);

        this.primeiro = this.primeiro.getProx();
    }

    public void removaUltimo() throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó Nulo, nada a retirar");

        No atual = this.primeiro;
        while (atual.getProx() != null)
            atual = atual.getProx();
        if (atual.getAnt() != null)
            atual.getAnt().setProx(null);
        else
            this.primeiro = null;
    }

    public void remova(int posicao) throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó nulo, nada a retirar");
        if (posicao < 0)
            throw new Exception("Posição inválida");

        if (posicao == 0) {
            removaPrimeiro();
            return;
        }

        No atual = this.primeiro;
        int indice = 0;
        while (atual != null && indice < posicao) {
            atual = atual.getProx();
            indice++;
        }
        if (atual == null)
            throw new Exception("Posição inválida");
        if (atual.getProx() != null)
            atual.getProx().setAnt(atual.getAnt());
        if (atual.getAnt() != null)
            atual.getAnt().setProx(atual.getProx());
    }

    public void reverter() throws Exception {
        if (this.primeiro == null || this.primeiro.getProx() == null)
            return;

        No atual = this.primeiro;
        while (atual != null) {
            No temp = atual.getProx();
            atual.setProx(atual.getAnt());
            atual.setAnt(temp);
            if (temp == null)
                this.primeiro = atual;
            atual = temp;
        }
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
                ret += " <-> ";
            atual = atual.getProx();
        }
        ret += "]";
        return ret;
    }

    @Override
    public int hashCode() {
        int ret = 777;

        No atual = this.primeiro;

        while (atual != null) {
            ret = 13 * ret + atual.getInfo().hashCode();
            atual = atual.getProx();
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

        No atualDoThis = this.primeiro;
        No atualDoObj = ((ListaDuplamenteLigadaDesordenada<X>) obj).primeiro;

        while (atualDoThis != null && atualDoObj != null) {
            if (!atualDoThis.getInfo().equals(atualDoObj.getInfo()))
                return false;
            atualDoThis = atualDoThis.getProx();
            atualDoObj = atualDoObj.getProx();
        }
        if (atualDoThis != null || atualDoObj != null)
            return false;

        return true;
    }

    public ListaDuplamenteLigadaDesordenada(ListaDuplamenteLigadaDesordenada<X> model) throws Exception {
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

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object clone() {
        ListaDuplamenteLigadaDesordenada ret = null;
        try {
            ret = new ListaDuplamenteLigadaDesordenada(this);
        } catch (Exception error) {
        }
        return ret;
    }
}
