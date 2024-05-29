package RecursiveFunc;

public class Ex2 {

    public static int somaRecursiva(int num1, int num2) {
        if (num2 == 0)
            return num1;
        if (num1 == 0)
            return num2;

        if (Ex1.ehNegativo(num1)) {
            num1++;
            num2--;
            return somaRecursiva(num1, num2);
        }

        num1--;
        num2++;
        return somaRecursiva(num1, num2);
    }

    public static void main(String[] args) {
        System.out.println(somaRecursiva(-4, -4));
    }

}
