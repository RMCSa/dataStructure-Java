package Huffman;
import java.io.RandomAccessFile;

public class TesteHuffman {
    public static void main(String[] args) {
        try {
            // Caminhos dos arquivos
            String inputFilePath = "C:\\Users\\Rafael\\Documents\\GitHub\\dataStructure-Java\\resources\\20240325_180320.jpg";
            String encodedFilePath = "C:\\Users\\Rafael\\Documents\\GitHub\\dataStructure-Java\\src\\Huffman\\files\\arquivo_codificado.bin";
            String decodedFilePath = "C:\\Users\\Rafael\\Documents\\GitHub\\dataStructure-Java\\src\\Huffman\\files\\arquivo_decodificado.jpg";

            // Etapa 2: Codificar o arquivo usando uma instância de Huffman
            System.out.println("[LOG] Codificando o arquivo...");
            Huffman encoder = new Huffman();
            encoder.encode(inputFilePath, encodedFilePath);
            System.out.println("[LOG] Arquivo codificado salvo em: " + encodedFilePath);

            // Etapa 3: Decodificar o arquivo usando outra instância de Huffman
            System.out.println("[LOG] Decodificando o arquivo...");
            Huffman decoder = new Huffman();
            decoder.decode(encodedFilePath, decodedFilePath);
            System.out.println("[LOG] Arquivo decodificado salvo em: " + decodedFilePath);

            // Etapa 4: Verificar se o arquivo decodificado é idêntico ao original
            System.out.println("[LOG] Verificando a integridade dos dados...");
            if (compararArquivos(inputFilePath, decodedFilePath)) {
                System.out.println("[LOG] SUCESSO: O arquivo decodificado é idêntico ao original!");
            } else {
                System.err.println("[LOG] ERRO: O arquivo decodificado não corresponde ao original.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para comparar dois arquivos byte a byte
    private static boolean compararArquivos(String filePath1, String filePath2) throws Exception {
        RandomAccessFile file1 = new RandomAccessFile(filePath1, "r");
        RandomAccessFile file2 = new RandomAccessFile(filePath2, "r");

        boolean iguais = true;
        int byte1, byte2;
        while ((byte1 = file1.read()) != -1 && (byte2 = file2.read()) != -1) {
            if (byte1 != byte2) {
                iguais = false;
                break;
            }
        }

        // Verificar se ambos os arquivos chegaram ao fim
        if (file1.read() != -1 || file2.read() != -1) {
            iguais = false;
        }

        file1.close();
        file2.close();
        return iguais;
    }
}
