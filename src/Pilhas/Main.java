package Pilhas;


public class Main {
    public static void main(String[] args) throws Exception {
        Pilha<Integer> pilha = new Pilha<>();

        pilha.guardeUmItem(1);
        pilha.guardeUmItem(2);
        pilha.guardeUmItem(3);
        pilha.guardeUmItem(4);
        pilha.guardeUmItem(5);

        System.out.println("Pilha: " + pilha);

        while (!pilha.isVazia()) {
            System.out.println("Desempilhando: " + pilha.recupereUmItem());
            pilha.removaUmItem();
        }
      
    }
}
