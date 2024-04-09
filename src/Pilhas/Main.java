package Pilhas;


public class Main {
    public static void main(String[] args) {
        Pilha<Integer> pilha = new Pilha<>(5);

        try {
            pilha.guardeUmItem(1);
            pilha.guardeUmItem(2);
            pilha.guardeUmItem(3);
            pilha.guardeUmItem(4);
            pilha.guardeUmItem(5);

            System.out.println("Tamanho da pilha: " + pilha.tamanho());

            while (!pilha.isVazia()) {
                System.out.println("Desempilhando: " + pilha.recupereUmItem());
                pilha.removaUmItem();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
