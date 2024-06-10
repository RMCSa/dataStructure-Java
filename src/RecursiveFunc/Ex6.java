package RecursiveFunc;

public class Ex6 {
    public static int potenciacaoRecursivo(int num1, int num2) {
        if (num2 == 0)
            return 1;

        if(Ex1.ehNegativo(num2)){
            
        }

        num2--;
        return Ex5.multiplicacaoRecursivo(num1, potenciacaoRecursivo(num1, num2));
    }

    public static void main(String[] args) {
        System.out.println(potenciacaoRecursivo(-2, 3));
    }

}
