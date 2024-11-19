package Huffman;
public class Main {
    public static void main(String[] args) {
        try {
            String inputFilePath = "/Users/u23333/GitHub/dataStructure-Java/src/Huffman/files/original.txt";
            String encodedFilePath = "/Users/u23333/GitHub/dataStructure-Java/src/Huffman/files/arquivo_codificado.bin";
            String decodedFilePath = "/Users/u23333/GitHub/dataStructure-Java/src/Huffman/files/arquivo_decodificado.txt";

            System.out.println("Codificando o arquivo...");
            // Huffman encoder = new Huffman();
            HuffmanStringBuilder compactador = new HuffmanStringBuilder();
            compactador.compactar(inputFilePath, encodedFilePath);
            System.out.println("Concluído");


            System.out.println("Decodificando o arquivo...");
            // Huffman decoder = new Huffman();
            HuffmanStringBuilder descompactador = new HuffmanStringBuilder();
            descompactador.descompactar(encodedFilePath, decodedFilePath);
            System.out.println("Concluído");

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
