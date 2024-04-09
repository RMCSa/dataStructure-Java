package LinkedList.Desordenadas.ListaEncadeadaSimplesDesordenada;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            ListaEncadeadaSimplesDesordenada<Integer> lista = new ListaEncadeadaSimplesDesordenada<Integer>();
        
            lista.guardeNoFinal(1);
            lista.guardeNoFinal(3);
            lista.guardeNoFinal(2);

            System.out.println("Lista inicial: " + lista);

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
