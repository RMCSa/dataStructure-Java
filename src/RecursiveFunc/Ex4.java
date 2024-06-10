package RecursiveFunc;

public class Ex4 {
    public static int moduloRecursivo(int num) {
        if (Ex1.ehNegativo(num)) {
            return Ex3.subtracaoRecursiva(0, num);
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(moduloRecursivo(-7));
    }
}
