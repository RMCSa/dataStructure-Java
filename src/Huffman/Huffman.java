package Huffman;

import java.io.RandomAccessFile;
import HashMap.HashMap;
import LinkedList.Ordenadas.ListaEncadeadaSimplesOrdenada.ListaEncadeadaSimplesOrdenada;

public class Huffman {
    private HashMap<Character, String> codes;
    private ListaEncadeadaSimplesOrdenada<Node> priorityQueue;

    private class Node implements Comparable<Node> {
        private char character;
        private int frequency;
        private Node left, right;

        // Construtor para um nó folha
        public Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
            this.left = null;
            this.right = null;
        }

        // Construtor para um nó intermediário
        public Node(int frequency, Node left, Node right) {
            this.character = '\0'; // Nó intermediário não tem caractere associado
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }


        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.frequency, other.frequency);
        }

        public boolean isLeaf() {
            return (left == null && right == null);
        }

        public int getFrequency() {
            return frequency;
        }
    }
    // Construtor
    public Huffman() {
        this.codes = new HashMap<>(10);
        this.priorityQueue = new ListaEncadeadaSimplesOrdenada<>();
    }

    // Método para codificar um arquivo
    public void encode(String inputFilePath, String outputFilePath) throws Exception {
        System.out.println("Iniciando processo de codificação...");
        RandomAccessFile inputFile = new RandomAccessFile(inputFilePath, "r");
        RandomAccessFile outputFile = new RandomAccessFile(outputFilePath, "rw");

        System.out.println("Passo 1: Contando frequência dos caracteres...");
        HashMap<Character, Integer> frequencyMap = new HashMap<>(10);
        int character;
        while ((character = inputFile.read()) != -1) {
            char ch = (char) character;
            try {
                int freq = frequencyMap.recupereUmItem(ch);
                frequencyMap.guardeUmItem(ch, freq + 1);
            } catch (Exception e) {
                frequencyMap.guardeUmItem(ch, 1);
            }
        }
        System.out.println("Frequências calculadas: " + frequencyMap.toString());

        System.out.println("Passo 2: Construindo a árvore de Huffman...");
        buildHuffmanTree(frequencyMap);
        System.out.println("Árvore de Huffman construída com base nas frequências!");

        System.out.println("Passo 3: Gerando códigos de Huffman...");
        generateCodes(priorityQueue.getPrimeiro(), "");
        System.out.println("Códigos gerados: " + codes.toString());

        // Passo 4: Escrever a árvore de Huffman no arquivo
        writeTree(outputFile, priorityQueue.getPrimeiro());

        // Passo 5: Escrever o número de bits significativos
        inputFile.seek(0);
        StringBuilder encodedData = new StringBuilder();
        while ((character = inputFile.read()) != -1) {
            char ch = (char) character;
            encodedData.append(codes.recupereUmItem(ch));
        }
        int significantBits = encodedData.length();
        outputFile.writeInt(significantBits);

        // Passo 6: Escrever os dados codificados no arquivo
        writeEncodedData(outputFile, encodedData.toString());

        inputFile.close();
        outputFile.close();
    }
    // Método para decodificar um arquivo
    public void decode(String inputFilePath, String outputFilePath) throws Exception {
        RandomAccessFile inputFile = new RandomAccessFile(inputFilePath, "r");
        RandomAccessFile outputFile = new RandomAccessFile(outputFilePath, "rw");

        // Passo 1: Reconstruir a árvore de Huffman a partir do arquivo
        Node root = readTree(inputFile);

        // Passo 2: Ler o número de bits significativos
        int significantBits = inputFile.readInt();

        // Passo 3: Ler os dados codificados
        StringBuilder encodedData = new StringBuilder();
        int character;
        while ((character = inputFile.read()) != -1) {
            String byteString = String.format("%8s", Integer.toBinaryString(character & 0xFF)).replace(' ', '0');
            encodedData.append(byteString);
        }

        // Truncar para o número de bits significativos
        encodedData.setLength(significantBits);

        // Passo 4: Decodificar os dados usando a árvore de Huffman
        Node currentNode = root;
        StringBuilder decodedData = new StringBuilder();
        for (int i = 0; i < encodedData.length(); i++) {
            currentNode = (encodedData.charAt(i) == '0') ? currentNode.left : currentNode.right;
            if (currentNode.isLeaf()) {
                decodedData.append(currentNode.character);
                currentNode = root;
            }
        }

        // Escrever os dados decodificados no arquivo de saída
        outputFile.writeBytes(decodedData.toString());

        inputFile.close();
        outputFile.close();
    }

    // Método para construir a árvore de Huffman
    private void buildHuffmanTree(HashMap<Character, Integer> frequencyMap) throws Exception {
        System.out.println("Iniciando construção da árvore de Huffman...");

        // Criar nós para cada caractere com base na frequência
        for (int i = 0; i < frequencyMap.getTamanho(); i++) {
            Character character = frequencyMap.recupereUmaChave(i);
            int frequency = frequencyMap.recupereUmItem(character);
            System.out.println("Criando nó para caractere '" + character + "' com frequência " + frequency);
            priorityQueue.guardeOrdenado(new Node(character, frequency));
        }

        // Combinar nós para formar a árvore de Huffman
        while (priorityQueue.getTamanho() > 1) {
            Node left = priorityQueue.getPrimeiro();
            priorityQueue.removaPrimeiro();
            Node right = priorityQueue.getPrimeiro();
            priorityQueue.removaPrimeiro();

            int combinedFrequency = left.getFrequency() + right.getFrequency();
            Node parent = new Node(combinedFrequency, left, right);

            System.out.println("Combinando nós: [" + left.character + ", " + left.getFrequency() + "] e [" +
                    right.character + ", " + right.getFrequency() + "] -> Frequência combinada: " + combinedFrequency);

            priorityQueue.guardeOrdenado(parent);
        }

        System.out.println("Construção da árvore de Huffman concluída.");
    }

    // Método para gerar códigos de Huffman
    private void generateCodes(Node node, String code) throws Exception {
        if (node.isLeaf()) {
            codes.guardeUmItem(node.character, code);
            return;
        }
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }
    // Método para escrever a árvore de Huffman no arquivo
    private void writeTree(RandomAccessFile file, Node node) throws Exception {
        if (node.isLeaf()) {
            file.writeBoolean(true); // Indica que é uma folha
            file.writeChar(node.character); // Escreve o caractere
            return;
        }
        file.writeBoolean(false); // Indica que é um nó intermediário
        writeTree(file, node.left);
        writeTree(file, node.right);
    }

    // Método para reconstruir a árvore de Huffman a partir do arquivo
    private Node readTree(RandomAccessFile file) throws Exception {
        if (file.readBoolean()) { // Se for uma folha
            return new Node(file.readChar(), 0); // Retorna o nó folha com o caractere (a frequência não é relevante
                                                 // aqui)
        } else {
            Node left = readTree(file); // Reconstrói a subárvore esquerda
            Node right = readTree(file); // Reconstrói a subárvore direita
            return new Node(0, left, right); // Cria um nó intermediário (a frequência não é usada na decodificação)
        }
    }

    // Método para escrever os dados codificados no arquivo
    private void writeEncodedData(RandomAccessFile file, String data) throws Exception {
        for (int i = 0; i < data.length(); i += 8) {
            String byteString = data.substring(i, Math.min(i + 8, data.length()));
            while (byteString.length() < 8) {
                byteString += "0";
            }
            file.writeByte((byte) Integer.parseInt(byteString, 2));
        }
    }
}
