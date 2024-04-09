package LinkedList.Desordenadas.ListaDuplamenteLigadaCircularDesordenada;



public class Main {
    public static void main(String[] args) {
        try {
            ListaDuplamenteLigadaCircularDesordenada<Integer> lista = new ListaDuplamenteLigadaCircularDesordenada<Integer>();

            lista.guardeNoFinal(1);
            lista.guardeNoFinal(3);
            lista.guardeNoFinal(2);

            System.out.println("\nLista inicial: " + lista);

            lista.guardeNoInicio(0);
            System.out.println("Lista após adicionar 0 no início: " + lista);

            lista.removaPrimeiro();
            System.out.println("Lista após remover o primeiro elemento: " + lista);

            lista.removaUltimo();
            System.out.println("Lista após remover o último elemento: " + lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
