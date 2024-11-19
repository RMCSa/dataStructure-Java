package Huffman;
public class Main {
    public static void main(String[] args) {
        try {
            String inputFilePath = "/Users/u23333/GitHub/dataStructure-Java/src/Huffman/files/20240325_180320.jpg";
            String caminhoArquivoCompactado = "/Users/u23333/GitHub/dataStructure-Java/src/Huffman/files/arquivo_codificado.huff";
            String caminhoArquivoDescompactado = "/Users/u23333/GitHub/dataStructure-Java/src/Huffman/files/arquivo_decodificado.jpg";

            System.out.println("Codificando o arquivo...");
            // Huffman encoder = new Huffman();
            Huffman compactador = new Huffman();
            compactador.compactar(inputFilePath, caminhoArquivoCompactado);
            System.out.println("Concluído");


            System.out.println("Decodificando o arquivo...");
            // Huffman decoder = new Huffman();
            Huffman descompactador = new Huffman();
            descompactador.descompactar(caminhoArquivoCompactado, caminhoArquivoDescompactado);
            System.out.println("Concluído");

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
