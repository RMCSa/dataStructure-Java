package Filas;

public class Main {
    public static void main(String[] args) {
        Fila<Integer> fila = new Fila<>(5);

        try {
            fila.guardeUmItem(1);
            fila.guardeUmItem(2);
            fila.guardeUmItem(3);
            fila.guardeUmItem(4);
            fila.guardeUmItem(5);

            System.out.println("Tamanho da fila: " + fila.tamanho());

            while (!fila.isVazia()) {
                System.out.println("Desenfileirando: " + fila.recupereUmItem());
                fila.removaUmItem();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
