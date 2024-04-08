
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

    private No primeiro = null;
    private int tamanho = 0;

    public ListaDuplamenteLigadaDesordenada() {
        // Implementação do construtor
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    public int tamanho() {
        return tamanho;
    }

    public void guarde(X elemento) {
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

    public void guardeEm(int posicao, X info) throws Exception {
        if (this.primeiro == null)
            throw new Exception("Nó Vazio");
        if (info == null)
            throw new Exception("Informação Ausente");
        if (posicao < 0)
            throw new Exception("Posição inválida");
    

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

        anterior.setProx(new No(info, atual, anterior));
    }

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

    public static void main(String[] args) throws Exception {
        ListaDuplamenteLigadaDesordenada<Integer> lista = new ListaDuplamenteLigadaDesordenada<>();
        lista.guarde(1);
        lista.guarde(2);
        lista.guarde(3);
        lista.guarde(4);
        lista.guarde(5);
        System.out.println(lista);
        System.out.println(lista.tamanho());
        System.out.println(lista.contem(3));
        lista.guardeEm(2, 4);
        System.out.println(lista);
        System.out.println(lista.tamanho());
        System.out.println(lista.contem(3));
    }
    
}
