package LinkedList.Ordenadas.ListaDuplamenteLigadaCircularOrdenada;

public class Main {
    public static void main(String[] args) {
        try {
            ListaDuplamenteLigadaCircularOrdenada<Integer> lista = new ListaDuplamenteLigadaCircularOrdenada<Integer>();

            lista.guarde(1);
            lista.guarde(3);
            lista.guarde(2);

            System.out.println("\nLista inicial: " + lista);
            
            lista.removaPrimeiro();
            System.out.println("Lista após remover o primeiro elemento: " + lista);

            lista.removaUltimo();
            System.out.println("Lista após remover o último elemento: " + lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
