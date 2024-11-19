package Huffman;
public class Main {
    public static void main(String[] args) {
        try {
            String inputFilePath = "C:\\Users\\Rafael\\Documents\\GitHub\\dataStructure-Java\\src\\Huffman\\files\\original.txt";
            String encodedFilePath = "C:\\Users\\Rafael\\Documents\\GitHub\\dataStructure-Java\\src\\Huffman\\files\\arquivo_codificado.bin";
            String decodedFilePath = "C:\\Users\\Rafael\\Documents\\GitHub\\dataStructure-Java\\src\\Huffman\\files\\arquivo_decodificado.txt";

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
