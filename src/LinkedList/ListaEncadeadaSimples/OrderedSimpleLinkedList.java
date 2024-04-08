package ListaEncadeadaSimples;

import Clonador.Clonador;
public class OrderedSimpleLinkedList <X extends Comparable<X>> implements Cloneable{
    private class Node implements Cloneable {
        private X data;
        private Node next;

        public Node(X d, Node n){
            this.data = d;
            this.next = n;
        
        }
        public Node(X d){
            this.data = d;  
            this.next = null;
        }

        public X getData(){
            return this.data;
        }
        public Node getNext(){
            return this.next;
        }
        public void setNext(Node n){
            this.next = n;
        }
        public void setData(X d){
            this.data = d;
        }
        
        @Override 
        public String toString(){
            if (this.next != null)
                return this.data.toString() + " -> " + this.next.getData().toString();
            else
                return this.data.toString();
        }

        @Override
        public boolean equals(Object obj){
            if(this == obj)
                return true;

            if(obj == null || this.getClass() != obj.getClass())
                return false;
            
            if(!((Node) obj).data.equals(this.getData()))
                return false;
            
            return true;
        }

        @Override
        public int hashCode(){
            int ret = 666;

            ret += ret + this.data.hashCode();

            if (ret < 0) ret = -ret;

            return ret;
        }

        public Node(Node model) throws Exception{
            if (model == null){
                throw new Exception("Invalid Model");
            }

            if (model.data instanceof Cloneable) {
                this.data = new Clonador<X>().clone(model.data);
            } else
                this.data = model.data;
        }

        public Object clone(){
            Node ret = null;

            try{
                ret = new Node(this);
            }
            catch(Exception e){ }
            return ret;
        }
    }
    
    private Node first;

    public OrderedSimpleLinkedList(){
		this.first = null;
	}

    public void add(X i) throws Exception {
        if (i == null)
            throw new Exception("Informação ausente");
        if (this.first == null) {
            this.first = new Node(i);
            return;
        }
        
        Node newNode = new Node(i);
        if (i.compareTo(this.first.getData()) < 0) {
            newNode.setNext(this.first);
            this.first = newNode;
        } else {
            Node current = this.first;
            while (current.getNext() != null && i.compareTo(current.getNext().getData()) > 0) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
    }

    public boolean contains(X i) {
        Node atual = this.first;

        while (atual != null) {
            if (atual.getData().equals(i))
                return true;

            atual = atual.getNext();
        }

        return false;
    }

    public X getFirst() throws Exception {
        if (this.first == null) {
            throw new Exception("ponteiro nulo");
        }

        X dataFirst = new Clonador<X>().clone(this.first.getData());
        return dataFirst;
    }

    public X getLast() throws Exception {
        if (this.first == null)
            throw new Exception("A lista está vazia");

        Node atual = this.first;
        while (atual.getNext() != null) {
            atual = atual.getNext();
        }
        X dataLast = new Clonador<X>().clone(atual.getData());
        return dataLast;

    }

    private int getSize() {
        if (this.first == null) {
            return 0;
        }

        Node atual = this.first;
        int elementos = 0;

        while (atual != null) {
            atual = atual.getNext();
            elementos++;
        }

        return elementos;
    }

    // position poderá ser 0, 1, etc
    public X get(int position) throws Exception {
        if (this.first == null) {
            throw new Exception("Nó invalido");
        }
        if (position < 0 || position > getSize() - 1) {
            throw new Exception("Posicao invalida");
        }

        if (position == 0) {
            return this.first.getData();
        }

        Node atual = this.first;
        int indice = 0;

        while (atual != null && indice < position) {
            atual = atual.getNext();
            indice++;
        }

        X cont = new Clonador<X>().clone(atual.getData());
        return cont;
    }

    public void removeFirst() throws Exception {
        if (this.first == null)
            throw new Exception("Nó invalido");
        this.first = this.first.getNext();
    }

    public void removeLast() throws Exception {
        if (this.first == null)
            throw new Exception("Nó invalido");

        if (this.first.getNext() == null) {
            this.first = null;
            return;
        }

        Node atual = this.first;

        while (atual.getNext() != null) {
            atual = atual.getNext();
        }

        atual.setNext(null);
    }

    public void remove(int position) throws Exception {
        if (position < 0 || position >= this.getSize()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (position == 0) {
            this.first = this.first.getNext();
        } else {
            Node aux = this.first;
            for (int i = 0; i < position - 1; i++) {
                aux = aux.getNext();
            }

            aux.setNext(aux.getNext().getNext());
        }
    }

    @Override
    public String toString() {
        if (this.first == null)
            return "[]";

        String ret = "[";

        ret += this.first.getData();

        Node atual = this.first.getNext();

        while (atual != null) {
            ret += ", " + atual.getData();
            atual = atual.getNext();
        }

        return ret + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;

        Node atualDoThis = this.first;
        Node atualDoObj = ((OrderedSimpleLinkedList<X>) obj).first;

        while (atualDoThis != null && atualDoObj != null) {
            if (!atualDoThis.getData().equals(atualDoObj.getData())) {

                return false;
            }
            atualDoThis = atualDoThis.getNext();
            atualDoObj = atualDoObj.getNext();
        }

        if (atualDoThis != null || atualDoObj != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int ret = 1;

        Node atual = this.first;

        while (atual != null) {
            ret = ret * 2 + atual.getData().hashCode();
            atual = atual.getNext();
        }

        if (ret < 0)
            ret = -ret;
        return ret;
    }

    public OrderedSimpleLinkedList (OrderedSimpleLinkedList<X> modelo) throws Exception
	{
		if (modelo==null) throw new Exception ("Modelo ausente");
		
		if (modelo.first==null)
		{
			this.first=null;
			return;
		}
		
		this.first = new Node (modelo.first.getData());
		
		Node atualDoThis   = this.first;
		Node atualDoModelo = modelo.first.getNext();
		
		while (atualDoModelo!=null)
		{
			atualDoThis.setNext (new Node (atualDoModelo.getData()));
			
			atualDoThis  =atualDoThis  .getNext();
			atualDoModelo=atualDoModelo.getNext();
		}
	}

    public Object clone() {
        OrderedSimpleLinkedList<X> ret = null;

        try {
            ret = new OrderedSimpleLinkedList(this);
        } catch (Exception erro) {
        } // sei que nao vai dar erro, pois o construtor de cópia só da erro ao receber
          // parâmetro null, e o que estou passando como parâmetro para ele é o this, que
          // NUNCA, NUNQUINHA MESMO, é null

        return ret;
    }


}