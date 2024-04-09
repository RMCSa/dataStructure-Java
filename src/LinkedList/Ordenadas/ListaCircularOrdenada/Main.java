package LinkedList.Ordenadas.ListaCircularOrdenada;
public class Main {
    public static void main(String[] args) {
        try {
            ListaCircularOrdenada<Integer> lista = new ListaCircularOrdenada<Integer>();

            lista.guarde(1);
            lista.guarde(2);
            lista.guarde(3);
            
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
