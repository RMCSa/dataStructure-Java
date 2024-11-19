package Huffman;

import java.io.RandomAccessFile;
import HashMap.HashMap;
import LinkedList.Ordenadas.ListaEncadeadaSimplesOrdenada.ListaEncadeadaSimplesOrdenada;

public class Huffman {
    private HashMap<Character, String> codes;
    private ListaEncadeadaSimplesOrdenada<No> listaOrdenada;

    private class No implements Comparable<No> {
        private char charactere;
        private int frequencia;
        private No esq, dir;

        // Construtor para um nó folha
        public No(char character, int frequencia) {
            this.charactere = character;
            this.frequencia = frequencia;
            this.esq = null;
            this.dir = null;
        }

        // Construtor para um nó intermediário
        public No(int frequencia, No esq, No dir) {
            this.charactere = '\0'; // Nó intermediário não tem caractere associado
            this.frequencia = frequencia;
            this.esq = esq;
            this.dir = dir;
        }

        public boolean ehFolha() {
            return (esq == null && dir == null);
        }
        
        public int getFrequencia() {
            return frequencia;
        }

        public char getCharactere() {
            return charactere;
        }

        public No getEsq() {
            return esq;
        }

        public No getDir() {
            return dir;
        }

        public void setEsq(No esq) {
            this.esq = esq;
        }

        public void setDir(No dir) {
            this.dir = dir;
        }

        public void setFrequencia(int frequencia) {
            this.frequencia = frequencia;
        }

        public void setCharactere(char charactere) {
            this.charactere = charactere;
        }

        @Override
        public String toString() {
            return "[" + charactere + ", " + frequencia + "]";
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final No other = (No) obj;
            return this.frequencia == other.frequencia;
        }

        @Override
        public int hashCode() {
            int ret = 777;

            ret += 7 * Integer.valueOf(frequencia).hashCode();
            ret += 7 * Character.valueOf(charactere).hashCode();

            return ret < 0 ? -ret : ret;
        }

        @Override
        public int compareTo(No other) {
            return Integer.compare(this.frequencia, other.frequencia);
        }
    }
    // Construtor
    public Huffman() {
        this.codes = new HashMap<>(10);
        this.listaOrdenada = new ListaEncadeadaSimplesOrdenada<>();
    }

    // Método para codificar um arquivo
    public void compactar(String inputFilePath, String outputFilePath) throws Exception {
        System.out.println("Iniciando processo de codificação...");
        RandomAccessFile inputFile = new RandomAccessFile(inputFilePath, "r");
        RandomAccessFile outputFile = new RandomAccessFile(outputFilePath, "rw");

        // Passo 1: Contando frequência dos caracteres...
        System.out.println("Contando frequência dos caracteres...");
        HashMap<Character, Integer> frequenciaMap = new HashMap<>(10);
        int character;
        while ((character = inputFile.read()) != -1) {
            char ch = (char) character;
            try {
                int freq = frequenciaMap.recupereUmItem(ch);
                frequenciaMap.guardeUmItem(ch, freq + 1); // Atualizando a frequência
            } catch (Exception e) {
                frequenciaMap.guardeUmItem(ch, 1); // Inserindo novo caractere
            }
        }
        System.out.println("Frequências calculadas: " + frequenciaMap.toString());

        System.out.println("Passo 2: Construindo a árvore de Huffman...");
        construitArvoreHuffman(frequenciaMap);
        System.out.println("Árvore de Huffman construída com base nas frequências!");

        System.out.println("Passo 3: Gerando códigos de Huffman...");
        gerarCodigos(listaOrdenada.getPrimeiro(), "");
        System.out.println("Códigos gerados: " + codes.toString());

        // Passo 4: Escrever a árvore de Huffman no arquivo
        salvarArvore(outputFile, listaOrdenada.getPrimeiro());

        // Passo 5: Escrever o número de bits significativos
        inputFile.seek(0);
        StringBuilder compactarData = new StringBuilder();
        while ((character = inputFile.read()) != -1) {
            char ch = (char) character;
            compactarData.append(codes.recupereUmItem(ch));
        }
        int bitsImportantes = compactarData.length();
        outputFile.writeInt(bitsImportantes);

        // Passo 6: Escrever os dados codificados no arquivo
        salvarDados(outputFile, compactarData.toString());

        inputFile.close();
        outputFile.close();
    }
    // Método para decodificar um arquivo
    public void descompactar(String inputFilePath, String outputFilePath) throws Exception {
        RandomAccessFile inputFile = new RandomAccessFile(inputFilePath, "r");
        RandomAccessFile outputFile = new RandomAccessFile(outputFilePath, "rw");

        // Passo 1: Reconstruir a árvore de Huffman a partir do arquivo
        No root = recuperarArvore(inputFile);

        // Passo 2: Ler o número de bits significativos
        int bitsImportantes = inputFile.readInt();

        // Passo 3: Ler os dados codificados
        StringBuilder compactarData = new StringBuilder();
        int character;
        while ((character = inputFile.read()) != -1) {
            String byteString = String.format("%8s", Integer.toBinaryString(character & 0xFF)).replace(' ', '0');
            compactarData.append(byteString);
        }

        // Truncar para o número de bits significativos
        compactarData.setLength(bitsImportantes);

        // Passo 4: Decodificar os dados usando a árvore de Huffman
        No currentNode = root;
        StringBuilder descompactarData = new StringBuilder();
        for (int i = 0; i < compactarData.length(); i++) {
            currentNode = (compactarData.charAt(i) == '0') ? currentNode.esq : currentNode.dir;
            if (currentNode.ehFolha()) {
                descompactarData.append(currentNode.charactere);
                currentNode = root;
            }
        }

        // Escrever os dados decodificados no arquivo de saída
        outputFile.writeBytes(descompactarData.toString());

        inputFile.close();
        outputFile.close();
    }

    // Método para construir a árvore de Huffman
    private void construitArvoreHuffman(HashMap<Character, Integer> frequenciaMap) throws Exception {
        System.out.println("Iniciando construção da árvore de Huffman...");

        // Criar nós para cada caractere com base na frequência
        for (int i = 0; i < frequenciaMap.getTamanho(); i++) {
            Character character = frequenciaMap.recupereUmaChave(i);
            int frequencia = frequenciaMap.recupereUmItem(character);
            System.out.println("Criando nó para caractere '" + character + "' com frequência " + frequencia);
            listaOrdenada.guardeOrdenado(new No(character, frequencia));
        }

        // Combinar nós para formar a árvore de Huffman
        while (listaOrdenada.getTamanho() > 1) {
            No esq = listaOrdenada.getPrimeiro();
            listaOrdenada.removaPrimeiro();
            No dir = listaOrdenada.getPrimeiro();
            listaOrdenada.removaPrimeiro();

            int combinedfrequencia = esq.getFrequencia() + dir.getFrequencia();
            No parent = new No(combinedfrequencia, esq, dir);

            System.out.println("Combinando nós: [" + esq.charactere + ", " + esq.getFrequencia() + "] e [" +
                    dir.charactere + ", " + dir.getFrequencia() + "] -> Frequência combinada: " + combinedfrequencia);

            listaOrdenada.guardeOrdenado(parent);
        }

        System.out.println("Construção da árvore de Huffman concluída.");
    }

    // Método para gerar códigos de Huffman
    private void gerarCodigos(No node, String code) throws Exception {
        if (node.ehFolha()) {
            codes.guardeUmItem(node.charactere, code);
            return;
        }
        gerarCodigos(node.esq, code + "0");
        gerarCodigos(node.dir, code + "1");
    }
    // Método para escrever a árvore de Huffman no arquivo
    private void salvarArvore(RandomAccessFile file, No node) throws Exception {
        if (node.ehFolha()) {
            file.writeBoolean(true); // Indica que é uma folha
            file.writeChar(node.charactere); // Escreve o caractere
            return;
        }
        file.writeBoolean(false); // Indica que é um nó intermediário
        salvarArvore(file, node.esq);
        salvarArvore(file, node.dir);
    }

    // Método para reconstruir a árvore de Huffman a partir do arquivo
    private No recuperarArvore(RandomAccessFile file) throws Exception {
        if (file.readBoolean()) { // Se for uma folha
            return new No(file.readChar(), 0); // Retorna o nó folha com o caractere (a frequência não é relevante
                                                 // aqui)
        } else {
            No esq = recuperarArvore(file); // Reconstrói a subárvore esquerda
            No dir = recuperarArvore(file); // Reconstrói a subárvore direita
            return new No(0, esq, dir); // Cria um nó intermediário (a frequência não é usada na decodificação)
        }
    }

    // Método para escrever os dados codificados no arquivo
    private void salvarDados(RandomAccessFile file, String data) throws Exception {
        for (int i = 0; i < data.length(); i += 8) {
            String byteString = data.substring(i, Math.min(i + 8, data.length()));
            while (byteString.length() < 8) {
                byteString += "0";
            }
            file.writeByte((byte) Integer.parseInt(byteString, 2));
        }
    }
}
