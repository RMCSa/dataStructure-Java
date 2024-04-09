package LinkedList.Ordenadas.ListaDuplamenteLigadaOrdenada;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            ListaDuplamenteLigadaOrdenada<Integer> lista = new ListaDuplamenteLigadaOrdenada<Integer>();

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
