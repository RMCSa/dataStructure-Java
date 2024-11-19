package Huffman;
public class Main {
    public static void main(String[] args) {
        try {
            // Caminhos dos arquivos
            String inputFilePath = "C:\\Users\\Rafael\\Documents\\GitHub\\dataStructure-Java\\src\\Huffman\\files\\original.txt";
            String encodedFilePath = "C:\\Users\\Rafael\\Documents\\GitHub\\dataStructure-Java\\src\\Huffman\\files\\arquivo_codificado.bin";
            String decodedFilePath = "C:\\Users\\Rafael\\Documents\\GitHub\\dataStructure-Java\\src\\Huffman\\files\\arquivo_decodificado.txt";

            // Etapa 2: Codificar o arquivo usando uma instância de Huffman
            System.out.println("Codificando o arquivo...");
            Huffman encoder = new Huffman();
            encoder.compactar(inputFilePath, encodedFilePath);
            System.out.println("Concluido");

            // Decodificar o arquivo 
            System.out.println("Decodificando o arquivo...");
            Huffman decoder = new Huffman();
            decoder.descompactar(encodedFilePath, decodedFilePath);
            System.out.println("Concluido");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
