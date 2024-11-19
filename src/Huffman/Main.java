package Huffman;
public class Main {
    public static void main(String[] args) {
        try {
            String inputFilePath = "/Users/u23333/GitHub/dataStructure-Java/src/Huffman/files/original.txt";
            String encodedFilePath = "/Users/u23333/GitHub/dataStructure-Java/src/Huffman/files/arquivo_codificado.bin";
            String decodedFilePath = "/Users/u23333/GitHub/dataStructure-Java/src/Huffman/files/arquivo_decodificado.txt";

            //  Codificar
            System.out.println("Codificando o arquivo...");
            // Huffman encoder = new Huffman();
            HuffmanStringBuilder compactador = new HuffmanStringBuilder();
            compactador.compactar(inputFilePath, encodedFilePath);
            System.out.println("Concluido");

            // Decodificar
            System.out.println("Decodificando o arquivo...");
            // Huffman decoder = new Huffman();
            HuffmanStringBuilder descompactador = new HuffmanStringBuilder();
            descompactador.descompactar(encodedFilePath, decodedFilePath);
            System.out.println("Concluido");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
