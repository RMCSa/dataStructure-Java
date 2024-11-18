package HashMap;

import LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada.ListaEncadeadaSimplesDesordenada;

public class HashMap<K, V> {
    private class Elemento {
        private K chave;
        private V valor;

        Elemento(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }

        public K getChave() {
            return chave;
        }

        public V getValor() {
            return valor;
        }

        public void setValor(V valor) {
            this.valor = valor;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final Elemento other = (Elemento) obj;
            return this.chave.equals(other.chave);
        }

        @Override
        public String toString() {
            return "(" + chave + ", " + valor + ")";
        }
    }

    private ListaEncadeadaSimplesDesordenada<Elemento>[] tabela;
    private int qtdElems = 0;
    private int capacidadeInicial;
    private float txMinDesperdicio, txMaxDesperdicio;

    // Construtor principal
    public HashMap(int capacidadeInicial, float txMinDesperdicio, float txMaxDesperdicio) {
        this.capacidadeInicial = capacidadeInicial;
        this.txMinDesperdicio = txMinDesperdicio;
        this.txMaxDesperdicio = txMaxDesperdicio;
        this.tabela = new ListaEncadeadaSimplesDesordenada[capacidadeInicial];
    }

    // Construtor alternativo com taxas padrão
    public HashMap(int capacidadeInicial) {
        this(capacidadeInicial, 0.7f, 0.9f); // Valores padrão de 70% e 90%
    }

    private int calcularIndice(K chave) {
        return Math.abs(chave.hashCode()) % capacidadeInicial;
    }

    private void verificarTaxaEDimensionar() {
        float taxaOcupacao = (float) qtdElems / capacidadeInicial;

        if (taxaOcupacao > txMaxDesperdicio) {
            redimensionar(capacidadeInicial * 2); // Aumenta o tamanho
        } else if (taxaOcupacao < txMinDesperdicio && capacidadeInicial > 1) {
            redimensionar(Math.max(capacidadeInicial / 2, 1)); // Diminui o tamanho, mas não abaixo de 1
        }
    }

    private void redimensionar(int novaCapacidade) {
        ListaEncadeadaSimplesDesordenada<Elemento>[] novaTabela = new ListaEncadeadaSimplesDesordenada[novaCapacidade];
        for (ListaEncadeadaSimplesDesordenada<Elemento> lista : tabela) {
            if (lista != null) {
                for (int i = 0; i < lista.getTamanho(); i++) {
                    try {
                        Elemento elemento = lista.get(i);
                        int novoIndice = Math.abs(elemento.getChave().hashCode()) % novaCapacidade;
                        if (novaTabela[novoIndice] == null) {
                            novaTabela[novoIndice] = new ListaEncadeadaSimplesDesordenada<>();
                        }
                        novaTabela[novoIndice].guardeNoFinal(elemento);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        tabela = novaTabela;
        capacidadeInicial = novaCapacidade;
    }

    public void guardeUmItem(K chave, V valor) throws Exception {
        if (chave == null) {
            throw new Exception("Chave não pode ser nula");
        }

        int indice = calcularIndice(chave);

        if (tabela[indice] == null) {
            tabela[indice] = new ListaEncadeadaSimplesDesordenada<>();
        }

        ListaEncadeadaSimplesDesordenada<Elemento> lista = tabela[indice];
        Elemento novoElemento = new Elemento(chave, valor);

        for (int i = 0; i < lista.getTamanho(); i++) {
            Elemento atual = lista.get(i);
            if (atual.getChave().equals(chave)) {
                atual.setValor(valor);
                return;
            }
        }

        lista.guardeNoFinal(novoElemento);
        qtdElems++;
        verificarTaxaEDimensionar();
    }

    public V recupereUmItem(K chave) throws Exception {
        if (chave == null) {
            throw new Exception("Chave não pode ser nula");
        }

        int indice = calcularIndice(chave);
        ListaEncadeadaSimplesDesordenada<Elemento> lista = tabela[indice];

        if (lista != null) {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Elemento atual = lista.get(i);
                if (atual.getChave().equals(chave)) {
                    return atual.getValor();
                }
            }
        }

        throw new Exception("Item não encontrado");
    }

    public void removaUmItem(K chave) throws Exception {
        int indice = calcularIndice(chave);
        ListaEncadeadaSimplesDesordenada<Elemento> lista = tabela[indice];

        if (lista != null) {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Elemento atual = lista.get(i);
                if (atual.getChave().equals(chave)) {
                    lista.remova(i);
                    qtdElems--;
                    verificarTaxaEDimensionar(); // Verifica e redimensiona se necessário
                    return;
                }
            }
        }

        throw new Exception("Item não encontrado");
    }

    public K recupereUmaChave(int index) throws Exception {
        if (index < 0 || index >= qtdElems) {
            throw new Exception("Índice fora dos limites");
        }

        int currentIndex = 0;
        for (ListaEncadeadaSimplesDesordenada<Elemento> lista : tabela) {
            if (lista != null) {
                for (int i = 0; i < lista.getTamanho(); i++) {
                    if (currentIndex == index) {
                        return lista.get(i).getChave();
                    }
                    currentIndex++;
                }
            }
        }

        throw new Exception("Item não encontrado");
    }

    public int getTamanho() {
        return qtdElems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (ListaEncadeadaSimplesDesordenada<Elemento> lista : tabela) {
            if (lista != null) {
                for (int i = 0; i < lista.getTamanho(); i++) {
                    try {
                        Elemento elemento = lista.get(i);
                        sb.append(elemento.toString()).append(", ");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (sb.length() > 1)
            sb.setLength(sb.length() - 2); // Remove a última vírgula
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            // Criação do HashMap com capacidade inicial de 10 e taxas de desperdício padrão
            HashMap<String, Integer> mapa = new HashMap<>(10);

            // Adicionando alguns elementos
            mapa.guardeUmItem("Alice", 25);
            mapa.guardeUmItem("Bob", 30);
            mapa.guardeUmItem("Charlie", 35);

            // Recuperando elementos
            System.out.println("Valor associado a 'Alice': " + mapa.recupereUmItem("Alice"));
            System.out.println("Valor associado a 'Bob': " + mapa.recupereUmItem("Bob"));
            System.out.println("Valor associado a 'Charlie': " + mapa.recupereUmItem("Charlie"));

            // Atualizando valor de um elemento existente
            mapa.guardeUmItem("Bob", 40);
            System.out.println("Valor atualizado associado a 'Bob': " + mapa.recupereUmItem("Bob"));

            // Removendo um elemento
            mapa.removaUmItem("Charlie");
            System.out.println("Elemento 'Charlie' removido com sucesso.");

            // Tentando recuperar um elemento removido (deve lançar uma exceção)
            try {
                System.out.println(mapa.recupereUmItem("Charlie"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // Exibindo o conteúdo do HashMap
            System.out.println("Conteúdo do HashMap: " + mapa.toString());

            // Testando redimensionamento
            for (int i = 0; i < 20; i++) {
                mapa.guardeUmItem("Chave" + i, i * 10);
            }
            System.out.println("Conteúdo do HashMap após redimensionamento: " + mapa.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
