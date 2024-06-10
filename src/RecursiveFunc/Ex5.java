package RecursiveFunc;

import java.lang.reflect.Executable;

public class Ex5 {
    public static int multiplicacaoRecursivo(int num1, int num2) {
        if (num2 == 0)
            return 0;

        if (Ex1.ehNegativo(num2)) {
            if( Ex1.ehNegativo(num1)){
                return multiplicacaoRecursivo(Ex4.moduloRecursivo(num1), Ex4.moduloRecursivo(num2));
            }
            return Ex3.subtracaoRecursiva(0,
                    multiplicacaoRecursivo(Ex4.moduloRecursivo(num1), Ex4.moduloRecursivo(num2)));
        }
      
        num2--;
        return Ex2.somaRecursiva(num1, multiplicacaoRecursivo(num1, num2));
    }

    public static void main(String[] args) {
        System.out.println(multiplicacaoRecursivo(-4,-3));
    }
    
}
