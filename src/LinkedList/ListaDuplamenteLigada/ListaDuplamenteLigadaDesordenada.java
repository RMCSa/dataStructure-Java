
/**
 * author: Rafael Moreira Cavalcante de Souza - 23333
 */
package ListaDuplamenteLigada;
import Clonador.Clonador;
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

        public No(X i, No p) {
            this.info = i;
            this.prox = p;
            this.ant = null;
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

        public void setAnt(No ant) {
            this.ant = ant;
        }

        public void setInfo(X info) {
            this.info = info;
        }

        public void setProx(No prox) {
            this.prox = prox;
        }

        @Override
        public String toString() {
            if (prox != null && ant != null)
                return this.ant.toString() + " -> " + this.info.toString() + " -> " + this.prox.toString();
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

        // Definição da classe No
    private No primeiro = null;
    private int tamanho = 0;

    // Construtor da lista
    public ListaDuplamenteLigadaDesordenada() {
        // Implementação do construtor
    }

    // Verifica se a lista está vazia
    public boolean estaVazia() {
        return primeiro == null;
    }

    // Retorna o tamanho da lista
    public int tamanho() {
        return tamanho;
    }

    // Insere um elemento no final da lista
    public void inserir(X elemento) {
        No novo = new No(elemento);
        if (estaVazia()) {
            primeiro = novo;
        } else {
            No atual = primeiro;
            while (atual.getProx() != null) {
                atual = atual.getProx();
            }
            atual.setProx(novo);
            novo.setAnt(atual);
        }
        tamanho++;
    }

    // Remove um elemento da lista
    public void remover(X elemento) {
        No atual = primeiro;
        No anterior = null;

        while (atual != null && !atual.getInfo().equals(elemento)) {
            anterior = atual;
            atual = atual.getProx();
        }

        if (atual != null) {
            if (anterior == null) {
                primeiro = atual.getProx();
            } else {
                anterior.setProx(atual.getProx());
                if (atual.getProx() != null) {
                    atual.getProx().setAnt(anterior);
                }
            }
            tamanho--;
        }
    }

    // Busca um elemento na lista
    public boolean contem(X elemento) {
        No atual = primeiro;
        while (atual != null) {
            if (atual.getInfo().equals(elemento)) {
                return true;
            }
            atual = atual.getProx();
        }
        return false;
    }

    // Retorna uma representação em String da lista
    @Override
    public String toString() {
        if (estaVazia()) {
            return "Lista vazia";
        }

        StringBuilder builder = new StringBuilder();
        No atual = primeiro;
        while (atual != null) {
            builder.append(atual.getInfo()).append(" ");
            atual = atual.getProx();
        }
        return builder.toString();
    }

        // Outros métodos como clonagem, inversão, etc. podem ser adicionados conforme
        // necessário
    
}
