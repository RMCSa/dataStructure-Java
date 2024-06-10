package BinaryTree;

import java.io.EOFException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        @Override
        public String toString() {
            return this.info.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (this.getClass() != obj.getClass())
                return false;
            No no = (No) obj;
            return this.info.equals(no.info);
        }

        @Override
        public int hashCode() {
            int ret = 777;
            ret = 7 * ret + this.info.hashCode();
            if (ret < 0)
                ret = -ret;
            return ret;
        }

        @Override
        public No clone() {
            No ret = new No(this.info);
            if (this.esq != null)
                ret.esq = this.esq.clone();
            if (this.dir != null)
                ret.dir = this.dir.clone();
            return ret;
        }
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

        X info;
        if (inf instanceof Cloneable)
            info = (X)meuCloneDeX(inf);
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
            else { // deve-se inserir info para o lado direito
                if (atual.getDir() != null)
                    atual = atual.getDir();
                else // achei onde inserir; eh para a direito do atual
                {
                    atual.setDir(new No(info));
                    return;
                }
            }
        }
    }

    public boolean tem (X info) throws Exception
	{
		if (info==null) throw new Exception ("informacao ausente");

        No atual=this.raiz;
        for(;;) // forever
        {
			if (atual==null) return false;
			
            int comparacao = info.compareTo(atual.getInfo());
            if (comparacao==0) return true;
            
            if (comparacao<0)
                atual=atual.getEsq();
            else // comparacao>0
                atual=atual.getDir();
        }
    }
        

    public X getMenor () throws Exception
    { 
        if (this.raiz==null) throw new Exception ("arvore vazia");
	    
        No atual=this.raiz ; X ret =null;
        for(;;) // forever
        {
			if (atual.getEsq()==null)
			{
				if (atual.getInfo() instanceof Cloneable)
     				ret = (X)meuCloneDeX(atual.getInfo());
     			else
     			    ret = atual.getInfo();
     			
     			break;
			}
            else
                atual=atual.getEsq();
		}
		
		return ret;
    }

    public int getQtdDeNodos() throws Exception {
        return getQtdDeNodos(this.raiz);
    }

    private int getQtdDeNodos(No r) {
        if (r == null)
            return 0;

        return 1 + getQtdDeNodos(r.getEsq()) + getQtdDeNodos(r.getDir());
    }

    public void balanceieSe() {
        balanceieSe(this.raiz);
    }

    private void balanceieSe(No r) {
        // enquanto a quantidade de nós a esquerda menos
        // a quantidade de nós a direita for maior 1,
        // remova da esquerda a extrema direita, guardando
        // numa variável o valor ali presente; substitua por
        // esse valor o valor presente na raiz, salvando-o
        // antes numa outra variavel; insira na arvore o valor
        // que estava presente na raiz
        // OBS: CHAME 1 SÓ VEZ getQtdDeNodos PARA A ESQUERDA E
        // PARA A DIREITA, ARMAZENANDO OS RESULTADOS EM
        // VARIÁVEIS QUE VOCÊ ATUALIZA NO WHILE


        // enquanto a quantidade de nós a direita menos
        // a quantidade de nós a esquerda for maior 1,
        // remova da direita a extrema esquerda, guardando
        // numa variável o valor ali presente; substitua por
        // esse valor o valor presente na raiz, salvando-o
        // antes numa outra variavel; insira na arvore o valor
        // que estava presente na raiz
        // OBS: CHAME 1 SÓ VEZ getQtdDeNodos PARA A ESQUERDA E
        // PARA A DIREITA, ARMAZENANDO OS RESULTADOS EM
        // VARIÁVEIS QUE VOCÊ ATUALIZA NO WHILE

        // faça recursão para a esquerda e para a direita
    }

    public X getMaior () throws Exception
    {
	    if (this.raiz==null) throw new Exception ("arvore vazia");
	    
        No atual=this.raiz; X ret=null;
        for(;;) // forever
        {
			if (atual.getDir()==null)
			{
				if (atual.getInfo() instanceof Cloneable)
     				ret = (X)meuCloneDeX(atual.getInfo());
     			else
     			    ret = atual.getInfo();
     			
     			break;
			}
            else
                atual=atual.getDir();
		}
		
		return ret;
    }

    public void remova(X info) throws Exception{
        if(info == null){
            throw new Exception("Informação ausente");
        }
        if (this.raiz == null){
            throw new Exception("Nó nulo");
        }
        if (!tem(info)){ //se não for encontrada na arvore
            throw new Exception("Informação Inexistente");
        }

        No atual = this.raiz;
        No pai = null;
        boolean filhoEsquerdo = true;

        for (;;){
            int comparacao = info.compareTo(atual.getInfo());
            if (comparacao == 0){
                break;
            }
            pai = atual;
            if (comparacao < 0){
                atual = atual.getEsq();
                filhoEsquerdo = true;
            }
            else{
                atual = atual.getDir();
                filhoEsquerdo = false;
            }
        }

        // se a info for encontrada numa folha, deslique a folha da árvore,
        // fazendo o ponteiro que aponta para ela dentro do seu nó pai,
        // tornar-se null
        if ( atual.getEsq() == null && atual.getDir() == null){
            if (atual == this.raiz) {
                this.raiz = null;
            } else if (filhoEsquerdo){
                pai.setEsq(null);
            }
            else{
                pai.setDir(null);
            }
        }
        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à esquerda, e sendo N filho esquerdo de um certo
        // pai P, faça o ponteiro esquerdo de P, passar a apontar para
        // esse filho que ha na esquerda de N
        else if ( atual.getDir() == null && filhoEsquerdo) {
            if (atual == this.raiz) {
                this.raiz = atual.getEsq();
            }
            else{
                pai.setEsq(atual.getEsq());
            }
        }

        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à esquerda, e sendo N filho direito de um certo
        // pai P, faça o ponteiro direito de P, passar a apontar para
        // esse filho que ha na esquerda de N
        else if ( atual.getDir() == null && !filhoEsquerdo){
            if (atual == this.raiz) {
                this.raiz = atual.getEsq();
            }
            else {
                pai.setDir(atual.getEsq());
            }
        }

        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à direita, e sendo N filho esquerdo de um certo
        // pai P, faça o ponteiro esquerdo de P, passar a apontar para
        // esse filho que ha na direita de N
        else if ( atual.getEsq() == null && filhoEsquerdo){
            if (atual == this.raiz) {
                this.raiz = atual.getDir();
            }
            else{
                pai.setEsq(atual.getDir());
            }
        }

        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à direita, e sendo N filho direita de um certo
        // pai P, faça o ponteiro direito de P, passar a apontar para
        // esse filho que ha na direita de N
        else if (atual.getEsq() == null && !filhoEsquerdo){
            if (atual == this.raiz) {
                this.raiz = atual.getDir();
            }
            else{
                pai.setDir(atual.getDir());
            }
        }

        // se info for encontrada num nó N, que não é folha e tem 2 filhos,
        // encontre a informação info que existe à extrema esquerda da
        // subarvore direita de N ou à extrema direita da subarvore esquerda
        // de N; remova o nó que contém info e substitua dentro do nó N,
        // a informação que ali se encontra por info
        else{
            No sucessor = null;
            if (atual.getEsq() != null){
                pai = atual;
                sucessor = atual.getEsq();
                filhoEsquerdo = true;
                while (sucessor.getDir() != null){
                    pai = sucessor;
                    sucessor = sucessor.getDir();
                    filhoEsquerdo = false;
                }
            }
            else{
                pai = atual;
                sucessor = atual.getDir();
                filhoEsquerdo = false;
                while (sucessor.getEsq() != null){
                    pai = sucessor;
                    sucessor = sucessor.getEsq();
                    filhoEsquerdo = true;
                }
            }
            if (filhoEsquerdo){
                pai.setEsq(null);
            }
            else{
                pai.setDir(null);
            }

            atual.setInfo(sucessor.getInfo());
        }
    }

    // O método emOrdem percorre a árvore binária de gorma que o nó atual é visitado entre os filhos
    public void emOrdem(No atual){
        if (atual != null){
            emOrdem(atual.getEsq());
            System.out.println(atual.getInfo());
    
            emOrdem(atual.getDir());
        }
    }

    // O método preOrdem percorre a árvore de forma que o nó atual é visitado antes de seus filhos
    public void preOrdem(No atual){
        if (atual != null) {
            System.out.println(atual.getInfo());
            preOrdem(atual.getEsq());
            preOrdem(atual.getDir());
        }
    }

    // O método posOrdem percorre a árvore de forma que o nó atual é visitado depois de seus filhos
    public void posOrdem(No atual){
        if (atual != null) {
            posOrdem(atual.getEsq());
            posOrdem(atual.getDir());
            System.out.println(atual.getInfo());
        }
    }
    
    @Override
    public String toString() {
        return "";
    }

    public No getRaiz(){
        return this.raiz;
    }

    public static void main(String[] args) throws Exception{
        ArvoreBinariaDeBusca<Integer> arvore = new ArvoreBinariaDeBusca<>();
        arvore.inclua(10);
        arvore.inclua(8);
        arvore.inclua(18);
        arvore.inclua(5);
        arvore.inclua(9);
        arvore.inclua(7);
        arvore.inclua(13);
        arvore.inclua(20);
        arvore.remova(10);
        System.out.println("Em Ordem:");
        arvore.emOrdem(arvore.getRaiz());
        System.out.println("Número de Nós: " + ArvoreBinariaDeBusca.aux);
        

    }

}
