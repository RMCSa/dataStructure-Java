package Filas.ComListaEncadeada;

public class Main {
    public static void main(String[] args) throws Exception{
        Fila<Integer> fila = new Fila<>();

        
        fila.guardeUmItem(1);
        fila.guardeUmItem(2);
        fila.guardeUmItem(3);
        fila.guardeUmItem(4);
        fila.guardeUmItem(5);
            
        System.out.println("Fila: " + fila);

        while (!fila.isVazia()) {
            System.out.println("Desenfileirando: " + fila.recupereUmItem());
            fila.removaUmItem();
        }
        
    }
}
