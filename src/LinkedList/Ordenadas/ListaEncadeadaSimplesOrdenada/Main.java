package LinkedList.Ordenadas.ListaEncadeadaSimplesOrdenada;



public class Main {
    public static void main(String[] args) throws Exception {
        try {
            OrderedSimpleLinkedList<Integer> lista = new OrderedSimpleLinkedList<Integer>();
        
            lista.add(1);
            lista.add(3);
            lista.add(2);

            System.out.println("\nLista inicial: " + lista);
            
            lista.removeFirst();
            System.out.println("Lista após remover o primeiro elemento: " + lista);

            lista.removeLast();
            System.out.println("Lista após remover o último elemento: " + lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
