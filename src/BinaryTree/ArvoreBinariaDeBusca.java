package BinaryTree;

import java.io.EOFException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public boolean tem(X info) throws Exception {
        if (info == null)
            throw new Exception("informacao ausente");

        No atual = this.raiz;
        for (;;) // forever
        {
            if (atual == null)
                return false;

            int comparacao = info.compareTo(atual.getInfo());
            if (comparacao == 0)
                return true;

            if (comparacao < 0)
                atual = atual.getEsq();
            else // comparacao>0
                atual = atual.getDir();
        }
    }

    public X getMenor() throws Exception {
        if (this.raiz == null)
            throw new Exception("arvore vazia");

        No atual = this.raiz;
        X ret = null;
        for (;;) // forever
        {
            if (atual.getEsq() == null) {
                if (atual.getInfo() instanceof Cloneable)
                    ret = (X) meuCloneDeX(atual.getInfo());
                else
                    ret = atual.getInfo();

                break;
            } else
                atual = atual.getEsq();
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

    public void balanceieSe() throws Exception {
        balanceieSe(this.raiz);
    }

    private void balanceieSe(No r) throws Exception {
        if (r == null)
            return;

        int qtdDir = getQtdDeNodos(r.getDir());
        int qtdEsq = getQtdDeNodos(r.getEsq());

        while (Math.abs(qtdDir - qtdEsq) > 1) {
            // enquanto a quantidade de nós a esquerda menos
            // a quantidade de nós a direita for maior 1,
            if (qtdEsq - qtdDir > 1) {
                X antigaRaiz = r.getInfo();
                No atual = r;

                // remova da esquerda a extrema direita, guardando
                // numa variável o valor ali presente;
                atual = atual.getEsq();
                while (atual.getDir() != null) {
                    atual = atual.getDir();
                }

                // substitua por esse valor o valor presente na raiz, salvando-o
                // antes numa outra variavel;
                r.setInfo(atual.getInfo());
                remova(atual.getInfo());

                // insira na arvore o valor que estava presente na raiz
                inclua(antigaRaiz);
                qtdEsq--;
                qtdDir++;

                // OBS: CHAME 1 SÓ VEZ getQtdDeNodos PARA A ESQUERDA E
                // PARA A DIREITA, ARMAZENANDO OS RESULTADOS EM
                // VARIÁVEIS QUE VOCÊ ATUALIZA NO WHILE
            }

            // enquanto a quantidade de nós a direita menos
            // a quantidade de nós a esquerda for maior 1,
            if (qtdDir - qtdEsq > 1) {
                X antigaRaiz = r.getInfo();
                No atual = r;

                // remova da direita a extrema esquerda, guardando
                // numa variável o valor ali presente;
                atual = atual.getDir();
                while (atual.getEsq() != null) {
                    atual = atual.getEsq();
                }

                // substitua por esse valor o valor presente na raiz, salvando-o
                // antes numa outra variavel;
                r.setInfo(atual.getInfo());
                remova(atual.getInfo());

                // insira na arvore o valor que estava presente na raiz
                inclua(antigaRaiz);
                qtdEsq++;
                qtdDir--;

                // OBS: CHAME 1 SÓ VEZ getQtdDeNodos PARA A ESQUERDA E
                // PARA A DIREITA, ARMAZENANDO OS RESULTADOS EM
                // VARIÁVEIS QUE VOCÊ ATUALIZA NO WHILE
            }
        }
        // faça recursão para a esquerda e para a direita
        balanceieSe(r.getDir());
        balanceieSe(r.getEsq());
    }

    public X getMaior() throws Exception {
        if (this.raiz == null)
            throw new Exception("arvore vazia");

        No atual = this.raiz;
        X ret = null;
        for (;;) // forever
        {
            if (atual.getDir() == null) {
                if (atual.getInfo() instanceof Cloneable)
                    ret = (X) meuCloneDeX(atual.getInfo());
                else
                    ret = atual.getInfo();

                break;
            } else
                atual = atual.getDir();
        }

        return ret;
    }

    public void remova(X info) throws Exception {
        if (info == null) {
            throw new Exception("Informação ausente");
        }
        if (this.raiz == null) {
            throw new Exception("Nó nulo");
        }
        if (!tem(info)) { // se não for encontrada na arvore
            throw new Exception("Informação Inexistente");
        }

        No atual = this.raiz;
        No pai = null;
        boolean filhoEsquerdo = true;

        for (;;) {
            int comparacao = info.compareTo(atual.getInfo());
            if (comparacao == 0) {
                break;
            }
            pai = atual;
            if (comparacao < 0) {
                atual = atual.getEsq();
                filhoEsquerdo = true;
            } else {
                atual = atual.getDir();
                filhoEsquerdo = false;
            }
        }

        // se a info for encontrada numa folha, deslique a folha da árvore,
        // fazendo o ponteiro que aponta para ela dentro do seu nó pai,
        // tornar-se null
        if (atual.getEsq() == null && atual.getDir() == null) {
            if (atual == this.raiz) {
                this.raiz = null;
            } else if (filhoEsquerdo) {
                pai.setEsq(null);
            } else {
                pai.setDir(null);
            }
        }
        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à esquerda, e sendo N filho esquerdo de um certo
        // pai P, faça o ponteiro esquerdo de P, passar a apontar para
        // esse filho que ha na esquerda de N
        else if (atual.getDir() == null && filhoEsquerdo) {
            if (atual == this.raiz) {
                this.raiz = atual.getEsq();
            } else {
                pai.setEsq(atual.getEsq());
            }
        }

        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à esquerda, e sendo N filho direito de um certo
        // pai P, faça o ponteiro direito de P, passar a apontar para
        // esse filho que ha na esquerda de N
        else if (atual.getDir() == null && !filhoEsquerdo) {
            if (atual == this.raiz) {
                this.raiz = atual.getEsq();
            } else {
                pai.setDir(atual.getEsq());
            }
        }

        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à direita, e sendo N filho esquerdo de um certo
        // pai P, faça o ponteiro esquerdo de P, passar a apontar para
        // esse filho que ha na direita de N
        else if (atual.getEsq() == null && filhoEsquerdo) {
            if (atual == this.raiz) {
                this.raiz = atual.getDir();
            } else {
                pai.setEsq(atual.getDir());
            }
        }

        // se info for encontrada num nó N, que não é folha, sendo que N
        // só tem filho à direita, e sendo N filho direita de um certo
        // pai P, faça o ponteiro direito de P, passar a apontar para
        // esse filho que ha na direita de N
        else if (atual.getEsq() == null && !filhoEsquerdo) {
            if (atual == this.raiz) {
                this.raiz = atual.getDir();
            } else {
                pai.setDir(atual.getDir());
            }
        }

        // se info for encontrada num nó N, que não é folha e tem 2 filhos,
        // encontre a informação info que existe à extrema esquerda da
        // subarvore direita de N ou à extrema direita da subarvore esquerda
        // de N; remova o nó que contém info e substitua dentro do nó N,
        // a informação que ali se encontra por info
        else {
            No sucessor = null;
            if (getQtdDeNodos(atual.getEsq()) > getQtdDeNodos(atual.getDir())) {
                pai = atual;
                sucessor = atual.getEsq();
                filhoEsquerdo = true;
                while (sucessor.getDir() != null) {
                    pai = sucessor;
                    sucessor = sucessor.getDir();
                    filhoEsquerdo = false;
                }
            } else {
                pai = atual;
                sucessor = atual.getDir();
                filhoEsquerdo = false;
                while (sucessor.getEsq() != null) {
                    pai = sucessor;
                    sucessor = sucessor.getEsq();
                    filhoEsquerdo = true;
                }
            }
            if (filhoEsquerdo) {
                pai.setEsq(null);
            } else {
                pai.setDir(null);
            }

            atual.setInfo(sucessor.getInfo());
        }
    }

    // O método emOrdem percorre a árvore binária de gorma que o nó atual é visitado
    // entre os filhos
    public void emOrdem(No atual) {
        if (atual != null) {
            emOrdem(atual.getEsq());
            System.out.println(atual.getInfo());
            emOrdem(atual.getDir());
        }
    }

    // O método preOrdem percorre a árvore de forma que o nó atual é visitado antes
    // de seus filhos
    public void preOrdem(No atual) {
        if (atual != null) {
            System.out.println(atual.getInfo());
            preOrdem(atual.getEsq());
            preOrdem(atual.getDir());
        }
    }
    
    // O método posOrdem percorre a árvore de forma que o nó atual é visitado depois
    // de seus filhos
    public void posOrdem(No atual) {
        if (atual != null) {
            posOrdem(atual.getEsq());
            posOrdem(atual.getDir());
            System.out.println(atual.getInfo());
        }
    }

    //PODEM CAIR NA PROVA
/* 
1. **Altura da Árvore**: Um método para calcular a altura da árvore.
2. **Buscar Nó**: Um método para buscar um nó específico e retornar o próprio nó.
3. **Remover Nó**: Já implementado, mas pode ser otimizado.
4. **Encontrar Antecessor e Sucessor**: Métodos para encontrar o antecessor e sucessor de um nó específico.
5. **Verificar Balanceamento**: Um método para verificar se a árvore está balanceada.
6. **Caminhamento em Nível**: Um método para percorrer a árvore em nível (ou por largura).
7. **Contar Folhas**: Um método para contar o número de folhas na árvore.
8. **Verificar Se é BST**: Um método para verificar se a árvore é uma árvore binária de busca válida.
*/

// 1. Altura da Árvore
public int altura() {
    return altura(this.raiz);
}

private int altura(No r) {
    if (r == null)
        return -1;
    int esqAltura = altura(r.getEsq());
    int dirAltura = altura(r.getDir());
    return 1 + Math.max(esqAltura, dirAltura);
}


// 2. Buscar Nó
private No buscarNo(No atual, X info) {
    if (atual == null || atual.getInfo().equals(info))
        return atual;
    int comparacao = info.compareTo(atual.getInfo());
    if (comparacao < 0)
        return buscarNo(atual.getEsq(), info);
    else
        return buscarNo(atual.getDir(), info);
}


// 3. Encontrar Antecessor e Sucessor
public X antecessor(X info) throws Exception {
    No atual = buscarNo(this.raiz, info);
    if (atual == null || atual.getEsq() == null)
        return null;
    No antecessor = atual.getEsq();
    while (antecessor.getDir() != null)
        antecessor = antecessor.getDir();
    return antecessor.getInfo();
}

public X sucessor(X info) throws Exception {
    No atual = buscarNo(this.raiz, info);
    if (atual == null || atual.getDir() == null)
        return null;
    No sucessor = atual.getDir();
    while (sucessor.getEsq() != null)
        sucessor = sucessor.getEsq();
    return sucessor.getInfo();
}


// 4. Verificar Balanceamento
public boolean estaBalanceada() {
    return estaBalanceada(this.raiz);
}

private boolean estaBalanceada(No r) {
    if (r == null)
        return true;
    int esqAltura = altura(r.getEsq());
    int dirAltura = altura(r.getDir());
    return Math.abs(esqAltura - dirAltura) <= 1 && estaBalanceada(r.getEsq()) && estaBalanceada(r.getDir());
}


// 5. Caminhamento em Nível
public void emNivel() {
    if (this.raiz == null)
        return;
    Queue<No> fila = new LinkedList<>();
    fila.add(this.raiz);
    while (!fila.isEmpty()) {
        No atual = fila.poll();
        System.out.println(atual.getInfo());
        if (atual.getEsq() != null)
            fila.add(atual.getEsq());
        if (atual.getDir() != null)
            fila.add(atual.getDir());
    }
}


// 6. Contar Folhas
public int contarFolhas() {
    return contarFolhas(this.raiz);
}

private int contarFolhas(No r) {
    if (r == null)
        return 0;
    if (r.getEsq() == null && r.getDir() == null)
        return 1;
    return contarFolhas(r.getEsq()) + contarFolhas(r.getDir());
}


// 7. Verificar Se é BST

public boolean eBST() {
    return eBST(this.raiz, null, null);
}

private boolean eBST(No r, X min, X max) {
    if (r == null)
        return true;
    if ((min != null && r.getInfo().compareTo(min) <= 0) || (max != null && r.getInfo().compareTo(max) >= 0))
        return false;
    return eBST(r.getEsq(), min, r.getInfo()) && eBST(r.getDir(), r.getInfo(), max);
}

// 8. Obter Caminho até um Nó

public List<X> getCaminho(X info) throws Exception {
    List<X> caminho = new ArrayList<>();
    if (!getCaminho(this.raiz, info, caminho)) {
        throw new Exception("Informação não encontrada na árvore");
    }
    return caminho;
}

private boolean getCaminho(No atual, X info, List<X> caminho) {
    if (atual == null) {
        return false;
    }
    caminho.add(atual.getInfo());
    if (atual.getInfo().equals(info)) {
        return true;
    }
    if (info.compareTo(atual.getInfo()) < 0) {
        return getCaminho(atual.getEsq(), info, caminho);
    } else {
        return getCaminho(atual.getDir(), info, caminho);
    }
}


// 9. Verificar se a Árvore é Completa

public boolean eCompleta() {
    if (this.raiz == null) {
        return true;
    }
    Queue<No> fila = new LinkedList<>();
    fila.add(this.raiz);
    boolean encontrouNulo = false;
    while (!fila.isEmpty()) {
        No atual = fila.poll();
        if (atual == null) {
            encontrouNulo = true;
        } else {
            if (encontrouNulo) {
                return false;
            }
            fila.add(atual.getEsq());
            fila.add(atual.getDir());
        }
    }
    return true;
}


// 10. Verificar se a Árvore é Perfeita

public boolean ePerfeita() {
    int altura = altura(this.raiz);
    return ePerfeita(this.raiz, altura, 0);
}

private boolean ePerfeita(No r, int altura, int nivel) {
    if (r == null) {
        return true;
    }
    if (r.getEsq() == null && r.getDir() == null) {
        return altura == nivel + 1;
    }
    if (r.getEsq() == null || r.getDir() == null) {
        return false;
    }
    return ePerfeita(r.getEsq(), altura, nivel + 1) && ePerfeita(r.getDir(), altura, nivel + 1);
}


// 11. Espelhar a Árvore

public void espelhar() {
    this.raiz = espelhar(this.raiz);
}

private No espelhar(No r) {
    if (r == null) {
        return null;
    }
    No esq = espelhar(r.getEsq());
    No dir = espelhar(r.getDir());
    r.setEsq(dir);
    r.setDir(esq);
    return r;
}


// 12. Contar Nodos Internos

public int contarNodosInternos() {
    return contarNodosInternos(this.raiz);
}

private int contarNodosInternos(No r) {
    if (r == null || (r.getEsq() == null && r.getDir() == null)) {
        return 0;
    }
    return 1 + contarNodosInternos(r.getEsq()) + contarNodosInternos(r.getDir());
}


// 13. Contar Nodos Externos (Folhas)

public int contarNodosExternos() {
    return contarNodosExternos(this.raiz);
}

private int contarNodosExternos(No r) {
    if (r == null) {
        return 0;
    }
    if (r.getEsq() == null && r.getDir() == null) {
        return 1;
    }
    return contarNodosExternos(r.getEsq()) + contarNodosExternos(r.getDir());
}


// 14. Obter Profundidade de um Nó

public int obterProfundidade(X info) throws Exception {
    return obterProfundidade(this.raiz, info, 0);
}

private int obterProfundidade(No r, X info, int profundidade) throws Exception {
    if (r == null) {
        throw new Exception("Informação não encontrada na árvore");
    }
    if (r.getInfo().equals(info)) {
        return profundidade;
    }
    if (info.compareTo(r.getInfo()) < 0) {
        return obterProfundidade(r.getEsq(), info, profundidade + 1);
    } else {
        return obterProfundidade(r.getDir(), info, profundidade + 1);
    }
}


// 15. Obter Diâmetro da Árvore

public int diametro() {
    return diametro(this.raiz);
}

private int diametro(No r) {
    if (r == null) {
        return 0;
    }
    int esqAltura = altura(r.getEsq());
    int dirAltura = altura(r.getDir());
    int esqDiametro = diametro(r.getEsq());
    int dirDiametro = diametro(r.getDir());
    return Math.max(esqAltura + dirAltura + 2, Math.max(esqDiametro, dirDiametro));
}

    // ATÉ AQUI 

    @Override
    public String toString() {
        return "";
    }

    public No getRaiz() {
        return this.raiz;
    }

    public static void main(String[] args) throws Exception {
        ArvoreBinariaDeBusca<Integer> arvore = new ArvoreBinariaDeBusca<>();
        arvore.inclua(10);
        arvore.inclua(8);
        arvore.inclua(18);
        arvore.inclua(5);
        arvore.inclua(9);
        arvore.inclua(7);
        arvore.inclua(13);
        arvore.inclua(20);
        System.out.println("Em Ordem:");
        arvore.emOrdem(arvore.getRaiz());
        System.out.println("separador");
        arvore.emOrdem(arvore.getRaiz().getEsq());
    }

}
