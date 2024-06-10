package RecursiveFunc;

public class Ex7 {
    public static int divisaoRecursiva(int dividendo, int divisor){
        if (divisor == 0) throw new Exception("Não ");
        if (divisor == 1) return dividendo;
        
    }
    
    public static void main(String[] args) {
        System.out.println(divisaoRecursiva(10, 2));
    }
}


